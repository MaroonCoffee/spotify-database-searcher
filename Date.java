public class Date {
    int day;
    int month;
    int year;
    
    public Date(int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }
    
    public boolean isEarlier(Date two){
        if (year != two.year) return year<two.year;
        else{
            if (month != two.month) return month<two.month;
            else{
                if (day != two.day) return day<two.day;
            }
        }
        return true;
    }
    
    public String toString(){
        String monthString = new String();
        switch(month){
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "January";
                break;
        }
        
        String daySuffix = new String();
        switch(day){
            case 1:
                daySuffix = "st";
                break;
            case 2:
                daySuffix = "nd";
                break;
            case 3:
                daySuffix = "rd";
                break;
            case 21:
                daySuffix = "st";
                break;
            case 22:
                daySuffix = "nd";
                break;
            case 23:
                daySuffix = "rd";
                break;
            case 31:
                daySuffix = "st";
                break;
            default:
                daySuffix = "th";
                break;
        }
        return monthString + " " + day + daySuffix + ", " + year;
    }
}