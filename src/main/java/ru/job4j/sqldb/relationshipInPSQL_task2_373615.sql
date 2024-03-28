-- many-to-many
-- From UserCompany to -> Users and Companies
CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL
);

CREATE TABLE Companies (
    CompanyID SERIAL PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL
);

CREATE TABLE UserCompany (
    UserCompanyId SERIAL PRIMARY KEY,
    UserID INT REFERENCES Users(UserID),
    CompanyID INT REFERENCES Companies(CompanyID)
);

-- many-to-one
-- In this case, Companies is(many) and the arrow will go from it to the Users(one) table in the diagram.
-- The arrow goes from the entity in which the field is located (FK REFERENCES) to the referring entity
CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL
);

CREATE TABLE Companies (
    CompanyID SERIAL PRIMARY KEY,
    UserID INT REFERENCES Users(UserID),
    CompanyName VARCHAR(100) NOT NULL
);

-- one-to-one
-- From Users to > 1 > UserDetails
CREATE TABLE UserDetails (
    UserDetailsID SERIAL PRIMARY KEY,
    UserID INT UNIQUE,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
    UserID SERIAL PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    UserDetailsID INT UNIQUE,
    FOREIGN KEY (UserDetailsID) REFERENCES UserDetails(UserDetailsID)
);

