Feature: UI tests for Factoring calculator

  Background: The user is on required page
    Given application is launched

    @TC1_CalculateInvoiceFinancingExpenses
    Scenario Outline: User enters parameters to calculate Invoice Financing Expenses
      When user enters invoice amount as "<amount>" EUR
      Then user inputs Advance Rate
      And user inputs Interest Rate as "<interestRate>" %
      And user inputs Payment Term
      And user enters Commission Fee as "<commissionFee>" % per invoice
      Then user clicks on Calculate button
      And user validates Invoice Financing Expenses
      Examples:
        | amount |  interestRate | commissionFee |
        | 1 |  0 | 0 |
        | 1000 |  20 | -1 |
        | 1000 |  1 | 200 |
        | 1000000000000 |  20 | 10 |

    @TC2_ValidateErrorMessages_invoiceAmount
    Scenario Outline: User enters invalid values in Invoice Amount field and verifies error message
      When user enters invoice amount as "<amount>" EUR
      Then user verifies error message
      Examples:
        | amount |
        | -1 |
        | qwerty |

    @TC3_ValidateErrorMessages_interestRate
    Scenario Outline: User enters invalid values in Interest Rate field and verifies error message
      When user inputs Interest Rate as "<interestRate>" %
      Then user verifies error message
      Examples:
        | interestRate |
        | -1 |
        | 21 |
        | qwerty |

    @TC4_ValidateErrorMessages_commissionFee
    Scenario: User enters invalid value in Interest Rate field and verifies error message
      When user enters Commission Fee as "qwerty" % per invoice
      Then user verifies error message
