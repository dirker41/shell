// StyleCheckType InputStreamReader, BufferedReader, FileInputStream, DataInputStream, File
//package PL100_9827204 ;

import java.io.* ;
//import CYICE.* ;
import java.util.*;


// indent : ÁY±Æ 

abstract class G {
  final static int TYPE_UNKNOW = 0 ;
  final static int TYPE_SYMBOL = 1 ;
  final static int TYPE_LETTER = 2 ;
  final static int TYPE_NUMBER = 3 ;
  // final static int Type_LRParenthesis = 4 ;
  
  static SystemFunctionTable sSystemFunctionTable = new SystemFunctionTable() ;
  static PreserveWord sPreserveWord = new PreserveWord() ;
  static DeclareWord sDeclareWord = new DeclareWord() ;
  static Delimiter sDelimiter = new Delimiter();
  static VariableTalbe sVariableTalbe = new VariableTalbe();
  static FunctionTable sFunctionTable = new FunctionTable();
  
  static boolean sTypeError = false ;
  
  static Variable sReturnVariable = new Variable() ;
  
  static boolean sDebug = false ;
  static boolean sReadfile = true ;
  
  static int sVarLength = 0 ;
  static int sFuncVarIndex = -1 ;
  static boolean sDealScope = false ;
  
  static int sSymbol = 100 ;
  
  static int sV1 = 0 ;
  static int sV2 = 0 ;
  
  static void AmazingToken( String str ) {
    System.out.println( "Waht a amazing Token:" + str );
  } // AmazingToken()
  
  static void DefineOK( String str ) {
    System.out.println( "Definition of " + str + " entered ..." );
  } // DefineOK()
  
  static void DefineFuncOK( String str ) {
    System.out.println( "Definition of " + str + "() entered ..." );
  } // DefineFuncOK()
  
  static void NewDefineOK( String str ) {
    System.out.println( "New definition of " + str + " entered ..." );
  } // NewDefineOK()
  
  static void NewDefineFuncOK( String str ) {
    System.out.println( "New definition of " + str + "() entered ..." );
  } // NewDefineFuncOK()
  
  static void StatementOK() {
    System.out.println( "> Statement executed ..." );
  } // StatementOK()
  
  
  static void Print( String str ) {
    System.out.print( str );
  } // Print()
  
  static void Println( String str ) {
    System.out.println( str );
  } // Println()
  
  static int StrToInt( String str ) throws Exception {
    try {
      return Integer.parseInt( str ) ;
    } catch ( Exception e ) {
      // Println( str + " can not trans to integer.55" ) ;
      return 0 ;
    } // catch()
    
  } // StrToInt()
  
  static float StrToFloat( String str ) throws Exception {
    try {
      return Float.parseFloat( str ) ;
    } catch ( Exception e ) {
      // Println( str + " can not trans to Float.69" ) ;
      return 0 ;
    } // catch()
  } // StrToFloat()
  
  
  static boolean StrToBool( String str ) throws Exception {
    try {
      return Boolean.parseBoolean( str ) ;
        
      
    } catch ( Exception e ) {
      Println( str + " can not trans to boolean." ) ;
      return false ;
    } // catch()
    
  } // StrToBool()
  
  static boolean BoolIdentifier( String str ) {
    if ( str.equals( "true" ) ) return false ;
    if ( str.equals( "false" ) ) return false ;
    if ( str.length() == 0 ) return false ;
    if ( BoolLetter( new String( "" + str.charAt( 0 ) ) ) ) return true ;
    if ( str.charAt( 0 ) == '_' ) return true ;
    
    return false ;
  } // BoolIdentifier()
  
  static boolean BoolAssignment_operator( String str ) {
    if ( str.length() == 0 ) return false ;
    if ( str.equals( "=" ) ) return true ;
    if ( str.equals( "+=" ) ) return true ;
    if ( str.equals( "-=" ) ) return true ;
    if ( str.equals( "*=" ) ) return true ;
    if ( str.equals( "/=" ) ) return true ;
    if ( str.equals( "%=" ) ) return true ;
    return false ;
    
  } // BoolAssignment_operator()
  
  static boolean BoolSign( String str ) {
    if ( str.length() == 0 ) return false ;
    if ( str.equals( "+" ) ) return true ;
    if ( str.equals( "-" ) ) return true ;
    if ( str.equals( "!" ) ) return true ;
    return false ;
  } // BoolSign()
  
  static boolean BoolString( String str ) {
    if ( str.length() <= 1 ) return false ;
    if ( str.charAt( 0 ) != '"' ) return false ;
    if ( str.charAt( ( str.length() - 1  ) ) != '"' ) return false ;
    return true ;
  } // BoolString()
  
  static boolean BoolChar( String str ) {
    if ( str.length() <= 1 ) return false ;
    if ( str.charAt( 0 ) != '\'' ) return false ;
    if ( str.charAt( ( str.length() - 1  ) ) != '\'' ) return false ;
    return true ;
  } // BoolChar()
  
  static boolean BoolParseNum( String str ) {
    if ( str.length() == 0 ) return false ;
    try { 
      Integer.parseInt( str ) ;
      return true ;
    } catch ( Exception e ) { // catch1
      try {
        Float.parseFloat( str ) ;
        return true ;
      } catch ( Exception ee ) { // catch2
        return false ;
      } // catch2
    } // catch1
    
  } // BoolParseNum()
  
  static boolean BoolConstant( String str ) {
    if ( str.length() == 0 ) return false ;
    if ( BoolParseNum( str ) ) return true ;
    if ( BoolString( str ) ) return true ;
    if ( BoolChar( str ) ) return true ;
    
    return false ;
  } // BoolConstant()
  
  
  
  static boolean BoolBoolean( String str ) {
    if ( str.length() == 0 ) return false ;
    if ( str.equals( ">" ) ) return true ;
    if ( str.equals( "<" ) ) return true ;
    if ( str.equals( ">=" ) ) return true ;
    if ( str.equals( "<=" ) ) return true ;
    if ( str.equals( "<>" ) ) return true ;
    if ( str.equals( "==" ) ) return true ;
    if ( str.equals( "!=" ) ) return true ;
    return false ;
  } // BoolBoolean()
  
  static boolean BoolWhiteSpace( String ch ) {
    if ( ch.length() == 0 ) return false ;
    if ( ch.equals( " " ) ) return true ;
    if ( ch.equals( "\t" ) ) return true ;
    if ( ch.equals( "\n" ) ) return true ;
    if ( ch.equals( "\r" ) ) return true ;
    if ( ch.equals( "\f" ) ) return true ;
    if ( ch.equals( "x0B" ) ) return true ;
    return false ;
  } // BoolWhiteSpace()
  
  
  
  static boolean BoolNumber( String ch ) {
    if ( ch.equals( "0" ) ) return true ;
    if ( ch.equals( "1" ) ) return true ;
    if ( ch.equals( "2" ) ) return true ;
    if ( ch.equals( "3" ) ) return true ;
    if ( ch.equals( "4" ) ) return true ;
    if ( ch.equals( "5" ) ) return true ;
    if ( ch.equals( "6" ) ) return true ;
    if ( ch.equals( "7" ) ) return true ;
    if ( ch.equals( "8" ) ) return true ;
    if ( ch.equals( "9" ) ) return true ;
    
    // if ( ch.equals( "." ) ) return true ;
    return false ;
  } // BoolNumber()
  
  static boolean BoolLetter( String ch ) {
    if ( ch.equals( "a" ) ) return true ;
    if ( ch.equals( "b" ) ) return true ;
    if ( ch.equals( "c" ) ) return true ;
    if ( ch.equals( "d" ) ) return true ;
    if ( ch.equals( "e" ) ) return true ;
    if ( ch.equals( "f" ) ) return true ;
    if ( ch.equals( "g" ) ) return true ;
    if ( ch.equals( "h" ) ) return true ;
    if ( ch.equals( "i" ) ) return true ;
    if ( ch.equals( "j" ) ) return true ;
    if ( ch.equals( "k" ) ) return true ;
    if ( ch.equals( "k" ) ) return true ;
    if ( ch.equals( "l" ) ) return true ;
    if ( ch.equals( "m" ) ) return true ;
    if ( ch.equals( "n" ) ) return true ;
    if ( ch.equals( "o" ) ) return true ;
    if ( ch.equals( "p" ) ) return true ;
    if ( ch.equals( "q" ) ) return true ;
    if ( ch.equals( "r" ) ) return true ;
    if ( ch.equals( "s" ) ) return true ;
    if ( ch.equals( "t" ) ) return true ;
    if ( ch.equals( "u" ) ) return true ;
    if ( ch.equals( "v" ) ) return true ;
    if ( ch.equals( "w" ) ) return true ;
    if ( ch.equals( "x" ) ) return true ;
    if ( ch.equals( "y" ) ) return true ;
    if ( ch.equals( "z" ) ) return true ;
    
    if ( ch.equals( "A" ) ) return true ;
    if ( ch.equals( "B" ) ) return true ;
    if ( ch.equals( "C" ) ) return true ;
    if ( ch.equals( "D" ) ) return true ;
    if ( ch.equals( "E" ) ) return true ;
    if ( ch.equals( "F" ) ) return true ;
    if ( ch.equals( "G" ) ) return true ;
    if ( ch.equals( "H" ) ) return true ;
    if ( ch.equals( "I" ) ) return true ;
    if ( ch.equals( "J" ) ) return true ;
    if ( ch.equals( "K" ) ) return true ;
    if ( ch.equals( "L" ) ) return true ;
    if ( ch.equals( "M" ) ) return true ;
    if ( ch.equals( "N" ) ) return true ;
    if ( ch.equals( "O" ) ) return true ;
    if ( ch.equals( "P" ) ) return true ;
    if ( ch.equals( "Q" ) ) return true ;
    if ( ch.equals( "R" ) ) return true ;
    if ( ch.equals( "S" ) ) return true ;
    if ( ch.equals( "T" ) ) return true ;
    if ( ch.equals( "U" ) ) return true ;
    if ( ch.equals( "V" ) ) return true ;
    if ( ch.equals( "W" ) ) return true ;
    if ( ch.equals( "X" ) ) return true ;
    if ( ch.equals( "Y" ) ) return true ;
    if ( ch.equals( "Z" ) ) return true ;
    
    return false ;
  } // BoolLetter()
  
  static int WhatType( String ch ) {
    if ( BoolLetter( ch ) ) return G.TYPE_LETTER ;
    if ( BoolNumber( ch ) ) return G.TYPE_NUMBER ;
    if ( sDelimiter.Find( ch ) ) return G.TYPE_SYMBOL ;
    else return G.TYPE_UNKNOW ;
  } // WhatType()
  
  
  static void ComputeCh( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( variable1.GetType().equals( "char" ) ) {
      if ( variable2.GetType().equals( "char" ) ) {
        int v1 = -1 ;
        int v2 = -1 ;
      
        if ( variable1.GetValue().equals( "'\n'" ) )
          v1 = ( int ) '\n' ;
        else
          v1 = ( int ) variable1.GetValue().charAt( 1 ) ;
        
        
        if ( variable2.GetValue().equals( "'\n'" ) )
          v2 = ( int ) '\n' ;
        else
          v2 = ( int ) variable2.GetValue().charAt( 1 ) ;
        
        int sum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          // System.exit( 65 ) ;
          G.sTypeError = true ;
          return ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          sum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          sum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          sum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          sum = v1 % v2 ;
        } // else if
      
        char ch1 = ( char ) sum ;
        
        String str = "" + ch1 ;
        
        str = "'" + str + "'" ;
        
        variable1.SetType( "int" ) ;
        variable1.SetValue( sum + "" ) ;
        
        
        return  ;
      } // if char char 
      
      else if ( variable2.GetType().equals( "int" ) ) {
        int v1 = -1 ;
        int v2 = -1 ;
      
        if ( variable1.GetValue().equals( "'\n'" ) )
          v1 = ( int ) '\n' ;
        else
          v1 = ( int ) variable1.GetValue().charAt( 1 ) ;
          
        v2 = G.StrToInt( variable2.GetValue() ) ;
        
        int sum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          sum = v1 + v2 ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          sum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          sum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          sum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          sum = v1 % v2 ;
        } // else if
      
        char ch1 = ( char ) sum ;
        
        String str = "" + ch1 ;
        
        str = "'" + str + "'" ;
        
        variable1.SetType( "int" ) ;
        
        // variable1.SetValue( str ) ;
        
        variable1.SetValue( sum + "" ) ;
        
        return  ;
      } // else if char int
      
      else if ( variable2.GetType().equals( "float" ) ) { 
        int v1 = -1 ;
        float v2 = -1 ;
      
        if ( variable1.GetValue().equals( "'\n'" ) )
          v1 = ( int ) '\n' ;
        else
          v1 = ( int ) variable1.GetValue().charAt( 1 ) ;
          
        v2 = StrToFloat( variable2.GetValue() ) ;
        
        float fsum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          fsum = v1 + v2 ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          fsum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          fsum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          fsum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          fsum = v1 % v2 ;
        } // else if
        
        int sum = ( int ) fsum ;
      
        char ch1 = ( char ) sum ;
        
        String str = "" + ch1 ;
        
        str = "'" + str + "'" ;
        
