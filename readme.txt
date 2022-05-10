How to setup and run the platform:
-connect to your database
-run your backend
-open the project in Intellij and run it through a configuration running Main


The functionalities provided and how to use them:
-Home page: 
			add transaction: input a transaction you made (not on the platform), enter LBP and USD amount and select sell if you're going USD to LBP and buy otherwise
			rate calculator: select your desired transaction (buy or sell), then input the LBP/USD amount you want to buy/sell accordingly
-Log in and sign up are self explanatory
-Statistics: takes you to another window showing a graph of the number of transactions made on each of the last 20 days
-Rate changes: takes you to another window showing a graph of the average rate od exchange on each of the last 20 days

Once Logged in, extra buttons appear in the taskbar:
-Transactions shows all the transactions the user made within a table
-The make transactions tab:
							it shows the amount of money the use has in her/her account in USD and LBP
							it allows the user to request a transaction by inputting the type (select button if selling USD, otherwise buying), 
							and the amount of LBP/USD to be exchanged
							it shows the user a list of all posted transactions by all user, the user can then pick a transaction to make, input
							its id, and press select to proceed with it
-Finally, admin side is only accessible to users that are manually set to be admins in the DB
			admins will have access to a page that allows them to input a user's username and the amount of money to add to their balance in USD and LBP 
			(that would be after the user actually paid the amount to the admin in real life)