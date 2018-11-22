# -*- coding: utf-8 -*-
import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
from  jianshu_sprider.items import JianshuSpriderItem

class JsSpider(CrawlSpider):
    name = 'js'
    allowed_domains = ['jianshu.com']
    start_urls = ['https://www.jianshu.com/']

    rules = (
        Rule(LinkExtractor(allow=r'.*/p/[0-9a-z]{12}.*'), callback='parse_item',
             follow=True),
    )

    def parse_item(self, response):
        title=response.xpath("//h1[@class='title']/text()").get()
        avatar=response.xpath("//div[@class='author']/a[@class='avatar']/img/@src").get()
        author=response.xpath("//div[@class='author']//span[@class='name']//text()").get()
        pub_time=response.xpath("//span[@class='publish-time']/text()").get()
        url=response.url
        url1=url.split("?")[0]
        article_id=url1.split("/")[-1]
        content=response.xpath("//div[@class='show-content']").get()

        word_count=response.xpath("//span[@class='wordage']/text()").get()
        read_count=response.xpath("//span[@class='views-count']/text()").get()
        like_count=response.xpath("//span[@class='likes-count']/text()").get()
        comments_count=response.xpath("//span[@class='comments-count']/text()").get()
        rewards_count=response.xpath("//span[@class='rewards-count ']/text()").get()
        subjects=".".join(response.xpath("//div[@class='include-collection']/a/div/text()").getall())
        item=JianshuSpriderItem(
            title=title,
            avatar=avatar,
            author=author,
            pub_time=pub_time,
            origin_url=response.url,
            article_id=article_id,
            content=content,
            word_count=word_count,
            read_count=read_count,
            like_count=like_count,
            comments_count=comments_count,
            rewards_count=rewards_count,
            subjects=subjects,
        )
        yield item

        #i['domain_id'] = response.xpath('//input[@id="sid"]/@value').extract()
        #i['name'] = response.xpath('//div[@id="name"]').extract()
        #i['description'] = response.xpath('//div[@id="description"]').extract()
