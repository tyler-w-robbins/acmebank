DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE MortgageApplication (
	id	VARCHAR(36)	NOT NULL,
	customer	BOOLEAN	NOT NULL,
	requestedAmount	BIGINT	NOT NULL,
	address	TEXT	NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE CreditCardApplication (
	id	VARCHAR(36)	NOT NULL,
	customer	BOOLEAN	NOT NULL,
	cardType VARCHAR(10) NOT NULL,
	requestedCreditLimit	BIGINT	NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE Borrower (
	mortgageId VARCHAR(36) REFERENCES MortgageApplication(id),
	creditId VARCHAR(36) REFERENCES CreditCardApplication(id),
	firstName VARCHAR(10) NOT NULL,
	lastName VARCHAR(10) NOT NULL,
	age INT NOT NULL,
	address VARCHAR(20) NOT NULL,
	city VARCHAR(10) NOT NULL,
	state CHAR(2) NOT NULL,
	zip INT NOT NULL,
	ssn INT NOT NULL,
	relationship VARCHAR(10) NOT NULL,
	borrowerId SERIAL,
	PRIMARY KEY (borrowerId)
);

CREATE TABLE Employer (
	startDate TIMESTAMP NOT NULL,
	endDate TIMESTAMP,
	employerName VARCHAR(20) NOT NULL,
	employerPhone BIGINT NOT NULL,
	borrowerId SERIAL REFERENCES Borrower(borrowerId),
	employerId SERIAL,
	PRIMARY KEY (employerId)
);
