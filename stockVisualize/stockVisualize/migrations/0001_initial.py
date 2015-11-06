# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='DailyPrice',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('stock', models.CharField(max_length=200)),
                ('openPrice', models.FloatField(max_length=200)),
                ('closePrice', models.FloatField(max_length=200)),
                ('high', models.FloatField(max_length=200)),
                ('low', models.FloatField(max_length=200)),
                ('adjustedClose', models.FloatField(max_length=200)),
                ('volume', models.IntegerField(max_length=500)),
                ('date', models.CharField(max_length=200)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
    ]
