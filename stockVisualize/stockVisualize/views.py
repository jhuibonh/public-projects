from django.shortcuts import render, redirect
from django.core.exceptions import ObjectDoesNotExist
from django.db import connections
from django.db.models import Count
from django.http import JsonResponse
from django.core import serializers
import json as simplejson


#import DailyPrice model
from models import *


# Create your views here.

def home(request):
    return render(request,'index.html')

def stock_prices(request):
    data_as_json = serializers.serialize('json', DailyPrice.objects.all())
    data_list = simplejson.loads(data_as_json)
    # keep track of the current company we're seeing in the data
    current_company = None
    # create an array containing daily prices for each specific comapany 
    # and ignore extraneous information 
    data_dict = {}
    company_prices = []
    for item in data_list:
        for field in item:
            if field == 'fields':
                company_data = item[field]
                company_name = item[field]['stock']
                # if current company is none assign the variable a value
                # and create an array that contains the daily prices for the current company
                if current_company == None:
                    company_prices.append(company_data)
                    current_company = company_name
                # if company name is different we use the stock name as the key 
                # and the company_price list as the value and add it to our dictionary
                # then we reset the company_prices list and add the data for our new company
                elif current_company != company_name:
                    data_dict[current_company] = company_prices
                    current_company = company_name
                    company_prices = []
                    company_prices.append(company_data)
                else:
                    company_prices.append(company_data)
    #add the last company to the dictionary
    data_dict[current_company] = company_prices
    return JsonResponse(data_dict)







