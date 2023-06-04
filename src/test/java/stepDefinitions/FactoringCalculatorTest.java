package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.Config;
import framework.Driver;
import framework.Report;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.ThreadLocalRandom;

public class FactoringCalculatorTest {

    @Given("^application is launched$")
    public void applicationIsLaunched() {
        Driver.get(Config.getProperty("appURL"));
        if (Driver.getTitle().contains("Swedbank factoring"))   {
            Report.pass("Factoring page launched successfully.");
            if (Driver.getElements("acceptCookies").size() !=0) {
                if (Driver.getElement("acceptCookies").isDisplayed())
                    Driver.getElement("acceptCookies").click();
            }
        } else
            Report.fail("Factoring page could not be launched.");
    }

    @When("^user enters invoice amount as \"([^\"]*)\" EUR$")
    public void userEntersInvoiceAmountAsEUR(String arg0) {
        if (Driver.getElements("invoiceAmount").size() !=0)  {
            Driver.sendKeys(Driver.getElement("invoiceAmount"), arg0);
            Report.pass("Invoice Amount = " + arg0);
        }
        else
            Report.fail("Invoice Amount field not present on screen.");
    }

    @Then("^user inputs Advance Rate$")
    public void userInputsAdvanceRateAs() {
        if (Driver.getElements("advanceRate").size() !=0)  {
            Select advRate = new Select(Driver.getElement("advanceRate"));
            int index = ThreadLocalRandom.current().nextInt(0, advRate.getOptions().size());
            advRate.selectByIndex(index);
            Report.pass("Advance Rate= " + advRate.getFirstSelectedOption().getText());
        }
        else
            Report.fail("Advance Rate field not present on screen.");
    }

    @And("^user inputs Interest Rate as \"([^\"]*)\" %$")
    public void userInputsInterestRateAs(String arg0) {
        if (Driver.getElements("interestRate").size() !=0)  {
            Driver.sendKeys(Driver.getElement("interestRate"), arg0);
            Report.pass("Interest Rate = " + arg0);
        }
        else
            Report.fail("Interest Rate field not present on screen.");
    }

    @And("^user inputs Payment Term$")
    public void userInputsPaymentTermAsDays() {
        if (Driver.getElements("paymentTerm").size() !=0)  {
            Select payTerm = new Select(Driver.getElement("paymentTerm"));
            int index = ThreadLocalRandom.current().nextInt(0, payTerm.getOptions().size());
            payTerm.selectByIndex(index);
            Report.pass("Payment Term= " + payTerm.getFirstSelectedOption().getText());
        }
        else
            Report.fail("Payment Term field not present on screen.");
    }

    @And("^user enters Commission Fee as \"([^\"]*)\" % per invoice$")
    public void userEntersCommissionFeeAsPerInvoice(String arg0) {
        if (Driver.getElements("commissionFee").size() !=0)  {
            Driver.sendKeys(Driver.getElement("commissionFee"), arg0);
            Report.pass("Commission Fee = " + arg0);
        }
        else
            Report.fail("Commission Fee field not present on screen.");
    }

    @Then("^user clicks on Calculate button$")
    public void userClicksOnCalculateButton() {
        if (Driver.getElement("calculateButton").isDisplayed() &&
                Driver.getElement("calculateButton").isDisplayed())    {
            Driver.safeClick(Driver.getElement("calculateButton"));
            Report.pass("Calculate button clicked.");
        } else
            Report.fail("Calculate button could not be clicked.");
    }

    @And("^user validates Invoice Financing Expenses$")
    public void userValidatesInvoiceFinancingExpenses() {
        WebElement result = Driver.getElement("calculatorResult");
        WebElement resultPerc = Driver.getElement("calculatorResultPerc");
        Assert.assertNotNull(result);
        Assert.assertNotNull(resultPerc);
        Report.pass("Result amount = " +  result.getText() + "EUR");
        Report.pass("Result percentage = " +  resultPerc.getText() + "%");
    }

    @And("^user verifies error message$")
    public void userVerifiesErrorMessage() {
        if (Driver.getElements("errorMessage").size() !=0)  {
            WebElement error = Driver.getElement("errorMessage");
            Assert.assertTrue(error.isDisplayed());
            Report.pass("Error message on screen = " + error.getText());
        }
        else
            Report.fail("Expected error message not present on screen.");
    }
}