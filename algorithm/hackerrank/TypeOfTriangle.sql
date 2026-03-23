-- Write a query identifying the type of each record in the TRIANGLES table using its three side lengths. Output one of the following statements for each record in the table:

-- Equilateral: It's a triangle with  sides of equal length.
-- Isosceles: It's a triangle with  sides of equal length.
-- Scalene: It's a triangle with  sides of differing lengths.
-- Not A Triangle: The given values of A, B, and C don't form a triangle.
 
    case 
        when a+b<=c or a+c<=b or b+c<=a then 'Not A Triangle'
        when a=b and b=c then 'Equilateral'
        when a=b or b=c or a=c then 'Isosceles'
        else 'Scalene'
    end as type



    Equilateral
Equilateral
Isosceles
Equilateral
Isosceles
Equilateral
Scalene
Not A Triangle
Scalene
Scalene
Scalene
Not A Triangle