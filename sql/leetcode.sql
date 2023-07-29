-- 编写一个SQL查询来报告 Person 表中每个人的姓、名、城市和州。如果 personId 的地址不在 Address 表中，则报告为空  null 。
-- 1. https://leetcode.cn/problems/combine-two-tables/
select p.firstName, lastName, a.city, state
from `1_Person` p
         left join `1_Address` a on p.personId = a.personId;


-- 查询并返回 Employee 表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 null(Pandas 则返回 None) 。
-- 2. https://leetcode.cn/problems/second-highest-salary/
select (select distinct salary from `2_3_Employee` order by salary desc LIMIT 1,1) as SecondHighestSalary;


-- 查询 Employee 表中第 n 高的工资。如果没有第 n 个最高工资，查询结果应该为 null 。
-- 3. https://leetcode.cn/problems/nth-highest-salary/
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    set N = N - 1;
    RETURN (select distinct salary
            from `2_3_Employee`
            order by salary desc
            LIMIT N,1);
END;

# 查询并对分数进行排序。排名按以下规则计算:
# 分数应按从高到低排列。
# 如果两个分数相等，那么两个分数的排名应该相同。
# 在排名相同的分数后，排名数应该是下一个连续的整数。换句话说，排名之间不应该有空缺的数字。
# 按score降序返回结果表。
-- 4. https://leetcode.cn/problems/rank-scores/

select `4_Scores`.score, `rank`
from `4_Scores`,
     (select score, row_number() over (order by score desc) as `rank` from `4_Scores` group by score) as temp
where `4_Scores`.score = temp.score
order by score desc;

-- 编写一个 SQL 查询，查找所有至少连续出现三次的数字。
-- 5. https://leetcode.cn/problems/consecutive-numbers/

select distinct l1.num as ConsecutiveNums
from `5_Logs` as l1,
     `5_Logs` as l2,
     `5_Logs` as l3
where l1.num = l2.num
  and l1.num = l3.num
  and l1.id = l2.id - 1
  and l1.id = l3.id - 2;

-- 编写一个SQL查询来查找收入比经理高的员工。
-- 6. https://leetcode.cn/problems/employees-earning-more-than-their-managers/

select e1.name as Employee
from `6_Employee` e1,
     `6_Employee` e2
where e1.managerId = e2.id
  and e1.salary > e2.salary;


-- 编写一个 SQL 查询来报告所有重复的电子邮件。 请注意，可以保证电子邮件字段不为 NULL。
-- 7. https://leetcode.cn/problems/duplicate-emails/
select email as Email
from `7_Person`
group by email
having count(email) > 1;

-- 找出所有从不点任何东西的顾客。
-- 8. https://leetcode.cn/problems/customers-who-never-order/
select name as `Customers`
from `8_Customers`
where id not in (select distinct customerId from `8_Orders`);


-- 查找出每个部门中薪资最高的员工。
-- 9. https://leetcode.cn/problems/department-highest-salary/
select `9_10_Department`.name as `Department`, `9_10_Employee`.name as `Employee`, `9_10_Employee`.salary as `Salary`
from `9_10_Employee`,
     `9_10_Department`,
     (select departmentId, max(salary) as max from `9_10_Employee` group by departmentId) as temp
where `9_10_Employee`.departmentId = `9_10_Department`.id
  and `9_10_Employee`.departmentId = temp.departmentId
  and `9_10_Employee`.salary = temp.max;

-- 编写一个SQL查询，找出每个部门中 收入高的员工 。
-- 10. https://leetcode.cn/problems/department-top-three-salaries/
select `9_10_Department`.name as `Department`, `9_10_Employee`.name as `Employee`, `9_10_Employee`.salary as `Salary`
from `9_10_Employee`,
     `9_10_Department`,
     (select departmentId, salary, row_number() over (partition by departmentId order by salary desc) as `no`
      from `9_10_Employee`
      group by salary, departmentId) as temp
where `9_10_Employee`.departmentId = `9_10_Department`.id
  and `9_10_Employee`.departmentId = temp.departmentId
  and `9_10_Employee`.salary = temp.salary
  and temp.no <= 3;

-- 删除 所有重复的电子邮件，只保留一个具有最小 id 的唯一电子邮件。
-- 11. https://leetcode.cn/problems/delete-duplicate-emails/
delete `11_Person`
from `11_Person`,
     (select min(id) as id, Email from `11_Person` group by Email) p2
where `11_Person`.Email = p2.Email
  and `11_Person`.Id > p2.id;

-- 编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 id 。
-- 12. https://leetcode.cn/problems/rising-temperature/
select w2.id as id
from `12_Weather` as w1,
     `12_Weather` as w2
where date_add(w1.recordDate, interval 1 day) = w2.recordDate
  and w1.temperature < w2.temperature;


# 取消率 的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)。
# 写一段 SQL 语句查出"2013-10-01"至"2013-10-03"期间非禁止用户（乘客和司机都必须未被禁止）的取消率。非禁止用户即 banned 为 No 的用户，禁止用户即 banned 为 Yes 的用户。
# 返回结果表中的数据可以按任意顺序组织。其中取消率 Cancellation Rate 需要四舍五入保留 两位小数 。
-- 13. https://leetcode.cn/problems/trips-and-users/

