create database DigitalLibrary;
use DigitalLibrary;

create table Books(
book_id int primary key,
book_name varchar(100) not null,
book_category varchar(100) not null,
book_copies int default 1
);
insert into books(book_id,book_name,book_category,book_copies)values(1,"INtroduction to Web Development","Tech",10),
(2,'Computer Networks','Networking',4),
(3,'Operating System Concepts','Operating Systems',3),
(4,'Database System Concepts','Database',6),
(5,'Java The Complete Reference','Programming',5),
(6,'Python Crash Course','Programming',7),
(7,'Artificial Intelligence Basics','AI',4),
(8,'Machine Learning with Python','AI',3),
(9,'Data Structures Using C','Programming',5),
(10,'Cyber Security Essentials','Security',4);

create table Students(
student_id int primary key,
student_name varchar(100) not null,
student_status varchar(20) default 'active'
);


insert into Students (student_id,student_name)values
(1,'Karthik'),
(2,'Santhosh'),
(3,'Ravi'),
(4,'Raju'),
(5,'Vikas'),
(6,'Vijay'),
(7,'Naresh'),
(8,'Suresh'),
(9,'Mahesh'),
(10,'Praveen');

create table Issuedbooks(
issue_id int auto_increment primary key,
book_id int,
student_id int,
issue_date date not null,
return_date date,
foreign key(student_id) references Students(student_id),
foreign key(book_id)references Books(book_id)
);
insert into IssuedBooks (book_id, student_id, issue_date, return_date) values
(1,7,'2026-03-20',NULL);  
insert into IssuedBooks (book_id, student_id, issue_date, return_date) values
(1,1,'2026-03-20',NULL),          
(2,2,'2026-04-10','2026-04-18'), 
(3,3,'2026-03-28',NULL),         
(4,4,'2026-04-15',NULL),         
(5,5,'2025-01-10','2025-01-25'),
(6,6,'2024-02-12','2024-02-20'),
(7,7,'2022-01-05','2022-01-18'),
(8,8,'2021-07-11','2021-07-25'),
(9,9,'2026-04-01',NULL),         
(10,10,'2023-03-10','2023-03-22'),
(2,1,'2026-02-15','2026-02-28'),
(5,3,'2026-04-05',NULL),
(1,6,'2023-01-14','2023-01-30'),
(7,2,'2026-04-12',NULL),
(4,8,'2022-05-20','2022-06-01');

select * from students;
select * from books;
select * from issuedbooks;
-- 2. Overdue Logic

select s.student_id, s.student_name ,b.book_name ,i.issue_date from Issuedbooks i 
join students s on i.student_id = s.student_id 
join books b on i.book_id = b.book_id where i.return_date is null and
datediff(current_date(),i.issue_date)>14;

-- 3. Popularity Index

select b.book_category, count(i.book_id)as popularity from issuedbooks i 
join books b on i.book_id = b.book_id 
group by b.book_category order by popularity desc;

-- 4. Data Cleanup
set SQL_SAFE_UPDATES=0;
update  students s left join issuedbooks i on s.student_id = i.student_id 
and i.issue_date>current_date()-interval 3 year
set s.student_status='inactive' where i.student_id is null ;

select * from students;
