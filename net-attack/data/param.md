Duration                  ---> 连接的持续时间长度
Protocol Type             ---> 连接中使用的协议
Service                   ---> 使用的目的网络服务
Flag                      ---> 连接状态-正常或者错误
Src Bytes                 ---> 在单次连接中从源到目的传输的数据字节数
Dst Bytes                 ---> 在单词连接中从目的到源传输的数据字节数
Land                      ---> 如果源IP地址和目的地址以及端口号相等，则该变量为1，否则为0   ===0/1
Wrong Fragment            ---> 在此连接中错误的片段总数   ===0/1/3
Urgent                    ---> 此连接中的紧急数据包数。紧急数据包是紧急位被激活的数据包   ===0-3
Hot                       ---> "热点"指标在内容上的数量，如：进入系统目录，创建程序并执行程序  ===0-101
Num Failed Logins         ---> 登陆失败次数    ===0-4
Logged In                 ---> 登录状态：1为成功，否则为0    ==0/1
Num Compromised           ---> "中毒/妥协"条件的条件
Root Shell                ---> 是否获得root权限   ===0/1
Su Attempted              ---> 如果"su root"命令尝试或使用,则返回1，否则为0   ===0/1
Num Root                  ---> "根"的访问次数或作为根在连接中执行的操作次数
Num File Creations        ---> 在连接中创建文件的操作次数   0-100
Num Shells                ---> shell提示符的数量      0-2
Num Access Files          ---> 访问控制文件的操作次数    0-2
Num Outbound Cmds         ---> ftp会话输出的命令个数    0-9
Is Hot Logins             ---> 如果登录属于"hot"列表，即root,admin为1，否则为0    ===0/1
Is Guest Login            ---> 如果登录是顾客为1，否则为0   ===0/1
Count                     ---> 过去两秒钟内与当前连接相同的目标主机的连接数
Srv Count                 ---> 过去两秒内连接到与当前连接相同服务(端口号)的连接数
Serror Rate               ---> 在Count  的连接中，激活flag s0、s1、s2或s3的连接的百分比
Srv Serror Rate           ---> 在Srv Count  的连接中，激活flag s0、s1、s2或s3的连接的百分比
Rerror Rate               ---> 在Count  的连接中，激活了flag REJ的连接的百分比
Srv Rerror Rate           ---> 在Srv Count  的连接中，激活了flag REJ的连接的百分比
Same Srv Rate             ---> 在Count  的连接中，指向相同服务的连接的百分比
Diff Srv Rate             ---> 在Count  的连接中，指向不同服务的连接的百分比
Srv Diff Host Rate        ---> 在Srv Count中聚合的连接中，到不同目标机器的连接所占的百分比
Dst Host Count            ---> 具有相同目的主机IP地址的连接数
Dst Host Srv Count        ---> 具有相同端口号的连接数
Dst Host Same Srv Rate    ---> 在Dst Host Count中聚合的连接中，指向相同服务的连接的百分比
Dst Host Diff Srv Rate    ---> 在Dst Host Count中聚合的连接中，指向不同服务的连接的百分比
Dst Host Same Src Port Rate ---> 在Dst Host Srv Count中聚合的连接中，指向同一源端口的连接的百分比
Dst Host Srv Diff Host Rate ---> 在Dst Host Srv Count中聚合的连接中，指向不同源端口的连接的百分比
Dst Host Serror Rate      ---> 在Dst Host Count  的连接中，激活了flag s0、s1、s2或s3的连接的百分比
Dst Host Srv Serror Rate  ---> 在Dst Host Srv Count  的连接中，激活了flag s0、s1、s2或s3的连接的百分比
Dst Host Rerror Rate      ---> 在Dst Host Count  的连接中，激活了flag REJ的连接的百分比
Dst Host Srv Rerror Rate  ---> 在Dst Host Srv Count  的连接中，激活了flag REJ的连接的百分比
Class                     ---> 输入流量的分类
Difficulty Level          ---> 难度等级    ===0-21