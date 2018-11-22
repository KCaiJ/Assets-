# -*- coding: utf-8 -*-
import scrapy
from  qsbk.items import QsbkItem

class QsbkSpriderSpider(scrapy.Spider):
    name = 'qsbk_sprider'
    allowed_domains = ['qiushibaike.com']
    start_urls = ['https://www.qiushibaike.com/text/page/1/']

    def parse(self, response):
       # items = []
        outerbox = response.xpath("//div[@id='content-left']/div")
        for box in outerbox:
             author = box.xpath(".//div[contains(@class,'author')]//h2/text()").extract_first().strip()
             content = box.xpath(".//div[@class='content']/span/text()").extract_first().strip()
             item = QsbkItem(author=author,content=content)
            # item["author"] = author
            # item["content"] = content
             yield  item
            # items.append(item)
        next_url=response.xpath("//ul[@class='pagination']/li[last()]/a/@href").get()
        if not next_url:
            return
        else :
            yield  scrapy.Request('https://www.qiushibaike.com'+next_url,callback=self.parse)
        #return items