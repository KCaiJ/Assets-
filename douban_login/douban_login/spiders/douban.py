# -*- coding: utf-8 -*-
import scrapy
import tesserocr
from  urllib import  request
import pytesseract
from PIL import Image
import  time
class DoubanSpider(scrapy.Spider):
    name = 'douban'
    allowed_domains = ['douban.com']
    start_urls = ['https://accounts.douban.com/login']
    login_url='https://accounts.douban.com/login'
    def parse(self, response):
        formdata={
            'source': 'None',
            'redir': 'https://www.douban.com/',
            'form_email': 'qaz1780526188@163.com',
            'form_password':'qaz1780526188',
          #  'captcha-solution':' medical',
         #   'captcha-id': 'nMSDblpQohqg9paSu5Xg8Qq3:en',
            'login': '登录'
        }
        captcha_url=response.css("img#captcha_image::attr(src)").get()
        if captcha_url:
            formdata['captcha-solution']=self.regonize_captcha(captcha_url)
            formdata['captcha-id']=response.xpath("//input[@name='captcha-id']/@value").get()
        yield  scrapy.FormRequest(url=self.login_url,formdata=formdata,callback=self.parse_after_login)


    def parse_after_login(self,response):
        if response.url=='https://www.douban.com/':
            print("登录成功")
        else :
            print("登录失败")
       # with open("douban.html","w",encoding="utf-8") as fp:
       #     fp.write(response.text)

    def regonize_captcha(self,img_url):
        request.urlretrieve(img_url,'captcha.png')
        image = Image.open("captcha.png")
        image.show()
        text=input("验证码:")
        return text


#  source: None
# redir: https://www.douban.com/
# form_email: qaz1780526188@163.com
# form_password: qaz1780526188
# captcha-solution: medical
# captcha-id: nMSDblpQohqg9paSu5Xg8Qq3:en
# login: 登录  https://accounts.douban.com/login