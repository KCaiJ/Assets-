# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class NewHouseItem(scrapy.Item):
    province = scrapy.Field() #省份
    city=scrapy.Field() #城市
    name=scrapy.Field()#所属小区
    price=scrapy.Field()#价格
    rooms=scrapy.Field()#几居，列表类型
    area=scrapy.Field()#面积
    address=scrapy.Field()#地址
    district=scrapy.Field()#行政区
    sale=scrapy.Field() #是否在售
    origin_url=scrapy.Field()#详情页面


class ESFHouseItem(scrapy.Item):
    province = scrapy.Field() #省份
    city=scrapy.Field() #城市
    name=scrapy.Field()#所属小区
    rooms=scrapy.Field()#几室几厅
    floor=scrapy.Field()#层数
    toward=scrapy.Field()#面向方位
    year=scrapy.Field()#年代
    address=scrapy.Field()#地址
    area=scrapy.Field()#面积
    price=scrapy.Field()#价格
    unit=scrapy.Field()#单价
    url=scrapy.Field()
