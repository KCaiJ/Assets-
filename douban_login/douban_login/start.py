__author__ = 'root'
from scrapy import  cmdline
import  requests
import tesserocr
from  PIL import Image

# def image_thresholding_method(image):
#     threshold =150
#     table = []
#     for i in range(256):
#         if i < threshold:
#             table.append(0)
#         else:
#             table.append(1)
#     image = image.point(table, '1')
#     return image





# def depoint(img):
#  """传入二值化后的图片进行降噪"""
#  pixdata = img.load()
#  w,h = img.size
#  for y in range(1,h-1):
#   for x in range(1,w-1):
#    count = 0
#    if pixdata[x,y-1] > 245:#上
#     count = count + 1
#    if pixdata[x,y+1] > 245:#下
#     count = count + 1
#    if pixdata[x-1,y] > 245:#左
#     count = count + 1
#    if pixdata[x+1,y] > 245:#右
#     count = count + 1
#    if pixdata[x-1,y-1] > 245:#左上
#     count = count + 1
#    if pixdata[x-1,y+1] > 245:#左下
#     count = count + 1
#    if pixdata[x+1,y-1] > 245:#右上
#     count = count + 1
#    if pixdata[x+1,y+1] > 245:#右下
#     count = count + 1
#    if count > 4:
#     pixdata[x,y] = 255
#  return img
if __name__ == '__main__':
    # image =  image = Image.open('captcha.png')
    # img1 =  image.convert('L')
    # img2 = image_thresholding_method(img1)
    # img2.show()
    # result = tesserocr.image_to_text(img2)
    # print(result)
    cmdline.execute("scrapy crawl douban".split())
