#!/bin/sh

JAR="HelloScott-0.0.1-SNAPSHOT.jar"
#OPTS="-Xmx48m -Xss256k"
PID="application.pid"
JAVA=java

case $1 in
        start)
                echo "Starting..."
                if [ -f $PID ] && [ -n $PID ]; then
                      if ps -p `cat $PID` > /dev/null; then
                              echo "Already running"
                              exit 0;
                      fi
                fi
                
                nohup $JAVA -jar $OPTS $JAR /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID
                echo "Started"
        ;;
        stop)
                if [ -f $PID ]; then
                        PIDN=$(cat $PID);
                        echo "Stopping"
                        kill $PIDN;
                        echo "Stopped"
                        rm $PID
                else
                        echo "Not running"
                fi
        ;;
        status)
                if [ -f $PID ] && [ -n $PID ]; then
                        if ps -p `cat $PID` > /dev/null; then
                                echo "Running"
                        else
                                echo "Not running"
                        fi
                else
                        echo "Not running"
                fi
        ;;
esac
