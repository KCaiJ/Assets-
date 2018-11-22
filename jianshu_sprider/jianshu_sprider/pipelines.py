# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import pymysql
from  pymysql import  cursors
from  twisted.enterprise import adbapi
"""  非异步存储数据库
class JianshuSpriderPipeline(object):
    def __init__(self):
        db={
            "host":'127.0.0.1',
            "port":3306,
            "user":'root',
            "password":'123456',
            'database':"jianshu",
            'charset':'utf8'
        }
        self.conn=pymysql.connect(**db)
        self.cursor=self.conn.cursor()
        self._sql=None

    @property
    def sql(self):
        if not self._sql :
            self._sql="
            insert into article(title,content,author,avatar,pub_time,article_id,url)
            values(%s,%s,%s,%s,%s,%s,%s)
            "
        return self._sql

    def process_item(self, item, spider):
        self.conn.ping(reconnect=True)
        self.cursor.execute(self.sql,(item['title'],item['content'],item['author'],item['avatar'],item['pub_time'],item['article_id'],item['origin_url']))
        self.conn.commit()
        return item
"""
class JianshuSpriderPipeline(object):
    def __init__(self):
        db={
            "host":'127.0.0.1',
            "port":3306,
            "user":'root',
            "password":'123456',
            'database':"jianshu",
            'charset':'utf8',
            'cursorclass':cursors.DictCursor
        }
        self.dbpool=adbapi.ConnectionPool("pymysql",cp_reconnect=True,**db)

        self._sql=None

    @property
    def sql(self):
        if not self._sql :
            self._sql="""
                insert into article(title,content,author,avatar,pub_time,article_id,url,read_count,like_count,rewards_count,comments_count,word_count,subjects)
                values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
            """
        return self._sql


    def process_item(self, item, spider):
        defer=self.dbpool.runInteraction(self.insert_item,item)
        defer.addErrback(self.handle_error,item,spider)


    def insert_item(self,cursor,item):
        cursor.execute(self.sql,(item['title'],item['content'],item['author'],item['avatar'],item['pub_time'],item['article_id'],item['origin_url'],
        item['read_count'],item['like_count'],item['rewards_count'], item['comments_count'],
        item['word_count'],item['subjects'],))


    def handle_error(self,error,item,spider):
        print("="*20)
        print(error)
        print("="*20)