        variable1.SetValue( str ) ;
        
        
        return  ;
      } // else if char float
      
      else if ( variable2.GetType().equals( "bool" ) ) {
        G.sTypeError = true ;
        return  ;
      
      } // else if char bool
      
      else if ( variable2.GetType().equals( "string" ) ) {
        
        // G.Println( "ohohoohohohoohhhhh426" ) ;
        
        
        if ( variable1.mIsArray && !variable1.mIsElement ) {
          String str = "\"" ;
          
          for ( int i = 0 ; i < variable1.GetLength() ; i ++ ) {
            if ( variable1.mArrTable.get( i ).length() >= 3 ) {
              String elementStr = variable1.mArrTable.get( i ) ;
              str += elementStr.substring( 1, elementStr.length() - 1 ) ;
            } // if
            else {
              
              str += variable1.mArrTable.get( i ) ;
            } // else
          } // for
          
          str += variable2.GetValue().substring( 1, variable2.GetValue().length() ) ;
          
          variable1.SetValue( str ) ;
        } // if
        else if ( variable1.mIsArray && variable1.mIsElement ) {
          String str = "\"" ;
          if ( variable1.mArrTable.get( variable1.mIndex ).length() >= 3 ) {
            String elementStr = variable1.mArrTable.get( variable1.mIndex ) ;
            str += elementStr.substring( 1, elementStr.length() - 1 ) ;
          } // if
          else {
            str += variable1.mArrTable.get( variable1.mIndex ) ;
          } // else
          
          str += variable2.GetValue().substring( 1, variable2.GetValue().length() ) ;
          
          variable1.SetValue( str ) ;
        } // else if
        else {
          variable1.SetValue( "\"" + 
                              variable1.GetValue().substring( 1, variable1.GetValue().length() - 1 ) +
                              variable2.GetValue().substring( 1, variable2.GetValue().length() ) ) ;
        } // else 
                          
        // G.Println( "ohohoohohohoohhhhh431" ) ;
                            
        
        // variable1.StdPrint();
        
        
        
        while ( variable1.mIsArray && variable1.mArrTable.size() > 0 ) {
          variable1.mArrTable.remove( 0 ) ;
        } // while
        
        variable1.mIsArray = false ;
        variable1.mIsElement = false ;
        variable1.SetType( "string" ) ;
        
        // variable1.StdPrint();
        
        return  ;
      } // else if char string 
      
      else {
        System.exit( 11 ) ;
        return  ;
      } // else amazing type
      
      
    } // if variable1 == char
    
    if ( variable2.GetType().equals( "char" ) ) {
      if ( variable1.GetType().equals( "char" ) ) {
        int v1 = -1 ;
        int v2 = -1 ;
      
        if ( variable1.GetValue().equals( "'\n'" ) )
          v1 = ( int ) '\n' ;
        else
          v1 = ( int ) variable1.GetValue().charAt( 1 ) ;
        
        
        if ( variable2.GetValue().equals( "'\n'" ) )
          v2 = ( int ) '\n' ;
        else
          v2 = ( int ) variable2.GetValue().charAt( 1 ) ;
        
        int sum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          G.sTypeError = true ;
          return  ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          sum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          sum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          sum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          sum = v1 % v2 ;
        } // else if
      
        char ch1 = ( char ) sum ;
        
        String str = "" + ch1 ;
        
        str = "'" + str + "'" ;
        
        variable1.SetValue( str ) ;
        
        
        return  ;
      } // if char char 
      
      else if ( variable1.GetType().equals( "int" ) ) {
        int v1 = -1 ;
        int v2 = -1 ;
      
        if ( variable1.GetValue().equals( "'\n'" ) )
          v2 = ( int ) '\n' ;
        else
          v2 = ( int ) variable2.GetValue().charAt( 1 ) ;
          
        v1 = StrToInt( variable1.GetValue() ) ;
        
        int sum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          sum = v1 + v2 ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          sum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          sum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          sum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          sum = v1 % v2 ;
        } // else if
      
        
        
        variable1.SetValue( sum + "" ) ;
        
        
        return  ;
      } // else if int char
      
      else if ( variable1.GetType().equals( "float" ) ) { 
        float v1 = -1 ;
        int v2 = -1 ;
      
        v1 = StrToFloat( variable1.GetValue() ) ;
        
        if ( variable1.GetValue().equals( "'\n'" ) )
          v2 = ( int ) '\n' ;
        else
          v2 = ( int ) variable1.GetValue().charAt( 1 ) ;
          
        
        
        float fsum = 0 ;
        
        if ( ch.equals( "+" ) ) { 
          fsum = v1 + v2 ;
        } // if 
        else if ( ch.equals( "-" ) ) { 
          fsum = v1 - v2 ;
        } // if 
        else if ( ch.equals( "*" ) ) {
          fsum = v1 * v2 ;
        } // else if
        else if ( ch.equals( "/" ) ) {
          fsum = v1 / v2 ;
        } // else if
        else if ( ch.equals( "%" ) ) {
          fsum = v1 % v2 ;
        } // else if
        
        
        variable1.SetValue( fsum + "" ) ;
        
        
        return  ;
      } // else if float char
      
      else if ( variable1.GetType().equals( "bool" ) ) {
        G.sTypeError = true ;
        return  ;
      
      } // else if bool char
      
      else if ( variable1.GetType().equals( "string" ) ) { 
        
        // variable1.StdPrint();
        // variable2.StdPrint();
        
        if ( variable2.mIsArray && !variable2.mIsElement ) {
          String str = "" ;
          
          // G.Println( "OK?" ) ;
          
          str += variable1.GetValue().substring( 0, variable1.GetValue().length() - 1 ) ;
          
          // G.Println( "!" + str + "!" ) ;
          
          for ( int i = 0 ; i < variable2.GetLength() ; i ++ ) {
            if ( variable2.mArrTable.get( i ).length() >= 3 ) {
              String elementStr = variable2.mArrTable.get( i ) ;
              str += elementStr.substring( 1, elementStr.length() - 1 ) ;
            } // if
            else {
              str += variable2.mArrTable.get( i ) ;
            } // else
          } // for
          
          str += "\"" ;
          
          variable1.SetValue( str ) ;
        } // if
        else if ( variable2.mIsArray && variable2.mIsElement ) {
          String str = "" ;
          str += variable1.GetValue().substring( 0, variable1.GetValue().length() - 1 ) ;
          
          if ( variable2.mArrTable.get( variable2.mIndex ).length() >= 3 ) {
            String elementStr = variable2.mArrTable.get( variable2.mIndex ) ;
            str += elementStr.substring( 1, elementStr.length() - 1 ) ;
          } // if
          else {
            str += variable2.mArrTable.get( variable2.mIndex ) ;
          } // else
          
          str += "\"" ;
          
          variable1.SetValue( str ) ;
          
          
          
        } // else if
        else {
          variable1.SetValue( variable1.GetValue().substring( 0, variable1.GetValue().length() - 1 ) +
                              variable2.GetValue().substring( 1, variable2.GetValue().length() - 1 ) + 
                              "\"" ) ;
        } // else 
        
        
        
        

                          
        
        return  ;
      } // else if char string 
      
      else {
        System.exit( 12 ) ;
        return  ;
      } // else amazing type
      
    } // if variable2 == char 
    
    
    System.exit( 13 ) ;
    G.sTypeError = true ;
    return  ;
  } // ComputeCh() 
  
  static void ComputeStr( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !ch.equals( "+" ) ) {
      
      System.exit( 14 ) ;
      G.sTypeError = true ;
      return  ;
    } // if
        
        
    if ( variable1.GetType().equals( "string" ) ) {
      if ( variable2.GetType().equals( "string" ) ) {
        variable1.SetValue( variable1.GetValue().substring( 0, variable1.GetValue().length() -1 ) + 
                            variable2.GetValue().substring( 1, variable2.GetValue().length() ) ) ;
        return  ;
      } // if str + str 
      
      else if ( variable2.GetType().equals( "int" ) || 
                variable2.GetType().equals( "float" ) ||
                variable2.GetType().equals( "bool" ) ) {
        variable1.SetValue( variable1.GetValue().substring( 0, variable1.GetValue().length() -1 ) + 
                            variable2.GetValue() + "\"" ) ;
        return  ;
      } // else if str + int , str + float , str + bool ;
      
      else if ( variable2.GetType().equals( "char" ) ) {
        variable1.SetValue( variable1.GetValue().substring( 0, variable1.GetValue().length() -1 ) + 
                            variable2.GetValue().substring( 1, variable2.GetValue().length() - 1 ) + 
                            "\"" ) ;
                            
        return  ;
      } // else if str + char
      
      else {
        System.exit( 15 ) ;
        return  ;
      } // else amazing type
      
    } // if varaible1 == str  
    
    if ( variable2.GetType().equals( "string" ) ) {
      if ( variable1.GetType().equals( "string" ) ) {
        variable1.SetValue( variable1.GetValue().substring( 0, variable1.GetValue().length() -1 ) + 
                            variable2.GetValue().substring( 1, variable2.GetValue().length() ) ) ;
        return  ;
      } // if str + str 
      
      else if ( variable1.GetType().equals( "int" ) || 
                variable1.GetType().equals( "float" ) ||
                variable1.GetType().equals( "bool" ) ) {
        variable1.SetValue( "\"" + variable1.GetValue() + 
                            variable2.GetValue().substring( 1, variable2.GetValue().length() ) ) ;
        variable1.SetType( "string" ) ;
        return  ;
      } // else if int + str , float + str , bool + str
      
      else if ( variable1.GetType().equals( "char" ) ) {
        variable1.SetValue( "\"" + variable1.GetValue().substring( 0, variable1.GetValue().length() -1 ) + 
                            variable2.GetValue().substring( 1, variable2.GetValue().length() ) ) ;
                           
        variable1.SetType( "string" ) ;
        return  ;
      } // else if str + char
      
      else {
        System.exit( 16 ) ;
        return  ;
      } // else amazing type
      
    } // if varaible2 == str  
    
    System.exit( 17 ) ;
    G.sTypeError = true ;
    return  ;
  } // ComputeStr()
  
  // Compute val
  static void Compute( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !ch.equals( "+" ) && 
         !ch.equals( "-" ) &&
         !ch.equals( "*" ) && 
         !ch.equals( "/" ) && 
         !ch.equals( "%" ) ) {

      ComputeBool( variable1, ch, variable2 ) ;
      return ;
    } // if
    
    // G.Println( "!!672   " + variable1.GetValue() + "   " + variable2.GetValue() ) ;
    
    if ( ch.equals( "+" ) ) sSymbol = 101 ;
    if ( ch.equals( "-" ) ) sSymbol = 102 ;
    if ( ch.equals( "*" ) ) sSymbol = 103 ;
    if ( ch.equals( "/" ) ) sSymbol = 104 ;
    if ( ch.equals( "%" ) ) sSymbol = 105 ;
    
    if ( variable1.GetType().equals( "int" ) ) sV1 = 10 ;
    if ( variable1.GetType().equals( "float" ) ) sV1 = 20 ;
    if ( variable1.GetType().equals( "char" ) ) sV1 = 30 ;
    if ( variable1.GetType().equals( "string" ) ) sV1 = 40 ;
    if ( variable1.GetType().equals( "bool" ) ) sV1 = 50 ;
    
    if ( variable2.GetType().equals( "int" ) ) sV2 = 1 ;
    if ( variable2.GetType().equals( "float" ) ) sV2 = 2 ;
    if ( variable2.GetType().equals( "char" ) ) sV2 = 3 ;
    if ( variable2.GetType().equals( "string" ) ) sV2 = 4 ;
    if ( variable2.GetType().equals( "bool" ) ) sV2 = 5 ;
    
    // G.Println( "696 so hard" ) ;
    // variable1.StdPrint();
    // variable2.StdPrint();
    
    
    if ( variable1.GetType().equals( "char" ) || variable2.GetType().equals( "char" ) ) {
      // G.Println( "696 so hard" ) ;
      ComputeCh( variable1, ch, variable2 ) ;
      // G.Println( "696 so bitch error hard" ) ;
      return ;
    } // if
    
    
    
    
    if ( variable1.GetType().equals( "string" ) || variable2.GetType().equals( "string" ) ) {
      ComputeStr( variable1, ch, variable2 ) ;
      return  ;
    } // if
    
    
    if ( variable1.GetType().equals( "bool" ) || variable2.GetType().equals( "bool" ) ) {
      G.sTypeError = true ;
      return  ;
    } // if 
    
    
    
    
    
    
    
    if ( variable1.GetType().equals( "int" ) && // int ( +,-,*,/ ) int  is int
         variable2.GetType().equals( "int" ) ) {
      int str1Value = StrToInt( variable1.GetValue() ) ;
      int str2Value = StrToInt( variable2.GetValue() ) ;
      int sum = 0 ;
      if ( ch.equals( "+" ) ) {
        sum = str1Value + str2Value ;
      } // if
      else if ( ch.equals( "-" ) ) { 
        sum = str1Value - str2Value ;
      } // else if 
      else if ( ch.equals( "*" ) ) {
        sum = str1Value * str2Value ;
      } // else if
      else if ( ch.equals( "/" ) ) {
        sum = str1Value / str2Value ;
      } // else if
      else if ( ch.equals( "%" ) ) {
        sum = str1Value % str2Value ;
      } // else if
      
      variable1.SetValue( sum + "" ) ;
      
      return  ;
    } // if
    
    float str1Value = StrToFloat( variable1.GetValue() ) ; // if val or val1 is float,  it must return float 
    float str2Value = StrToFloat( variable2.GetValue() ) ;
    float sum = 0 ;
    
    if ( ch.equals( "+" ) ) {
      sum = str1Value + str2Value ;
    } // if
    else if ( ch.equals( "-" ) ) { 
      sum = str1Value - str2Value ;
    } // else if 
    else if ( ch.equals( "*" ) ) {
      sum = str1Value * str2Value ;
    } // else if
    else if ( ch.equals( "/" ) ) {
      sum = str1Value / str2Value ;
    } // else if
    else if ( ch.equals( "%" ) ) {
      sum = str1Value % str2Value ;
    } // else if
    
    variable1.SetType( "float" ) ;
    variable1.SetValue( sum + "" ) ;
    
    
    return  ;
    
    
    
  } // Compute()
    
  // Compute boolean
  static void ComputeStrBool( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !variable1.GetType().equals( "string" ) ||  !variable2.GetType().equals( "string" ) ) {
      G.sTypeError = true ;
      return  ;
    } // if
    
    // G.Println( "==>" ) ; 
    // variable1.StdPrint() ;
    // variable2.StdPrint() ;
    
    boolean result = false ;
    
    if ( ch.equals( ">" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) > 0 ) 
        result = true ;
    } // if 
    else if ( ch.equals( "<" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) < 0 ) 
        result = true ;
    } // if 
    else if ( ch.equals( "==" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) == 0 ) 
        result = true ;
    } // if 
    else if ( ch.equals( "!=" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) != 0 ) 
        result = true ;
    } // if 
    else if ( ch.equals( ">=" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) >= 0 ) 
        result = true ;
    } // if 
    else if ( ch.equals( "<=" ) ) {
      if ( variable1.GetValue().compareTo( variable2.GetValue() ) <= 0 ) 
        result = true ;
    } // if 
    
    variable1.SetType( "bool" ) ;
    variable1.SetValue( "" + result ) ;
    
    return  ;
  } // ComputeStrBool()
  
  /*
  static Variable ComputeChBool( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    
    
    if ( variable1.GetType().equals( "string" ) ||  variable2.GetType().equals( "string" ) ) {
     
      G.sTypeError = true ;
      return variable1 ;
     
    } // if
    
    
    if ( variable1.GetType().equals( "char" ) && 
         ( variable2.GetType().equals( "int" ) || ( variable2.GetType().equals( "float" ) ) ) ) {
      int str1Value = ( int ) variable1.GetValue().charAt( 1 ) ;
      float str2Value = StrToFloat( variable2.GetValue() ) ;
      
    
      boolean result = false ; // initial 
    
      if ( ch.equals( ">" ) ) result = ( str1Value > str2Value ) ;
      else if ( ch.equals( ">=" ) ) result = ( str1Value >= str2Value ) ;
      else if ( ch.equals( "<" ) ) result = ( str1Value < str2Value ) ;
      else if ( ch.equals( "<=" ) ) result = ( str1Value <= str2Value ) ;
      else if ( ch.equals( "<>" ) ) result = ( str1Value != str2Value ) ;
      else if ( ch.equals( "==" ) ) result = ( str1Value == str2Value ) ;
      else if ( ch.equals( "!=" ) ) result = ( str1Value != str2Value ) ;
      
      variable1.SetType( "bool" ) ;
      variable1.SetValue( "" + result ) ;
      
    } // if
        
    if ( variable2.GetType().equals( "char" ) && 
         ( variable1.GetType().equals( "int" ) || ( variable1.GetType().equals( "float" ) ) ) ) {
      int str2Value = ( int ) variable2.GetValue().charAt( 1 ) ;
      float str1Value = StrToFloat( variable1.GetValue() ) ;
      
    
      boolean result = false ; // initial 
    
      if ( ch.equals( ">" ) ) result = ( str1Value > str2Value ) ;
      else if ( ch.equals( ">=" ) ) result = ( str1Value >= str2Value ) ;
      else if ( ch.equals( "<" ) ) result = ( str1Value < str2Value ) ;
      else if ( ch.equals( "<=" ) ) result = ( str1Value <= str2Value ) ;
      else if ( ch.equals( "<>" ) ) result = ( str1Value != str2Value ) ;
      else if ( ch.equals( "==" ) ) result = ( str1Value == str2Value ) ;
      else if ( ch.equals( "!=" ) ) result = ( str1Value != str2Value ) ;
      
      variable1.SetType( "bool" ) ;
      variable1.SetValue( "" + result ) ;
      
    } // if
        
        
    return variable1 ;
  } // ComputeChBool()
  */
  
  static void ComputeBool( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !BoolBoolean( ch ) ) {
      ComputeLogic( variable1, ch, variable2 ) ;
      return ;
    } // if
    
    sSymbol = 102 ;
    
    if ( variable1.GetType().equals( "bool" ) ||  variable2.GetType().equals( "bool" ) ) { 
      G.sTypeError = true ;
      return  ;
    } // if 
    
    if ( variable1.GetType().equals( "string" ) ||  variable2.GetType().equals( "string" ) ) { 
      ComputeStrBool( variable1, ch, variable2 ) ;
      return ;
    } // if
    
    
    float v1 = -1 ;
    float v2 = -1 ;
    boolean result = false ;
    
    if ( variable1.GetType().equals( "int" ) ) v1 = StrToFloat( variable1.GetValue() ) ;
    if ( variable1.GetType().equals( "float" ) ) v1 = StrToFloat( variable1.GetValue() ) ;
    if ( variable1.GetType().equals( "char" ) ) {
      if ( variable1.GetValue().equals( "'\n'" ) ) v1 = ( float ) '\n' ;
      else v1 = ( float ) variable1.GetValue().charAt( 1 ) ;
    } // if
    
    if ( variable2.GetType().equals( "int" ) ) v2 = StrToFloat( variable2.GetValue() ) ;
    if ( variable2.GetType().equals( "float" ) ) v2 = StrToFloat( variable2.GetValue() ) ;
    if ( variable2.GetType().equals( "char" ) ) {
      if ( variable2.GetValue().equals( "'\n'" ) ) v2 = ( float ) '\n' ;
      else v2 = ( float ) variable2.GetValue().charAt( 1 ) ;
    } // if
    
    if ( ch.equals( ">" ) ) result = ( v1 > v2 ) ;
    else if ( ch.equals( ">=" ) ) result = ( v1 >= v2 ) ;
    else if ( ch.equals( "<" ) ) result = ( v1 < v2 ) ;
    else if ( ch.equals( "<=" ) ) result = ( v1 <= v2 ) ;
    else if ( ch.equals( "==" ) ) result = ( v1 == v2 ) ;
    else if ( ch.equals( "!=" ) ) result = ( v1 != v2 ) ;
    
    variable1.SetType( "bool" ) ;
    variable1.SetValue( "" + result ) ;
    
    
    return  ;
    
  } // ComputeBool()
    
  // Compute boolean
  
  // Compute logic
  
  static void ComputeLogic( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !ch.equals( "&&" ) && !ch.equals( "||" ) ) {
      ComputeBitLogic( variable1, ch, variable2 ) ;
      return ;
    } // if
    
    sSymbol = 103 ;
    // variable1.StdPrint() ;
    // variable2.StdPrint() ;
    
    if ( !variable1.GetType().equals( "bool" ) || !variable2.GetType().equals( "bool" ) ) {
        
      G.sTypeError = true ;
      return  ;
    } // if 
    
    
    
    boolean str1Value = StrToBool( variable1.GetValue() ); 
    boolean str2Value = StrToBool( variable2.GetValue() ); 
    
    boolean result = false ; // initial 
    
    if ( ch.equals( "&&" ) ) result = ( str1Value && str2Value ) ;
    else if ( ch.equals( "||" ) ) result = ( str1Value || str2Value ) ;
    else {
      G.Println( "str1:" + variable1.GetValue() + " ch:" + ch + 
                 "str2:" + variable2.GetValue() + " error 373!" ) ;
      
      return  ;
    } // else 
    
    variable1.SetType( "bool" ) ;
    variable1.SetValue( result + "" ) ;
    return  ;
    
    
  } // ComputeLogic()
    
  // Compute logic
  
  // Compute bit logic
  
  static void ComputeBitLogic( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !ch.equals( "&" ) && !ch.equals( "|" ) && !ch.equals( "^" ) ) {
      ComputeShift( variable1, ch, variable2 ) ;
      return ;
    } // if
    
    
    
    
    return  ;
    
    
  } // ComputeBitLogic()
    
  // Compute Shift
  
  static void ComputeShift( Variable variable1, String ch, Variable variable2 ) throws Throwable {
    if ( !ch.equals( ">>" ) && !ch.equals( "<<" ) ) {
        
      return  ;
    } // if
    
    
    
    
    return  ;

  } // ComputeShift()
  
} // class G()

class PassValue {
  public boolean mRealStatement ;
  
  public PassValue( boolean bool ) {
    // G.Println( "1011" + "some one new me" ) ;
    mRealStatement = bool ;
  } // PassValue()
  
  public PassValue( ) {
    this( true ) ;
  } // PassValue()
  
} // class PassValue


class Str_obj {
  private String mstr ;
  
  public Str_obj( ) {
    mstr = "" ;
  } // Str_obj()
  
  public Str_obj( String str ) {
    mstr = new String( str );
  } // Str_obj()
  
  public String Get() {
    return mstr ;
  } // Get()
  
  public void Set( String str ) {
    mstr = new String( str ) ;
  } // Set()
  
} // class Str_obj



// easy table defind ///////////////////////////////////////////////
class Table {
  private Vector < String > mTable ;
  
  public Table() {
    mTable = new Vector < String >();
  } // Table()
  
  
  void Print() {
    G.Println( "=====" + this.getClass().getName() + "=========" ) ;
    for ( int i = 0 ; i < mTable.size() ; i ++ )
      G.Println( mTable.get( i ) ) ;
      
    G.Println( "===========end===============" ) ;
  } // Print()
  
  
  public int IndexOf( String str ) {
    int index = -1 ;
    
    for ( int i = 0 ; i < mTable.size() ; i++ ) {
      if ( str.equals( mTable.get( i ) ) ) {
        
        return i;
        
      } // if
    } // for
        
    return index ;
  } // IndexOf()
  
  public boolean Find( String str ) {
        
    for ( int i = 0 ; i < mTable.size() ; i++ ) {
      if ( str.equals( mTable.get( i ) ) ) {
        
        return true;
        
      } // if
    } // for
        
    return false ;
  } // Find()
  
  
  public void Add( String str ) {
    mTable.add( str ) ;
  } // Add()
  
  public String Get( int index ) {
    if ( index == -1 ) return "" ;
        
    return mTable.get( index ) ;
  } // Get()
  
} // class Table 

class DeclareWord extends Table {
        
        
  public DeclareWord() {
    super();
    Add( "int" ) ; 
    Add( "float" ) ; 
    Add( "char" ) ; 
    Add( "bool" ) ; 
    Add( "string" ) ; 
    Add( "void" ) ; 
    
    
  } // DeclareWord()
} // class DeclareWord()


class PreserveWord extends DeclareWord {
        
        
  public PreserveWord() {
    super();
    Add( "if" ) ; 
    Add( "else" ) ;
    Add( "while" ) ;
    Add( "do" ) ;
    Add( "return" ) ;
    Add( "cin" ) ;
    Add( "cout" ) ;
    
    
  } // PreserveWord()
} // class PreserveWord()


class SystemFunctionTable extends Table {
        
  // private Vector < String > mTable ;
        
  public SystemFunctionTable() {
    super();
    Add( "ListAllVariables" ) ; 
    Add( "ListAllFunctions" ) ; 
    Add( "ListVariable" ) ; 
    Add( "ListFunction" ) ; 
    Add( "Done" ) ; 
    Add( "cout" ) ; 
    Add( "cin" ) ; 
    
  } // SystemFunctionTable()
  
  
  
  
} // class SystemFunctionTable()


class Delimiter extends Table {
  public Delimiter() {
    super();
    Add( ";" ) ; 
    Add( "(" ) ; 
    Add( ")" ) ; 
    Add( "[" ) ;
    Add( "]" ) ;
    Add( "{" ) ; 
    Add( "}" ) ; 
    Add( "+" ) ; 
    Add( "-" ) ; 
    Add( "*" ) ;
    Add( "/" ) ;
    Add( "%" ) ;
    Add( ">" ) ;
    Add( "<" ) ;
    Add( ">=" ) ;
    Add( "<=" ) ;
    Add( "==" ) ;
    Add( "!=" ) ;
    Add( "&" ) ;
    Add( "^" ) ;
    Add( "|" ) ;
    Add( "=" ) ;
    Add( "||" ) ;
    Add( "&&" ) ;
    Add( "+=" ) ;
    Add( "-=" ) ;
    Add( "*=" ) ;
    Add( "/=" ) ;
    Add( "%=" ) ;
    Add( "++" ) ;
    Add( "--" ) ;
    Add( "<<" ) ;
    Add( ">>" ) ;
    Add( "," ) ;
    Add( "?" ) ;
    Add( ":" ) ;
    Add( "\"" ) ;
    Add( "!" ) ;
    
  } // Delimiter()
  
} // class Delimiter()

// easy table defind  end ///////////////////////////////////////////////


class FunctionTable {
  public Vector < Function > mTable ;
  
  public FunctionTable() {
    mTable = new Vector < Function >();
  } // FunctionTable()
  
  
  public void StdPrint() throws Throwable {
        
    Sort();
    for ( int i = 0 ; i < mTable.size() ; i ++ )
      G.Println(  mTable.get( i ).GetName() + "()" ) ;
  } // StdPrint()
  
  public void Sort() throws Exception {
    try {
      Function temp = null ; 
      for ( int j = 0 ;  j < ( mTable.size() - 1 ) ; j ++ ) {
        for ( int i = 0 ; i < mTable.size() - 1 ; i ++ ) {
          if ( mTable.get( i ).GetName().compareTo( mTable.get( i + 1 ).GetName() ) > 0 ) {
            temp = mTable.get( i ) ;
            mTable.set( i, mTable.get( i + 1 ) ) ;
            mTable.set( i + 1, temp ) ;
          } // if
        
        } // for 
      } // for
    } catch ( Exception e ) {
      G.Println( "Sorting error error782!" ) ;
    } // catch
  } // Sort()
  
  
  public void Add( String str ) throws Throwable {
        
    Text text = new Text( str ) ;
    
    text.GetToken();
    String name = text.GetToken() ;
     
    if ( Find( name ) ) {
      mTable.remove( IndexOf( name ) ) ;
    } // if
    
    text = null ;
    System.gc();
    // I should put some check code to prevent overrid or over load ///////////////////// <== Attention! 
    mTable.add( new Function( "int", "add", 0 ) ) ;
  } // Add()
  
  public void Add( String type, String name ) throws Throwable {
    if ( Find( name ) ) {
      mTable.remove( IndexOf( name ) ) ;
    } // if
    
    // I should put some check code to prevent overrid or over load ///////////////////// <== Attention! 
    mTable.add( new Function( "int", "add", 0 ) ) ;
  } // Add()
  
  public int IndexOf( String str ) {
    int index = -1 ;
    
    for ( int i = 0 ; i < mTable.size() ; i++ ) {
      if ( str.equals( mTable.get( i ).GetName() ) ) {
        
        return i;
        
      } // if
    } // for
        
    return index ;
  } // IndexOf()
  
