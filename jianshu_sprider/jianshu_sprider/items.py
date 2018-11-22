# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class JianshuSpriderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    title=scrapy.Field()
    content=scrapy.Field()
    article_id=scrapy.Field()
    origin_url=scrapy.Field()
    author=scrapy.Field()
    avatar=scrapy.Field()
    pub_time=scrapy.Field()
    read_count=scrapy.Field()
    like_count=scrapy.Field()
    word_count=scrapy.Field()
    comments_count=scrapy.Field()
    rewards_count=scrapy.Field()
    subjects=scrapy.Field()


