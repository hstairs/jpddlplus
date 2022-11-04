// $ANTLR 3.5.2 Pddl.g 2021-11-03 08:32:56
 package com.hstairs.ppmajal.parser; 

import org.antlr.runtime.*;

@SuppressWarnings("all")
public class PddlLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__70=70;
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int T__73=73;
	public static final int T__74=74;
	public static final int T__75=75;
	public static final int T__76=76;
	public static final int T__77=77;
	public static final int T__78=78;
	public static final int T__79=79;
	public static final int T__80=80;
	public static final int T__81=81;
	public static final int T__82=82;
	public static final int T__83=83;
	public static final int T__84=84;
	public static final int T__85=85;
	public static final int T__86=86;
	public static final int T__87=87;
	public static final int T__88=88;
	public static final int T__89=89;
	public static final int T__90=90;
	public static final int T__91=91;
	public static final int T__92=92;
	public static final int T__93=93;
	public static final int T__94=94;
	public static final int T__95=95;
	public static final int T__96=96;
	public static final int T__97=97;
	public static final int T__98=98;
	public static final int T__99=99;
	public static final int T__100=100;
	public static final int T__101=101;
	public static final int T__102=102;
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int T__114=114;
	public static final int T__115=115;
	public static final int T__116=116;
	public static final int T__117=117;
	public static final int T__118=118;
	public static final int T__119=119;
	public static final int T__120=120;
	public static final int T__121=121;
	public static final int T__122=122;
	public static final int T__123=123;
	public static final int T__124=124;
	public static final int T__125=125;
	public static final int T__126=126;
	public static final int T__127=127;
	public static final int T__128=128;
	public static final int T__129=129;
	public static final int T__130=130;
	public static final int T__131=131;
	public static final int T__132=132;
	public static final int T__133=133;
	public static final int T__134=134;
	public static final int T__135=135;
	public static final int T__136=136;
	public static final int T__137=137;
	public static final int T__138=138;
	public static final int T__139=139;
	public static final int T__140=140;
	public static final int T__141=141;
	public static final int T__142=142;
	public static final int T__143=143;
	public static final int T__144=144;
	public static final int T__145=145;
	public static final int T__146=146;
	public static final int T__147=147;
	public static final int T__148=148;
	public static final int T__149=149;
	public static final int ABS=4;
	public static final int ACTION=5;
	public static final int AND_EFFECT=6;
	public static final int AND_GD=7;
	public static final int ANY_CHAR=8;
	public static final int ASSIGN_EFFECT=9;
	public static final int ATAN=10;
	public static final int BELIEF=11;
	public static final int BINARY_OP=12;
	public static final int COMPARISON_GD=13;
	public static final int CONSTANTS=14;
	public static final int CONSTRAINT=15;
	public static final int COS=16;
	public static final int DIGIT=17;
	public static final int DOMAIN=18;
	public static final int DOMAIN_NAME=19;
	public static final int DURATIVE_ACTION=20;
	public static final int EFFECT=21;
	public static final int EITHER_TYPE=22;
	public static final int EQUALITY_CON=23;
	public static final int EVENT=24;
	public static final int EXISTS_GD=25;
	public static final int FORALL_EFFECT=26;
	public static final int FORALL_GD=27;
	public static final int FORMULAINIT=28;
	public static final int FREE_FUNCTIONS=29;
	public static final int FUNCTIONS=30;
	public static final int FUNC_HEAD=31;
	public static final int GLOBAL_CONSTRAINT=32;
	public static final int GOAL=33;
	public static final int IMPLY_GD=34;
	public static final int INIT=35;
	public static final int INIT_AT=36;
	public static final int INIT_EQ=37;
	public static final int LETTER=38;
	public static final int LINE_COMMENT=39;
	public static final int MINUS_OP=40;
	public static final int MULTI_OP=41;
	public static final int NAME=42;
	public static final int NOT_EFFECT=43;
	public static final int NOT_GD=44;
	public static final int NOT_PRED_INIT=45;
	public static final int NUMBER=46;
	public static final int OBJECTS=47;
	public static final int ONEOF=48;
	public static final int OR_GD=49;
	public static final int PRECONDITION=50;
	public static final int PREDICATES=51;
	public static final int PRED_HEAD=52;
	public static final int PRED_INST=53;
	public static final int PROBLEM=54;
	public static final int PROBLEM_CONSTRAINT=55;
	public static final int PROBLEM_DOMAIN=56;
	public static final int PROBLEM_METRIC=57;
	public static final int PROBLEM_NAME=58;
	public static final int PROCESS=59;
	public static final int REQUIREMENTS=60;
	public static final int REQUIRE_KEY=61;
	public static final int SIN=62;
	public static final int TAN=63;
	public static final int TYPES=64;
	public static final int UNARY_MINUS=65;
	public static final int UNKNOWN=66;
	public static final int VARIABLE=67;
	public static final int WHEN_EFFECT=68;
	public static final int WHITESPACE=69;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public PddlLexer() {} 
	public PddlLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public PddlLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "Pddl.g"; }

	// $ANTLR start "T__70"
	public final void mT__70() throws RecognitionException {
		try {
			int _type = T__70;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:4:7: ( '#t' )
			// Pddl.g:4:9: '#t'
			{
			match("#t"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__70"

	// $ANTLR start "T__71"
	public final void mT__71() throws RecognitionException {
		try {
			int _type = T__71;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:5:7: ( '(' )
			// Pddl.g:5:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__71"

	// $ANTLR start "T__72"
	public final void mT__72() throws RecognitionException {
		try {
			int _type = T__72;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:6:7: ( ')' )
			// Pddl.g:6:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__72"

	// $ANTLR start "T__73"
	public final void mT__73() throws RecognitionException {
		try {
			int _type = T__73;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:7:7: ( '*' )
			// Pddl.g:7:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__73"

	// $ANTLR start "T__74"
	public final void mT__74() throws RecognitionException {
		try {
			int _type = T__74;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:8:7: ( '+' )
			// Pddl.g:8:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__74"

	// $ANTLR start "T__75"
	public final void mT__75() throws RecognitionException {
		try {
			int _type = T__75;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:9:7: ( '-' )
			// Pddl.g:9:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__75"

	// $ANTLR start "T__76"
	public final void mT__76() throws RecognitionException {
		try {
			int _type = T__76;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:10:7: ( '/' )
			// Pddl.g:10:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__76"

	// $ANTLR start "T__77"
	public final void mT__77() throws RecognitionException {
		try {
			int _type = T__77;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:11:7: ( ':action' )
			// Pddl.g:11:9: ':action'
			{
			match(":action"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__77"

	// $ANTLR start "T__78"
	public final void mT__78() throws RecognitionException {
		try {
			int _type = T__78;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:12:7: ( ':condition' )
			// Pddl.g:12:9: ':condition'
			{
			match(":condition"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__78"

	// $ANTLR start "T__79"
	public final void mT__79() throws RecognitionException {
		try {
			int _type = T__79;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:13:7: ( ':constants' )
			// Pddl.g:13:9: ':constants'
			{
			match(":constants"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__79"

	// $ANTLR start "T__80"
	public final void mT__80() throws RecognitionException {
		try {
			int _type = T__80;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:14:7: ( ':constraint' )
			// Pddl.g:14:9: ':constraint'
			{
			match(":constraint"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__80"

	// $ANTLR start "T__81"
	public final void mT__81() throws RecognitionException {
		try {
			int _type = T__81;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:15:7: ( ':constraints' )
			// Pddl.g:15:9: ':constraints'
			{
			match(":constraints"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__81"

	// $ANTLR start "T__82"
	public final void mT__82() throws RecognitionException {
		try {
			int _type = T__82;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:16:7: ( ':derived' )
			// Pddl.g:16:9: ':derived'
			{
			match(":derived"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__82"

	// $ANTLR start "T__83"
	public final void mT__83() throws RecognitionException {
		try {
			int _type = T__83;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:17:7: ( ':domain' )
			// Pddl.g:17:9: ':domain'
			{
			match(":domain"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__83"

	// $ANTLR start "T__84"
	public final void mT__84() throws RecognitionException {
		try {
			int _type = T__84;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:18:7: ( ':duration' )
			// Pddl.g:18:9: ':duration'
			{
			match(":duration"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__84"

	// $ANTLR start "T__85"
	public final void mT__85() throws RecognitionException {
		try {
			int _type = T__85;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:19:7: ( ':durative-action' )
			// Pddl.g:19:9: ':durative-action'
			{
			match(":durative-action"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__85"

	// $ANTLR start "T__86"
	public final void mT__86() throws RecognitionException {
		try {
			int _type = T__86;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:20:7: ( ':effect' )
			// Pddl.g:20:9: ':effect'
			{
			match(":effect"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__86"

	// $ANTLR start "T__87"
	public final void mT__87() throws RecognitionException {
		try {
			int _type = T__87;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:21:7: ( ':event' )
			// Pddl.g:21:9: ':event'
			{
			match(":event"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__87"

	// $ANTLR start "T__88"
	public final void mT__88() throws RecognitionException {
		try {
			int _type = T__88;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:22:7: ( ':free_functions' )
			// Pddl.g:22:9: ':free_functions'
			{
			match(":free_functions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__88"

	// $ANTLR start "T__89"
	public final void mT__89() throws RecognitionException {
		try {
			int _type = T__89;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:23:7: ( ':functions' )
			// Pddl.g:23:9: ':functions'
			{
			match(":functions"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__89"

	// $ANTLR start "T__90"
	public final void mT__90() throws RecognitionException {
		try {
			int _type = T__90;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:24:7: ( ':goal' )
			// Pddl.g:24:9: ':goal'
			{
			match(":goal"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__90"

	// $ANTLR start "T__91"
	public final void mT__91() throws RecognitionException {
		try {
			int _type = T__91;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:25:7: ( ':init' )
			// Pddl.g:25:9: ':init'
			{
			match(":init"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__91"

	// $ANTLR start "T__92"
	public final void mT__92() throws RecognitionException {
		try {
			int _type = T__92;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:26:7: ( ':metric' )
			// Pddl.g:26:9: ':metric'
			{
			match(":metric"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__92"

	// $ANTLR start "T__93"
	public final void mT__93() throws RecognitionException {
		try {
			int _type = T__93;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:27:7: ( ':objects' )
			// Pddl.g:27:9: ':objects'
			{
			match(":objects"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__93"

	// $ANTLR start "T__94"
	public final void mT__94() throws RecognitionException {
		try {
			int _type = T__94;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:28:7: ( ':parameters' )
			// Pddl.g:28:9: ':parameters'
			{
			match(":parameters"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__94"

	// $ANTLR start "T__95"
	public final void mT__95() throws RecognitionException {
		try {
			int _type = T__95;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:29:7: ( ':precondition' )
			// Pddl.g:29:9: ':precondition'
			{
			match(":precondition"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__95"

	// $ANTLR start "T__96"
	public final void mT__96() throws RecognitionException {
		try {
			int _type = T__96;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:30:7: ( ':predicates' )
			// Pddl.g:30:9: ':predicates'
			{
			match(":predicates"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__96"

	// $ANTLR start "T__97"
	public final void mT__97() throws RecognitionException {
		try {
			int _type = T__97;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:31:7: ( ':process' )
			// Pddl.g:31:9: ':process'
			{
			match(":process"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__97"

	// $ANTLR start "T__98"
	public final void mT__98() throws RecognitionException {
		try {
			int _type = T__98;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:32:7: ( ':requirements' )
			// Pddl.g:32:9: ':requirements'
			{
			match(":requirements"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__98"

	// $ANTLR start "T__99"
	public final void mT__99() throws RecognitionException {
		try {
			int _type = T__99;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:33:7: ( ':types' )
			// Pddl.g:33:9: ':types'
			{
			match(":types"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__99"

	// $ANTLR start "T__100"
	public final void mT__100() throws RecognitionException {
		try {
			int _type = T__100;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:34:8: ( '<' )
			// Pddl.g:34:10: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__100"

	// $ANTLR start "T__101"
	public final void mT__101() throws RecognitionException {
		try {
			int _type = T__101;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:35:8: ( '<=' )
			// Pddl.g:35:10: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__101"

	// $ANTLR start "T__102"
	public final void mT__102() throws RecognitionException {
		try {
			int _type = T__102;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:36:8: ( '=' )
			// Pddl.g:36:10: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__102"

	// $ANTLR start "T__103"
	public final void mT__103() throws RecognitionException {
		try {
			int _type = T__103;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:37:8: ( '>' )
			// Pddl.g:37:10: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__103"

	// $ANTLR start "T__104"
	public final void mT__104() throws RecognitionException {
		try {
			int _type = T__104;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:38:8: ( '>=' )
			// Pddl.g:38:10: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__104"

	// $ANTLR start "T__105"
	public final void mT__105() throws RecognitionException {
		try {
			int _type = T__105;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:39:8: ( '?duration' )
			// Pddl.g:39:10: '?duration'
			{
			match("?duration"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__105"

	// $ANTLR start "T__106"
	public final void mT__106() throws RecognitionException {
		try {
			int _type = T__106;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:40:8: ( '^' )
			// Pddl.g:40:10: '^'
			{
			match('^'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__106"

	// $ANTLR start "T__107"
	public final void mT__107() throws RecognitionException {
		try {
			int _type = T__107;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:41:8: ( '__at__' )
			// Pddl.g:41:10: '__at__'
			{
			match("__at__"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__107"

	// $ANTLR start "T__108"
	public final void mT__108() throws RecognitionException {
		try {
			int _type = T__108;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:42:8: ( 'abs' )
			// Pddl.g:42:10: 'abs'
			{
			match("abs"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__108"

	// $ANTLR start "T__109"
	public final void mT__109() throws RecognitionException {
		try {
			int _type = T__109;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:43:8: ( 'all' )
			// Pddl.g:43:10: 'all'
			{
			match("all"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__109"

	// $ANTLR start "T__110"
	public final void mT__110() throws RecognitionException {
		try {
			int _type = T__110;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:44:8: ( 'always' )
			// Pddl.g:44:10: 'always'
			{
			match("always"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__110"

	// $ANTLR start "T__111"
	public final void mT__111() throws RecognitionException {
		try {
			int _type = T__111;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:45:8: ( 'always-within' )
			// Pddl.g:45:10: 'always-within'
			{
			match("always-within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__111"

	// $ANTLR start "T__112"
	public final void mT__112() throws RecognitionException {
		try {
			int _type = T__112;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:46:8: ( 'and' )
			// Pddl.g:46:10: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__112"

	// $ANTLR start "T__113"
	public final void mT__113() throws RecognitionException {
		try {
			int _type = T__113;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:47:8: ( 'assign' )
			// Pddl.g:47:10: 'assign'
			{
			match("assign"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__113"

	// $ANTLR start "T__114"
	public final void mT__114() throws RecognitionException {
		try {
			int _type = T__114;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:48:8: ( 'at-most-once' )
			// Pddl.g:48:10: 'at-most-once'
			{
			match("at-most-once"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__114"

	// $ANTLR start "T__115"
	public final void mT__115() throws RecognitionException {
		try {
			int _type = T__115;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:49:8: ( 'atan' )
			// Pddl.g:49:10: 'atan'
			{
			match("atan"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__115"

	// $ANTLR start "T__116"
	public final void mT__116() throws RecognitionException {
		try {
			int _type = T__116;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:50:8: ( 'atan2' )
			// Pddl.g:50:10: 'atan2'
			{
			match("atan2"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__116"

	// $ANTLR start "T__117"
	public final void mT__117() throws RecognitionException {
		try {
			int _type = T__117;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:51:8: ( 'cos' )
			// Pddl.g:51:10: 'cos'
			{
			match("cos"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__117"

	// $ANTLR start "T__118"
	public final void mT__118() throws RecognitionException {
		try {
			int _type = T__118;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:52:8: ( 'decrease' )
			// Pddl.g:52:10: 'decrease'
			{
			match("decrease"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__118"

	// $ANTLR start "T__119"
	public final void mT__119() throws RecognitionException {
		try {
			int _type = T__119;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:53:8: ( 'define' )
			// Pddl.g:53:10: 'define'
			{
			match("define"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__119"

	// $ANTLR start "T__120"
	public final void mT__120() throws RecognitionException {
		try {
			int _type = T__120;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:54:8: ( 'domain' )
			// Pddl.g:54:10: 'domain'
			{
			match("domain"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__120"

	// $ANTLR start "T__121"
	public final void mT__121() throws RecognitionException {
		try {
			int _type = T__121;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:55:8: ( 'either' )
			// Pddl.g:55:10: 'either'
			{
			match("either"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__121"

	// $ANTLR start "T__122"
	public final void mT__122() throws RecognitionException {
		try {
			int _type = T__122;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:56:8: ( 'end' )
			// Pddl.g:56:10: 'end'
			{
			match("end"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__122"

	// $ANTLR start "T__123"
	public final void mT__123() throws RecognitionException {
		try {
			int _type = T__123;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:57:8: ( 'exists' )
			// Pddl.g:57:10: 'exists'
			{
			match("exists"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__123"

	// $ANTLR start "T__124"
	public final void mT__124() throws RecognitionException {
		try {
			int _type = T__124;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:58:8: ( 'forall' )
			// Pddl.g:58:10: 'forall'
			{
			match("forall"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__124"

	// $ANTLR start "T__125"
	public final void mT__125() throws RecognitionException {
		try {
			int _type = T__125;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:59:8: ( 'hold-after' )
			// Pddl.g:59:10: 'hold-after'
			{
			match("hold-after"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__125"

	// $ANTLR start "T__126"
	public final void mT__126() throws RecognitionException {
		try {
			int _type = T__126;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:60:8: ( 'hold-during' )
			// Pddl.g:60:10: 'hold-during'
			{
			match("hold-during"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__126"

	// $ANTLR start "T__127"
	public final void mT__127() throws RecognitionException {
		try {
			int _type = T__127;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:61:8: ( 'imply' )
			// Pddl.g:61:10: 'imply'
			{
			match("imply"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__127"

	// $ANTLR start "T__128"
	public final void mT__128() throws RecognitionException {
		try {
			int _type = T__128;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:62:8: ( 'increase' )
			// Pddl.g:62:10: 'increase'
			{
			match("increase"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__128"

	// $ANTLR start "T__129"
	public final void mT__129() throws RecognitionException {
		try {
			int _type = T__129;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:63:8: ( 'is-violated' )
			// Pddl.g:63:10: 'is-violated'
			{
			match("is-violated"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__129"

	// $ANTLR start "T__130"
	public final void mT__130() throws RecognitionException {
		try {
			int _type = T__130;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:64:8: ( 'maximize' )
			// Pddl.g:64:10: 'maximize'
			{
			match("maximize"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__130"

	// $ANTLR start "T__131"
	public final void mT__131() throws RecognitionException {
		try {
			int _type = T__131;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:65:8: ( 'minimize' )
			// Pddl.g:65:10: 'minimize'
			{
			match("minimize"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__131"

	// $ANTLR start "T__132"
	public final void mT__132() throws RecognitionException {
		try {
			int _type = T__132;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:66:8: ( 'not' )
			// Pddl.g:66:10: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__132"

	// $ANTLR start "T__133"
	public final void mT__133() throws RecognitionException {
		try {
			int _type = T__133;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:67:8: ( 'number' )
			// Pddl.g:67:10: 'number'
			{
			match("number"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__133"

	// $ANTLR start "T__134"
	public final void mT__134() throws RecognitionException {
		try {
			int _type = T__134;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:68:8: ( 'oneof' )
			// Pddl.g:68:10: 'oneof'
			{
			match("oneof"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__134"

	// $ANTLR start "T__135"
	public final void mT__135() throws RecognitionException {
		try {
			int _type = T__135;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:69:8: ( 'or' )
			// Pddl.g:69:10: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__135"

	// $ANTLR start "T__136"
	public final void mT__136() throws RecognitionException {
		try {
			int _type = T__136;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:70:8: ( 'over' )
			// Pddl.g:70:10: 'over'
			{
			match("over"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__136"

	// $ANTLR start "T__137"
	public final void mT__137() throws RecognitionException {
		try {
			int _type = T__137;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:71:8: ( 'preference' )
			// Pddl.g:71:10: 'preference'
			{
			match("preference"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__137"

	// $ANTLR start "T__138"
	public final void mT__138() throws RecognitionException {
		try {
			int _type = T__138;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:72:8: ( 'problem' )
			// Pddl.g:72:10: 'problem'
			{
			match("problem"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__138"

	// $ANTLR start "T__139"
	public final void mT__139() throws RecognitionException {
		try {
			int _type = T__139;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:73:8: ( 'scale-down' )
			// Pddl.g:73:10: 'scale-down'
			{
			match("scale-down"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__139"

	// $ANTLR start "T__140"
	public final void mT__140() throws RecognitionException {
		try {
			int _type = T__140;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:74:8: ( 'scale-up' )
			// Pddl.g:74:10: 'scale-up'
			{
			match("scale-up"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__140"

	// $ANTLR start "T__141"
	public final void mT__141() throws RecognitionException {
		try {
			int _type = T__141;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:75:8: ( 'sin' )
			// Pddl.g:75:10: 'sin'
			{
			match("sin"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__141"

	// $ANTLR start "T__142"
	public final void mT__142() throws RecognitionException {
		try {
			int _type = T__142;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:76:8: ( 'sometime' )
			// Pddl.g:76:10: 'sometime'
			{
			match("sometime"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__142"

	// $ANTLR start "T__143"
	public final void mT__143() throws RecognitionException {
		try {
			int _type = T__143;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:77:8: ( 'sometime-after' )
			// Pddl.g:77:10: 'sometime-after'
			{
			match("sometime-after"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__143"

	// $ANTLR start "T__144"
	public final void mT__144() throws RecognitionException {
		try {
			int _type = T__144;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:78:8: ( 'sometime-before' )
			// Pddl.g:78:10: 'sometime-before'
			{
			match("sometime-before"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__144"

	// $ANTLR start "T__145"
	public final void mT__145() throws RecognitionException {
		try {
			int _type = T__145;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:79:8: ( 'start' )
			// Pddl.g:79:10: 'start'
			{
			match("start"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__145"

	// $ANTLR start "T__146"
	public final void mT__146() throws RecognitionException {
		try {
			int _type = T__146;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:80:8: ( 'tan' )
			// Pddl.g:80:10: 'tan'
			{
			match("tan"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__146"

	// $ANTLR start "T__147"
	public final void mT__147() throws RecognitionException {
		try {
			int _type = T__147;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:81:8: ( 'unknown' )
			// Pddl.g:81:10: 'unknown'
			{
			match("unknown"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__147"

	// $ANTLR start "T__148"
	public final void mT__148() throws RecognitionException {
		try {
			int _type = T__148;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:82:8: ( 'when' )
			// Pddl.g:82:10: 'when'
			{
			match("when"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__148"

	// $ANTLR start "T__149"
	public final void mT__149() throws RecognitionException {
		try {
			int _type = T__149;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:83:8: ( 'within' )
			// Pddl.g:83:10: 'within'
			{
			match("within"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__149"

	// $ANTLR start "REQUIRE_KEY"
	public final void mREQUIRE_KEY() throws RecognitionException {
		try {
			int _type = REQUIRE_KEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:583:5: ( ':strips' | ':typing' | ':negative-preconditions' | ':disjunctive-preconditions' | ':equality' | ':existential-preconditions' | ':universal-preconditions' | ':quantified-preconditions' | ':conditional-effects' | ':fluents' | ':adl' | ':durative-actions' | ':derived-predicates' | ':timed-initial-literals' | ':preferences' | ':constraints' | ':' ( ANY_CHAR )* )
			int alt2=17;
			alt2 = dfa2.predict(input);
			switch (alt2) {
				case 1 :
					// Pddl.g:583:7: ':strips'
					{
					match(":strips"); 

					}
					break;
				case 2 :
					// Pddl.g:584:7: ':typing'
					{
					match(":typing"); 

					}
					break;
				case 3 :
					// Pddl.g:585:7: ':negative-preconditions'
					{
					match(":negative-preconditions"); 

					}
					break;
				case 4 :
					// Pddl.g:586:7: ':disjunctive-preconditions'
					{
					match(":disjunctive-preconditions"); 

					}
					break;
				case 5 :
					// Pddl.g:587:7: ':equality'
					{
					match(":equality"); 

					}
					break;
				case 6 :
					// Pddl.g:588:7: ':existential-preconditions'
					{
					match(":existential-preconditions"); 

					}
					break;
				case 7 :
					// Pddl.g:589:7: ':universal-preconditions'
					{
					match(":universal-preconditions"); 

					}
					break;
				case 8 :
					// Pddl.g:590:7: ':quantified-preconditions'
					{
					match(":quantified-preconditions"); 

					}
					break;
				case 9 :
					// Pddl.g:591:7: ':conditional-effects'
					{
					match(":conditional-effects"); 

					}
					break;
				case 10 :
					// Pddl.g:592:7: ':fluents'
					{
					match(":fluents"); 

					}
					break;
				case 11 :
					// Pddl.g:593:7: ':adl'
					{
					match(":adl"); 

					}
					break;
				case 12 :
					// Pddl.g:594:7: ':durative-actions'
					{
					match(":durative-actions"); 

					}
					break;
				case 13 :
					// Pddl.g:595:7: ':derived-predicates'
					{
					match(":derived-predicates"); 

					}
					break;
				case 14 :
					// Pddl.g:596:7: ':timed-initial-literals'
					{
					match(":timed-initial-literals"); 

					}
					break;
				case 15 :
					// Pddl.g:597:7: ':preferences'
					{
					match(":preferences"); 

					}
					break;
				case 16 :
					// Pddl.g:598:7: ':constraints'
					{
					match(":constraints"); 

					}
					break;
				case 17 :
					// Pddl.g:599:7: ':' ( ANY_CHAR )*
					{
					match(':'); 
					// Pddl.g:599:10: ( ANY_CHAR )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// Pddl.g:
							{
							if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop1;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REQUIRE_KEY"

	// $ANTLR start "NAME"
	public final void mNAME() throws RecognitionException {
		try {
			int _type = NAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:600:5: ( LETTER ( ANY_CHAR )* )
			// Pddl.g:600:10: LETTER ( ANY_CHAR )*
			{
			mLETTER(); 

			// Pddl.g:600:17: ( ANY_CHAR )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='-'||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// Pddl.g:
					{
					if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NAME"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// Pddl.g:602:16: ( 'a' .. 'z' | 'A' .. 'Z' )
			// Pddl.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "ANY_CHAR"
	public final void mANY_CHAR() throws RecognitionException {
		try {
			// Pddl.g:604:18: ( LETTER | '0' .. '9' | '-' | '_' )
			// Pddl.g:
			{
			if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANY_CHAR"

	// $ANTLR start "VARIABLE"
	public final void mVARIABLE() throws RecognitionException {
		try {
			int _type = VARIABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:606:10: ( '?' LETTER ( ANY_CHAR )* )
			// Pddl.g:606:12: '?' LETTER ( ANY_CHAR )*
			{
			match('?'); 
			mLETTER(); 

			// Pddl.g:606:23: ( ANY_CHAR )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0=='-'||(LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'Z')||LA4_0=='_'||(LA4_0 >= 'a' && LA4_0 <= 'z')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// Pddl.g:
					{
					if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop4;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VARIABLE"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:608:8: ( ( '-' )? ( DIGIT )+ ( '.' ( DIGIT )+ )? | '#t' )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='-'||(LA9_0 >= '0' && LA9_0 <= '9')) ) {
				alt9=1;
			}
			else if ( (LA9_0=='#') ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// Pddl.g:608:10: ( '-' )? ( DIGIT )+ ( '.' ( DIGIT )+ )?
					{
					// Pddl.g:608:10: ( '-' )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0=='-') ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// Pddl.g:608:11: '-'
							{
							match('-'); 
							}
							break;

					}

					// Pddl.g:608:17: ( DIGIT )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// Pddl.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
					}

					// Pddl.g:608:24: ( '.' ( DIGIT )+ )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0=='.') ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// Pddl.g:608:25: '.' ( DIGIT )+
							{
							match('.'); 
							// Pddl.g:608:29: ( DIGIT )+
							int cnt7=0;
							loop7:
							while (true) {
								int alt7=2;
								int LA7_0 = input.LA(1);
								if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
									alt7=1;
								}

								switch (alt7) {
								case 1 :
									// Pddl.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt7 >= 1 ) break loop7;
									EarlyExitException eee = new EarlyExitException(7, input);
									throw eee;
								}
								cnt7++;
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// Pddl.g:608:40: '#t'
					{
					match("#t"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// Pddl.g:610:15: ( '0' .. '9' )
			// Pddl.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:613:5: ( ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
			// Pddl.g:613:7: ';' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
			{
			match(';'); 
			// Pddl.g:613:11: (~ ( '\\n' | '\\r' ) )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\t')||(LA10_0 >= '\u000B' && LA10_0 <= '\f')||(LA10_0 >= '\u000E' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// Pddl.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop10;
				}
			}

			// Pddl.g:613:25: ( '\\r' )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='\r') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// Pddl.g:613:25: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			 _channel = HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINE_COMMENT"

	// $ANTLR start "WHITESPACE"
	public final void mWHITESPACE() throws RecognitionException {
		try {
			int _type = WHITESPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Pddl.g:617:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// Pddl.g:617:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// Pddl.g:617:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt12=0;
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( ((LA12_0 >= '\t' && LA12_0 <= '\n')||LA12_0=='\r'||LA12_0==' ') ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// Pddl.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt12 >= 1 ) break loop12;
					EarlyExitException eee = new EarlyExitException(12, input);
					throw eee;
				}
				cnt12++;
			}

			 _channel = HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITESPACE"

	@Override
	public void mTokens() throws RecognitionException {
		// Pddl.g:1:8: ( T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | REQUIRE_KEY | NAME | VARIABLE | NUMBER | LINE_COMMENT | WHITESPACE )
		int alt13=86;
		alt13 = dfa13.predict(input);
		switch (alt13) {
			case 1 :
				// Pddl.g:1:10: T__70
				{
				mT__70(); 

				}
				break;
			case 2 :
				// Pddl.g:1:16: T__71
				{
				mT__71(); 

				}
				break;
			case 3 :
				// Pddl.g:1:22: T__72
				{
				mT__72(); 

				}
				break;
			case 4 :
				// Pddl.g:1:28: T__73
				{
				mT__73(); 

				}
				break;
			case 5 :
				// Pddl.g:1:34: T__74
				{
				mT__74(); 

				}
				break;
			case 6 :
				// Pddl.g:1:40: T__75
				{
				mT__75(); 

				}
				break;
			case 7 :
				// Pddl.g:1:46: T__76
				{
				mT__76(); 

				}
				break;
			case 8 :
				// Pddl.g:1:52: T__77
				{
				mT__77(); 

				}
				break;
			case 9 :
				// Pddl.g:1:58: T__78
				{
				mT__78(); 

				}
				break;
			case 10 :
				// Pddl.g:1:64: T__79
				{
				mT__79(); 

				}
				break;
			case 11 :
				// Pddl.g:1:70: T__80
				{
				mT__80(); 

				}
				break;
			case 12 :
				// Pddl.g:1:76: T__81
				{
				mT__81(); 

				}
				break;
			case 13 :
				// Pddl.g:1:82: T__82
				{
				mT__82(); 

				}
				break;
			case 14 :
				// Pddl.g:1:88: T__83
				{
				mT__83(); 

				}
				break;
			case 15 :
				// Pddl.g:1:94: T__84
				{
				mT__84(); 

				}
				break;
			case 16 :
				// Pddl.g:1:100: T__85
				{
				mT__85(); 

				}
				break;
			case 17 :
				// Pddl.g:1:106: T__86
				{
				mT__86(); 

				}
				break;
			case 18 :
				// Pddl.g:1:112: T__87
				{
				mT__87(); 

				}
				break;
			case 19 :
				// Pddl.g:1:118: T__88
				{
				mT__88(); 

				}
				break;
			case 20 :
				// Pddl.g:1:124: T__89
				{
				mT__89(); 

				}
				break;
			case 21 :
				// Pddl.g:1:130: T__90
				{
				mT__90(); 

				}
				break;
			case 22 :
				// Pddl.g:1:136: T__91
				{
				mT__91(); 

				}
				break;
			case 23 :
				// Pddl.g:1:142: T__92
				{
				mT__92(); 

				}
				break;
			case 24 :
				// Pddl.g:1:148: T__93
				{
				mT__93(); 

				}
				break;
			case 25 :
				// Pddl.g:1:154: T__94
				{
				mT__94(); 

				}
				break;
			case 26 :
				// Pddl.g:1:160: T__95
				{
				mT__95(); 

				}
				break;
			case 27 :
				// Pddl.g:1:166: T__96
				{
				mT__96(); 

				}
				break;
			case 28 :
				// Pddl.g:1:172: T__97
				{
				mT__97(); 

				}
				break;
			case 29 :
				// Pddl.g:1:178: T__98
				{
				mT__98(); 

				}
				break;
			case 30 :
				// Pddl.g:1:184: T__99
				{
				mT__99(); 

				}
				break;
			case 31 :
				// Pddl.g:1:190: T__100
				{
				mT__100(); 

				}
				break;
			case 32 :
				// Pddl.g:1:197: T__101
				{
				mT__101(); 

				}
				break;
			case 33 :
				// Pddl.g:1:204: T__102
				{
				mT__102(); 

				}
				break;
			case 34 :
				// Pddl.g:1:211: T__103
				{
				mT__103(); 

				}
				break;
			case 35 :
				// Pddl.g:1:218: T__104
				{
				mT__104(); 

				}
				break;
			case 36 :
				// Pddl.g:1:225: T__105
				{
				mT__105(); 

				}
				break;
			case 37 :
				// Pddl.g:1:232: T__106
				{
				mT__106(); 

				}
				break;
			case 38 :
				// Pddl.g:1:239: T__107
				{
				mT__107(); 

				}
				break;
			case 39 :
				// Pddl.g:1:246: T__108
				{
				mT__108(); 

				}
				break;
			case 40 :
				// Pddl.g:1:253: T__109
				{
				mT__109(); 

				}
				break;
			case 41 :
				// Pddl.g:1:260: T__110
				{
				mT__110(); 

				}
				break;
			case 42 :
				// Pddl.g:1:267: T__111
				{
				mT__111(); 

				}
				break;
			case 43 :
				// Pddl.g:1:274: T__112
				{
				mT__112(); 

				}
				break;
			case 44 :
				// Pddl.g:1:281: T__113
				{
				mT__113(); 

				}
				break;
			case 45 :
				// Pddl.g:1:288: T__114
				{
				mT__114(); 

				}
				break;
			case 46 :
				// Pddl.g:1:295: T__115
				{
				mT__115(); 

				}
				break;
			case 47 :
				// Pddl.g:1:302: T__116
				{
				mT__116(); 

				}
				break;
			case 48 :
				// Pddl.g:1:309: T__117
				{
				mT__117(); 

				}
				break;
			case 49 :
				// Pddl.g:1:316: T__118
				{
				mT__118(); 

				}
				break;
			case 50 :
				// Pddl.g:1:323: T__119
				{
				mT__119(); 

				}
				break;
			case 51 :
				// Pddl.g:1:330: T__120
				{
				mT__120(); 

				}
				break;
			case 52 :
				// Pddl.g:1:337: T__121
				{
				mT__121(); 

				}
				break;
			case 53 :
				// Pddl.g:1:344: T__122
				{
				mT__122(); 

				}
				break;
			case 54 :
				// Pddl.g:1:351: T__123
				{
				mT__123(); 

				}
				break;
			case 55 :
				// Pddl.g:1:358: T__124
				{
				mT__124(); 

				}
				break;
			case 56 :
				// Pddl.g:1:365: T__125
				{
				mT__125(); 

				}
				break;
			case 57 :
				// Pddl.g:1:372: T__126
				{
				mT__126(); 

				}
				break;
			case 58 :
				// Pddl.g:1:379: T__127
				{
				mT__127(); 

				}
				break;
			case 59 :
				// Pddl.g:1:386: T__128
				{
				mT__128(); 

				}
				break;
			case 60 :
				// Pddl.g:1:393: T__129
				{
				mT__129(); 

				}
				break;
			case 61 :
				// Pddl.g:1:400: T__130
				{
				mT__130(); 

				}
				break;
			case 62 :
				// Pddl.g:1:407: T__131
				{
				mT__131(); 

				}
				break;
			case 63 :
				// Pddl.g:1:414: T__132
				{
				mT__132(); 

				}
				break;
			case 64 :
				// Pddl.g:1:421: T__133
				{
				mT__133(); 

				}
				break;
			case 65 :
				// Pddl.g:1:428: T__134
				{
				mT__134(); 

				}
				break;
			case 66 :
				// Pddl.g:1:435: T__135
				{
				mT__135(); 

				}
				break;
			case 67 :
				// Pddl.g:1:442: T__136
				{
				mT__136(); 

				}
				break;
			case 68 :
				// Pddl.g:1:449: T__137
				{
				mT__137(); 

				}
				break;
			case 69 :
				// Pddl.g:1:456: T__138
				{
				mT__138(); 

				}
				break;
			case 70 :
				// Pddl.g:1:463: T__139
				{
				mT__139(); 

				}
				break;
			case 71 :
				// Pddl.g:1:470: T__140
				{
				mT__140(); 

				}
				break;
			case 72 :
				// Pddl.g:1:477: T__141
				{
				mT__141(); 

				}
				break;
			case 73 :
				// Pddl.g:1:484: T__142
				{
				mT__142(); 

				}
				break;
			case 74 :
				// Pddl.g:1:491: T__143
				{
				mT__143(); 

				}
				break;
			case 75 :
				// Pddl.g:1:498: T__144
				{
				mT__144(); 

				}
				break;
			case 76 :
				// Pddl.g:1:505: T__145
				{
				mT__145(); 

				}
				break;
			case 77 :
				// Pddl.g:1:512: T__146
				{
				mT__146(); 

				}
				break;
			case 78 :
				// Pddl.g:1:519: T__147
				{
				mT__147(); 

				}
				break;
			case 79 :
				// Pddl.g:1:526: T__148
				{
				mT__148(); 

				}
				break;
			case 80 :
				// Pddl.g:1:533: T__149
				{
				mT__149(); 

				}
				break;
			case 81 :
				// Pddl.g:1:540: REQUIRE_KEY
				{
				mREQUIRE_KEY(); 

				}
				break;
			case 82 :
				// Pddl.g:1:552: NAME
				{
				mNAME(); 

				}
				break;
			case 83 :
				// Pddl.g:1:557: VARIABLE
				{
				mVARIABLE(); 

				}
				break;
			case 84 :
				// Pddl.g:1:566: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 85 :
				// Pddl.g:1:573: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;
			case 86 :
				// Pddl.g:1:586: WHITESPACE
				{
				mWHITESPACE(); 

				}
				break;

		}
	}


	protected DFA2 dfa2 = new DFA2(this);
	protected DFA13 dfa13 = new DFA13(this);
	static final String DFA2_eotS =
		"\1\uffff\14\15\1\uffff\34\15\1\uffff\37\15\2\uffff\30\15\1\uffff\6\15"+
		"\1\uffff\45\15\2\uffff\47\15\1\uffff\20\15\1\uffff\12\15\1\uffff\14\15"+
		"\2\uffff\6\15\1\uffff\3\15\3\uffff";
	static final String DFA2_eofS =
		"\u00f2\uffff";
	static final String DFA2_minS =
		"\1\72\1\141\1\164\1\151\2\145\1\161\1\156\1\165\1\157\1\154\1\144\1\162"+
		"\1\uffff\1\162\1\160\1\155\1\147\1\163\2\162\1\165\2\151\1\141\1\156\1"+
		"\165\1\154\1\145\2\151\1\145\1\141\1\152\1\141\1\151\1\141\1\163\1\166"+
		"\1\156\1\144\1\145\1\uffff\1\146\1\160\1\156\1\144\1\164\1\165\1\164\1"+
		"\166\1\154\1\164\1\145\1\164\1\151\1\164\1\156\1\145\1\163\1\147\1\55"+
		"\1\151\1\156\1\151\1\145\1\151\1\145\1\162\1\151\1\164\1\162\1\164\1\162"+
		"\2\uffff\1\151\1\166\1\143\1\166\1\144\1\164\1\156\1\163\1\146\1\151\1"+
		"\141\1\163\1\145\1\156\1\145\1\164\1\145\1\55\1\171\1\164\1\141\1\151"+
		"\1\157\1\151\1\uffff\1\156\1\151\1\55\1\151\1\55\1\160\1\uffff\1\151\1"+
		"\154\1\145\2\156\1\143\1\164\1\160\1\166\1\141\1\162\1\141\1\55\1\144"+
		"\1\141\1\164\1\145\1\151\1\162\1\145\1\143\1\145\1\154\1\160\1\55\1\154"+
		"\2\163\1\141\1\145\1\55\1\164\1\144\1\55\1\162\1\160\1\55\2\uffff\1\154"+
		"\1\143\1\160\2\151\1\160\1\145\1\162\1\145\1\55\1\157\1\162\1\157\1\143"+
		"\1\162\1\143\1\145\1\146\1\154\1\156\1\145\1\156\1\141\1\145\1\157\1\143"+
		"\1\146\1\151\1\144\1\143\1\163\1\164\1\143\1\156\1\157\1\145\1\164\1\151"+
		"\1\157\1\uffff\1\145\1\157\1\144\1\156\1\143\1\145\1\164\1\156\1\163\1"+
		"\156\1\151\1\144\1\164\1\162\1\151\1\144\1\uffff\1\144\1\164\1\151\1\163"+
		"\1\141\1\157\3\151\1\164\1\uffff\1\154\1\156\2\164\1\157\1\151\2\163\2"+
		"\151\1\156\1\157\2\uffff\2\157\1\163\3\156\1\uffff\3\163\3\uffff";
	static final String DFA2_maxS =
		"\1\72\1\165\1\164\1\171\1\145\1\165\1\170\1\156\1\165\1\157\1\154\1\144"+
		"\1\162\1\uffff\1\162\1\160\1\155\1\147\1\163\2\162\1\165\2\151\1\141\1"+
		"\156\1\165\1\154\1\145\2\151\1\145\1\141\1\152\1\141\1\151\1\141\1\163"+
		"\1\166\1\156\1\163\1\145\1\uffff\1\146\1\160\1\156\1\144\1\164\1\165\1"+
		"\164\1\166\1\154\1\164\1\145\1\164\1\151\1\164\1\156\1\145\1\163\1\147"+
		"\1\55\1\151\1\156\1\151\1\145\1\151\1\145\1\162\1\151\1\164\1\162\1\164"+
		"\1\162\2\uffff\1\151\1\166\1\143\1\166\1\144\1\164\1\156\1\163\1\146\1"+
		"\151\1\141\1\163\1\145\1\156\1\145\1\164\1\145\1\55\1\171\1\164\1\141"+
		"\1\151\1\157\1\151\1\uffff\1\156\1\151\1\55\1\151\1\55\1\160\1\uffff\1"+
		"\151\1\154\1\145\2\156\1\143\1\164\1\160\1\166\1\141\1\162\1\141\1\55"+
		"\1\144\1\141\1\164\1\145\1\151\1\162\1\145\1\143\1\145\1\154\1\160\1\55"+
		"\1\154\2\163\1\141\1\145\1\55\1\164\1\144\1\55\1\162\1\160\1\55\2\uffff"+
		"\1\154\1\143\1\160\2\151\1\160\1\145\1\162\1\145\1\55\1\157\1\162\1\157"+
		"\1\143\1\162\1\143\1\145\1\146\1\154\1\156\1\145\1\156\1\141\1\145\1\157"+
		"\1\143\1\146\1\151\1\144\1\143\1\163\1\164\1\143\1\156\1\157\1\145\1\164"+
		"\1\151\1\157\1\uffff\1\145\1\157\1\144\1\156\1\143\1\145\1\164\1\156\1"+
		"\163\1\156\1\151\1\144\1\164\1\162\1\151\1\144\1\uffff\1\144\1\164\1\151"+
		"\1\163\1\141\1\157\3\151\1\164\1\uffff\1\154\1\156\2\164\1\157\1\151\2"+
		"\163\2\151\1\156\1\157\2\uffff\2\157\1\163\3\156\1\uffff\3\163\3\uffff";
	static final String DFA2_acceptS =
		"\15\uffff\1\21\34\uffff\1\13\37\uffff\1\1\1\2\30\uffff\1\12\6\uffff\1"+
		"\5\45\uffff\1\20\1\17\47\uffff\1\14\20\uffff\1\15\12\uffff\1\11\14\uffff"+
		"\1\16\1\3\6\uffff\1\7\3\uffff\1\10\1\4\1\6";
	static final String DFA2_specialS =
		"\u00f2\uffff}>";
	static final String[] DFA2_transitionS = {
			"\1\1",
			"\1\13\1\uffff\1\11\1\5\1\6\1\12\7\uffff\1\4\1\uffff\1\14\1\10\1\uffff"+
			"\1\2\1\3\1\7",
			"\1\16",
			"\1\20\17\uffff\1\17",
			"\1\21",
			"\1\24\3\uffff\1\22\13\uffff\1\23",
			"\1\25\6\uffff\1\26",
			"\1\27",
			"\1\30",
			"\1\31",
			"\1\32",
			"\1\33",
			"\1\34",
			"",
			"\1\35",
			"\1\36",
			"\1\37",
			"\1\40",
			"\1\41",
			"\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67\16\uffff\1\70",
			"\1\71",
			"",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"",
			"",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"\1\141",
			"\1\142",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"\1\153",
			"\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"",
			"\1\161",
			"\1\162",
			"\1\163",
			"\1\164",
			"\1\165",
			"\1\166",
			"",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"",
			"",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00b4",
			"\1\u00b5",
			"\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00bc",
			"\1\u00bd",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1",
			"\1\u00d2",
			"",
			"\1\u00d3",
			"\1\u00d4",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00db",
			"\1\u00dc",
			"",
			"\1\u00dd",
			"\1\u00de",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"",
			"",
			"\1\u00e9",
			"\1\u00ea",
			"\1\u00eb",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1",
			"",
			"",
			""
	};

	static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
	static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
	static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
	static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
	static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
	static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
	static final short[][] DFA2_transition;

	static {
		int numStates = DFA2_transitionS.length;
		DFA2_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
		}
	}

	protected class DFA2 extends DFA {

		public DFA2(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 2;
			this.eot = DFA2_eot;
			this.eof = DFA2_eof;
			this.min = DFA2_min;
			this.max = DFA2_max;
			this.accept = DFA2_accept;
			this.special = DFA2_special;
			this.transition = DFA2_transition;
		}
		@Override
		public String getDescription() {
			return "582:1: REQUIRE_KEY : ( ':strips' | ':typing' | ':negative-preconditions' | ':disjunctive-preconditions' | ':equality' | ':existential-preconditions' | ':universal-preconditions' | ':quantified-preconditions' | ':conditional-effects' | ':fluents' | ':adl' | ':durative-actions' | ':derived-predicates' | ':timed-initial-literals' | ':preferences' | ':constraints' | ':' ( ANY_CHAR )* );";
		}
	}

	static final String DFA13_eotS =
		"\6\uffff\1\43\1\uffff\1\60\1\62\1\uffff\1\64\3\uffff\17\36\6\uffff\14"+
		"\60\5\uffff\1\66\1\uffff\25\36\1\u0082\12\36\1\uffff\21\60\1\66\1\u00a1"+
		"\1\u00a2\1\36\1\u00a4\3\36\1\u00a8\4\36\1\u00ad\10\36\1\u00b6\2\36\1\uffff"+
		"\4\36\1\u00bd\2\36\1\u00c0\3\36\22\60\1\66\2\uffff\1\36\1\uffff\2\36\1"+
		"\u00dd\1\uffff\4\36\1\uffff\10\36\1\uffff\2\36\1\u00ec\3\36\1\uffff\2"+
		"\36\1\uffff\1\36\1\u00f3\1\36\12\60\1\u00ff\1\u0100\10\60\1\66\3\36\1"+
		"\u010d\1\uffff\7\36\1\u0116\5\36\1\u011c\1\uffff\4\36\1\u0121\1\36\1\uffff"+
		"\1\36\7\60\1\u012c\2\60\2\uffff\7\60\1\u0136\1\66\1\u0139\1\u013a\1\36"+
		"\1\uffff\1\36\1\u013d\1\u013e\1\u013f\1\u0140\1\u0141\2\36\1\uffff\4\36"+
		"\1\u0148\1\uffff\4\36\1\uffff\1\36\1\u014f\1\u0150\4\60\1\u0155\1\60\1"+
		"\u0158\1\uffff\2\60\1\u015b\6\60\1\uffff\1\66\1\36\2\uffff\2\36\5\uffff"+
		"\6\36\1\uffff\1\36\1\u016d\3\36\1\u0171\2\uffff\3\60\1\u0175\1\uffff\2"+
		"\60\1\uffff\2\60\1\uffff\1\u017a\3\60\1\u017e\1\60\1\66\2\36\1\u0183\2"+
		"\36\1\u0186\1\36\1\u0188\1\u0189\1\36\1\uffff\1\36\1\u018c\1\u018e\1\uffff"+
		"\3\60\1\uffff\1\u0192\3\60\1\uffff\3\60\1\uffff\1\60\1\u019a\2\36\1\uffff"+
		"\2\36\1\uffff\1\36\2\uffff\2\36\1\uffff\1\36\1\uffff\1\u01a4\1\u01a5\1"+
		"\60\1\uffff\2\60\1\u01a9\4\60\1\uffff\2\36\1\u01b0\2\36\1\u01b3\1\u01b4"+
		"\2\36\2\uffff\1\u01b8\2\60\1\uffff\1\u01bb\1\60\1\u01bd\1\60\2\36\1\uffff"+
		"\1\u01c1\1\u01c2\2\uffff\2\36\1\u01c5\1\uffff\2\60\1\uffff\1\60\1\uffff"+
		"\1\60\1\36\1\u01cb\2\uffff\2\36\1\uffff\2\60\1\u01d0\1\u01d1\1\u01d2\1"+
		"\uffff\2\36\2\60\3\uffff\1\u01d7\1\36\1\60\1\u01da\1\uffff\1\u01db\1\u01dc"+
		"\3\uffff";
	static final String DFA13_eofS =
		"\u01dd\uffff";
	static final String DFA13_minS =
		"\1\11\1\164\4\uffff\1\60\1\uffff\1\141\1\75\1\uffff\1\75\1\101\2\uffff"+
		"\1\142\1\157\1\145\1\151\2\157\1\155\1\141\1\157\1\156\1\162\1\143\1\141"+
		"\1\156\1\150\6\uffff\1\143\1\157\1\145\1\146\1\162\1\157\1\156\1\145\1"+
		"\142\1\141\1\145\1\171\5\uffff\1\165\1\uffff\1\163\1\154\1\144\1\163\1"+
		"\55\1\163\1\143\1\155\1\164\1\144\1\151\1\162\1\154\1\160\1\143\1\55\1"+
		"\170\1\156\1\164\1\155\1\145\1\55\2\145\1\141\1\156\1\155\1\141\1\156"+
		"\1\153\1\145\1\164\1\uffff\1\164\1\156\1\162\1\155\1\162\1\146\2\145\1"+
		"\156\1\141\1\151\1\164\1\152\1\162\1\145\1\161\1\160\1\162\2\55\1\141"+
		"\1\55\1\151\1\155\1\156\1\55\1\162\1\151\1\141\1\150\1\55\1\163\1\141"+
		"\1\144\1\154\1\162\1\166\2\151\1\55\1\142\1\157\1\uffff\1\162\1\146\1"+
		"\142\1\154\1\55\1\145\1\162\1\55\2\156\1\150\1\151\1\144\1\151\2\141\1"+
		"\145\1\156\1\145\1\143\1\154\1\164\1\162\1\145\1\141\2\143\1\165\1\145"+
		"\1\141\2\uffff\1\171\1\uffff\1\147\1\157\1\55\1\uffff\1\145\1\156\1\151"+
		"\1\145\1\uffff\1\164\1\154\1\55\1\171\1\145\1\151\2\155\1\uffff\1\145"+
		"\1\146\1\55\1\145\1\154\1\145\1\uffff\2\164\1\uffff\1\157\1\55\1\151\1"+
		"\157\1\151\1\164\1\166\1\151\1\164\1\143\1\164\1\137\1\164\2\55\1\151"+
		"\1\143\1\155\1\157\1\151\1\145\1\151\1\163\1\164\1\163\1\156\1\163\1\55"+
		"\1\uffff\1\141\1\145\1\156\1\162\1\163\1\154\1\141\1\55\1\141\1\157\2"+
		"\151\1\162\1\55\1\uffff\1\162\1\145\1\55\1\151\1\55\1\167\1\uffff\2\156"+
		"\1\164\1\141\1\145\1\156\1\151\1\164\1\55\1\146\1\151\2\uffff\1\143\1"+
		"\164\1\145\1\156\1\143\1\163\1\162\1\55\1\151\2\55\1\164\1\uffff\1\163"+
		"\5\55\1\146\1\165\1\uffff\1\163\1\154\2\172\1\55\1\uffff\1\145\1\155\1"+
		"\144\1\155\1\uffff\1\156\2\55\1\151\1\156\1\141\1\144\1\55\1\157\1\55"+
		"\1\uffff\1\165\1\157\1\55\1\163\1\164\1\144\1\141\1\163\1\145\1\uffff"+
		"\1\157\1\167\2\uffff\1\55\1\145\5\uffff\1\164\1\162\1\145\1\141\2\145"+
		"\1\uffff\1\156\1\55\1\157\1\160\1\145\1\55\2\uffff\1\157\1\164\1\151\1"+
		"\55\1\uffff\1\156\1\145\1\uffff\2\156\1\uffff\1\55\1\145\1\151\1\164\1"+
		"\55\1\155\1\156\1\151\1\157\1\55\1\145\1\151\1\55\1\164\2\55\1\143\1\uffff"+
		"\1\167\2\55\1\uffff\1\156\1\163\1\156\1\uffff\2\55\1\143\1\163\1\uffff"+
		"\1\162\1\164\1\145\1\uffff\1\145\1\55\1\164\1\156\1\uffff\1\162\1\156"+
		"\1\uffff\1\145\2\uffff\1\145\1\156\1\uffff\1\141\1\uffff\2\55\1\164\1"+
		"\uffff\1\141\1\164\1\55\1\163\1\151\1\163\1\156\1\uffff\1\150\1\143\1"+
		"\55\1\147\1\144\2\55\1\146\1\145\2\uffff\1\55\1\143\1\151\1\uffff\1\55"+
		"\1\157\1\55\1\164\1\151\1\145\1\uffff\2\55\2\uffff\1\164\1\146\1\55\1"+
		"\uffff\1\164\1\157\1\uffff\1\156\1\uffff\1\163\1\156\1\55\2\uffff\1\145"+
		"\1\157\1\uffff\1\151\1\156\3\55\1\uffff\2\162\1\157\1\163\3\uffff\1\55"+
		"\1\145\1\156\1\55\1\uffff\2\55\3\uffff";
	static final String DFA13_maxS =
		"\1\172\1\164\4\uffff\1\71\1\uffff\1\164\1\75\1\uffff\1\75\1\172\2\uffff"+
		"\1\164\2\157\1\170\2\157\1\163\1\151\1\165\1\166\1\162\1\164\1\141\1\156"+
		"\1\151\6\uffff\1\143\1\157\1\165\1\166\1\165\1\157\1\156\1\145\1\142\1"+
		"\162\1\145\1\171\5\uffff\1\165\1\uffff\1\163\1\167\1\144\1\163\1\141\1"+
		"\163\1\146\1\155\1\164\1\144\1\151\1\162\1\154\1\160\1\143\1\55\1\170"+
		"\1\156\1\164\1\155\1\145\1\172\1\145\1\157\1\141\1\156\1\155\1\141\1\156"+
		"\1\153\1\145\1\164\1\uffff\1\164\1\156\1\162\1\155\1\162\1\146\2\145\1"+
		"\156\1\141\1\151\1\164\1\152\1\162\1\157\1\161\1\160\1\162\2\172\1\141"+
		"\1\172\1\151\1\155\1\156\1\172\1\162\1\151\1\141\1\150\1\172\1\163\1\141"+
		"\1\144\1\154\1\162\1\166\2\151\1\172\1\142\1\157\1\uffff\1\162\1\146\1"+
		"\142\1\154\1\172\1\145\1\162\1\172\2\156\1\150\1\151\1\163\1\151\2\141"+
		"\1\145\1\156\1\145\1\143\1\154\1\164\1\162\1\145\1\141\1\144\1\143\1\165"+
		"\1\145\1\141\2\uffff\1\171\1\uffff\1\147\1\157\1\172\1\uffff\1\145\1\156"+
		"\1\151\1\145\1\uffff\1\164\1\154\1\55\1\171\1\145\1\151\2\155\1\uffff"+
		"\1\145\1\146\1\172\1\145\1\154\1\145\1\uffff\2\164\1\uffff\1\157\1\172"+
		"\1\151\1\157\1\151\1\164\1\166\1\151\1\164\1\143\1\164\1\137\1\164\2\172"+
		"\1\151\1\143\1\155\1\157\1\151\1\145\1\151\1\163\1\164\1\163\1\156\1\163"+
		"\1\172\1\uffff\1\141\1\145\1\156\1\162\1\163\1\154\1\144\1\172\1\141\1"+
		"\157\2\151\1\162\1\172\1\uffff\1\162\1\145\1\55\1\151\1\172\1\167\1\uffff"+
		"\2\156\1\164\1\162\1\145\1\156\1\151\1\164\1\172\1\146\1\151\2\uffff\1"+
		"\143\1\164\1\145\1\156\1\143\1\163\1\162\1\172\1\151\2\172\1\164\1\uffff"+
		"\1\163\5\172\1\146\1\165\1\uffff\1\163\1\154\3\172\1\uffff\1\145\1\155"+
		"\1\165\1\155\1\uffff\1\156\2\172\1\151\1\156\1\141\1\144\1\172\1\166\1"+
		"\172\1\uffff\1\165\1\157\1\172\1\163\1\164\1\144\1\141\1\163\1\145\1\uffff"+
		"\1\157\1\167\2\uffff\1\55\1\145\5\uffff\1\164\1\162\1\145\1\141\2\145"+
		"\1\uffff\1\156\1\172\1\157\1\160\1\145\1\172\2\uffff\1\157\1\164\1\151"+
		"\1\172\1\uffff\1\156\1\145\1\uffff\2\156\1\uffff\1\172\1\145\1\151\1\164"+
		"\1\172\1\155\1\156\1\151\1\157\1\172\1\145\1\151\1\172\1\164\2\172\1\143"+
		"\1\uffff\1\167\2\172\1\uffff\1\156\1\163\1\156\1\uffff\1\172\1\55\1\143"+
		"\1\163\1\uffff\1\162\1\164\1\145\1\uffff\1\145\1\172\1\164\1\156\1\uffff"+
		"\1\162\1\156\1\uffff\1\145\2\uffff\1\145\1\156\1\uffff\1\142\1\uffff\2"+
		"\172\1\164\1\uffff\1\141\1\164\1\172\1\163\1\151\1\163\1\156\1\uffff\1"+
		"\150\1\143\1\172\1\147\1\144\2\172\1\146\1\145\2\uffff\1\172\1\143\1\151"+
		"\1\uffff\1\172\1\157\1\172\1\164\1\151\1\145\1\uffff\2\172\2\uffff\1\164"+
		"\1\146\1\172\1\uffff\1\164\1\157\1\uffff\1\156\1\uffff\1\163\1\156\1\172"+
		"\2\uffff\1\145\1\157\1\uffff\1\151\1\156\3\172\1\uffff\2\162\1\157\1\163"+
		"\3\uffff\1\172\1\145\1\156\1\172\1\uffff\2\172\3\uffff";
	static final String DFA13_acceptS =
		"\2\uffff\1\2\1\3\1\4\1\5\1\uffff\1\7\2\uffff\1\41\2\uffff\1\45\1\46\17"+
		"\uffff\1\122\1\124\1\125\1\126\1\1\1\6\14\uffff\1\121\1\40\1\37\1\43\1"+
		"\42\1\uffff\1\123\40\uffff\1\1\52\uffff\1\102\36\uffff\1\47\1\50\1\uffff"+
		"\1\53\3\uffff\1\60\4\uffff\1\65\10\uffff\1\77\6\uffff\1\110\2\uffff\1"+
		"\115\34\uffff\1\56\16\uffff\1\103\6\uffff\1\117\13\uffff\1\25\1\26\14"+
		"\uffff\1\57\10\uffff\1\72\5\uffff\1\101\4\uffff\1\114\12\uffff\1\22\11"+
		"\uffff\1\36\2\uffff\1\51\1\54\2\uffff\1\62\1\63\1\64\1\66\1\67\6\uffff"+
		"\1\100\6\uffff\1\120\1\10\4\uffff\1\16\2\uffff\1\21\2\uffff\1\27\21\uffff"+
		"\1\105\3\uffff\1\116\3\uffff\1\15\4\uffff\1\30\3\uffff\1\34\4\uffff\1"+
		"\61\2\uffff\1\73\1\uffff\1\75\1\76\2\uffff\1\107\1\uffff\1\111\3\uffff"+
		"\1\17\7\uffff\1\44\11\uffff\1\11\1\12\3\uffff\1\24\6\uffff\1\70\2\uffff"+
		"\1\104\1\106\3\uffff\1\13\2\uffff\1\31\1\uffff\1\33\3\uffff\1\71\1\74"+
		"\2\uffff\1\14\5\uffff\1\55\4\uffff\1\32\1\35\1\52\4\uffff\1\112\2\uffff"+
		"\1\23\1\113\1\20";
	static final String DFA13_specialS =
		"\u01dd\uffff}>";
	static final String[] DFA13_transitionS = {
			"\2\41\2\uffff\1\41\22\uffff\1\41\2\uffff\1\1\4\uffff\1\2\1\3\1\4\1\5"+
			"\1\uffff\1\6\1\uffff\1\7\12\37\1\10\1\40\1\11\1\12\1\13\1\14\1\uffff"+
			"\32\36\3\uffff\1\15\1\16\1\uffff\1\17\1\36\1\20\1\21\1\22\1\23\1\36\1"+
			"\24\1\25\3\36\1\26\1\27\1\30\1\31\2\36\1\32\1\33\1\34\1\36\1\35\3\36",
			"\1\42",
			"",
			"",
			"",
			"",
			"\12\37",
			"",
			"\1\44\1\uffff\1\45\1\46\1\47\1\50\1\51\1\uffff\1\52\3\uffff\1\53\1\uffff"+
			"\1\54\1\55\1\uffff\1\56\1\uffff\1\57",
			"\1\61",
			"",
			"\1\63",
			"\32\66\6\uffff\3\66\1\65\26\66",
			"",
			"",
			"\1\67\11\uffff\1\70\1\uffff\1\71\4\uffff\1\72\1\73",
			"\1\74",
			"\1\75\11\uffff\1\76",
			"\1\77\4\uffff\1\100\11\uffff\1\101",
			"\1\102",
			"\1\103",
			"\1\104\1\105\4\uffff\1\106",
			"\1\107\7\uffff\1\110",
			"\1\111\5\uffff\1\112",
			"\1\113\3\uffff\1\114\3\uffff\1\115",
			"\1\116",
			"\1\117\5\uffff\1\120\5\uffff\1\121\4\uffff\1\122",
			"\1\123",
			"\1\124",
			"\1\125\1\126",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\130",
			"\1\131",
			"\1\132\11\uffff\1\133\5\uffff\1\134",
			"\1\135\17\uffff\1\136",
			"\1\137\2\uffff\1\140",
			"\1\141",
			"\1\142",
			"\1\143",
			"\1\144",
			"\1\145\20\uffff\1\146",
			"\1\147",
			"\1\150",
			"",
			"",
			"",
			"",
			"",
			"\1\151",
			"",
			"\1\152",
			"\1\153\12\uffff\1\154",
			"\1\155",
			"\1\156",
			"\1\157\63\uffff\1\160",
			"\1\161",
			"\1\162\2\uffff\1\163",
			"\1\164",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0083",
			"\1\u0084\11\uffff\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c\11\uffff\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a0",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00a3",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00b4",
			"\1\u00b5",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00b7",
			"\1\u00b8",
			"",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00bc",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00be",
			"\1\u00bf",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00c1",
			"\1\u00c2",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5\16\uffff\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1",
			"\1\u00d2",
			"\1\u00d3\1\u00d4",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"",
			"",
			"\1\u00d9",
			"",
			"\1\u00da",
			"\1\u00db",
			"\1\36\2\uffff\2\36\1\u00dc\7\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32"+
			"\36",
			"",
			"\1\u00de",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"\1\u00e9",
			"",
			"\1\u00ea",
			"\1\u00eb",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00ed",
			"\1\u00ee",
			"\1\u00ef",
			"",
			"\1\u00f0",
			"\1\u00f1",
			"",
			"\1\u00f2",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f6",
			"\1\u00f7",
			"\1\u00f8",
			"\1\u00f9",
			"\1\u00fa",
			"\1\u00fb",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u0101",
			"\1\u0102",
			"\1\u0103",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109",
			"\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u010e",
			"\1\u010f",
			"\1\u0110",
			"\1\u0111",
			"\1\u0112",
			"\1\u0113",
			"\1\u0114\2\uffff\1\u0115",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\u011b",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u011d",
			"\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0122",
			"",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126\20\uffff\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"\1\u012b",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u012d",
			"\1\u012e",
			"",
			"",
			"\1\u012f",
			"\1\u0130",
			"\1\u0131",
			"\1\u0132",
			"\1\u0133",
			"\1\u0134",
			"\1\u0135",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u0137",
			"\1\u0138\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u013b",
			"",
			"\1\u013c",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0142",
			"\1\u0143",
			"",
			"\1\u0144",
			"\1\u0145",
			"\1\u0146",
			"\1\u0147",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u0149",
			"\1\u014a",
			"\1\u014b\20\uffff\1\u014c",
			"\1\u014d",
			"",
			"\1\u014e",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u0151",
			"\1\u0152",
			"\1\u0153",
			"\1\u0154",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u0156\6\uffff\1\u0157",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"",
			"\1\u0159",
			"\1\u015a",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u015c",
			"\1\u015d",
			"\1\u015e",
			"\1\u015f",
			"\1\u0160",
			"\1\u0161",
			"",
			"\1\u0162",
			"\1\u0163",
			"",
			"",
			"\1\u0164",
			"\1\u0165",
			"",
			"",
			"",
			"",
			"",
			"\1\u0166",
			"\1\u0167",
			"\1\u0168",
			"\1\u0169",
			"\1\u016a",
			"\1\u016b",
			"",
			"\1\u016c",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u016e",
			"\1\u016f",
			"\1\u0170",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"",
			"\1\u0172",
			"\1\u0173",
			"\1\u0174",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"",
			"\1\u0176",
			"\1\u0177",
			"",
			"\1\u0178",
			"\1\u0179",
			"",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u017b",
			"\1\u017c",
			"\1\u017d",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\1\u0182",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0184",
			"\1\u0185",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u0187",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u018a",
			"",
			"\1\u018b",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u018d\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u018f",
			"\1\u0190",
			"\1\u0191",
			"",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u0193",
			"\1\u0194",
			"\1\u0195",
			"",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"",
			"\1\u0199",
			"\1\66\2\uffff\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
			"\1\u019b",
			"\1\u019c",
			"",
			"\1\u019d",
			"\1\u019e",
			"",
			"\1\u019f",
			"",
			"",
			"\1\u01a0",
			"\1\u01a1",
			"",
			"\1\u01a2\1\u01a3",
			"",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u01a6",
			"",
			"\1\u01a7",
			"\1\u01a8",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u01aa",
			"\1\u01ab",
			"\1\u01ac",
			"\1\u01ad",
			"",
			"\1\u01ae",
			"\1\u01af",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u01b1",
			"\1\u01b2",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u01b5",
			"\1\u01b6",
			"",
			"",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\22\60\1\u01b7"+
			"\7\60",
			"\1\u01b9",
			"\1\u01ba",
			"",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u01bc",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\u01be",
			"\1\u01bf",
			"\1\u01c0",
			"",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"",
			"\1\u01c3",
			"\1\u01c4",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"",
			"\1\u01c6",
			"\1\u01c7",
			"",
			"\1\u01c8",
			"",
			"\1\u01c9",
			"\1\u01ca",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"",
			"\1\u01cc",
			"\1\u01cd",
			"",
			"\1\u01ce",
			"\1\u01cf",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"",
			"\1\u01d3",
			"\1\u01d4",
			"\1\u01d5",
			"\1\u01d6",
			"",
			"",
			"",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\u01d8",
			"\1\u01d9",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"",
			"\1\36\2\uffff\12\36\7\uffff\32\36\4\uffff\1\36\1\uffff\32\36",
			"\1\60\2\uffff\12\60\7\uffff\32\60\4\uffff\1\60\1\uffff\32\60",
			"",
			"",
			""
	};

	static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
	static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
	static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
	static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
	static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
	static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
	static final short[][] DFA13_transition;

	static {
		int numStates = DFA13_transitionS.length;
		DFA13_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
		}
	}

	protected class DFA13 extends DFA {

		public DFA13(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 13;
			this.eot = DFA13_eot;
			this.eof = DFA13_eof;
			this.min = DFA13_min;
			this.max = DFA13_max;
			this.accept = DFA13_accept;
			this.special = DFA13_special;
			this.transition = DFA13_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | REQUIRE_KEY | NAME | VARIABLE | NUMBER | LINE_COMMENT | WHITESPACE );";
		}
	}

}
