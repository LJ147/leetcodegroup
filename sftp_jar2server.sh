mvn clean package
scp  target/leetcode-group-1.0-SNAPSHOT.jar  root@lc.hellogod.cn:/root/old-jar/
ssh root@lc.hellogod.cn "bash /root/old-jar/run_group.sh"
