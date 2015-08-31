from django.conf.urls import patterns, include, url
from django.contrib import admin

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'stockVisualize.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^$', 'stockVisualize.views.home'),
    url(r'^api/getprice', 'stockVisualize.views.stock_prices', name="get_stock_prices")

)
