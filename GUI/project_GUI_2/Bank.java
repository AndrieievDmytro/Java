//////////////////Andrieiev Dmytro s21353 group 18c/////////////////////////////

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;

public class Bank{
    
    public static void main ( String[] args ) {
        String fIn                = "Bank.dat";
        String fErr               = "Bank.err";
        Map <String,Account> accs = readData( fIn, fErr );

        for ( Map.Entry <String,Account> e : accs.entrySet() )
            System.out.print( e.getValue() );

        try {
            String errLog = new String( Files.readAllBytes( Paths.get( fErr ) ), UTF_8) ;
            System.out.println( "\nContent of \"Bank.err\" follows:\n" );
            System.out.println( errLog );
        } catch(IOException e) {
            System.out.println( "Problems with error log" );
            return;
        }
    }

    public static Map <String, Account> readData(String fIn , String fErr) {
        Transaction.lineNum = 0;
        Transaction.fErr    = fErr;

        File file = new File(fErr);
        if ( file.exists() ) file.delete();

        try  ( Stream <String> lines = Files.lines( Paths.get( fIn ) ) ) {
            lines.forEach( Transaction::handleString );            
        } catch (IOException e){
            System.out.println( "Can't read file " + fIn );
        }

        return Transaction.accs;
    }    
}

/**
* Account class contains:
*<pre>Members:</pre>
* <blockquote><pre>
*- id as identifier
*- owner as class Person
*- balance: amount of money on the account
*- list as class List &lt;Transaction&gt;
* </pre></blockquote>
*<pre>Methods:</pre>
* <blockquote><pre>
*- Account doOperation( int amount, Transaction tr ) change balance of account and store transaction to list
*- String getId() returns identifier of account
*- balance: amount of money on the account
*- list as class List &lt;Transaction&gt;
* </pre></blockquote>
**/
class Account{

    private String             id;
    private Person             owner;
    private int                balance;
    private List <Transaction> list ;

    Account( String id, Person owner, int balance, Transaction tr ) {
        this.id      = id;
        this.owner   = owner;
        this.balance = balance;
        this.list    = new ArrayList <Transaction> ();
        if ( tr != null ) list.add( tr );
    }

    public String getId() { return this.id; }

    public Person getOwner() { return this.owner; }

    public Account doOperation( int amount, Transaction tr ){
        int b;
        if ( (b = this.balance + amount) < 0 )
            throw new IllegalArgumentException( "Insufficient funds." );
        this.balance = b;
        if ( tr != null ) list.add( tr );
        return this;
    }

    @Override public String toString() {
        return "*** Acc " + this.id + "" + " (" + this.owner + ") " + 
               " Balance: " + this.balance + 
               " Transactions:" + System.lineSeparator() +
               list
               .stream()
               .map( t-> t.toString( this.id ) )
               .collect(Collectors.joining(System.lineSeparator())) + 
               System.lineSeparator();
    }
}

class Person{
    private String firstName;
    private String secondName;

    Person (String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override public String toString() { return this.firstName + " " + this.secondName; }
}

class Transaction {
    public enum opTypes {
        INIT_DEPOS { @Override public String toString()  { return "Init depos ";                  } },
        DEPOSIT    { @Override public String toString()  { return "Deposit";                      } },
        WITHDRAWAL { @Override public String toString()  { return "Withdrawal";                   } }, 
        TRANS_FROM { @Override public String toString()  { return "Trans. from this account to "; } }, 
        TRANS_TO   { @Override public String toString()  { return "Trans. to this account from "; } } 
    };

    public static Map<String,Account> accs    = new HashMap <String,Account> ();
    public static String              fErr = "errorLog.txt";
    public static int                 lineNum = 0;
    private long                      timeStamp;
    private List <TransOps>           operations;

    public long getTimeStamp() { return this.timeStamp; };

    public static Transaction handleString( String s ) {
        return new Transaction( s );
    }
    
    Transaction( String operation ) {
        this.timeStamp = System.currentTimeMillis();
        this.operations = new ArrayList<TransOps>();
        lineNum++;
        try{
            selectOperation( operation );
        } catch ( Exception e ) {
            logErrors( Arrays.asList( 
                "Line " + lineNum + ": " + operation, 
                "\tError: " + e.getMessage() ) );
        }
    }

