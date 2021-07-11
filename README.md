### CIS 573 Project

Project setup guide can be found [here](https://docs.google.com/document/d/1ERV79t_5kH_c4x5rCqEHbUp5VATvQM1lBiFNzqSf0wA/edit).

Project Phase 0 Writeup can be found [here](https://docs.google.com/document/d/17hkYnv1S82FEYruonaooUfmTm8qw4XGIjdKNCce_4to/edit).

## Changes Made
* Task 1.8: To complete this task, the project utilizes Android's built-in class called `SimpleDateFormat` to parse the original format and to convert it into human-readable format. In detail, a new class called `DateParser` is created in the Contributor app in order to handle the conversion. The class has two new parameters, `oldDateFormat` (the date format originallly used in this project: `yyyy-MM-dd T HH:mm:ss Z`) and `newDateFormat` (the format is `MM/dd/yyyy`). And the class has a method `convertDateToNewFormat` that parses the date time in original format and converts to the new date format. The method throws `ParseException` if the parameter passed is not in the old format.