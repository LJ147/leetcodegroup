mvn clean package
scp  target/leetcode-group-1.0-SNAPSHOT.jar  root@123.207.26.32:/root/jar/
ssh root@123.207.26.32 "bash /root/jar/run_group.sh"