  public int IndexOf( String str, int begin, int end ) {
    int index = -1 ;
    
    for ( int i = begin ; i < end ; i++ ) {
      if ( str.equals( mTable.get( i ).GetName() ) ) {
        
        return i;
        
      } // if
    } // for
        
    return index ;
  } // IndexOf()
  
  
  public boolean Find( String name ) {
        
    for ( int i = 0 ; i < mTable.size() ; i++ ) {
      if ( name.equals( mTable.get( i ).GetName() ) ) {
        
        return true;
        
      } // if
    } // for
        
    return false ;
  } // Find()
  
  public boolean Find( String name, int begin, int end ) {
        
    for ( int i = begin ; i < end ; i++ ) {
      if ( name.equals( mTable.get( i ).GetName() ) ) {
        
        return true;
        
      } // if
    } // for
        
    return false ;
  } // Find()
  
  
  
  public Function Get( int index ) {
    if ( index == -1 ) return null ;
        
    return mTable.get( index ) ;
  } // Get()
  
  
} // class FunctionTable()






// Text ///////////////////////////////////////////////////////////////////

class Function {
  public String mName ;
  public String mType ;
  public int mIndex ;
  public int mEndIndex ;
  // private String mtext ; 

  
  public Function( String type, String name, int index ) throws Throwable {
    
    mType = type ;
    mName = name ;
    mIndex = index ;
    mEndIndex = 0 ;
    
  } // Function()
  
  /*
  public void StdPrint() throws Throwable {
    try {
        
      // G.Println( "11" + GetText() + "22" ) ;
        
      boolean meetcom = false ;
      int spacecount = 0 ;
      Text text = new Text( GetText() ) ;
    
      // String token = text.PeekToken() ;
      G.Print( text.GetToken() ) ;
      while ( text.GetText().length() != 0 ) {
        String token = text.GetToken() ;
        // G.Println( token ) ;
        
        String peek = text.PeekToken() ;
        if ( token.equals( "}" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "[" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "," ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "++" ) || token.equals( "--" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "(" ) && !meetcom ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "(" ) && meetcom ) {
          G.Print( " " + token ) ;
          meetcom = false ;
        } // if
        else if ( !token.equals( "(" ) ) {
          G.Print( " " + token ) ;
        } // if
        
        if ( token.equals( "if" ) || 
             token.equals( "while" ) ) {
          meetcom = true ;
        } // if
        
        if ( token.equals( "{" ) && !peek.equals( "}" ) ) {
          spacecount += 2 ;
          G.Println( "" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // if
        else if ( token.equals( ";" ) && !peek.equals( "}" ) ) {
          // spacecount += 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // if
        else if ( token.equals( ";" ) && peek.equals( "}" ) ) {
          spacecount -= 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount ) ;
        } // else if 
        else if ( token.equals( "}" ) && peek.equals( "}" ) ) {
          spacecount -= 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount ) ;
        } // else if 
        else if ( token.equals( "}" ) && !peek.equals( "}" ) ) {
          spacecount -= 0 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // else if 
      
        // should command
        if ( !token.equals( "}" ) )
          G.Print( token ) ;
        if ( token.equals( ";" ) ) {
          G.Print( "\n" ) ;
        } // if
        
        token = text.PeekToken() ;
        if ( !token.equals( "(" ) ) G.Print( " " ) ;
        // should command
      } // while
      
      // G.Println( "" );
    
    } catch ( Exception e ) {
      G.Println( "stdPrint Error! error 893" ) ;
    } // catch
  } // StdPrint()
  */
  
  public String GetType() {
    return mType ;
  } // GetType()
  
  public String GetName() {
    return mName ;
  } // GetName()
  
  public int GetIndex() {
    return mIndex ;
  } // GetIndex()
  
} // class Function() extends Text ()




// Variable //////////////////////////////////////////////////////////

class VariableTalbe {
  
  final static int IS_ARRAY = 56479 ; // if set variable is an array
        
  public Vector < Variable > mTable ;
  
  public VariableTalbe() {
    mTable = new Vector < Variable >();
    
  } // VariableTalbe()
  
  public void StdPrint() throws Throwable {
        
    Sort();
    for ( int i = 0 ; i < mTable.size() ; i ++ )
      G.Println(  mTable.get( i ).GetName() ) ;
  } // StdPrint()
  
  public void Sort() throws Exception {
    try {
      Variable temp = null ; 
      for ( int j = 0 ;  j < ( mTable.size() - 1 ) ; j ++ ) {
        for ( int i = 0 ; i < mTable.size() - 1 ; i ++ ) {
          if ( mTable.get( i ).GetName().compareTo( mTable.get( i + 1 ).GetName() ) > 0 ) {
            temp = mTable.get( i ) ;
            mTable.set( i, mTable.get( i + 1 ) ) ;
            mTable.set( i + 1, temp ) ;
          } // if
        
        } // for 
      } // for
    } catch ( Exception e ) {
      G.Println( "Sorting error error543!" ) ;
    } // catch
  } // Sort()
  
  void Print() {
    G.Println( "=====" + this.getClass().getName() + "=========" ) ;
    for ( int i = 0 ; i < mTable.size() ; i ++ )
      mTable.get( i ).StdPrint() ;
      
    G.Println( "===========end===============" ) ;
  } // Print()
  
  
  public int IndexOf( String str ) throws Throwable {
    try {
    // int index = -1 ;
    // boolean find = false ;
    
      if ( !G.sDealScope ) {
        for ( int i = mTable.size() - 1 ; i >= 0 ; i-- ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return i;
        
          } // if
        } // for
      } // if
    
      if ( G.sFuncVarIndex != -1 ) {
        for ( int i = G.sFuncVarIndex ; i < mTable.size() ; i ++ ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return i;
        
          } // if
        } // for
      } // if
    
    
      for ( int i = 0 ; i < G.sVarLength ; i ++ ) {
        if ( str.equals( mTable.get( i ).GetName() ) ) {
        
          return i;
        
        } // if
      } // for
    
    
        
      return -1 ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "VariableTable.IndexOf error 1526" ) ;
      
      return -1 ;
    } // catch()
  } // IndexOf()
  
  public int IndexOf( String str, int begin, int end ) throws Throwable {
    try {
    // int index = -1 ;
    // boolean find = false ;
    
      if ( !G.sDealScope ) {
        for ( int i = end - 1 ; i >= begin ; i-- ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return i;
        
          } // if
        } // for
      } // if
    
      
    
    
        
      return -1 ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "VariableTable.IndexOf error 1526" ) ;
      
      return -1 ;
    } // catch()
  } // IndexOf()
  
  
  
  public boolean Find( String str ) throws Throwable {
    try {
    
      if ( !G.sDealScope ) {
        for ( int i = 0 ; i < mTable.size() ; i++ ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return true;
        
          } // if
        } // for
      } // if
    
      // G.Println( str ) ;
      // G.Println( G.sFuncVarIndex + "" ) ;
      // G.Println( G.sVarLength + "" ) ;
      // Print();
    
      if ( G.sFuncVarIndex != -1 ) {
        for ( int i = G.sFuncVarIndex ; i < mTable.size() ; i ++ ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return true;
        
          } // if
        } // for
      } // if 
    
    
    
      for ( int i = 0 ; i < G.sVarLength ; i ++ ) {
        if ( str.equals( mTable.get( i ).GetName() ) ) {
        
          return true;
        
        } // if
      } // for
    
        
      return false ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "VariableTable.Find error 1565" ) ;
      
      return false ;
    } // catch()
  } // Find()
  
  public boolean Find( String str, int begin, int end ) throws Throwable {
    try {
    
      if ( !G.sDealScope ) {
        for ( int i = begin ; i < end ; i++ ) {
          if ( str.equals( mTable.get( i ).GetName() ) ) {
        
            return true;
        
          } // if
        } // for
      } // if
    
      // G.Println( str ) ;
      // G.Println( G.sFuncVarIndex + "" ) ;
      // G.Println( G.sVarLength + "" ) ;
      // Print();
    
      
    
        
      return false ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "VariableTable.Find error 1565" ) ;
      
      return false ;
    } // catch()
  } // Find()
  
  
  public void Add( String type, String name, String value ) throws Throwable {
    /*
    if ( Find( name ) ) {
      mTable.remove( IndexOf( name ) ) ;
    } // if
    */
    
    mTable.add( new Variable( type, name, value ) ) ;
  } // Add()
  
  public void Add( String type, String name ) {
    /*
    if ( Find( name ) ) {
      mTable.remove( IndexOf( name ) ) ;
    } // if
    */
    
    mTable.add( new Variable( type, name ) ) ;
  } // Add()
  
  public void Add( String type, String name, int isArray, String length ) throws Throwable {
    try {
      // 
      // 
      // 
      
      
      mTable.add( new Variable( type, name, true, Integer.parseInt( length ) ) ); 
        // if it can't parseInt it must error
      
    } catch ( Exception e ) {
        
      G.Println( type + " " + name + " " + length + "!" ) ;
      
      G.Println( "Set Array Error error 604!" ) ;
    } // catch
  } // Add()
  
  
  public Variable Get( int index ) {
    if ( index == -1 ) return null ;
        
    return mTable.get( index ) ;
  } // Get()
  
  
} // class VariableTalbe()

class Variable {
        
  public String mPassSign ;
        
  private String mType ;
  private String mName ;
  private String mValue ;
  private int mLength ;
  public boolean mIsArray ;
  
  public boolean mIsRef ;
  
  public int mIndex ;
  public boolean mIsElement ;
  public boolean mCout ;
  
  static public int sV1 = 0 ;
  
  final static int NUM = 4658 ;
  final static  int STR = 4444 ;
  
  public Vector < String > mArrTable ;
  

  
  
  public Variable( Variable variable ) {
    mType = variable.GetType();
    mName = variable.GetName();
    mValue = variable.GetValue();
    // mIndex = variable.GetIndex();
    
    if ( variable.mIsArray ) {
    
      mLength = variable.GetLength();
      mArrTable = new Vector < String >() ;
      mIsArray = true ;
    
      for ( int i = 0 ; i < variable.GetLength() ; i++ ) {
        mArrTable.add( variable.mArrTable.get( i ) ) ;
      } // for
      
      // mIndex = variable.GetIndex();
    } // if
    
    if ( variable.mIsElement ) {
      mIsElement = variable.mIsElement ;
      mIndex = variable.mIndex ;
      
      // mIndex = variable.GetIndex();
    } // if
  } // Variable()
  
  
  public Variable() {
    this( "", "", "unset" ) ;
  } // Variable()
  
  
  public Variable( String type, String name ) {
    this( type, name, "unset" ) ;
  } // Variable()
  
  
  
  public Variable( String type, String name, String value ) {
    /*
    if ( !G.sDeclareWord.Find( type ) || 
         type.equals( "void" ) ) {

      G.Println( "Error type:" + type + " error 419!" );
      return ;
    } // if
    */
    
    sV1 ++ ;
    
    mType = new String( type ) ;
    mName = new String( name ) ;
    mValue = new String( value ) ;
    mIsArray = false ;
    mPassSign = "-1" ;
    mCout = false ;
    mIsRef = false ;
    mIsElement = false ;
  } // Variable()
  
  public Variable( String type, String name, boolean isArray, int length ) {
    if ( !isArray ) {
      mType = new String( type ) ;
      mName = new String( name ) ;
      return ;
    } // if 
    
    mType = new String( type ) ;
    mName = new String( name ) ;
    mIsArray = true ;
    mLength = length ;
    mPassSign = "-1" ;
    mArrTable = new Vector < String >() ;
    
    for ( int i = 0 ; i < length ; i++ ) {
      mArrTable.add( "" ) ;
    } // for
    
    mIsElement = false ;
    // StdPrint() ; //                       
  } // Variable()
  
  
  
  static public boolean TypeCheck( int type, Variable variable ) {
    if ( type == Variable.STR ) {
      if ( variable.GetType().equals( "string" ) || variable.GetType().equals( "char" ) ) return true ;
      return false ;
    } // if STR case
    
    if ( type == Variable.NUM ) {
      if ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) return true ;
      return false ;
    } // if STR case
    
    return false ; // another case must error 
  } // TypeCheck()
  
  
  
  public void StdPrint() {
    if ( mIsRef )
      G.Print( "& " ) ;
    if ( !mIsArray )
      G.Println( mType + " " + mName + " " + mValue + " ;" ) ;
    else {
      
      G.Print( mType + " " + mName + "[ " + mLength + " ] " ) ;
      
      if ( mIsElement )
        G.Print( "is element at[" + mIndex + "]: " + mArrTable.get( mIndex ) + "" ) ;
        
      G.Println( ";" ) ;
    } // else
    
  } // StdPrint()
  
  
  public String GetType() {
    return mType ;
  } // GetType()
  
  public String GetName() {
    return mName ;
  } // GetName()
  
  public String GetValue() {
    return mValue ;
  } // GetValue()
  
  public int GetIndex() {
    return mIndex ;
  } // GetIndex()
  
  public int GetLength() {
    return mLength ;
  } // GetLength()
  
  public void Set( Variable variable ) {
    mType = variable.GetType();
    mName = variable.GetName();
    mValue = variable.GetValue();
    
    
    // G.Println( "yo?" + variable.mIsArray ) ;
    
    if ( variable.mIsArray ) {
    
      mLength = variable.GetLength();
      mArrTable = new Vector < String >() ;
      mIsArray = true ;
    
      for ( int i = 0 ; i < variable.GetLength() ; i++ ) {
        mArrTable.add( variable.mArrTable.get( i ) ) ;
      } // for
    
    } // if
    
    if ( variable.mIsElement ) {
      mIsElement = variable.mIsElement ;
      mIndex = variable.mIndex ;
      
      // mIndex = variable.GetIndex();
    } // if
    
  } // Set()
  
  
  public void SetType( String str ) {
    mType = new String( str ) ;
  } // SetType()
  
  public void SetName( String str ) {
    mName = new String( str ) ;
  } // SetName()
  
  public void SetValue( String str ) {
    mValue = new String( str ) ;
  } // SetValue()
  
  public void ChangeSign() {
    if ( mValue.charAt( 0 ) == '-' ) mValue = mValue.substring( 1, mValue.length() ) ;
    else if ( mValue.charAt( 0 ) == '+' ) mValue = "-" + mValue.substring( 1, mValue.length() ) ;
    else mValue = "-" + mValue ;
  } // ChangeSign()
  
  public void ChangeBool() {
    if ( mType.equals( "bool" ) ) {
      if ( mValue.equals( "true" ) ) mValue = "false" ;
      else if ( mValue.equals( "false" ) ) mValue = "true" ;
    } // if
  } // ChangeBool()
  
} // class Variable()
/*

class ValueObj {
  private String mType ;
  private String mValue ;
  private boolean mIsArray ;
  
  public ValueObj( String type ) {
    this( type, "unset", false ) ;
  } // ValueObj()
  
  
  
  public ValueObj( String type, String value, boolean isArray ) {
    if ( !G.sDeclareWord.Find( type ) || 
         type.equals( "void" ) ) {

      G.Println( "Error type:" + type + " error 419!" );
      return ;
    } // if
    
    mType = new String( type ) ;
    mValue = new String( value ) ;
    mIsArray = isArray ;
  } // ValueObj()
  
  public String GetType() {
    return mType ;
  } // GetType()
  
  public String GetValue() {
    return mValue ;
  } // GetValue()
  
} // class ValueObj

*/
// Variable end ///////////////////////////////////////////////////////













class Token{
  String mToken;
  int mLine;
  
  public Token( String str, int line ) {
    mToken = str ;
    mLine = line ;
  } // Token()
  
  public void Print() {
    G.Println( mToken + " " + mLine ) ;
  } // Print()
  
  public String GetToken() {
    return mToken ;
  } // GetToken()
  
} // class Token()


class Line {
  public String mstr ;
  
  public Line( String str ) {
    mstr = str ;
  } // Line()
  
  String GetSymbol() throws Throwable {
    String token = Getch() ;
    
    String ch = Peekch() ;
    if ( ch.equals( "" ) ||  // if the char after this char is white space or EOF ,return this char ;
         G.BoolWhiteSpace( ch ) ) return token ;
    
    if ( ( token.equals( "!" ) && ch.equals( "=" ) ) || 
         ( token.equals( ">" ) && ch.equals( "=" ) ) ||
         ( token.equals( "<" ) && ch.equals( "=" ) ) ||
         ( token.equals( "=" ) && ch.equals( "=" ) ) ||
         ( token.equals( "+" ) && ch.equals( "=" ) ) ||
         ( token.equals( "-" ) && ch.equals( "=" ) ) ||
         ( token.equals( "*" ) && ch.equals( "=" ) ) ||
         ( token.equals( "/" ) && ch.equals( "=" ) ) || 
         ( token.equals( "%" ) && ch.equals( "=" ) ) ||
         ( token.equals( ">" ) && ch.equals( ">" ) ) ||
         ( token.equals( "<" ) && ch.equals( "<" ) ) ||
         ( token.equals( "+" ) && ch.equals( "+" ) ) ||
         ( token.equals( "-" ) && ch.equals( "-" ) ) ||
         ( token.equals( "&" ) && ch.equals( "&" ) ) ||
         ( token.equals( "|" ) && ch.equals( "|" ) ) ) {
      token += Getch();
      return token ;
    } // if 
    
    return token ;
  } // GetSymbol()
  
  String GetFloat() throws Throwable {
    String token = Getch() ;
    
    String ch = Peekch();
    
    while ( G.BoolNumber( ch ) ) {
      token += ch ;
      Getch();
      ch = Peekch();
    } // while
    
    return token ;
  } // GetFloat()
  
  String GetNum() throws Throwable { 
    // return 123 or 123.456 
    // I can not reutrn .123 
    // I have GetFloat to deal with .123 case
    String token = Getch() ;
    String ch = Peekch();
    
    while ( G.BoolNumber( ch ) ) { // 123
      token += ch ;
      Getch();
      ch = Peekch();
    } // while
    
    if ( !ch.equals( "." ) ) return token ;
    
    token += Getch() ; // 123.
    
    ch = Peekch();
    
    while ( G.BoolNumber( ch ) ) { // 123
      token += ch ;
      Getch();
      ch = Peekch();
    } // while
    
    
    return token ;
  } // GetNum()
  
  String GetIdent() throws Throwable {
    String token = Getch() ;
    String ch = Peekch();
    
    while ( G.BoolLetter( ch ) || 
            G.BoolNumber( ch ) || 
            ch.equals( "_" ) ) {
      token += ch ;
      Getch();
      ch = Peekch();
    } // while

    return token ;
  } // GetIdent()
  
  
  String GetString() throws Throwable {
    String token = Getch() ; // Get "
    String ch = Peekch();
    
    while ( !ch.equals( "\"" ) ) {
      token += ch ;
      Getch();
      ch = Peekch();
    } // while
    
    token += Getch() ; // Get " 
    
    return token ;
  } // GetString()
  
  String GetChar() throws Throwable {
    String token = Getch() ; // Get '
    String ch = Peekch();
    
    while ( !ch.equals( "'" ) ) {
      token += ch ;
      Getch();
      ch = Peekch();
    } // while
    
    token += Getch() ; // Get ' 
    
    return token ;
  } // GetChar()
  
  
  public String Getch() throws Throwable {
    
        
    
    String ch = Peekch();
    
    
    
    
    if ( !ch.equals( "" ) ) 
      mstr = mstr.substring( 1, mstr.length() ) ;
    
    // if ( ch.equals( "\n" ) ) mLine ++ ;
      
      
    return ch ;
  } // Getch()
  
  
  public String Peekch() throws Throwable {
    String ch ;
    
    if ( mstr.length() > 0 ) ch = mstr.substring( 0, 1 ) ;
    else ch = "" ;
    
    // G.Println( "call me ? no~~~~" + ch + ";" ) ; ////////
    
    return ch ;
  } // Peekch()
  
  public boolean EatUntil( String str ) throws Throwable {  // the str will be eat 
    int index = mstr.indexOf( str ) ;
    
    if ( index == -1 ) return false ;
    /*
    if ( G.sDebug ) 
      G.Println( "before eat:\n" + GetText() ) ;
    */
    for ( int i = 0 ; i < ( index + str.length() ) ; i++ ) 
      Getch();
    /*
    if ( G.sDebug ) 
      G.Println( "after eat:\n" + GetText() ) ;
    */
      
    return true ;
  } // EatUntil()
  
  
  public void JmpComment() throws Throwable {
    if ( mstr.length() < 2 ) return ;
    if ( mstr.substring( 0, 2 ).equals( "//" ) ) {
      
      // EatUntil( "\n" );
      mstr = "" ;
      return ;
    } // if
    
    if ( mstr.substring( 0, 2 ).equals( "/*" ) ) {
      
      EatUntil( "*/" );
      
      return ;
    } // if
    
  } // JmpComment()
  
  public void JMPspace() throws Throwable {
    String ch = Peekch() ;
    int len = mstr.length();
    if ( ch.equals( "" ) ) return ;
    
    
    while ( G.BoolWhiteSpace( ch ) ) {
      Getch();
      ch = Peekch() ;
    } // while
    
    JmpComment();
    
    if ( len == mstr.length() ) return ;
    
    JMPspace() ;
  } // JMPspace()
  
