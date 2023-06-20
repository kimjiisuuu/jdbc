select * from member_tbl_02 mt;
select * from money_tbl_02 ms;

insert into MEMBER_TBL_02 mt values ('100007','김지수','010-2184-7094','경기도 시흥시 은행동','to_date('2000-09-08','yyyy-dd-mm'),'A','55');

-- step 1 회원별 매출합계
select custno, sum(price) from money_tbl_02 group by custno;

-- step 2 정렬 기준 확인하기
select custno, sum(price) from money_tbl_02 group by custno order by sum(price) desc;

-- step 3 custno 컬럼으로 조인하여 고객 정보 전체 가져오기
select * from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
where met.custno = sale.custno;
-- 또는
select * from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
on met.custno = sale.custno;

-- step 4 필요한 컬럼만 가져오기
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   asum
   from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY asum desc;
-- 또는
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   sale.asum
   from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   where met.custno = sale.custno 
   ORDER BY total desc;

++ decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원');

-- 외부조인 : 매출이 없는 회원도 포함한다.
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   nvl(sale.asum,0) total
   from member_tbl_02 met LEFT OUTER join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY total DESC ,custno;
   

