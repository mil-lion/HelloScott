#!/bin/sh

#JAVA_HOME=
JAR="HelloScott-0.0.1-SNAPSHOT.jar"
#OPTS="-Xmx48m -Xss256k"
PID="application.pid"
JAVA=$JAVA_HOME/bin/java

case $1 in
        start)
                echo "Starting..."
                if [ -f $PID ] && [ -n $PID ]; then
                      if ps -p `cat $PID` > /dev/null; then
                              echo "Already running"
                              exit 0;
                      fi
                fi
                
                nohup $JAVA $OPTS -jar $JAR /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID
                echo "Started"
        ;;
        stop)
                if [ -f $PID ]; then
                        PIDN=$(cat $PID);
                        echo "Stopping..."
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
                                exit 0
                        else
                                echo "Not running"
                                exit 1
                        fi
                else
                        echo "Not running"
                        exit 1
                fi
        ;;
        *)
                echo "Usage: start-stop.sh {start|stop|status}"
                exit 3
        ;;
esac
