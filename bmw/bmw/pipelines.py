# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import os
from scrapy.pipelines.images import ImagesPipeline
from bmw import  settings
class BmwPipeline(object):
    def __init__(self):
        self.path=os.path.join(os.path.dirname(os.path.dirname(__file__)),'images')
        if not os.path.exists(self.path):
            os.mkdir(self.path)


    def close_spider(self):
        pass

    def process_item(self, item, spider):
        title=item['title']
        urls=item['urls']
        title_path=os.path.join(self.path,title)
        if not os.path.exists(title_path):
            os.mkdir(title_path)
        for url in urls:
            print(url)
            img_name=url.split("_")[-1]
            request.urlretrieve(url,os.path.join(title_path,img_name))

        return item


class BMWImagesPipeline(ImagesPipeline):
    def get_media_requests(self, item, info):
        request_objs=super(BMWImagesPipeline,self).get_media_requests(item,info)
        for request_obj in request_objs:
            request_obj.item=item
        return request_objs

    def file_path(self, request, response=None, info=None):
        path=super(BMWImagesPipeline,self).file_path(request,response,info)
        title=request.item.get("title")
        image_store=settings.IMAGES_STORE
        title_path=os.path.join(image_store,title)
        if not os.path.exists(title_path):
            os.mkdir(title_path)
        image_name=path.replace("full/","")
        image_path=os.path.join(title_path,image_name)
        return  image_path