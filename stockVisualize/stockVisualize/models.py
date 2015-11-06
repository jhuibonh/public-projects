from django.db import models
from datetime import datetime    
from adaptor.model import CsvModel
from adaptor import fields
# Create your models here.

class DailyPrice(models.Model):
    stock = models.CharField(max_length=200)
    openPrice =  models.FloatField(max_length=200)
    closePrice = models.FloatField(max_length=200)
    high =  models.FloatField(max_length=200)
    low = models.FloatField(max_length=200)
    adjustedClose = models.FloatField(max_length=200)
    volume = models.IntegerField(max_length=500)
    date = models.DateTimeField(default=datetime.now)