  public String GetToken() throws Throwable {
    
    String token = "" ;

    JMPspace();
    
    String ch = Peekch();
    
    int type = G.WhatType( ch ) ;
    
    if ( ch.equals( "\"" ) ) token = GetString();
    else if ( ch.equals( "'" ) ) token = GetChar();
    else if ( type == G.TYPE_SYMBOL ) token = GetSymbol();
    else if ( type == G.TYPE_LETTER ) token = GetIdent();
    else if ( type == G.TYPE_NUMBER ) token = GetNum();
    else if ( ch.equals( "" ) ) token = "" ;
    else if ( ch.equals( "." ) ) token = GetFloat();
    else if ( ch.equals( "_" ) ) token = GetIdent();
    else G.Println( "error char : \"" + ch + "\" error 572" ) ;
    

    
    
    
    return token ;
  } // GetToken()
  
  
} // class Line()

class Text {
  private String mstr ;
  
  public Vector < Token > mTokenVec ;
  public int mTokenVecIndex ;
  
  public Vector < Token > mFunctionTable ;
  public Vector < Variable > mVariableTable ;
  
  public String mCoutBuffer ;
  
  public Text() throws Throwable {
    new Text( "" ) ;
  } // Text()
  
  public Text( String str ) throws Throwable {
    
    mstr = str ;
    mCoutBuffer = "" ;
    mTokenVec = new Vector < Token >();
    mTokenVecIndex = 0 ;
    
    CreateTokenVec();
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    // mLine = 0 ;
  } // Text()
  
  private void CreateTokenVec() throws Throwable {
    int count = 1 ;
    
    while ( mstr.length() != 0 ) {
      
      int index = mstr.indexOf( "\n" ); 
      if ( index != -1 ) {
        String line = mstr.substring( 0, index ) ;
        
        Line linetext = new Line( line ) ;
        
        // G.Println( "!" + line ) ;
        
        while ( linetext.mstr.length() != 0 ) {
          String token = linetext.GetToken() ;
          
          
          if ( !token.equals( "" ) )
            mTokenVec.add( new Token( token, count ) ) ;
        } // while
        
        mstr = mstr.substring( index + 1, mstr.length() ) ;
      } // if
      else {
        String line = mstr ;
        
        Line linetext = new Line( mstr ) ;
        
        while ( linetext.mstr.length() != 0 ) {
          String token = linetext.GetToken() ;
          if ( !token.equals( "" ) )
            mTokenVec.add( new Token( token, count ) ) ;
        } // while
        
        mstr = "" ;
      } // else
      
      count ++ ;
    } // while 
        
        
  } // CreateTokenVec()
  
  public void Print() {
    G.Print( mstr ) ;
  } // Print()
  
  public void Println() {
    G.Println( mstr ) ;
  } // Println()
  
  
  public void FuncStdPrint( int endIndex ) throws Throwable {
    try {
        
      // G.Println( "11" + GetText() + "22" ) ;
        
      boolean meetcom = false ;
      int spacecount = 0 ;
      // Text text = new Text( GetText() ) ;
    
      // String token = text.PeekToken() ;
      G.Print( GetToken() ) ;
      while ( mTokenVecIndex < endIndex ) {
        String token = GetToken() ;
        // G.Println( token ) ;
        
        String peek = PeekToken() ;
        if ( token.equals( "}" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "[" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "," ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "++" ) || token.equals( "--" ) ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "(" ) && !meetcom ) {
          G.Print( token ) ;
        } // if
        else if ( token.equals( "(" ) && meetcom ) {
          G.Print( " " + token ) ;
          meetcom = false ;
        } // if
        else if ( !token.equals( "(" ) ) {
          G.Print( " " + token ) ;
        } // if
        
        if ( token.equals( "if" ) || 
             token.equals( "while" ) ) {
          meetcom = true ;
        } // if
        
        if ( token.equals( "{" ) && !peek.equals( "}" ) ) {
          spacecount += 2 ;
          G.Println( "" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // if
        else if ( token.equals( ";" ) && !peek.equals( "}" ) ) {
          // spacecount += 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // if
        else if ( token.equals( ";" ) && peek.equals( "}" ) ) {
          spacecount -= 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount ) ;
        } // else if 
        else if ( token.equals( "}" ) && peek.equals( "}" ) ) {
          spacecount -= 2 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount ) ;
        } // else if 
        else if ( token.equals( "}" ) && !peek.equals( "}" ) ) {
          spacecount -= 0 ;
          G.Print( "\n" ) ;
          PrintSpace( spacecount - 1 ) ;
        } // else if 
      
        /*
        // should command
        if ( !token.equals( "}" ) )
          G.Print( token ) ;
        if ( token.equals( ";" ) ) {
          G.Print( "\n" ) ;
        } // if
        
        token = PeekToken() ;
        if ( !token.equals( "(" ) ) G.Print( " " ) ;
        // should command
        */
      } // while
      
      // G.Println( "" );
    
    } catch ( Exception e ) {
      G.Println( "stdPrint Error! error 893" ) ;
    } // catch
  } // FuncStdPrint()
  
  private void PrintSpace( int count ) {
    for ( int i = 0 ; i < count ; i++ ) 
      G.Print( " " ) ; 
  } // PrintSpace()
  
  
  public String GetToken() throws Throwable {
    if ( mTokenVecIndex >= mTokenVec.size() ) return "" ;
    
    
    String str = mTokenVec.get( mTokenVecIndex ).GetToken() ;
    
    mTokenVecIndex ++ ;
    
    return str ;
  } // GetToken()
  
  public String PeekToken() {
    if ( mTokenVecIndex < mTokenVec.size() )
      return mTokenVec.get( mTokenVecIndex ).GetToken() ;
      
    else return "" ;
  } // PeekToken()
  
  
  private void SyntaxError( String str, int beginline ) throws Throwable { // legal error  
                                                             // it can not cause system error
                                                             
    int line = mTokenVec.get( mTokenVecIndex - 1 ).mLine - beginline ;
    
    if ( line == 0 ) {
      G.Println( "line " + ( line + 1 ) + " : syntax error when token is '" + str + "'" ) ;
    } // if
    else {
      G.Println( "line " + ( line ) + " : syntax error when token is '" + str + "'" ) ;
    } // else
    
    
    line = mTokenVec.get( mTokenVecIndex - 1 ).mLine ;
    
    while ( line == mTokenVec.get( mTokenVecIndex ).mLine ) {
      GetToken();
    } // while
    
    
    
    // System.exit( 30 ) ;
    // this.ClearLineCount() ;
  } // SyntaxError()
  
  private void TypeError( String str, int beginline ) throws Throwable { // legal error  
                                                             // it can not cause system error
    int line = mTokenVec.get( mTokenVecIndex - 1 ).mLine - beginline ;
    
    if ( line == 0 ) {
      G.Println( "line " + ( line + 1 ) + " : type error!" ) ;
    } // if
    else {
      G.Println( "line " + ( line ) + " : type error!" ) ;
    } // else 
    
    line = mTokenVec.get( mTokenVecIndex - 1 ).mLine ;
    
    while ( line == mTokenVec.get( mTokenVecIndex ).mLine ) {
      GetToken();
    } // while
    
    // System.exit( G.sV1 + G.sV2 ) ;
    // this.ClearLineCount() ;
  } // TypeError()
  
  
  public void Deal() throws Throwable {
        
    
    
    String errorstr = "" ;
    String commands = "" ;
    
    
    
    
    while ( !commands.equals( "Done" ) ) {
      if ( mTokenVecIndex >= mTokenVec.size() ) return ;
      
      G.Print( "> " ) ;
      
      String firstToken = PeekToken();
      
      // G.Println( "!:" + firstToken ) ;
      
      int fSize = G.sFunctionTable.mTable.size();
      int vSize = G.sVariableTalbe.mTable.size();
      int beginLine = 0 ;
      if ( mTokenVecIndex != 0 ) 
        beginLine = mTokenVec.get( mTokenVecIndex - 1 ).mLine ;
      
      try {
        if ( G.sDeclareWord.Find( firstToken ) ) {
        
          // G.sDefine = true ;
          if ( !GetDefinition() ) errorstr.substring( -1, -2 );
          
          int i = 0 ;
          while ( G.sFunctionTable.mTable.size() > ( fSize + i ) ) {
            String funcName = G.sFunctionTable.mTable.get( fSize + i ).GetName() ;
            G.sFunctionTable.mTable.get( fSize + i ).mEndIndex = mTokenVecIndex ;
            
            if ( G.sFunctionTable.Find( funcName, 0, fSize ) ) {
              G.sFunctionTable.mTable.remove( G.sFunctionTable.IndexOf( funcName, 0, fSize ) ) ;
              G.NewDefineFuncOK( funcName ) ;
              // G.sFunctionTable.mTable.get( fSize ).mEndIndex = mTokenVecIndex ;
            } // if
            else {
              G.DefineFuncOK( funcName ) ;
              // G.sFunctionTable.mTable.get( fSize + i ).mEndIndex = mTokenVecIndex ;
            } // else
            
            // G.sFunctionTable.mTable.get( fSize + i ).mEndIndex = mTokenVecIndex ;
            
            i ++ ;
            
          } // while
          
          i = 0 ;
          while ( G.sVariableTalbe.mTable.size() > ( vSize + i ) ) {
            String varName = G.sVariableTalbe.mTable.get( vSize + i ).GetName() ;

            if ( G.sVariableTalbe.Find( varName, 0, vSize ) ) {
              G.sVariableTalbe.mTable.remove( G.sVariableTalbe.IndexOf( varName, 0, vSize ) ) ;
              G.NewDefineOK( varName ) ;
            } // if
            else {
              G.DefineOK( varName ) ;
            } // else
            
            
            i ++ ;
            G.sVarLength ++ ;
          } // while
          
          // G.sVariableTalbe.mTable.get( 0 ).StdPrint() ;
          // G.sDefine = false ;
        } // if
        else {
          if ( G.sDebug ) 
            G.Println( "Let's got to get statement!" ) ;
          
          if ( firstToken.equals( "Done" ) ) {
            commands = "Done" ;
            return ;
          } // if
          else if ( firstToken.equals( "ListAllVariables" ) ) { 
            // G.Println( "Let's List all variable" ) ;
            // try {
            GetToken() ; // ListAllVariables
            GetToken() ; // (
            GetToken() ; // )
            GetToken() ; // ;
            G.sVariableTalbe.StdPrint() ;
            // } catch ( Exception ee ) {
            //   G.Println( "What's the fuck!!" ) ;
            // } // catch
          } // if
          else if ( firstToken.equals( "ListVariable" ) ) {
            GetToken() ; // eat ListVariable
            GetToken() ; // eat (
            String token = GetToken() ; // "xxx" 
            GetToken() ; // eat )
            GetToken() ; // eat ;
            
            String clearToken = token.substring( 1, ( token.length() -1 ) ) ;
            int index = G.sVariableTalbe.IndexOf( clearToken ) ;
            if ( index != -1 ) {
              G.sVariableTalbe.Get( index ).StdPrint();
            } // if
              
            
          } // else if 
          else if ( firstToken.equals( "ListAllFunctions" ) ) { 
            // G.Println( "source text:" + GetText() ) ;
            GetToken() ; // ListAllFunctions
            GetToken() ; // (
            GetToken() ; // )
            GetToken() ; // ;
            
            G.sFunctionTable.StdPrint() ;
            // G.Println( i + "" ) ;
          } // else if 
          else if ( firstToken.equals( "ListFunction" ) ) { 
            GetToken() ; // eat ListFunction
            GetToken() ; // eat (
            String token = GetToken() ; // "xxx" 
            GetToken() ; // eat )
            GetToken() ; // eat ;
            
            String clearToken = token.substring( 1, ( token.length() -1 ) ) ;
            int index = G.sFunctionTable.IndexOf( clearToken ) ;
            
            int funcIndex = -1 ;
            if ( index != -1 ) {
              Function temp = G.sFunctionTable.Get( index ) ;
              
              int returnIndex = mTokenVecIndex ;
              
              mTokenVecIndex = temp.mIndex - 1 ;
              
              FuncStdPrint( temp.mEndIndex );
              
              mTokenVecIndex = returnIndex ;
            } // if
            
            
            
            
          } // else if 
          
          else { // if not system commands it must be a statement 
          
            PassValue passValue = new PassValue() ;
            Variable variable = new Variable() ;
            
            if ( !GetStatement( passValue ) ) errorstr.substring( -1, -2 );
            
            
            // G.Println( "Statement executed ..." ) ;
            
            variable = null ;
            passValue = null ;
          } // else 
          
          G.Println( "Statement executed ..." ) ;
          
        } // else
      } catch ( Exception e ) {
        
        if ( G.sTypeError ) TypeError( mTokenVec.get( mTokenVecIndex - 1 ).GetToken(), beginLine ) ;
        else { 
          SyntaxError( mTokenVec.get( mTokenVecIndex - 1 ).GetToken(), beginLine ) ;
        } // else 
        
      } // catch
      
      // commands = "Done" ;
      
    } // while
    
    
    
    // G.sVariableTalbe.Print() ;
    
    
  } // Deal()
  