    public void logErrors( List <String> err ) {
        try{
            Files.write(Paths.get(fErr), err, UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e){
            System.out.println( "Can't write file " + fErr );
        }
    }

    public void selectOperation( String operation ) {
        String [] opParams = operation.split( "[^a-zA-Z0-9\\-]+" );
        if (  ! isAccId( opParams[0] )  )
            throw new IllegalArgumentException( "The first parameter has not correct format of account ID: " + opParams[0] );
        String accId = opParams[0];
        switch ( opParams.length ){
            case 2:
                changeAcc( accId, opParams[1] );
                break;
            case 3:
                transAcc( accId, opParams[1], opParams[2] );
                break;
            case 4:
                initAcc( accId, opParams[1], opParams[2], opParams[3] );
                break;
            default:
                throw new IllegalArgumentException( "Wrong operation's parameters count: " + operation );
        }
    }

    public Transaction changeAcc( String accId, String amnt ) {
        if ( ! isAccAmount( amnt ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + amnt );
        Account tmpAcc1 = accs.get( accId );
        if ( tmpAcc1 == null )
            throw new IllegalArgumentException( "Account for ID:(" + accId + ") does not exist." );
        Integer amount = Integer.parseInt( amnt );
        tmpAcc1.doOperation( amount, this );
        this.operations.add( new TransOps( tmpAcc1, amount < 0 ? opTypes.WITHDRAWAL : opTypes.DEPOSIT, amount ) );
        accs.put( accId, tmpAcc1 );
        return this;
    }

    public Transaction transAcc( String accIdF, String accIdT, String amnt ) {
        if ( ! isAccId( accIdT ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + accIdT );
        if ( ! isAccAmount( amnt ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + amnt );
        Account tmpAcc1 = accs.get( accIdF );
        Account tmpAcc2 = accs.get( accIdT );
        if ( tmpAcc1 == null )
            throw new IllegalArgumentException( "Account for ID:(" + accIdF + ") does not exist." );
        if ( tmpAcc2 == null )
            throw new IllegalArgumentException( "Account for ID:(" + accIdT + ") does not exist." );
        Integer amount = Integer.parseInt( amnt );
        tmpAcc1.doOperation( -amount, this );
        tmpAcc2.doOperation(  amount, this );
        this.operations.add( new TransOps( tmpAcc1, opTypes.TRANS_FROM, -amount, tmpAcc2 ) );
        this.operations.add( new TransOps( tmpAcc2, opTypes.TRANS_TO,    amount, tmpAcc1 ) );
        accs.put( accIdF, tmpAcc1 );
        accs.put( accIdT, tmpAcc2 );
        return this;
    }

    public Transaction initAcc( String accId, String pFName, String pSName, String amnt ) {
        if ( ! isAccPName( pFName ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + pFName );
        if ( ! isAccPName( pSName ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + pSName );
        if ( ! isAccAmount( amnt ) )
            throw new IllegalArgumentException( "Wrong operation's parameter format: " + amnt );
        if ( accs.get( accId ) != null )
            throw new IllegalArgumentException( "Account already exists." );
        Integer amount = Integer.parseInt( amnt );
        Account tmpAcc1 = new Account( accId, new Person( pFName, pSName ), amount, this );
        this.operations.add( new TransOps( tmpAcc1, opTypes.INIT_DEPOS, amount ) );
        accs.put( accId, tmpAcc1 );
        return this;
    }

    public static Boolean isAccId( String s ){ return s.matches( "[A-Z][a-z]+[A-Z]" ); }

    public static Boolean isAccPName( String s ){ return s.matches( "[A-Z][a-z]+" ); }

    public static Boolean isAccAmount( String s ){ return s.matches( "[\\-0-9]+" ); }

    public String toString( String accId ) {
        return this
        .operations
        .stream()
        .filter( op-> op.account.getId() == accId )
        .map(op-> op.toString()  )
        .collect( Collectors.joining(System.lineSeparator()) );
    }

    @Override public String toString() {
        return this
        .operations
        .stream()
        .map(op-> op.toString()  )
        .collect( Collectors.joining(System.lineSeparator()) );
    }
}

class TransOps {
    public Account             account;
    public Account             correspondAccount;
    public Integer             amount;
    public Transaction.opTypes opType;

    TransOps( Account acc, Transaction.opTypes opt, Integer amnt ) {
        this.account = acc;
        this.opType  = opt;
        this.amount  = amnt;
        this.correspondAccount = null;
    }

    TransOps( Account acc, Transaction.opTypes opt, Integer amnt, Account cAcc ) {
        this.account = acc;
        this.opType  = opt;
        this.amount  = amnt;
        this.correspondAccount = cAcc;
    }

    @Override public String toString() {
        String sAmount = Integer.toString( this.amount );
        sAmount = "       ".substring( sAmount.length() ) + sAmount;
        return sAmount + ": " + 
        this.opType + 
        ( this.correspondAccount == null ? 
          "" 
          : 
          correspondAccount.getOwner() + " (" +  correspondAccount.getId() + ")" 
        );
    }
}
