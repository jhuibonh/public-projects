from os import path
from stockVisualize.models import *

#paths
ibm_path = path.relpath('data/ibm.csv')
spy_path = path.relpath('data/spy.csv')
tsla_path = path.relpath('data/tsla.csv')


#load csv data into sql using django's model
def load_csv_data(stock_name,file_path):
    with open(file_path) as f: 
        #skip first line since 
        for i in xrange(1):
            f.next()
        for line in f:
            line = line.split(',')
            new_daily_price = DailyPrice()
            new_daily_price.stock = stock_name
            new_daily_price.date = line[0]
            new_daily_price.openPrice = line[1]
            new_daily_price.high = line[2]
            new_daily_price.low = line[3]
            new_daily_price.closePrice = line[4]
            new_daily_price.volume = line[5]
            new_daily_price.adjustedClose = line[6]
            new_daily_price.save()

load_csv_data('ibm',ibm_path)
load_csv_data('spy',spy_path)
load_csv_data('tsla',tsla_path)




