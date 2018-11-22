# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import  json
from scrapy.exporters import JsonItemExporter,JsonLinesItemExporter
class QsbkPipeline(object):
    def __init__(self):
       # self.fp=open("duanzi.json","w",encoding="utf-8")
        self.fp=open("duanzi.json","wb")
        self.exporter=JsonLinesItemExporter(self.fp,ensure_ascii=False,encoding="utf-8")
        #self.exporter.start_exporting()

    def process_item(self, item, spider):
       # item_json=json.dumps(dict(item),ensure_ascii=False)
       # self.fp.write(item_json+"\n")
        self.exporter.export_item(item)
        return item

    def close_spider(self,spider):
      #  self.exporter.finish_exporting()
        self.fp.close()
