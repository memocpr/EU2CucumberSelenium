package com.vytrack.step_definitions;

import com.vytrack.pages.ContactInfoPage;
import com.vytrack.pages.ContactsPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class ContactStepDefsX {

    @When("the user clicks the {string} from contacts")
    public void the_user_clicks_the_from_contacts(String email) {

        BrowserUtils.waitFor(3);
        ContactsPage contactsPage=new ContactsPage();
        contactsPage.getContactEmail(email).click();

    }

    @Then("the information should be same with database")
    public void the_information_should_be_same_with_database() {

        //get actual data from UI-GUI-Front end-Browser-Website(whatever we see)
        ContactInfoPage contactInfoPage=new ContactInfoPage();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();
        String actualFullName = contactInfoPage.contactFullName.getText();

        //get expected data from database
        //put query syntext from SQL to here as a String
        String query = "select concat(first_name,' ',last_name) as fullname,e.email,phone\n" +
                "from orocrm_contact c JOIN  orocrm_contact_email e\n" +
                "ON c.id=e.owner_id \n" +
                "JOIN orocrm_contact_phone p\n" +
                "ON e.owner_id=p.owner_id\n" +
                "where e.email=\"mbrackstone9@example.com\"";

//        DBUtils.createConnection();

        BrowserUtils.waitFor(2);

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        String expetedfullname = (String) rowMap.get("fullname");
        String expetedemail = (String) rowMap.get("email");
        String expetedphone = (String) rowMap.get("phone");


        System.out.println("fullname = " + expetedfullname);
        System.out.println("email = " + expetedemail);
        System.out.println("phone = " + expetedphone);

//        DBUtils.destroy();

        //Compare UI to DB

        Assert.assertEquals(expetedfullname,actualFullName);
        Assert.assertEquals(expetedemail,actualEmail);
        Assert.assertEquals(expetedphone,actualPhone);

    }


    @Then("the information {string} should be same with database")
    public void the_information_should_be_same_with_database(String email) {

        //get actual data from UI-GUI-Front end-Browser-Website(whatever we see)
        ContactInfoPage contactInfoPage = new ContactInfoPage();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();
        String actualFullName = contactInfoPage.contactFullName.getText();

        //get expected data from database
        //put query syntext from SQL to here as a String
        String query = "select concat(first_name,' ',last_name) as fullname,e.email,phone\n" +
                "from orocrm_contact c JOIN  orocrm_contact_email e\n" +
                "ON c.id=e.owner_id \n" +
                "JOIN orocrm_contact_phone p\n" +
                "ON e.owner_id=p.owner_id\n" +
                "where e.email="+email+"";

//        DBUtils.createConnection();

        BrowserUtils.waitFor(2);

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        String expetedfullname = (String) rowMap.get("fullname");
        String expetedemail = (String) rowMap.get("email");
        String expetedphone = (String) rowMap.get("phone");


        System.out.println("fullname = " + expetedfullname);
        System.out.println("email = " + expetedemail);
        System.out.println("phone = " + expetedphone);

//        DBUtils.destroy();

        //Compare UI to DB

        Assert.assertEquals(expetedfullname, actualFullName);
        Assert.assertEquals(expetedemail, actualEmail);
        Assert.assertEquals(expetedphone, actualPhone);

    }


}
