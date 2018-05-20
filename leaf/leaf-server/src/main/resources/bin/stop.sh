#!/bin/bash
echo "Stopping SpringBoot Application"
pid=`ps -ef | grep dubbo-provider.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
   kill $pid
fi