select t1.request_at as `Day`, convert(ifnull(t2.cancelled, 0) / t1.`all`, DECIMAL(15, 2)) as `Cancellation Rate`
from (select t.request_at as `request_at`, count(*) as `all`
      from `13_Trips` t,
           `13_Users` u1,
           `13_Users` u2
      where (t.client_id = u1.users_id and t.driver_id = u2.users_id)
        and (u1.banned = 'No' and u2.banned = 'No')
        and (t.request_at <= '2013-10-03' and t.request_at >= '2013-10-01')
      group by t.request_at) as t1
         left join
     (select t.request_at as `request_at`, count(*) as `cancelled`
      from `13_Trips` t,
           `13_Users` u1,
           `13_Users` u2
      where (t.client_id = u1.users_id and t.driver_id = u2.users_id)
        and (u1.banned = 'No' and u2.banned = 'No')
        and (t.request_at <= '2013-10-03' and t.request_at >= '2013-10-01')
        and (t.status = 'cancelled_by_client' or t.status = 'cancelled_by_driver')
      group by t.request_at) as t2
     on t1.request_at = t2.request_at;

-- 查询每位玩家 第一次登陆平台的日期。
-- 14. https://leetcode.cn/problems/game-play-analysis-i/

select player_id, min(event_date) as first_login
from `14_Activity`
group by player_id;

-- 编写一个 SQL 查询，报告在首次登录的第二天再次登录的玩家的比率，四舍五入到小数点后两位。换句话说，您需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。
-- 15. https://leetcode.cn/problems/game-play-analysis-iv/
select convert(target.`count` / `all`.`count`, DECIMAL(15, 2)) as fraction
from (select count(temp.player_id) as `count`
      from `15_Activity` a,
           (select player_id,
                   min(event_date) as first_login
            from `15_Activity`
            group by player_id) as `temp`
      where date_add(temp.first_login, interval 1 day) = a.event_date
        and a.player_id = temp.player_id) as `target`,
     (select count(distinct player_id) as `count` from `15_Activity`) as `all`;

-- 查询至少有5名直接下属的经理 。
-- 16. https://leetcode.cn/problems/managers-with-at-least-5-direct-reports/

select name
from `16_Employee`
where id in (select managerId
             from `16_Employee`
             group by managerId
             having count(managerId) >= 5);

-- 选出所有 bonus < 1000 的员工的 name 及其 bonus。
-- 17. https://leetcode.cn/problems/employee-bonus/
select `17_Employee`.name as `name`, `17_Bonus`.bonus as `bonus`
from `17_Employee`
         left join
     `17_Bonus` on `17_Employee`.empId = `17_Bonus`.empId
where bonus < 1000
   or bonus is null;

-- 写一个查询语句，返回一个客户列表，列表中客户的推荐人的编号都 不是 2。
-- 18. https://leetcode.cn/problems/find-customer-referee/
select name
from `18_Customer`
where referee_id != '2'
   or referee_id is null;

# 请你编写一个 SQL 查询，报告 2016 年 (tiv_2016) 所有满足下述条件的投保人的投保金额之和：
# 他在 2015 年的投保额(tiv_2015) 至少跟一个其他投保人在 2015 年的投保额相同。
# 他所在的城市必须与其他投保人都不同（也就是说(lat, lon) 不能跟其他任何一个投保人完全相同）。
-- 19. https://leetcode.cn/problems/investments-in-2016/
select convert(sum(tiv_2016), decimal(15, 2)) as tiv_2016
from `19_Insurance`
where tiv_2015 in (select tiv_2015 from `19_Insurance` group by tiv_2015 having count(*) > 1)
  and concat(LAT, LON) in
      (select concat(LAT, LON)
       from `19_Insurance`
       group by LAT, LON
       having count(*) = 1);

-- 查找下了 最多订单 的客户的 customer_number 。
-- 20. https://leetcode.cn/problems/customer-placing-the-largest-number-of-orders/
select t.customer_number
from (select customer_number, sum(1) as sum
      from `20_orders`
      group by customer_number) as t
where t.sum = (select max(sum)
               from (select sum(1) as sum
                     from `20_orders`
                     group by customer_number) as e);

-- 查询并报告 大国 的国家名称、人口和面积。
-- 21. https://leetcode.cn/problems/big-countries/
select name, population, area
from `21_World`
where population >= 25000000
   or area >= 3000000;

-- 查询 至少有5个学生 的所有班级。
-- 22. https://leetcode.cn/problems/classes-more-than-5-students/
select class
from `22_Courses`
group by class
having sum(1) >= 5;

-- 编写一个 SQL 查询以找出每行的人数大于或等于 100 且 id 连续的三行或更多行记录。
-- 23. https://leetcode.cn/problems/human-traffic-of-stadium/
select distinct id, visit_date, people
from `23_Stadium`,
     (select s1.id i1, s2.id i2, s3.id i3
      from `23_Stadium` s1,
           `23_Stadium` s2,
           `23_Stadium` s3
      where s1.id + 1 = s2.id
        and s2.id + 1 = s3.id
        and s1.people >= 100
        and s2.people >= 100
        and s3.people >= 100) temp
where id = i1
   or id = i2
   or id = i3;

-- 写一个查询语句，找出拥有最多的好友的人和他拥有的好友数目。
-- 24. https://leetcode.cn/problems/friend-requests-ii-who-has-the-most-friends/
select id, sum(c) as num
from (select requester_id as id, count(1) as c
      from `24_RequestAccepted` r1
      group by requester_id
      union all
      select accepter_id as id, count(1) as c
      from `24_RequestAccepted` r2
      group by accepter_id) as result
group by id
order by num desc
limit 0,1;

-- 查询没有任何与名为 “RED” 的公司相关的订单的所有销售人员的姓名。
-- 25. https://leetcode.cn/problems/sales-person/

select name
from `25_SalesPerson` s
where s.sales_id not in (select distinct s.sales_id
                         from `25_SalesPerson` s
                                  left join
                              `25_Orders` o on o.sales_id = s.sales_id
                                  left join `25_Company` c on o.com_id = c.com_id
                         where c.name = 'RED');