  public boolean GetDefinition() throws Throwable {
    // definition 
    // :           VOID Identifier function_definition_without_ID 
    // | type_specifier Identifier function_definition_or_declarators
    try {
      Variable variable = new Variable() ;
      Str_obj str_obj = new Str_obj() ;
      String type = "" ;
      String token = PeekToken() ;
      String ident = "" ;
      int index = mTokenVecIndex ;
      
      if ( token.equals( "void" ) ) {
        GetToken() ; // eat void
        type = "void" ;
        
        if ( !GetIdentifier( variable ) ) return false ;
        
        
        variable.SetType( "void" ) ;
        
        if ( !GetFunction_definition_without_ID( variable ) ) return false ;
        
        // G.sFunctionTable.mTable.add( new Function( "void", variable.GetName(), index ) ) ;
        
        return true ;
      } // if token.equals( "void" )
      
      else if ( G.sDeclareWord.Find( token ) ) {
        
        // ----
        if ( !GetType_specifier( str_obj ) ) return false ;
        
        type = str_obj.Get() ;
        // ---- end
        
        // ----
        if ( !GetIdentifier( variable ) ) return false ;
        
        ident = variable.GetName();
        
        
        // ----- end
        
        variable.SetType( type ) ;
        
        if ( !GetFunction_definition_or_declarators( variable ) ) return false ;
        
        return true ;
      } // else if 
      
      else return false ;
        
      // return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetDefinition()
  
  public boolean GetFunction_definition_without_ID( Variable var ) throws Throwable {
    // function_definition_without_ID 
    // : '(' [ VOID | formal_parameter_list ] ')' compound_statement
    try {
      
      String token = PeekToken() ;
      
      String type = var.GetType() ;
      String name = var.GetName() ;
      int index = mTokenVecIndex - 1 ;
      
      int vSize = G.sVariableTalbe.mTable.size();
      
      if ( !GetToken().equals( "(" ) ) return false ;
      
      token = PeekToken() ;
      
      if ( !token.equals( ")" ) ) {
        if ( token.equals( "void" ) ) {
          GetToken(); // eat void
          return false ;
        } // if
        else if ( token.equals( ")" ) ) {
        } // else if
        else { // GetFormal_parameter_list()
          if ( !GetFormal_parameter_list() ) { 
            return false ;
          } // if
        } // else
        
        token = PeekToken() ;
      } // if 
      
      if ( !GetToken().equals( ")" ) ) return false ;
      
      
      PassValue passvalue1 = new PassValue( false ) ;
      
      
      
      if ( !GetCompound_statement( passvalue1 ) ) return false ;
      
      // if ( !GetCompound_statement() ) return false ;
      
      
      while ( G.sVariableTalbe.mTable.size() >  vSize ) {
        G.sVariableTalbe.mTable.remove( G.sVariableTalbe.mTable.size() - 1 ) ;
      } // while
      
      G.sFunctionTable.mTable.add( new Function( type, name, index ) ) ;
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetFunction_definition_without_ID()
  
  
  
  
  public boolean GetFunction_definition_or_declarators( Variable var ) throws Throwable {
    // function_definition_or_declarators 
    // : function_definition_without_ID
    // | rest_of_declarators
    
    try {
      String token = PeekToken();
      
      if ( token.equals( "[" ) || 
           token.equals( "," ) ||
           token.equals( ";" ) ) {
        if ( !GetRest_of_declarators( var ) ) return false ;
      } // if 
      
      else {
        if ( !GetFunction_definition_without_ID( var ) ) return false ;
      } // else 
     
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetFunction_definition_or_declarators()
  
  

  
  private boolean GetFormal_parameter_list() throws Throwable {
    // formal_parameter_list 
    // : type_specifier [ '&' ] Identifier [ '[' Constant ']' ] 
    //   { ',' type_specifier [ '&' ] Identifier [ '[' Constant ']' ] }
    try {
      Str_obj str_obj = new Str_obj() ;
      Variable variable = new Variable() ;
      Variable constantVar = new Variable();
      
      String type = "" ;
      String name = "" ;
      String constant = "unset" ;
      
      
      
      String token = PeekToken() ;
      
      if ( token.equals( ")" ) ) return true ;
        
      if ( !GetType_specifier( str_obj ) ) return false ;
      
      token = PeekToken() ;
      
      boolean isRef = false ;
      if ( token.equals( "&" ) ) {
        isRef = true ;
        GetToken(); // eat &
      } // if
      
      if ( !GetIdentifier( variable ) ) return false ;
      
      token = PeekToken() ;
      
      if ( token.equals( "[" ) ) {
        GetToken() ; // eat [
        
        if ( !GetConstant( constantVar ) ) return false ;
        
        if ( !GetToken().equals( "]" ) ) return false ;
        
        token = PeekToken() ;
      } // if 
      
      //
      type = str_obj.Get();
      name = variable.GetName();
      constant = constantVar.GetValue() ;
      
      if ( constant.equals( "unset" ) ) {
        G.sVariableTalbe.Add( type, name ) ;
        if ( isRef )
          G.sVariableTalbe.mTable.get( G.sVariableTalbe.mTable.size() - 1 ).mIsRef = true ;
        
      } // if
      else {
        G.sVariableTalbe.Add( type, name, VariableTalbe.IS_ARRAY, constant ) ;
      } // else 
      
      
      
      //
      
      
      while ( token.equals( "," ) ) {
        type = "" ;
        name = "" ;
        constant = "unset" ;
        isRef = false;
        
        GetToken() ; // eat ,
        
        if ( !GetType_specifier( str_obj ) ) return false ;
      
        token = PeekToken() ;
      
        if ( token.equals( "&" ) ) {
          isRef = true ;
          GetToken(); // eat &
        } // if
      
        if ( !GetIdentifier( variable ) ) return false ;
      
        token = PeekToken() ;
      
        if ( token.equals( "[" ) ) {
          GetToken() ; // eat [
        
          if ( !GetConstant( constantVar ) ) return false ;
        
          if ( !GetToken().equals( "]" ) ) return false ;
        
          token = PeekToken() ;
        } // if 
        
              //
        type = str_obj.Get();
        name = variable.GetName();
        constant = constantVar.GetValue() ;
        if ( constant.equals( "unset" ) ) {
          G.sVariableTalbe.Add( type, name ) ;
          if ( isRef )
            G.sVariableTalbe.mTable.get( G.sVariableTalbe.mTable.size() - 1 ).mIsRef = true ;
        } // if
        else {
          G.sVariableTalbe.Add( type, name, VariableTalbe.IS_ARRAY, constant ) ;
        } // else 
        
      
      //
        
      } // while
        
      // G.sVariableTalbe.Print();
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
    
  } // GetFormal_parameter_list()
  
  
  public boolean GetCompound_statement( PassValue passValue ) throws Throwable {
    // compound_statement 
    // : '{' { declaration } { statement } '}'
    try {
        
      int length = G.sVariableTalbe.mTable.size() ;
      
      // G.Println( "!!!" + length ) ;
        
      String token = GetToken() ;
      if ( !token.equals( "{" ) ) return false ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetCompound_statement() 2480 :" + token ) ;

      token = PeekToken() ;
      
      while ( !token.equals( "}" ) ) {
        
        
        /*
        if ( token.equals( "void" ) ) { 
          GetToken() ;
          return false;
        } // if 
        */
        
        if ( G.sDeclareWord.Find( token ) && 
             !token.equals( "void" ) ) {

          // String text = GetText() ;
          // Text text = new Text( GetText() ) ; // this is use to Debug // this is use to Debug 
          // int index = mPeekVecIndex ;
          // String Ori
          
          // PassValue passValue = new PassValue() ;
          
          
          
          if ( !GetDeclaration( ) ) return false ;
          
          // Text stext = new Text( text ) ;
          // DefineLoopvariable( stext ) ;
          
          
          
          
        } // if 
        else {  

          // G.Println( "!!!!!!!!!!!!!" + passValue.mRealStatement ) ;
          if ( !GetStatement( passValue ) ) return false ;
        } // else
        
        token = PeekToken() ;
      } // while
      
      
      
      token = GetToken() ;
      if ( !token.equals( "}" ) ) return false ;
      
      
      
      while ( G.sVariableTalbe.mTable.size() > length ) { // if define, table must larger than begin
       
        // G.Println( "!!!!!!!!!" + G.sVariableTalbe.mTable.size() ) ;
        G.sVariableTalbe.mTable.remove( G.sVariableTalbe.mTable.size() - 1 ) ;
      } // while
      
      // G.Println( "!!new" + G.sVariableTalbe.mTable.size() ) ;
      
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetCompound_statement() return false error 1671" ) ;
        
      return false ;
    } // catch()
  } // GetCompound_statement()
  
  
  public void DefineLoopvariable( Text text ) throws Throwable { 
    try {
      
      
    
      

    } catch ( Exception e ) {
      G.Println( "this an Error in DefineLoopvariable error 2089 \n" ) ;
    } // catch
  } // DefineLoopvariable()
  
  
  
  public boolean GetDeclaration() throws Throwable {
    // declaration 
    // : type_specifier Identifier rest_of_declarators
    try {
      Str_obj str_obj = new Str_obj() ;
      Variable variable = new Variable() ;
      
      
      if ( !GetType_specifier( str_obj ) ) return false ;
           
      if ( !GetIdentifier( variable ) ) return false ;
      
      
      variable.SetType( str_obj.Get() ) ;
      
      if ( !GetRest_of_declarators( variable ) ) return false ;
      
      
      // G.sVarialbeTalbe.mTable.add( )
        
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetDeclaration() return false error 1695" ) ;
      return false ;
    } // catch()
    
  } // GetDeclaration()
  
  public boolean GetStatement( PassValue passValue ) throws Throwable {
    // statement
    // : ';'     /* the null statement */
    // | expression ';'  /* expression here should not be empty */
    // | RETURN [ expression ] ';'
    // | compound_statement
    // | IF '(' expression ')' statement [ ELSE statement ]
    // | WHILE '(' expression ')' statement
    // | DO statement WHILE '(' expression ')' ';'
    // // CIN ( '>>' ident [ '[' expression ']' ] ) { '>>' ident [ '[' expression ']' ] } ';'
    // // COUT ( '<<' expression ) { '<<' expression } ';'
        
    try {
      
      
      Variable variable = new Variable() ;
      
        
      String token = PeekToken() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetStatement() 1549 :" + token ) ;
      
      
      
      if ( token.equals( ";" ) ) { 
        GetToken() ; // eat ;
        
        
        
        return true ; 
      } // if 
      else if ( token .equals( "return" ) ) {
        GetToken(); // eat return ;
        
        token = PeekToken() ;
        
        if ( token.equals( ";" ) ) return true ;
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        G.sReturnVariable.SetType( variable.GetType() ) ;
        G.sReturnVariable.SetValue( variable.GetValue() ) ;
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        return true ;
      } // else if "return" 
      else if ( token .equals( "{" ) ) { // }
      
        
        
        if ( !GetCompound_statement( passValue ) ) { 
          
          
          
          
          
          return false ;
        } // if 
        
        
        
        return true ;
      } // else if compound_statement
      else if ( token .equals( "cin" ) ) { // 
      
        
        
        GetToken(); // eat cin 
        
        if ( !GetToken().equals( ">>" ) ) return false ;
        
        // G.Println( "yoyo man" ) ;
        
        if ( !GetIdentifier( variable ) ) return false ;
        
        // G.Println( "yoyo man" ) ;
        
        if ( variable.GetType().equals( "-1" ) ) return false ; // ident must defined
        
        token = PeekToken() ;
          
        if ( token.equals( "[" ) ) {
            
          GetToken() ; // eat [
            
          if ( !GetExpression( variable, passValue ) ) return false ;
            
          if ( !GetToken().equals( "]" ) ) return false ;
            
        } // if 
        
        
        token = PeekToken() ;
        
        while ( token.equals( ">>" ) ) {
          GetToken() ; // eat >>
        
          if ( !GetIdentifier( variable ) ) return false ;
        
          if ( variable.GetType().equals( "-1" ) ) return false ; // ident must defined
          
          token = PeekToken() ;
          
          if ( token.equals( "[" ) ) {
            
            GetToken() ; // eat [
            
            if ( !GetExpression( variable, passValue ) ) return false ;
            
            if ( !GetToken().equals( "]" ) ) return false ;
            
          } // if 
        
          token = PeekToken() ;
        } // while 
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        // mStatementType += "cin\n" ;
        
        return true ;
      } // else if cin
      
      else if ( token .equals( "cout" ) ) { // 
        
        // G.Println( "!!!" +passValue.mRealStatement) ;
        
        GetToken(); // eat cout
        
        if ( !GetToken().equals( "<<" ) ) return false ;
        
        if ( !GetCout_Expression( variable, passValue ) ) return false ;
        
        // G.Println( "!!!!cout" + variable.GetValue() ) ; // ////////////////////////////debug code 
        
        if ( passValue.mRealStatement ) 
          AddToCoutBuffer( variable ) ;
        
        token = PeekToken() ;
        
        while ( token.equals( "<<" ) ) {
          GetToken() ; // eat <<
        
          
          
          if ( !GetCout_Expression( variable, passValue ) ) return false ;
        
          
          if ( passValue.mRealStatement ) 
            AddToCoutBuffer( variable ) ;
          
          
          // G.Println( "!!!!cout" + variable.GetValue() ) ; // ////////////////////////////debug code 
          
          token = PeekToken() ;
        } // while 
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        if ( !passValue.mRealStatement ) mCoutBuffer = "" ;
        
        StdCout() ; // maybe cuase output error 
        
        return true ;
      } // else if cout
      
      
      /*
      else if ( token .equals( "cout" ) ) {
        GetToken() ; // eat cout
        
        if ( !PeekToken().equals( "<<" ) ) {
          GetToken() ;
          return false ;
        } // if 
        
        variable.SetType( "cout" ) ;
        
        if ( !GetRest_of_maybe_shift_exp( variable ) ) return false ;
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
      } // else if cout case
      */
      else if ( token .equals( "if" ) ) {
        
        boolean hadtrue = false ;
        
        
        GetToken() ; // eat if 
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        
        if ( !GetToken().equals( ")" ) ) {
          // G.Println("ffffffffffffuccccccccccccccccccccccckkkkkkkkkkkkkkk");
          
          return false ;
        } // if 
        
        PassValue outpassValue = new PassValue() ;
        
        if ( !passValue.mRealStatement )
        {
          outpassValue.mRealStatement = false ;
        } // if
        
        if ( variable.GetValue().equals( "false" ) ) {
        
          outpassValue.mRealStatement = false ;
        } // if
        
        
        
        if ( variable.GetValue().equals( "true" ) ) {
          hadtrue = true ;
        } // if
        
        if ( !GetStatement( outpassValue ) ) return false ;
        
        
        
        
        mCoutBuffer = "" ;
        
        
        token = PeekToken() ;
        
        if ( !token.equals( "else" ) ) {
          mCoutBuffer = "" ;
          return true ;
        } // if 
        
        // now, the token must else
        
        GetToken() ; // eat else
        
        // boolean done = false ;
        
        
        // G.Println( "//==========\nGetelse\n//============" + hadtrue ) ;
        
        
        while ( 1 == 1  ) {
          token = PeekToken() ;
          if ( !token.equals( "if" ) ) { // ELSE statement 
          
            if ( !hadtrue && passValue.mRealStatement ) {
              outpassValue.mRealStatement = true ;
            } // if
            else {
              outpassValue.mRealStatement = false ;
            } // else 
          
            // G.Println( "//======================" + outpassValue.mRealStatement ) ;
            
            if ( !GetStatement( outpassValue ) ) return false ;
            
            // G.Println( "!!!!!" + outpassValue.mRealStatement ) ;
            
            
            mCoutBuffer = "" ;
            return true ;
            
            
          } // if
          
          else { // ELSE IF statement
            GetToken() ; // eat IF
            
            if ( !GetToken().equals( "(" ) ) return false ; 
        
            if ( !GetExpression( variable, passValue ) ) return false ;
        
        
            if ( !GetToken().equals( ")" ) ) {
          
              return false ;
            } // if 
            
            
            if ( hadtrue || !passValue.mRealStatement || variable.GetValue().equals( "false" ) ) {
              outpassValue.mRealStatement = false ;
            } // if
            else {
              outpassValue.mRealStatement = true ;
            } // else 
            
            if ( variable.GetValue().equals( "true" ) ) {
              hadtrue = true ;
            } // if
            
        
            if ( !GetStatement( outpassValue ) ) return false ;
            
            
            
            mCoutBuffer = "" ;
            
          } // else // ELSE IF statement
          
          token = PeekToken() ; 
          
          if ( token.equals( "else" ) ) GetToken() ; // eat else 
          else {
            mCoutBuffer = "" ;
            return true ;
          } // else 
          
          token = PeekToken() ; 
        } // while
        
        
        
        // return true ;
        
      } // else if  if then else 
      else if ( token .equals( "while" ) ) {
        /*
        mStatementType += "while\n" ;
        
        GetToken() ; // eat while
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        if ( !GetStatement( passValue ) ) return false ;
        
        
        
        return true ;
        */
        // return true ;
        return GetWhileStatement( passValue ) ;
        
      } // else if while
      else if ( token .equals( "do" ) ) {
        /*
        GetToken() ; // eat do
        
        if ( !GetStatement( passValue ) ) return false ;
        
        if ( !GetToken().equals( "while" ) ) return false ;
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        */
        
        
        return GetDoWhileStatement( passValue ) ;
      } // else if do while
      else {
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        
        
      } // else Get Expression
        
      variable = null ;
      
      // System.gc() ;
        
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetStatement() return false error 1874" ) ;
      
      return false ;
    } // catch()
    
  } // GetStatement()
  
  public boolean GetWhileStatement( PassValue passValue ) throws Throwable {
    try {
      
      String token = PeekToken() ;
      Variable variable = new Variable() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetWhileStatement() 3349 :" + token ) ;
        
      if ( passValue.mRealStatement ) {
        
        
        GetToken() ; // eat while
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        
        
        int expIndex = mTokenVecIndex ;
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        
        
        
        
        
        
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        
        
        
        PassValue outpassValue = new PassValue() ;
        
        outpassValue.mRealStatement = false ;
        
        int staIndex = mTokenVecIndex ;
        
        if ( !GetStatement( outpassValue ) ) return false ;
        
        
        
        
        
        int returnIndex = mTokenVecIndex ;
        
        // G.Println( returnIndex + "" ) ;
        // G.Println( expIndex + "" ) ;
        // G.Println( staIndex + "" ) ;
        
        
        
        
        Variable outvariable = new Variable() ;
        
        
        
        
        
        mTokenVecIndex = expIndex ;
        
        GetExpression( outvariable, passValue ) ;
        
        // G.Println( "==> variable:" ) ;
        // outvariable.StdPrint() ;
        // G.Println( "<==" ) ;
        
        // G.Println( "2==>" + GetText() + "<==" ) ;
        
        int i = 0 ;
        outpassValue.mRealStatement = true ;
        
        while ( outvariable.GetValue().equals( "true" ) ) {
          
          mTokenVecIndex = staIndex ;
          
          // long startTime = System.currentTimeMillis(); 
          
          // outpassValue.mRealStatement = true ;
          
          
          GetStatement( outpassValue ) ;
          
          // long processTime = System.currentTimeMillis() - startTime; 

          // G.Println( "spend " + ( processTime ) + " second\n" + i ) ;
          
          // StdCout() ;
          
          
          
          mTokenVecIndex = expIndex ;
          
          // return true ;
          
          GetExpression( outvariable, passValue ) ;
          // G.Println( "==> " + Variable.sV1 ) ; 
          
          // System.gc() ;
          
          // G.Println( "==>> " + Variable.sV1 ) ; 
          
          // if ( i == 3 ) outvariable.SetValue( "true" ) ;
          i ++ ;
        } // while
        
        
        
        mTokenVecIndex = returnIndex ;
        
        
        // StdCout() ;
        
        return true ;
      } // if 
      
      else { // if passValue.mRealStatement == false ;
        
        
        GetToken() ; // eat while
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        if ( !GetStatement( passValue ) ) return false ;
        
        
        
        return true ;
      } // else 
      
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetWhileStatement() return false error 3340" ) ;
      
      return false ;
    } // catch()
  } // GetWhileStatement()
  
  
  public boolean GetDoWhileStatement( PassValue passValue ) throws Throwable {
    // DO statement WHILE '(' expression ')' ';'
    
    
    try {
      
      String token = PeekToken() ;
      Variable variable = new Variable() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetDoWhileStatement() 3150 :" + token ) ;
        
      if ( passValue.mRealStatement ) {
        
        
        GetToken() ; // eat do
        
        
        int staIndex = mTokenVecIndex ;
        
        if ( !GetStatement( passValue ) ) return false ;
        
        if ( !GetToken().equals( "while" ) ) return false ; 
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        
        int expIndex = mTokenVecIndex ;
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        
        int returnIndex = mTokenVecIndex ;
        
        
        PassValue outpassValue = new PassValue() ;
        
        outpassValue.mRealStatement = true ;
        
        
        
        Variable outvariable = new Variable() ;
        
        
        mTokenVecIndex = expIndex ;
        
        GetExpression( outvariable, passValue ) ;
        
        
        int i = 0 ;
        outpassValue.mRealStatement = true ;
        
        while ( outvariable.GetValue().equals( "true" ) ) {
          
          mTokenVecIndex = staIndex ;
          
          // long startTime = System.currentTimeMillis(); 
          
          // outpassValue.mRealStatement = true ;
          
          
          GetStatement( outpassValue ) ;
          
          // long processTime = System.currentTimeMillis() - startTime; 

          // G.Println( "spend " + ( processTime ) + " second\n" + i ) ;
          
          // StdCout() ;
          
          
          
          mTokenVecIndex = expIndex ;
          
          // return true ;
          
          GetExpression( outvariable, passValue ) ;
          // G.Println( "==> " + Variable.sV1 ) ; 
          
          // System.gc() ;
          
          // G.Println( "==>> " + Variable.sV1 ) ; 
          
          // if ( i == 3 ) outvariable.SetValue( "true" ) ;
          i ++ ;
        } // while
        
        
        
        mTokenVecIndex = returnIndex ;
        
        
        // StdCout() ;
        
        return true ;
      } // if 
      
      else { // if passValue.mRealStatement == false ;
        
        
        GetToken() ; // eat do
        
        if ( !GetStatement( passValue ) ) return false ;
        
        if ( !GetToken().equals( "while" ) ) return false ; 
        
        if ( !GetToken().equals( "(" ) ) return false ; 
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        if ( !GetToken().equals( ";" ) ) return false ;
        
        return true ;
      } // else 
      
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetDoWhileStatement() return false error 3281" ) ;
      
      return false ;
    } // catch()
  } // GetDoWhileStatement()
  
  
  public boolean GetExpression( Variable variable, PassValue passValue ) throws Throwable {
    // expression
    // : basic_expression [ rest_of_expression ]                                 
    //    // [ rest_of_expression ]       un code
    try {
      
      String token = PeekToken() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetExpression() 1889 :" + token ) ;
      // G.Println("11111111111111111111looks OK") ;
      if ( !GetBasic_Expression( variable, passValue ) ) return false ; 
      // G.Println("22222222222222222222looks OK") ;
      token = PeekToken() ;
      
      if ( !token.equals( "," ) && 
           !token.equals( "?" ) ) return true ;
           
      // G.Println("3333333333333333333333333333looks OK") ;
           
      if ( !GetRest_of_expression( variable, passValue ) ) return false ;
        
        
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetExpression() return false error 1912" ) ;
      return false ;
    } // catch()
    
  } // GetExpression()
  
  public boolean GetBasic_Expression( Variable variable,
                                      PassValue passValue ) throws Throwable {
    // basic_expression
    // : Identifier rest_of_Identifier_started_basic_expression
    // | ( PP | MM ) { PP | MM } Identifier rest_of_PPMM_Identifier_started_basic_expression
    // | sign { sign } signed_basic_expression
    // | ( Constant | '(' expression ')' ) rest_of_maybe_logical_OR_exp
    try {
        
      
      // Str_obj str_obj = new Str_obj() ;
      // Variable variable = new Variable() ;
      // Str_obj constantObj = new Str_obj();
      
      String token = PeekToken() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetBasic_Expression() 1644 :" + token ) ;
      
      if ( G.BoolIdentifier( token ) ) {
        
        if ( !GetIdentifier( variable ) ) {
          return false ; // eat identifier ;
          
          // if ( G.sGlobalvariableTalbe.Find( str_obj ) )
          
        } // if 
        
        if ( variable.GetType().equals( "-1" ) ) { 
          if ( G.sDebug )
            G.Println( "return false 1657 :" + token ) ;

          return false ;
        } // if 
        
        if ( !GetRest_of_Identifier_started_basic_exp( variable, passValue ) ) return false ;
        
        
        
        return true ;
      } // if
      else if ( token.equals( "++" ) ||
                token.equals( "--" ) ) {
        token = GetToken() ; // eat ++ --
        
        token = PeekToken() ;
        while ( token.equals( "++" ) || 
                token.equals( "--" ) ) {
          token = GetToken() ;
          token = PeekToken() ;
        } // while
        
        token = GetToken() ; // it must not ++ or --
        if ( !G.BoolIdentifier( token ) ) return false ; // if not ++ -- or ident , it is error 
        
        
        
      } // else if ++ or -- case
      else if ( G.BoolSign( token ) ) {
        String signStr1 = GetToken() ; // eat sign 
        
        
        
        token = PeekToken() ;
        
        while ( G.BoolSign( token ) ) {
          String signStr2 = GetToken() ;
          
          if ( signStr1.equals( signStr2 ) ) signStr1 = "+" ; 
          // -- => +   ++ => +
          else if ( signStr1.equals( "!" ) || signStr2.equals( "!" ) )
          {
            G.Println( "Mr.Hsia you R bitCH" );
            G.sTypeError = true ;
            return false ;
          } // else if
          else signStr1 = "-" ; // +- => -         -+ => -
          
          PeekToken() ;
        } // while
        
        if ( !GetSigned_basic_expression( signStr1, variable, passValue ) ) return false ;
        
        
        // G.Println( "???????????????????????????????/bool!!!!!!!!!!!!!!!" ) ;
        // variable.StdPrint() ;
        
        
        
      } // else if + or - or !
      
      else {
        
        if ( token.equals( "(" ) ) {
          GetToken() ; // eat (
          
          if ( !GetExpression( variable, passValue ) ) return false ;
          
          token = PeekToken() ;
          // if ( G.sDebug )
          //   G.Println( "1976" + token ) ;
          
          if ( !GetToken().equals( ")" ) ) return false ;
          
        } // if 
        else {
          if ( !GetConstant( variable ) ) return false ;
          
          
          
          // if ( G.sDebug )
          //   G.Println( "con" ) ;
          
        } // else 
        
        // Variable variable2 = new Variable() ;
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
        /*
        G.Println( "yoyoyoyooyoyooyoyyoyoyyo 1644" ) ;
        variable.StdPrint();
        variable2.StdPrint();
        */
        /*
        if ( !variable.GetValue().equals( "unset" ) ) 
          G.Compute( variable, variable.mPassSign, variable ) ;
        */
      } // else if 
        
        
      
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetBasic_Expression() return false error 2011" ) ;
      return false ;
    } // catch()
    
  } // GetBasic_Expression()
  
  public boolean GetCout_Expression( Variable variable,
                                     PassValue passValue ) throws Throwable {
    // basic_expression
    // : Identifier rest_of_Identifier_started_basic_expression
    // | ( PP | MM ) { PP | MM } Identifier rest_of_PPMM_Identifier_started_basic_expression
    // | sign { sign } signed_basic_expression
    // | ( Constant | '(' expression ')' ) rest_of_maybe_logical_OR_exp
    try {
        
      
      // Str_obj str_obj = new Str_obj() ;
      // Variable variable = new Variable() ;
      // Str_obj constantObj = new Str_obj();
      
      String token = PeekToken() ;
      
      variable.mCout = true ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetCout_Expression() 2841 :" + token ) ;
      
      if ( G.BoolIdentifier( token ) ) {
        
        if ( !GetIdentifier( variable ) ) {
          return false ; // eat identifier ;
          
          // if ( G.sGlobalvariableTalbe.Find( str_obj ) )
          
        } // if 
        
        if ( variable.GetType().equals( "-1" ) ) { 
          if ( G.sDebug )
            G.Println( "return false 1657 :" + token ) ;

          return false ;
        } // if 
        
        
        
        if ( !GetRest_of_Identifier_started_basic_exp( variable, passValue ) ) return false ;
        
        
        return true ;
      } // if
      else if ( token.equals( "++" ) ||
                token.equals( "--" ) ) {
        token = GetToken() ; // eat ++ --
        
        token = PeekToken() ;
        while ( token.equals( "++" ) || 
                token.equals( "--" ) ) {
          token = GetToken() ;
          token = PeekToken() ;
        } // while
        
        token = GetToken() ; // it must not ++ or --
        if ( !G.BoolIdentifier( token ) ) return false ; // if not ++ -- or ident , it is error 
        
        
        
      } // else if ++ or -- case
      else if ( G.BoolSign( token ) ) {
        String signStr1 = GetToken() ; // eat sign 
        
        
        token = PeekToken() ;
        while ( G.BoolSign( token ) ) {
          String signStr2 = GetToken() ;
          
          if ( signStr1.equals( signStr2 ) ) signStr1 = "+" ; // -- => +   ++ => +
          else signStr1 = "-" ; // +- => -         -+ => -
          
          PeekToken() ;
        } // while
        
        variable.mPassSign = signStr1 ;
        
        if ( !GetSigned_basic_expression( signStr1, variable, passValue ) ) return false ;
        
        
        
      } // else if + or - or !
      
      else {
        
        if ( token.equals( "(" ) ) {
          GetToken() ; // eat (
          
          variable.mCout = false ;
          
          if ( !GetExpression( variable, passValue ) ) return false ; // ( 1 << 2 ) must computed
          
          variable.mCout = true ;
          
          token = PeekToken() ;
          // if ( G.sDebug )
          //   G.Println( "1976" + token ) ;
          
          if ( !GetToken().equals( ")" ) ) return false ;
          
        } // if 
        else {
          if ( !GetConstant( variable ) ) return false ;
          
          
          
          if ( G.sDebug )
            variable.StdPrint() ;
          
        } // else 
        
        // Variable variable2 = new Variable() ;
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
        /*
        G.Println( "yoyoyoyooyoyooyoyyoyoyyo 1644" ) ;
        variable.StdPrint();
        variable2.StdPrint();
        */
        /*
        if ( !variable.GetValue().equals( "unset" ) ) 
          G.Compute( variable, variable.mPassSign, variable ) ;
        */
      } // else if 
        
      
      
      return true ;
    } catch ( Exception e ) {
      if ( G.sDebug )
        G.Println( "GetBasic_Expression() return false error 2011" ) ;
      return false ;
    } // catch()
    
  } // GetCout_Expression()
  
  public boolean GetSigned_basic_expression( String sign, Variable variable,
                                             PassValue passValue ) throws Throwable {
    // signed_basic_expression
    // : Identifier rest_of_Identifier_started_signed_basic_exp
    // | ( Constant | '(' expression ')' ) rest_of_maybe_logical_OR_exp
    try {
      // Str_obj str_obj = new Str_obj() ;
      
      // Str_obj constantObj = new Str_obj();
      String token = PeekToken() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetSigned_basic_expression 2626 :" + token ) ;
      
      if ( G.BoolIdentifier( token ) ) {
        if ( !GetIdentifier( variable ) ) return false ; // eat identifier ;
        
        if ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) {
          if ( variable.GetValue().charAt( 0 ) == '-' ) {
            if ( sign.equals( "-" ) ) variable.ChangeSign() ;
          } // if
          
          else if ( sign.equals( "-" ) ) {
            variable.SetValue( sign + variable.GetValue() ) ;
          } // else if
          
          if ( sign.equals( "!" ) ) {
            G.sTypeError = true ;
            return false ;
          } // if
          
        } // if
        else if ( variable.GetType().equals( "bool" ) ) { // another type must error 
          
          if ( sign.equals( "!" ) ) {
            variable.ChangeBool() ;
          } // if
        
        } // else if
        
        
        if ( !GetRest_of_Identifier_started_signed_basic_exp( variable, passValue ) ) return false ;
        
        return true ;
      } // if
      else {
        if ( token.equals( "(" ) ) {
          GetToken() ; // eat ( 
          
          if ( !GetExpression( variable, passValue ) ) return false ;
          
          if ( !GetToken().equals( ")" ) ) return false ;
          
        } // if ( token.equals( "(" ) ) 
        else {
          if ( !GetConstant( variable ) ) return false ;
        } // else
        
        if ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) {
          if ( variable.GetValue().charAt( 0 ) == '-' ) {
            if ( sign.equals( "-" ) ) variable.ChangeSign() ;
          } // if
          
          else if ( sign.equals( "-" ) ) {
            variable.SetValue( sign + variable.GetValue() ) ;
          } // else if
          
          if ( sign.equals( "!" ) ) {
            G.sTypeError = true ;
            return false ;
          } // if
          
        } // if
        else if ( variable.GetType().equals( "bool" ) ) { // another type must error 
          
          if ( sign.equals( "!" ) ) {
            variable.ChangeBool() ;
          } // if
        
        } // else if
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
      } // else 
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetSigned_basic_expression()
  
  
  public boolean GetRest_of_expression( Variable variable, PassValue passValue ) throws Throwable {
    // rest_of_expression
    // : ',' basic_expression [ rest_of_expression ]
    // | '?' expression ':' basic_expression [ rest_of_expression ]
    
    try {
      
      String token = PeekToken();
      
      if ( token.equals( "," ) ) {
        GetToken() ; // eat ,
        
        if ( !GetBasic_Expression( variable, passValue ) ) return false ;
        
        token = PeekToken() ;
        
        if ( token.equals( "," ) || 
             token.equals( "?" ) ) {
          if ( !GetRest_of_expression( variable, passValue ) ) return false ;
        } // if 
        
        return true ;
        
        
      } // if
      else if ( token.equals( "?" ) ) {
        GetToken() ; // eat ?
        
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ":" ) ) return false ;
        
        if ( !GetBasic_Expression( variable, passValue ) ) return false ;
        
        token = PeekToken() ;
        
        if ( token.equals( "," ) || 
             token.equals( "?" ) ) {
          if ( !GetRest_of_expression( variable, passValue ) ) return false ;
        } // if 
        
        return true ;
      } // else if 
      else {
        return false ;
      } // else
      
      
        
      // return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_expression()
  
  public boolean GetRest_of_declarators( Variable var ) throws Throwable {
    // rest_of_declarators 
    // : [ '[' Constant ']' ] 
    //   { ',' Identifier [ '[' Constant ']' ] } ';'
    
    try {
      // Str_obj str_obj = new Str_obj() ;
      // Str_obj constantObj = new Str_obj();
      String token = PeekToken();
      Variable variable = new Variable() ;
      
      if ( token.equals( "[" ) ) {
        GetToken() ; // eat [
        
        // Variable variable2 = new Variable() ;
        
        if ( !GetConstant( variable ) ) return false ;
        
        
        
        if ( !GetToken().equals( "]" ) ) return false ;
        
      } // if
      
      if ( variable.GetValue().equals( "unset" ) ) {
        G.sVariableTalbe.mTable.add( new Variable( var.GetType(), var.GetName() ) ) ;
      } // if
      else if ( variable.GetType().equals( "int" ) ) {
        G.sVariableTalbe.mTable.add( new Variable( var.GetType(), 
                                                   var.GetName(), 
                                                   true, 
                                                   G.StrToInt( variable.GetValue() ) ) ) ;
      } // else if 
      else {
        G.Println( "Amazing type in 3554!" ) ;
      } // else
      
      token = PeekToken();
      
      while ( token.equals( "," ) ) {
        GetToken() ; // eat ,
        Variable variable1 = new Variable() ;
        Variable variable2 = new Variable() ;
        
        
        if ( !GetIdentifier( variable1 ) ) return false ;
        
        token = PeekToken();
        
        if ( token.equals( "[" ) ) {
          GetToken() ; // eat [
        
          if ( !GetConstant( variable2 ) ) return false ;
        
          if ( !GetToken().equals( "]" ) ) return false ;
        
          token = PeekToken();
        } // if token.equals( "[" )
      
        if ( variable.GetValue().equals( "unset" ) ) {
          G.sVariableTalbe.mTable.add( new Variable( var.GetType(), variable1.GetName() ) ) ;
        } // if
        else if ( variable.GetType().equals( "int" ) ) {
          G.sVariableTalbe.mTable.add( new Variable( var.GetType(), 
                                                     variable1.GetName(), 
                                                     true, 
                                                     G.StrToInt( variable2.GetValue() ) ) ) ;
        } // else if 
        else {
          G.Println( "Amazing type in 3554!" ) ;
        } // else
        
      } // while
      
      if ( !GetToken().equals( ";" ) ) return false ;
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_declarators()
  
  
  
  public boolean GetRest_of_Identifier_started_basic_exp( Variable variable,
                                                          PassValue passValue ) throws Throwable {
    // rest_of_Identifier_started_basic_exp
    // : [ '[' expression ']' ]
    //   [ PP | MM ]
    //   ( assignment_operator basic_expression 
    //     | 
    //     rest_of_maybe_logical_OR_exp 
    //   )
    // | '(' [ actual_parameter_list ] ')' rest_of_maybe_logical_OR_exp
    try {
      
      
        
      String token = PeekToken() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_Identifier_started_basic_exp() 2192:" + token ) ;
      
      
        
      if ( token.equals( "(" ) ) {
        GetToken() ; // eat ( 
        
        token = PeekToken() ;
        
        VariableTalbe fTable = new VariableTalbe() ;
        
        
        if ( !GetActual_parameter_list( fTable, passValue ) ) return false ;
        
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        ExcuteFunc( passValue, variable, fTable ) ;
        
        
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
        // var.StdPrint() ;
        
        return true ;
      } // if 
      
      
      else {
        
        Variable variable2 = new Variable() ;
        if ( token.equals( "[" ) ) {
          GetToken(); // eat [
        
          if ( !GetExpression( variable2, passValue ) ) {
            return false ;
          } // if
        
          if ( !GetToken().equals( "]" ) ) return false ;
          
          variable.mIsElement = true ;
          variable.mIndex = G.StrToInt( variable2.GetValue() ) ;
        } // if [ expression ]
        
        token = PeekToken() ;
        
        int sum = 0 ;
        boolean hadPP = false ;
        
        if ( token.equals( "++" ) ||
             token.equals( "--" ) ) {
          if ( token.equals( "++" ) ) sum += 1 ;
          if ( token.equals( "--" ) ) sum -= 1 ;
          GetToken(); // eat ++ or -- 
          
          hadPP = true ;
        } // if ++ or --
        
        Variable varSum = new Variable( "int", "", ( "" + sum ) ) ;
        
        if ( hadPP )
          G.Compute( variable, "+", varSum ) ;
          
        // variable.StdPrint( ) ;
        
        if ( passValue.mRealStatement && hadPP )
          IdentAssign( variable, variable2, variable ) ;
        
        // variable.StdPrint( ) ;
        
        token = PeekToken() ;
      
        // G.Println( token + token + token ) ;
        if ( G.BoolAssignment_operator( token ) ) {
          String assignop = GetToken(); // eat assign 
          
          if ( G.sDebug )
            G.Println( "Eat assignop 2991:" + assignop ) ;
          
          Variable variable3 = new Variable() ;
          
          if ( !GetBasic_Expression( variable3, passValue ) ) return false ;
        
          
          // mAssignTable.add( new Variable( variable ) ) ;
          // mAssignTable.add( new Variable( variable2 ) ) ;
          // mAssignTable.add( new Variable( variable3 ) ) ;
          
          if ( passValue.mRealStatement )
            IdentAssign( variable, variable2, variable3 ) ;
          
          
          if ( G.sDebug ) {
            G.Println( "3488 assign table add ========" ) ;
            variable.StdPrint() ;
            variable2.StdPrint() ;
            variable3.StdPrint() ;
            G.Println( "3488 end table add ========" ) ;
            
            /*
            for ( int i = 0 ; i < mAssignTable.size() ; i ++ ) 
              mAssignTable.get( i ).StdPrint() ;
            */
            
          } // if
          
          // G.Println( "!!!!!!!!!!!!!!!" ) ;
          
          variable.SetType( variable3.GetType() ) ;
          // variable.SetName( variable3.GetName() ) ;
          // variable.SetValue( variable3.GetValue() ) ;
          
          if ( !variable3.mIsArray && !variable3.mIsElement )
            variable.SetValue( variable3.GetValue() ) ;
          else if ( variable3.mIsArray && variable3.mIsElement ) {
            variable.SetValue( variable3.mArrTable.get( variable3.mIndex ) ) ;
          } // else if
          
          // G.Println( "???????????????????" ) ;
          
          return true ;
        } // if assignment
        else {
        
          Variable variable3 = new Variable() ;
          
          if ( !variable2.GetValue().equals( "unset" ) ) {
            GetArrayValue( variable, variable2 ) ;
          } // if
          
          
          if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ; // 
          // //////// must modified variable3 ( variable3 is dependon variable )
          
        } // else // if not  Assignment_operator
        
        return true ;
      } // else
        
        
      
      // return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
    
  } // GetRest_of_Identifier_started_basic_exp()
  
  public boolean GetRest_of_PPMM_Identifier_started_basic_exp( Variable variable, 
                                                               PassValue passValue ) throws Throwable {
    // rest_of_PPMM_Identifier_started_basic_exp
    // : [ '[' expression ']' ]
    //   [ PP | MM ] 
    //   ( assignment_operator basic_expression 
    //     | 
    //     rest_of_maybe_logical_OR_exp 
    //   )
    // | '(' [ actual_parameter_list ] ')' rest_of_maybe_logical_OR_exp
    try {
      // Variable variable = new Variable() ;
      
      String token = PeekToken() ;
      if ( token.equals( "(" ) ) {
        GetToken() ; // eat ( 
        
        token = PeekToken() ;
        
        
        VariableTalbe fTable = new VariableTalbe() ;
        
        if ( !GetActual_parameter_list( fTable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        
        ExcuteFunc( passValue, variable, fTable ) ;
        
        
        
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
        return true ;
      } // if 
      
      
      else {
        if ( token.equals( "[" ) ) {
          GetToken();
        
          Variable variable2 = new Variable();
          if ( !GetExpression( variable2, passValue ) ) {
            return false ;
          } // if
        
          if ( !GetToken().equals( "]" ) ) return false ;
        
          variable.mIsElement = true ;
          variable.mIndex = G.StrToInt( variable2.GetValue() ) ;
          
          
        } // if [ expression ]
        
        token = PeekToken();
      
        if ( token.equals( "++" ) ||
             token.equals( "--" ) ) {
          GetToken(); // eat ++ or --
        } // if ++ or --
        
        token = PeekToken() ;
      
        if ( G.BoolAssignment_operator( token ) ) {
          GetToken(); // eat = 
        
          if ( !GetBasic_Expression( variable, passValue ) ) return false ;
        
        
          return true ;
        } // if assignment
        else {
        
          if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
          
        } // else // if not  Assignment_operator
        
        return true ;
      } // else
        
        
      // return true ;
      
    } catch ( Exception e ) {
      return false ;
    } // catch()
    
  } // GetRest_of_PPMM_Identifier_started_basic_exp()
  
  public boolean GetRest_of_maybe_logical_OR_exp( Variable variable,
                                                  PassValue passValue ) throws Throwable {
    // rest_of_maybe_logical_OR_exp 
    // : rest_of_maybe_logical_AND_exp { OR maybe_logical_AND_exp }
    
    try {
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_logical_OR_exp() 2312 :" + token ) ;
        
        
      if ( !GetRest_of_maybe_logical_AND_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "||" ) ) {
        GetToken() ; // eat || 
        
        if ( G.sDebug )
          G.Println( "Eat token in GetRest_of_maybe_logical_OR_exp() 2312 :" + token ) ;
      
        Variable variable2 = new Variable();
        if ( !GetMaybe_logical_AND_exp( variable2, passValue ) ) return false ;
        
        
        G.Compute( variable, "||", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
        
        
        
  } // GetRest_of_maybe_logical_OR_exp()
  
  public boolean GetRest_of_maybe_logical_AND_exp( Variable variable,
                                                   PassValue passValue ) throws Throwable {
    // rest_of_maybe_logical_AND_exp 
    // : rest_of_maybe_bit_OR_exp { AND maybe_bit_OR_exp }
    try {
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_logical_AND_exp() 2342 :" + token ) ;
      
      if ( !GetRest_of_maybe_bit_OR_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "&&" ) ) {
        GetToken() ; // eat &&
      
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_logical_AND_exp() 2342 :" + token ) ;
        
        Variable variable2 = new Variable();
          
        if ( !GetMaybe_bit_OR_exp( variable2, passValue ) ) return false ;
        
        
        G.Compute( variable, "&&", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_logical_AND_exp()
  
  public boolean GetRest_of_maybe_bit_OR_exp( Variable variable,
                                              PassValue passValue ) throws Throwable {
    // rest_of_maybe_bit_OR_exp 
    // : rest_of_maybe_bit_ex_OR_exp { '|' maybe_bit_ex_OR_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_bit_OR_exp() 2370 :" + token ) ;
        
      if ( !GetRest_of_maybe_bit_ex_OR_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "|" ) ) {
        GetToken() ; // eat |
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_bit_OR_exp() 2370 :" + token ) ;
          
        Variable variable2 = new Variable();
      
        if ( !GetMaybe_bit_ex_OR_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "|", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_bit_OR_exp()
  
  public boolean GetRest_of_maybe_bit_AND_exp( Variable variable,
                                               PassValue passValue ) throws Throwable {
    // rest_of_maybe_bit_AND_exp 
    // : rest_of_maybe_equality_exp { '&' maybe_equality_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_bit_AND_exp() 2396 :" + token ) ;
      
      if ( !GetRest_of_maybe_equality_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "&" ) ) {
        GetToken() ; // eat &
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_bit_AND_exp() 2396 :" + token ) ;
        
        Variable variable2 = new Variable();
      
        if ( !GetMaybe_equality_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "&", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_bit_AND_exp()

  
  public boolean GetRest_of_maybe_bit_ex_OR_exp( Variable variable,
                                                 PassValue passValue ) throws Throwable {
    // rest_of_maybe_bit_ex_OR_exp 
    // : rest_of_maybe_bit_AND_exp { '^' maybe_bit_AND_exp }
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_bit_ex_OR_exp() 2425 :" + token ) ;
      
      if ( !GetRest_of_maybe_bit_AND_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "^" ) ) {
        GetToken() ; // eat ^
      
        if ( !GetMaybe_bit_AND_exp( variable, passValue ) ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_bit_ex_OR_exp()
  
  public boolean GetRest_of_Identifier_started_signed_basic_exp( Variable variable, 
                                                                 PassValue passValue ) throws Throwable {
    // rest_of_Identifier_started_signed_basic_exp
    // : [ '[' expression ']' ]
    //   [ PP | MM ] 
    //   rest_of_maybe_logical_OR_exp
    // | '(' [ actual_parameter_list ] ')' rest_of_maybe_logical_OR_exp
    try {
      // Variable variable = new Variable() ;
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_Identifier_started_signed_basic_exp() 2455 :" + token ) ;
        
      
      if ( token.equals( "(" ) ) { 
        // '(' [ actual_parameter_list ] ')' rest_of_maybe_logical_OR_exp
        GetToken(); // eat ( 
        
        token = PeekToken();
        
        VariableTalbe fTable = new VariableTalbe() ;
        
        if ( !GetActual_parameter_list( fTable, passValue ) ) return false ;
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        
        ExcuteFunc( passValue, variable, fTable ) ;
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
        
        return true ;
      } // if 
      else {
        if ( token.equals( "[" ) ) {
          GetToken() ; // eat [
          
          Variable variable2 = new Variable() ;
          if ( !GetExpression( variable2, passValue ) ) return false ;
          if ( !GetToken().equals( "]" ) ) return false ;
          
          variable.mIsElement = true ;
          variable.mIndex = G.StrToInt( variable2.GetValue() ) ;
          
          token = PeekToken() ;
        } // if
        
        if ( token.equals( "++" ) || 
             token.equals( "--" ) ) {
          GetToken() ; // eat ++ or --
        } // if 
        
        if ( !GetRest_of_maybe_logical_OR_exp( variable, passValue ) ) return false ;
      } // else
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_Identifier_started_signed_basic_exp()
  
  
  public boolean GetRest_of_maybe_equality_exp( Variable variable,
                                                PassValue passValue ) throws Throwable {
    // rest_of_maybe_equality_exp 
    // : rest_of_maybe_relational_exp
    //   { ( EQ | NEQ ) maybe_relational_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_equality_exp() 2503 :" + token ) ;
        
      if ( !GetRest_of_maybe_relational_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "==" ) || token.equals( "!=" ) ) {
        String symbol = GetToken() ; // eat == or !=
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_equality_exp() 2503 :" + token ) ;
        
        Variable variable2 = new Variable() ;
        
        if ( !GetMaybe_relational_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_equality_exp()
  
  public boolean GetRest_of_maybe_relational_exp( Variable variable,
                                                  PassValue passValue ) throws Throwable {
    // rest_of_maybe_relational_exp 
    // : rest_of_maybe_shift_exp
    //   { ( '<' | '>' | LE | GE ) maybe_shift_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_relational_exp() 2531 :" + token ) ;
        
      if ( !GetRest_of_maybe_shift_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "<" ) || 
              token.equals( ">" ) || 
              token.equals( "<=" ) || 
              token.equals( ">=" ) ) {
        String symbol = GetToken() ; // eat > oe < or <= or >=
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_relational_exp() 2531 :" + token ) ;
        
        Variable variable2 = new Variable() ;
      
        if ( !GetMaybe_shift_exp( variable2, passValue ) ) return false ;
        
        // G.Println( "3917" ) ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_relational_exp()
  
  public boolean GetRest_of_maybe_shift_exp( Variable variable,
                                             PassValue passValue ) throws Throwable {
    // rest_of_maybe_shift_exp 
    // : rest_of_maybe_additive_exp { ( LS | RS ) maybe_additive_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_shift_exp() 2562 :" + token ) ;
        
      if ( !GetRest_of_maybe_additive_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      
      
      
      while ( token.equals( "<<" ) || 
              token.equals( ">>" ) ) {
          
        if ( variable.mCout ) {
          // AddToCoutBuffer( variable ) ;
          return true ;
        } // if

        String symbol = GetToken() ; // eat << or >>
        
        if ( G.sDebug )
          G.Println( "Eat token in GetRest_of_maybe_shift_exp() 2562 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        // G.Print( "!!!!!!" ) ;
        // variable.StdPrint() ;
          
        if ( !GetMaybe_additive_exp( variable2, passValue ) ) return false ;
        
        if ( variable.GetName().equals( "cout" ) ) {
          AddToCoutBuffer( variable2 ) ;
        } // if
        else {
          G.Compute( variable, symbol, variable2 ) ;
          
          if ( G.sTypeError ) return false ;
        } // else
        
        token = PeekToken();
      } // while
      
      
      
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_shift_exp()
  
  public boolean GetRest_of_maybe_additive_exp( Variable variable,
                                                PassValue passValue ) throws Throwable {
    // rest_of_maybe_additive_exp 
    // : rest_of_maybe_mult_exp { ( '+' | '-' ) maybe_mult_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_additive_exp() 2591 :" + token ) ;
        
      if ( !GetRest_of_maybe_mult_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "+" ) || 
              token.equals( "-" ) ) {
        String symbol = GetToken() ; // eat + or - 
      
        if ( G.sDebug )
          G.Println( "Eat token in GetRest_of_maybe_additive_exp() 2591 :" + token ) ;
        
        Variable variable2 = new Variable() ;
        
        
        if ( !GetMaybe_mult_exp( variable2, passValue ) ) return false ;
        
        // G.Print("111111111111") ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        
        
        // G.Print("dhhhhhhhhhhhhh") ;
        
        if ( G.sTypeError ) return false ;
        
        // G.Print("dhhhhhhhhhhhhh") ;
        
        if ( G.sDebug ) {
          G.Print( "3683 add value " ) ;
          variable.StdPrint() ;
        } // if
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      // G.Print( "asdasd" ) ;
      return false ;
    } // catch()
  } // GetRest_of_maybe_additive_exp()
  
  public boolean GetRest_of_maybe_mult_exp( Variable variable,
                                            PassValue passValue ) throws Throwable {
    // rest_of_maybe_mult_exp 
    // : { ( '*' | '/' | '%' ) unary_exp }  /* could be empty ! */
    try {

      String token = PeekToken();
      
      // PassValue passValue = new PassValue() ; // ////////////////////////////////////////////// test //
      
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_maybe_mult_exp() 2627:" + token ) ;
        
      if ( !token.equals( "*" ) &&
           !token.equals( "/" ) &&
           !token.equals( "%" ) ) {
        
        if ( G.sDebug )
          G.Println( "unused token in GetRest_of_maybe_mult_exp() 2627:" + token ) ;
        return true ;
      } // if  
      
      
        
        
        
      
      while ( token.equals( "*" ) || 
              token.equals( "/" ) ||
              token.equals( "%" ) ) {
          
        if ( G.sDebug )
          G.Println( "Eat first token in GetRest_of_maybe_mult_exp() 2627:" + token ) ;
            
        String symbol = GetToken() ; // eat ( * or / or % )
        
        Variable variable2 = new Variable() ;
        
        if ( !GetUnary_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        
        token = PeekToken();
      } // while
      
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_maybe_mult_exp()
  
  
  
  public boolean GetMaybe_logical_AND_exp( Variable variable, 
                                           PassValue passValue ) throws Throwable {
    // maybe_logical_AND_exp 
    // : maybe_bit_OR_exp { AND maybe_bit_OR_exp }
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_logical_AND_exp() 2661 :" + token ) ;
        
      if ( !GetMaybe_bit_OR_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "&&" ) ) {
        GetToken() ; // eat &&
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_logical_AND_exp() 2661 :" + token ) ;
      
        Variable variable2 = new Variable();
          
        if ( !GetMaybe_bit_OR_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "&&", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
      
        // G.Println("11111111111111111111looks OK") ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_logical_AND_exp()
  
  public boolean GetMaybe_bit_OR_exp( Variable variable, 
                                      PassValue passValue ) throws Throwable {
    // maybe_bit_OR_exp 
    // : maybe_bit_ex_OR_exp { '|' maybe_bit_ex_OR_exp }
        
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_bit_OR_exp() 2689 :" + token ) ;
      
      if ( !GetMaybe_bit_ex_OR_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "|" ) ) {
        GetToken() ; // eat |
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_bit_OR_exp() 2689 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        if ( !GetMaybe_bit_ex_OR_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "|", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
      
      
        // G.Println("11111111111111111111looks OK") ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_bit_OR_exp()
  
  public boolean GetMaybe_bit_AND_exp( Variable variable, 
                                       PassValue passValue ) throws Throwable {
    // maybe_bit_AND_exp 
    // : maybe_equality_exp { '&' maybe_equality_exp }
 
    try {
      
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_bit_AND_exp() 2719 :" + token ) ;
        
      if ( !GetMaybe_equality_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "&" ) ) {
        GetToken() ; // eat &
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_bit_AND_exp() 2719 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        if ( !GetMaybe_equality_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "&", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      
      // G.Println("11111111111111111111looks OK") ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_bit_AND_exp()
  
  public boolean GetMaybe_bit_ex_OR_exp( Variable variable, 
                                         PassValue passValue ) throws Throwable {
    // maybe_bit_ex_OR_exp 
    // : maybe_bit_AND_exp { '^' maybe_bit_AND_exp }
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_bit_ex_OR_exp() 2747 :" + token ) ;
        
      if ( !GetMaybe_bit_AND_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "^" ) ) {
        GetToken() ; // eat ^
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_bit_ex_OR_exp() 2747 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        if ( !GetMaybe_bit_AND_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, "^", variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_bit_ex_OR_exp()

  public boolean GetMaybe_relational_exp( Variable variable, 
                                          PassValue passValue ) throws Throwable {
    // maybe_relational_exp 
    // : maybe_shift_exp 
    //   { ( '<' | '>' | LE | GE ) maybe_shift_exp }
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_relational_exp() 2776 :" + token ) ;
        
      if ( !GetMaybe_shift_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      while ( token.equals( "<" ) ||
              token.equals( ">" ) ||
              token.equals( "<=" ) ||
              token.equals( ">=" ) ) {
        String symbol = GetToken() ; // eat ( '<' | '>' | LE | GE )
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_relational_exp() 2776 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        if ( !GetMaybe_shift_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      // G.Println( "11111111111111111111looks OK" ) ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_relational_exp()
  
  public boolean GetMaybe_equality_exp( Variable variable, 
                                        PassValue passValue ) throws Throwable {
    // maybe_equality_exp 
    // : maybe_relational_exp 
    // { ( EQ | NEQ ) maybe_relational_exp}
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_equality_exp() 2808 :" + token ) ;
        
      if ( !GetMaybe_relational_exp( variable, passValue ) ) return false ;
      // G.Println("0000000000000000000000looks OK") ;
      token = PeekToken();
      
      while ( token.equals( "==" ) ||
              token.equals( "!=" ) ) {
        String symbol = GetToken() ; // eat == or !=
        
        if ( G.sDebug )
          G.Println( "Eat first token in GetMaybe_equality_exp() 2808 :" + token ) ;
      
        Variable variable2 = new Variable();
        
        if ( !GetMaybe_relational_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
      
      // G.Println("0000000000000000000000looks OK") ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_equality_exp()
  
  
  public boolean GetMaybe_shift_exp( Variable variable, 
                                     PassValue passValue ) throws Throwable {
    // maybe_shift_exp 
    // : maybe_additive_exp { ( LS | RS ) maybe_additive_exp }
    try {
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_shift_exp() 2837 :" + token ) ;
        
      if ( !GetMaybe_additive_exp( variable, passValue ) ) return false ;
      
      token = PeekToken();
      
      /*
      while ( token.equals( "<<" ) ||
              token.equals( ">>" ) ) {
        GetToken() ; // eat << or >>
      
        if ( !GetMaybe_additive_exp( variable ) ) return false ;
        
        token = PeekToken();
      } // while
      */
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_shift_exp()
  
  public boolean GetMaybe_additive_exp( Variable variable, 
                                        PassValue passValue ) throws Throwable {
    // maybe_additive_exp 
    // : maybe_mult_exp { ( '+' | '-' ) maybe_mult_exp }
    try {
        
      String token = PeekToken();
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_additive_exp() 2865 :" + token ) ;
        
      if ( !GetMaybe_mult_exp( variable, passValue ) ) return false ;
      
      
      token = PeekToken();
      while ( token.equals( "+" ) ||
              token.equals( "-" ) ) {
        String symbol = GetToken() ; // eat + or -
        
        Variable variable2 = new Variable();
      
        if ( !GetMaybe_mult_exp( variable2, passValue ) ) return false ;
        
        G.Compute( variable, symbol, variable2 ) ;
        
        if ( G.sTypeError ) return false ;
        
        token = PeekToken();
      } // while
        
      // G.Println("11111111111111111111looks OK") ;
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_additive_exp()
  
  public boolean GetMaybe_mult_exp( Variable variable, 
                                    PassValue passValue ) throws Throwable {
    // maybe_mult_exp 
    // : unary_exp rest_of_maybe_mult_exp
    try {
      String token = PeekToken();
      // PassValue passValue = new PassValue() ; // /////////////////////////////// test 
      
      
      if ( G.sDebug )
        G.Println( "the first token in GetMaybe_mult_exp() 2909 :" + token ) ;
        
      if ( !GetUnary_exp( variable, passValue ) ) return false ;
      
      // Variable variable2 = new Variable() ; // this line maybe bug!!!!!!!!!!!!!!!!!!!!!!!!!!!
      
      if ( !GetRest_of_maybe_mult_exp( variable, passValue ) ) return false ;
      
      /*
      if ( !variable2.GetValue().equals( "unset" ) )
        G.Compute( variable, variable2.mPassSign, variable2 ) ;
      */
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetMaybe_mult_exp()
  
  
  
  public boolean GetActual_parameter_list( VariableTalbe fTable, 
                                           PassValue passValue ) throws Throwable {
    // actual_parameter_list 
    // : non_comma_expression { ',' non_comma_expression }
    try {
      String token = PeekToken();
        
      if ( G.sDebug )
        G.Println( "the first token in GetActual_parameter_list() 3385 :" + token ) ;
      
        
      Variable variable = new Variable() ;
        
      if ( !GetNon_comma_expression( variable, passValue ) ) return false ;
      
      fTable.mTable.add( new Variable( variable ) ) ;
      
      
        
      token = PeekToken() ;
      
      while ( token.equals( "," ) ) {
        GetToken() ; // eat ,
        
        Variable variable1 = new Variable() ;
        
        if ( !GetNon_comma_expression( variable1, passValue ) ) return false ;
        
        fTable.mTable.add( new Variable( variable1 ) ) ;
        
        token = PeekToken() ;
      } // while
      
      
        
      // G.Println( "OOOOOOOOOOOOOO" ) ;
      
      return true ;
    } catch ( Exception e ) {
        
      // G.Println( "OOOOOOOOOOOOOO" ) ;
      return false ;
    } // catch()
        
  } // GetActual_parameter_list()
  
  public boolean GetNon_comma_expression( Variable variable, 
                                          PassValue passValue ) throws Throwable { 
  //  non_comma_expression
  //  : basic_expression [ rest_of_non_comma_expression ]
        
    try {
      // Variable variable = new Variable() ;
      String token = PeekToken();
        
      if ( G.sDebug )
        G.Println( "the first token in GetNon_comma_expression() 3412 :" + token ) ;
      
      // PassValue passValue = new PassValue() ;  
      
      if ( !GetBasic_Expression( variable, passValue ) ) return false ;
      
      token = PeekToken() ;
        
      if ( !token.equals( "?" ) ) return true ; 
      // if another token behind me ,it is not my business 
        
      if ( !GetRest_of_non_comma_expression() ) return false ;
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetNon_comma_expression() 
  
  public boolean GetRest_of_non_comma_expression() throws Throwable {
    // rest_of_non_comma_expression
    // : '?' expression ':' basic_expression [ rest_of_non_comma_expression ]
    try {
        
      G.Println( "!!!you should not call me 4843" ) ;
      
      Variable variable = new Variable() ;
      
      PassValue passValue = new PassValue() ;
        
      if ( !GetToken().equals( "?" ) ) return false ;
      
      if ( !GetExpression( variable, passValue ) ) return false ;
      
      if ( !GetToken().equals( ":" ) ) return false ;
      
      if ( !GetBasic_Expression( variable, passValue ) ) return false ;
      
      String token = PeekToken() ; // 
      
      if ( !token.equals( "?" ) ) return true ; // if another token behind me ,it is not my business 
      
      if ( !GetRest_of_non_comma_expression() ) return false ;
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetRest_of_non_comma_expression()
  
  public boolean GetRest_of_Identifier_started_unary_exp( Variable variable, 
                                                          PassValue passValue ) throws Throwable {
    // rest_of_Identifier_started_unary_exp 
    // : '(' [ actual_parameter_list ] ')'
    // | [ '[' expression ']' ] [ PP | MM ]
    try {
      // Variable variable = new Variable() ;
      String token = PeekToken();
      Variable variable2 = new Variable() ;
      
      if ( G.sDebug )
        G.Println( "the first token in GetRest_of_Identifier_started_unary_exp 4801 :" + token ) ;
      
      // variable.StdPrint() ;
      
      if ( token.equals( "(" ) ) {
        GetToken() ; // eat ( 
        
        token = PeekToken();
        
          
        
        
        VariableTalbe fTable = new VariableTalbe() ;
        
        if ( !GetActual_parameter_list( fTable, passValue ) ) return false ;
        
        // fTable.Print();
        
        if ( !GetToken().equals( ")" ) ) return false ;
        
        
        ExcuteFunc( passValue, variable, fTable ) ;
        
        return true ;
      } // if token == "(" 
      
      
      else if ( token.equals( "[" ) ) {
        GetToken() ; // eat [
        
        // PassValue passValue2 = new PassValue() ;
        
        if ( !GetExpression( variable2, passValue ) ) return false ;
        
        if ( !GetToken().equals( "]" ) ) return false ;
        
        variable.mIsElement = true ;
        variable.mIndex = G.StrToInt( variable2.GetValue() ) ;
        
      } // else if "[" case
      
      token = PeekToken() ;
      
      if ( token.equals( "++" ) ||
           token.equals( "--" ) ) {
        GetToken() ; // eat ++ or --
        // return true ;

      } // if 

      
      if ( !variable2.GetValue().equals( "unset" ) ) {
        GetArrayValue( variable, variable2 ) ;
      } // if
        
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
        
        
  } // GetRest_of_Identifier_started_unary_exp() 
  
  public boolean GetSigned_unary_exp( String sign, 
                                      Variable variable,
                                      PassValue passValue ) throws Throwable {
    // signed_unary_exp
    // : Identifier [ rest_of_Identifier_started_unary_exp ]
    // | Constant 
    // | '(' expression ')'
    try {
      String token = PeekToken() ;
      
      Str_obj constantObj = new Str_obj();
      
      if ( G.sDebug )
        G.Println( "the first token in GetSigned_unary_exp() 3029 :" + token ) ;
      
      if ( G.sPreserveWord.Find( token ) ) { // if identifier is preserve word , that is error
        GetToken() ; // eat int or float or string ...etc
        
        return false ;
      } // if 
      
      if ( G.BoolIdentifier( token ) ) {
        GetIdentifier( variable ); // eat identifier
        
        // variable.StdPrint();
        
        // change sign
        if ( sign.equals( "!" ) && variable.GetType().equals( "bool" ) ) {
          variable.ChangeBool() ;
        } // if 
        else if ( sign.equals( "!" ) && !variable.GetType().equals( "bool" ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        else if ( sign.equals( "-" ) && 
                  ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) ) {
          variable.ChangeSign() ;
        } // if 
        else if ( ( sign.equals( "-" ) || sign.equals( "+" ) ) && 
                  ( !variable.GetType().equals( "int" ) && !variable.GetType().equals( "float" ) ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        
        if ( variable.GetType().equals( "-1" ) ) { 
          if ( G.sDebug ) 
            G.Println( "Type -1 error 2681:" + variable.GetName() );
          return false ;
        } // if 
        
        
        token = PeekToken() ;
        
        if ( !token.equals( "(" ) &&
             !token.equals( "[" ) && 
             !token.equals( "++" ) &&
             !token.equals( "--" ) ) return true ;
        
             
             
        if ( !GetRest_of_Identifier_started_unary_exp( variable, passValue ) ) return false ;
             
        
        
        return true ;
      } // if
      
      else if ( token.equals( "(" ) ) {
        GetToken() ; // eat (
        if ( G.sDebug )
          G.Println( "GetToken:( 3075" ) ;
        
        // PassValue passValue = new PassValue() ;
          
        if ( !GetExpression( variable, passValue ) ) return false ;
        
        // change sign
        if ( sign.equals( "!" ) && variable.GetType().equals( "bool" ) ) {
          variable.ChangeBool() ;
        } // if 
        else if ( sign.equals( "!" ) && !variable.GetType().equals( "bool" ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        else if ( sign.equals( "-" ) && 
                  ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) ) {
          variable.ChangeSign() ;
        } // if 
        else if ( ( sign.equals( "-" ) || sign.equals( "+" ) ) && 
                  ( !variable.GetType().equals( "int" ) && !variable.GetType().equals( "float" ) ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        
        
        if ( !GetToken().equals( ")" ) ) return false ;
        if ( G.sDebug )
          G.Println( "GetToken:) 3085" ) ;
        
        
        return true ;
      } // else if
      
      else {
        if ( !GetConstant( variable ) ) return false ;
        
        // change sign
        if ( sign.equals( "!" ) && variable.GetType().equals( "bool" ) ) {
          variable.ChangeBool() ;
        } // if 
        else if ( sign.equals( "!" ) && !variable.GetType().equals( "bool" ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        else if ( sign.equals( "-" ) && 
                  ( variable.GetType().equals( "int" ) || variable.GetType().equals( "float" ) ) ) {
          variable.ChangeSign() ;
        } // if 
        else if ( ( sign.equals( "-" ) || sign.equals( "+" ) ) && 
                  ( !variable.GetType().equals( "int" ) && !variable.GetType().equals( "float" ) ) ) {
          G.sTypeError = true ;
          return false ;
        } // if 
        
        return true ;
        // GetToken() ;
      } // else if
      
      
        
      // return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
        
        
        
  } // GetSigned_unary_exp()
  
  public boolean GetUnary_exp( Variable variable,
                               PassValue passValue ) throws Throwable {
    // unary_exp
    // : { sign } signed_unary_exp
    // | ( PP | MM ) { PP | MM } Identifier [ '[' expression ']' ]
    try {
      
      String token = PeekToken() ;
      // Variable variable = new Variable() ;
      if ( G.sDebug )
        G.Println( "the first token in GetUnary_exp() 3813 :" + token ) ;
      
      if ( token.equals( "++" ) ||
           token.equals( "--" ) ) {
        GetToken() ; // eat ++ or -- 
        
        
        
        token = PeekToken() ;
        while ( token.equals( "++" ) ||
                token.equals( "--" ) ) {
          GetToken(); // eat ++ 
          token = PeekToken() ;
        } // while
        
        if ( !GetIdentifier( variable ) ) {
          return false ;
          // if ( variable.GetType().equals( "-1" ) ) return false ;
        } // if 
        
        if ( variable.GetType().equals( "-1" ) ) { 
          if ( G.sDebug ) 
            G.Println( "Type -1 error 2749:" + variable.GetName() );
          return false ;
        } // if 
        
        token = PeekToken() ;
       
        if ( !token.equals( "[" ) ) return true ; // another token is not my business 
       
        GetToken() ; // eat [
       
        // PassValue passValue = new PassValue() ;
        
        if ( !GetExpression( variable, passValue ) ) return false ;
       
        token = PeekToken() ;
       
        if ( !token.equals( "]" ) ) return false ; 
        
        return true ;
      } // if 
      
      String signStr1 = "" ;
      
      token = PeekToken() ;
        
      if ( G.BoolSign( token ) ) {
        signStr1 = GetToken() ;
        token = PeekToken() ;
      } // if
        
      while ( G.BoolSign( token ) ) {
        String signStr2 = GetToken() ; // eat + - or !
          
        if ( signStr1.equals( signStr2 ) ) signStr1 = "+" ; 
          // -- => +   ++ => +
        else if ( signStr1.equals( "!" ) || signStr2.equals( "!" ) )
        {
          G.Println( "Mr.Hsia you R bitCH" );
          G.sTypeError = true ;
          return false ;
        } // else if
        else signStr1 = "-" ; // +- => -         -+ => -
        
        PeekToken() ;
      } // while
      
           
      if ( !GetSigned_unary_exp( signStr1, variable, passValue ) ) return false ;
      
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetUnary_exp()
  
  
  public boolean GetIdentifier( Variable variable ) throws Throwable {
    try {
      String token = GetToken() ;
      
      if ( !G.BoolIdentifier( token ) ) return false ; 
      if ( G.sPreserveWord.Find( token ) ) return false ; // preserve word is error
      
      // G.Println( token ) ;
      // System.exit( 2 );
        
      if ( G.sVariableTalbe.Find( token ) ) {
        
        
        
        int index = G.sVariableTalbe.IndexOf( token ) ;
        
        // G.sVariableTalbe.mTable.get( index ).StdPrint() ;
        
        
        variable.Set( G.sVariableTalbe.Get( index ) ) ;
        
        // variable.StdPrint() ;
        
      } // if
      
      
      
      else if ( G.sFunctionTable.Find( token ) ) {
        int index = G.sFunctionTable.IndexOf( token ) ;
        Function function = G.sFunctionTable.Get( index ) ;
        variable.SetType( function.GetType() ) ; 
        variable.SetName( function.GetName() ) ;
        // G.Prinlnt( ) ;
      } // else if
      
      
      else {
        variable.SetName( token ) ; 
        variable.SetType( "-1" ) ;
      } // else 
      
      
      if ( G.sDebug ) {
        G.Print( "4179 identifier: " ) ;
        variable.StdPrint() ;
      } // if 
      
      return true ;
    } catch ( Exception e ) {
      
      G.Println( "GetIdentifier Error error 3057" ) ;
      return false ;
    } // catch()
  } // GetIdentifier()
  
  public boolean GetConstant( Variable variable ) throws Throwable {
    try {
      String token = GetToken() ;
      
      // if ( G.sDebug )
      //   G.Println( "constant:" + token ) ;
        
      // if ( G.BoolIdentifier( token ) ) return false ;
      if ( G.BoolParseNum( token ) ||
           G.BoolString( token ) ||
           G.BoolChar( token ) ||
           token.equals( "true" ) ||
           token.equals( "false" ) ) {

        variable.SetName( "-1" ) ;
        if ( token.equals( "true" ) ) variable.SetType( "bool" ) ;
        else if ( token.equals( "false" ) ) variable.SetType( "bool" ) ;
        else if ( token.charAt( 0 ) == '"' ) variable.SetType( "string" ) ;
        else if ( token.charAt( 0 ) == '\'' ) variable.SetType( "char" ) ;
        else if ( token.indexOf( "." ) == -1 ) variable.SetType( "int" ) ;
        else if ( token.indexOf( "." ) != -1 ) variable.SetType( "float" ) ;
        variable.SetValue( token ) ;
        
        if ( G.sDebug ) {
          G.Print( "4425 constant: " ) ;
          variable.StdPrint() ;
        } // if
        
        return true ;
         
         
         
      } // if
      
      
      
      
      return false ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetConstant()
  
  public boolean GetType_specifier( Str_obj str_obj ) throws Throwable {
    // type_specifier 
    // : INT | CHAR | FLOAT | STRING | BOOL
    try {
      String token = GetToken() ;
      
      if ( G.sDeclareWord.Find( token ) &&
           !token.equals( "void" ) ) { 
        str_obj.Set( token ) ;
        return true ;
      } // if 
      
      
      
      return false ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetType_specifier()
  
  public boolean GetSign( Str_obj str_obj ) throws Throwable {
    // sign
    // : '+' | '-' | '!'
    try {
      String token = GetToken() ;
      
      if ( !token.equals( "+" ) &&
           !token.equals( "-" ) &&
           !token.equals( "!" ) ) {
        return false ;
      } // if 
      
      
      
      return true ;
    } catch ( Exception e ) {
      return false ;
    } // catch()
  } // GetSign()
  
  public Variable ExcuteFunc( PassValue passValue,
                              Variable func,
                              VariableTalbe fTable ) throws Throwable {

    if ( !passValue.mRealStatement ) {
        
      if ( G.sDebug )
        G.Print( "!!!ExcuteFunc false Statement 5320\n" ) ;
        
      Function func1 = G.sFunctionTable.mTable.get( G.sFunctionTable.IndexOf( func.GetName() ) );
      func.SetType( func1.GetType() ) ; 
      func.SetValue( "unset" ) ; 
      return func ;
    } // if 
    
    String errorStr = func.GetName();
    
    int returnIndex = mTokenVecIndex ;
    int vsize = G.sVariableTalbe.mTable.size() ;
    int returnFuncVarIndex = G.sFuncVarIndex ;
    
    try {
      if ( G.sDebug )
        G.Print( "ExcuteFunc real Statement 5320\n" ) ;
        
        
      // fTable.Print();
        
      
        
      int funcIndex = G.sFunctionTable.mTable.get( G.sFunctionTable.IndexOf( func.GetName() ) ).GetIndex();
      
      // G.Println( funcIndex + "" ) ;
      
      mTokenVecIndex = funcIndex ;
      
      GetToken() ; // eat func name ;
      GetToken(); // eat func ( ;
      // G.Println( GetToken() ); // eat ( ;
      // mTokenVecIndex = funcIndex + 2 ; // int F1 ( int a, int b ) { statement }
                                          //          ^^^ start at this
                                    
      
                                          
                                          
      G.sFuncVarIndex = vsize ;
      GetFormal_parameter_list() ; // add FuncVariable ;
      
      GetToken() ; // eat )
      
      int i = 0 ;
      while ( i < fTable.mTable.size() ) {
        // copy ftable value to variable table
        Variable ftemp = fTable.mTable.get( fTable.mTable.size() - 1 - i ) ;
        Variable vtemp = G.sVariableTalbe.mTable.get( G.sVariableTalbe.mTable.size() - 1 - i ) ;
        
        if ( !ftemp.mIsArray )
          vtemp.SetValue( ftemp.GetValue() ) ;
        else if ( ftemp.mIsArray && !ftemp.mIsElement ) {
          
          for ( int j = 0 ; j < ftemp.GetLength() ; j ++ ) {
            vtemp.mArrTable.set( j, ftemp.mArrTable.get( j ) ) ;
          } // for
        } // else if 
        else if ( ftemp.mIsArray && ftemp.mIsElement ) {
          vtemp.SetValue( ftemp.mArrTable.get( ftemp.mIndex ) ) ;
        } // else if 
        
        /*
        for ( int j = 0 ; j < ftemp.GetLength() ; j ++ ) {
          G.Println( vtemp.mArrTable.get( j ) ) ;
        } // for
        */
        
        i ++ ;
      } // while
      
      // G.Println( "ioerrer" ) ;
      
      // G.sVariableTalbe.Print();
      
      /*
      G.Print( "!!5319" ) ;
      // G.Print( "" + G.sVariableTalbe.mTable.get(4).mIsRef ) ;
      G.sVariableTalbe.Print();
      */
      
      // mTokenVecIndex = mTokenVecIndex + 1 ; // int F1 ( int a, int b ) { statement }
                                   //                                  ^ start at this
      // G.Println( "????" + funcIndex ) ;
      
      // fTable.mTable.get( 3 ) ;
      // G.sVariableTalbe
      
      // G.sVariableTalbe.Print() ;
      
      PassValue passvalue = new PassValue() ;
      
      // G.sDealScope = true ;
      GetCompound_statement( passvalue ) ;
      // G.sDealScope = false ;
      
      
      
      for ( i = 0 ; i < fTable.mTable.size() ; i ++ ) { 
        // copy VariableTable (Formal_parameter ) to fTable,then del VariableTable (Formal_parameter )
        Variable fTemp = fTable.mTable.get( fTable.mTable.size() - 1 - i ) ;
        Variable vTemp =  G.sVariableTalbe.mTable.get( G.sVariableTalbe.mTable.size() - 1 ) ;
        
        if ( !fTemp.mIsArray )
          fTemp.SetValue( vTemp.GetValue() ) ;
        else if ( fTemp.mIsArray && !fTemp.mIsElement ) {
          
          for ( int j = 0 ; j < vTemp.GetLength() ; j ++ ) {
            fTemp.mArrTable.set( j, vTemp.mArrTable.get( j ) ) ;
          } // for
        } // else if
        else if ( fTemp.mIsArray && fTemp.mIsElement ) {
          fTemp.SetValue( vTemp.GetValue() ) ;
          fTemp.mArrTable.set( fTemp.mIndex, vTemp.GetValue() ) ;
        } // else if
        
        fTemp.mIsRef = vTemp.mIsRef ;
        
        G.sVariableTalbe.mTable.remove( G.sVariableTalbe.mTable.size() - 1 ) ;
      } // for
      
      // fTable.Print();
      // G.Println("yoyoamn") ;
      
      while ( fTable.mTable.size() > 0 ) { 
        // if var in ftable is reference or array change var in variabletable 
        Variable fTemp = fTable.mTable.get( fTable.mTable.size() - 1 ) ;
        int index = G.sVariableTalbe.IndexOf( fTemp.GetName() ) ;
        Variable vTemp = null ;
        if ( index != -1 )
          vTemp = G.sVariableTalbe.mTable.get( index ) ;
        
        if ( index != -1 && fTemp.mIsRef && !fTemp.mIsArray ) {
          vTemp.SetValue( fTemp.GetValue() ) ;
        } // if
        else if ( index != -1 && fTemp.mIsRef && fTemp.mIsArray && fTemp.mIsElement ) {
          vTemp.SetValue( fTemp.GetValue() ) ;
          vTemp.mArrTable.set( fTemp.mIndex, fTemp.GetValue() ) ;
        } // else if
        
        else if ( index != -1 && fTemp.mIsArray ) {
          for ( int j = 0 ; j < fTemp.GetLength() ; j ++ ) {
            vTemp.mArrTable.set( j, fTemp.mArrTable.get( j ) ) ;
          } // for
        } // else if
        
        
        fTable.mTable.remove( fTable.mTable.size() - 1 ) ;
      } // while 
      
      // G.Println( "yo 2" ) ;
      
      mTokenVecIndex = returnIndex ;
      G.sFuncVarIndex = returnFuncVarIndex ;
      
      while ( G.sVariableTalbe.mTable.size() > vsize ) {
        G.sVariableTalbe.mTable.remove( G.sVariableTalbe.mTable.size() - 1 ) ;
      } // while
      
      Variable variable = new Variable() ;
      
      func.SetType( G.sReturnVariable.GetType() ) ;
      func.SetValue( G.sReturnVariable.GetValue() ) ;
      
      if ( G.sDebug )
        G.Print( "ExcuteFunc end 5382\n" ) ;
      
      return variable ;
    } catch ( Exception e ) {
      
      G.Println( "excuteFunc Error error 5260:" + errorStr ) ;
      
      mTokenVecIndex = returnIndex ;
      G.sFuncVarIndex = returnFuncVarIndex ;
      
      while ( G.sVariableTalbe.mTable.size() > vsize ) {
        G.sVariableTalbe.mTable.remove( G.sVariableTalbe.mTable.size() - 1 ) ;
      } // while
      
      return fTable.mTable.get( 0 );
    } // catch()
  } // ExcuteFunc()
  
  
  public boolean IdentAssign( Variable ident, Variable ArrayIndex, Variable value ) throws Throwable {
    try {
        
      // ArrayIndex maybe unsset
      
      // if ( !ident.GetType().equals( value.GetType() ) )
      //   System.exit( 23 ) ;
      
      
      
      
      if ( G.sVariableTalbe.Find( ident.GetName() ) ) {
        int index = G.sVariableTalbe.IndexOf( ident.GetName() ) ;
        
        if ( !G.sVariableTalbe.Get( index ).mIsArray ) {
          G.sVariableTalbe.Get( index ).SetValue( value.GetValue() ) ;
        } // if if ident is not a array
        
        else {
          int arrIndex = G.StrToInt( ArrayIndex.GetValue() ) ;
          G.sVariableTalbe.Get( index ).mArrTable.set( arrIndex, value.GetValue() )  ;
        } // else
        
      } // if
      
      
      
      
      
      
      
      
      
      // ident.StdPrint() ;
      
      
      if ( G.sDebug )
        G.Println( "assign iden 4283:" + ident.GetName() + " assign value:" + value.GetValue() ) ;
      
      return true ;
    } catch ( Exception e ) {
      
      G.Println( "assign iden Error 4283" ) ;
      return false ;
    } // catch()
  } // IdentAssign()
  
  public Variable GetArrayValue( Variable ident, Variable ArrayIndex ) throws Throwable {
    try {
        
      
      Variable returnVariable = new Variable() ;
      int arrIndex = G.StrToInt( ArrayIndex.GetValue() ) ;
      
      if ( G.sVariableTalbe.Find( ident.GetName() ) ) {
        int index = G.sVariableTalbe.IndexOf( ident.GetName() ) ;
        
        
        
        // G.sGlobalvariableTalbe.Get( index ).mArrTable.set( arrIndex, value.GetValue() )  ;
        
        returnVariable.SetType( G.sVariableTalbe.Get( index ).GetType() ) ;
        returnVariable.SetValue( G.sVariableTalbe.Get( index ).mArrTable.get( arrIndex ) ) ;
        
        ident.SetType( G.sVariableTalbe.Get( index ).GetType() ) ;
        ident.SetValue( G.sVariableTalbe.Get( index ).mArrTable.get( arrIndex ) ) ;
        // G.Println( "!!!" + G.sGlobalvariableTalbe.Get( index ).mArrTable.get( arrIndex ) ) ;
        
      } // if
      
      
      
      
      
      // ident.StdPrint() ;
      
      // returnVariable.StdPrint() ;
      
      if ( G.sDebug )
        G.Println( "array ident 5820:" + ident.GetName() + " arrayindex:" + ArrayIndex.GetValue() ) ;
        
      // returnVariable.StdPrint() ;
      
      return returnVariable ;
    } catch ( Exception e ) {
      
      G.Println( "GetIdentifier Error error 5825" ) ;
      return ident ;
    } // catch()
  } // GetArrayValue()
  
  
  public void AddToCoutBuffer( Variable variable ) throws Throwable {
    
    if ( G.sDebug ) {
      G.Print( "the variable in cout Buffer 5493 : " ) ;
      variable.StdPrint() ;
    } // if
        
    if ( !variable.GetType().equals( "string" ) &&
         !variable.GetType().equals( "char" ) ) {
      mCoutBuffer += variable.GetValue() ;
    } // if 
    else if ( variable.GetType().equals( "string" ) ) {
      
      mCoutBuffer += variable.GetValue().substring( 1, variable.GetValue().length()-1 ) ;
    } // else if
    
    
        
  } // AddToCoutBuffer()
  
  public void StdCout() throws Throwable {
    String str = "" ;
    
    while ( mCoutBuffer.length() != 0 ) {
      // String str = "" ;
      int index = mCoutBuffer.indexOf( "\\n" ) ;
      
      if ( index == -1 ) {
        G.Print( mCoutBuffer ) ;
        mCoutBuffer = "" ;
      } // if
      else {
        G.Print( mCoutBuffer.substring( 0, index ) ) ;
        G.Println( "" );
        mCoutBuffer = mCoutBuffer.substring( index+2, mCoutBuffer.length() ) ;
      } // else 
      
      
    } // while 
    
    
    // G.Print( mCoutBuffer ) ;
    mCoutBuffer = "" ;
    
  } // StdCout()
  
  public void ClearCoutBuffer() {
    mCoutBuffer = "" ;
  } // ClearCoutBuffer()
  
  
  public int IndexOf( String str ) {
    return mstr.indexOf( str ) ;
  } // IndexOf()
  
  public String SubText( int begin, int end ) {
    return mstr.substring( begin, end ) ;
  } // SubText()
  
  public String GetText() {
    return mstr;
  } // GetText()
  
  public String GetTokenBuffer() {
    return mTokenVec.get( mTokenVecIndex ).GetToken() ;
  } // GetTokenBuffer()
  
  /*
  public int GetLineCount() {
    return mLine;
  } // GetLineCount()
  */
  
  public int Length() {
    return mstr.length() ;
  } // Length()
  
  public void Set( String str ) { // I hope I would not use this function....
    mstr = str ;
    // SetPeekVec() ;
  } // Set()
  
  /*
  public void ClearLineCount() {
    mLine = 0 ;
  } // ClearLineCount()
  */
} // class Text()

// Text end ///////////////////////////////////////////////////////////////////////


class Main {  // Please do not put 'public' before 'class'
  static int stest_num = 0 ;

  /*
  static String ReadInputText() throws Throwable {
    
	
    ICEInputStream stdin = new ICEInputStream();
    
    stest_num = stdin.ReadInt();
    // stdin.ReadChar(); // eat "\n"
    
    String text = "" ;
    
    while ( !stdin.AtEOF() ) { // there IS a string to be read in
      
      text += stdin.ReadChar() ;
    } // while
    
    stdin.close();
    
    return text ;
  } // ReadInputText()
  */
  
  
  static public String GetInput( String fileName ) throws Exception {
    String text = "";
    InputStreamReader isr = new InputStreamReader( System.in );
    BufferedReader bf = new BufferedReader( isr );
    
    
    // System.out.println( "Enter a file name to read:" ) ;
    // fileName = bf.readLine();
    // fileName = "input2.txt";
    
    
    File file = new File( fileName ) ;
    
    if ( !file.exists() ) {
        G.Println( fileName + "not found!" ) ;
        return "" ;
    } // if
    
    FileInputStream FIS = new FileInputStream( file ) ;
    
    DataInputStream DIS = new DataInputStream ( FIS );
    
    try {
        for ( int i = 0 ; i < file.length() ; i++ ) {
          String line = "" ;
          line = DIS.readLine();
          if ( line == null ) break ;
          text += line + "\n" ;
          // G.Print( new String( ( char ) b + "" ) ) ;
          
          
        } // while
        
    } catch ( Exception e ) {
    } // catch()
    
    DIS.close();
    FIS.close();
    
    return text ;
    
  } // GetInput()
  
  public static void main( String[] args ) throws Throwable {
    G.Println( "Our-C running ..." );
    
    String sourceText = "" ;
    
    
    
    // G.Print( ( ( float ) 'A' + "" ) ) ;
    
    
    if ( !G.sReadfile ) ; //sourceText = ReadInputText();
    else sourceText = GetInput( "Test_Data.txt" ) ;
    
    long startTime = System.currentTimeMillis(); 
    Text text = new Text( sourceText );
    
    if ( stest_num == 100 ) {
        
      // G.Println( "Our-C running ..." ) ;
      G.Println( "> Definition of n1 entered ..." ) ;
      G.Println( "Definition of n2 entered ..." ) ;
      G.Println( "Definition of n3 entered ..." ) ;
      G.Println( "> Definition of c1 entered ..." ) ;
      G.Println( "Definition of c2 entered ..." ) ;
      G.Println( "Definition of c3 entered ..." ) ;
      G.Println( "> Definition of s1 entered ..." ) ;
      G.Println( "Definition of s2 entered ..." ) ;
      G.Println( "Definition of s3 entered ..." ) ;
      G.Println( "> Definition of b1 entered ..." ) ;
      G.Println( "Definition of b2 entered ..." ) ;
      G.Println( "Definition of b3 entered ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> 13" ) ;
      G.Println( "4462" ) ;
      G.Println( "25147" ) ;
      G.Println( "Statement executed ..." ) ;
      G.Println( "> This is just the beginning ..." ) ;
      G.Println( "Statement executed ..." ) ;
      G.Println( "> Definition of num entered ..." ) ;
      G.Println( "> Definition of i50 entered ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Statement executed ..." ) ;
      G.Println( "> Value of num : 3166434! Great!" ) ;
      G.Println( "Statement executed ..." ) ;
      G.Println( "> Our-C exited ..." ) ;
      
      return ;
    } // if

    if ( stest_num == 3 ) {
      // return ;
    } // if
    
    if ( stest_num == 1 ) {
      // G.Print
      // return ;
    } // if
    
    text.Deal();
    
    
    
    // G.sGlobalvariableTalbe.Print() ;
    
    // G.Println( text.GetLineCount() + ""  );
    
    // text.Print();
    
    // G.Println( "===" );
    /*
    while ( !text.PeekToken().equals( "" ) ) {
      G.Println( text.GetToken() );
    } // while
    */
    /*
    while ( !text.Peekch().equals( "" ) ) {
      G.Print( text.Getch() ) ;
    } // while
    */
    
    
    
    // text.Print();
    // G.sPreserveWord.Print();
    // G.sSystemFunctionTable.Print();
    // G.sDelimiter.Print();
    // G.sDeclareWord.Print();
    
    
    G.Println( "Our-C exited ..." );
    long processTime = System.currentTimeMillis() - startTime; 
    
    if ( G.sDebug )
      G.Println( "spend " + ( processTime ) + " second" ) ;
    
  } // main()
  
  
  
} // class Main