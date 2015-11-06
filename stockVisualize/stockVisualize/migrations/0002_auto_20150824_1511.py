# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime


class Migration(migrations.Migration):

    dependencies = [
        ('stockVisualize', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='dailyprice',
            name='date',
            field=models.DateTimeField(default=datetime.datetime.now),
            preserve_default=True,
        ),
    ]
