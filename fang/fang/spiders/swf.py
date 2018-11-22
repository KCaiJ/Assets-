# -*- coding: utf-8 -*-
import scrapy
import  re
from fang.items import NewHouseItem
from fang.items import ESFHouseItem
from scrapy_redis.spiders import RedisSpider

class SwfSpider(RedisSpider):
    name = 'swf'
    allowed_domains = ['fang.com']
   # start_urls = ['http://www.fang.com/SoufunFamily.htm']
    redis_key="fang:start_urls"
    def parse(self, response):
        trs=response.xpath("//div[@class='outCont']//tr")
        province=None
        for tr in trs:
            tds=tr.xpath(".//td[not(@class='font01')]")
            province_td=tds[0]
            province_text=province_td.xpath(".//text()").get()
            province_text=re.sub(r"\s","",province_text)
            if province_text:
                 province=province_text
            if province=="其它":
                continue

            city_td=tds[1]
            city_likes=city_td.xpath(".//a")
            for city_like in city_likes:
                city=city_like.xpath(".//text()").get()
                city_url=city_like.xpath(".//@href").get()
                #构建新房 ，二手房 ，租房的url
                url_module=city_url.split(".")
                if city=="北京":
                    esf_url="http://esf.fang.com/"
                    zu_url="http://zu.fang.com/"
                    new_url="http://newhouse.fang.com/house/s/"
                else:
                    esf_url=url_module[0]+".esf."+url_module[1]+"."+url_module[2]
                    zu_url=url_module[0]+".zu."+url_module[1]+"."+url_module[2]
                    new_url=url_module[0]+".newhouse."+url_module[1]+"."+url_module[2]+"house/s/"
                yield  scrapy.Request(url=new_url,callback=self.parse_newhouse,meta={"info":(province,city)})
                yield  scrapy.Request(url=esf_url,callback=self.parse_esf,meta={"info":(province,city)})
              #  yield  scrapy.Request(url=zu_url,callback=self.parse_zu,meta={"info":(province,city)})
    def parse_newhouse(self,response):
            province,city=response.meta.get("info")
            print(province+"   "+city)
            lis=response.xpath("//div[@class='nl_con clearfix']/ul/li")
            for li in lis:
                name=li.xpath(".//div[@class='nlcd_name']/a/text()").get()
                if(name == None):
                    continue
                name=name.strip()
                house_type=li.xpath(".//div[@class='house_type clearfix']//text()").getall()
                house_type="".join(list(map(lambda  x:re.sub(r"\s","",x),house_type)))
                area=None
                if(house_type.find("－")>=0):
                   area=house_type.split("－")[1]
                   house_type=house_type.split("－")[0].split("/")

                address=li.xpath(".//div[@class='address']/a/@title").get()
                district="".join(li.xpath(".//div[@class='address']/a//text()").getall())
                district=re.search(r".*\[(.+)\].*",district)
                if(district!=None):
                    district=district.group(1)
                sale=li.xpath(".//div[@class='fangyuan pr']/span/text()").get()
                price="".join(li.xpath(".//div[@class='nhouse_price']//text()").getall()).strip()
                price=re.sub(r"\s|广告","",price)
                origin_url=li.xpath(".//div[@class='nlcd_name']/a/@href").get()
                item=NewHouseItem(name=name,rooms=house_type,price=price,address=address,
                                  district=district,sale=sale,origin_url=origin_url,area=area,
                                  province=province,city=city)
                yield  item
            next_url=response.xpath("//div[@class='page']//a[@class='next'][last()]/@href").get()
            print(next_url)
            if next_url:
                yield  scrapy.Request(url=response.urljoin(next_url),callback=self.parse_newhouse,meta={"info":(province,city)})

    def parse_esf(self,response):
            province,city=response.meta.get("info")
            print(province+"   "+city)
            dls=response.xpath("//div[@class='shop_list shop_list_4']/dl")
            for dl in dls:
                name=dl.xpath(".//p[@class='add_shop']/a/@title").get()
                if name==None:
                    continue
                address=dl.xpath(".//p[@class='add_shop']/span/text()").get()
                shops="".join(dl.xpath(".//p[@class='tel_shop']//text()").getall())
                shops=re.sub(r"\s","",shops)
                shops=shops.split("|")
                toward=None
                rooms=shops[0]
                area=shops[1]
                floor=shops[2]
                if len(shops)>5:
                    toward=shops[3]
                    year=shops[4]
                else :
                    year=shops[3]

                url=response.urljoin(dl.xpath(".//h4[@class='clearfix']/a/@href").get())
                price="".join(dl.xpath(".//dd[@class='price_right']/span[@class='red']//text()").getall())
                unit=dl.xpath(".//dd[@class='price_right']/span[not(@class='red')]//text()").get().strip()
                item=ESFHouseItem(name=name,address=address,toward=toward,rooms=rooms,area=area,floor=floor,year=year,url=url,
                                  price=price,unit=unit,province=province,city=city)
                yield  item
            next_url=response.xpath("//div[@class='page_al']/p[last()-2]/a/@href").get()
            if next_url:
                yield  scrapy.Request(url=response.urljoin(next_url),callback=self.parse_esf,meta={"info":(province,city)})

    # def parse_zu(self,response):
    #         province,city=response.meta.get("info")
    #         print(province+"   "+city)