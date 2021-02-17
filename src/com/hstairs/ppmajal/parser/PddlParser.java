// $ANTLR 3.5 /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g 2019-03-24 14:38:39

package com.hstairs.ppmajal.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class PddlParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACTION", "AND_EFFECT", 
		"AND_GD", "ANY_CHAR", "ASSIGN_EFFECT", "BELIEF", "BINARY_OP", "COMPARISON_GD", 
		"CONSTANTS", "CONSTRAINT", "COS", "DIGIT", "DOMAIN", "DOMAIN_NAME", "DURATIVE_ACTION", 
		"EFFECT", "EITHER_TYPE", "EQUALITY_CON", "EVENT", "EXISTS_GD", "FORALL_EFFECT", 
		"FORALL_GD", "FORMULAINIT", "FREE_FUNCTIONS", "FUNCTIONS", "FUNC_HEAD", 
		"GLOBAL_CONSTRAINT", "GOAL", "IMPLY_GD", "INIT", "INIT_AT", "INIT_EQ", 
		"LETTER", "LINE_COMMENT", "MINUS_OP", "MULTI_OP", "NAME", "NOT_EFFECT", 
		"NOT_GD", "NOT_PRED_INIT", "NUMBER", "OBJECTS", "ONEOF", "OR_GD", "PRECONDITION", 
		"PREDICATES", "PRED_HEAD", "PRED_INST", "PROBLEM", "PROBLEM_CONSTRAINT", 
		"PROBLEM_DOMAIN", "PROBLEM_METRIC", "PROBLEM_NAME", "PROCESS", "REQUIREMENTS", 
		"REQUIRE_KEY", "SIN", "TYPES", "UNARY_MINUS", "UNKNOWN", "VARIABLE", "WHEN_EFFECT", 
		"WHITESPACE", "'#t'", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "':action'", 
		"':condition'", "':constants'", "':constraint'", "':constraints'", "':derived'", 
		"':domain'", "':duration'", "':durative-action'", "':effect'", "':event'", 
		"':free_functions'", "':functions'", "':goal'", "':init'", "':metric'", 
		"':objects'", "':parameters'", "':precondition'", "':predicates'", "':process'", 
		"':requirements'", "':types'", "'<'", "'<='", "'='", "'>'", "'>='", "'?duration'", 
		"'^'", "'abs'", "'all'", "'always'", "'always-within'", "'and'", "'assign'", 
		"'at'", "'at-most-once'", "'cos'", "'decrease'", "'define'", "'domain'", 
		"'either'", "'end'", "'exists'", "'forall'", "'hold-after'", "'hold-during'", 
		"'imply'", "'increase'", "'is-violated'", "'maximize'", "'minimize'", 
		"'not'", "'number'", "'oneof'", "'or'", "'over'", "'preference'", "'problem'", 
		"'scale-down'", "'scale-up'", "'sin'", "'sometime'", "'sometime-after'", 
		"'sometime-before'", "'start'", "'unknown'", "'when'", "'within'"
	};
	public static final int EOF=-1;
	public static final int T__68=68;
	public static final int T__69=69;
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
	public static final int ABS=4;
	public static final int ACTION=5;
	public static final int AND_EFFECT=6;
	public static final int AND_GD=7;
	public static final int ANY_CHAR=8;
	public static final int ASSIGN_EFFECT=9;
	public static final int BELIEF=10;
	public static final int BINARY_OP=11;
	public static final int COMPARISON_GD=12;
	public static final int CONSTANTS=13;
	public static final int CONSTRAINT=14;
	public static final int COS=15;
	public static final int DIGIT=16;
	public static final int DOMAIN=17;
	public static final int DOMAIN_NAME=18;
	public static final int DURATIVE_ACTION=19;
	public static final int EFFECT=20;
	public static final int EITHER_TYPE=21;
	public static final int EQUALITY_CON=22;
	public static final int EVENT=23;
	public static final int EXISTS_GD=24;
	public static final int FORALL_EFFECT=25;
	public static final int FORALL_GD=26;
	public static final int FORMULAINIT=27;
	public static final int FREE_FUNCTIONS=28;
	public static final int FUNCTIONS=29;
	public static final int FUNC_HEAD=30;
	public static final int GLOBAL_CONSTRAINT=31;
	public static final int GOAL=32;
	public static final int IMPLY_GD=33;
	public static final int INIT=34;
	public static final int INIT_AT=35;
	public static final int INIT_EQ=36;
	public static final int LETTER=37;
	public static final int LINE_COMMENT=38;
	public static final int MINUS_OP=39;
	public static final int MULTI_OP=40;
	public static final int NAME=41;
	public static final int NOT_EFFECT=42;
	public static final int NOT_GD=43;
	public static final int NOT_PRED_INIT=44;
	public static final int NUMBER=45;
	public static final int OBJECTS=46;
	public static final int ONEOF=47;
	public static final int OR_GD=48;
	public static final int PRECONDITION=49;
	public static final int PREDICATES=50;
	public static final int PRED_HEAD=51;
	public static final int PRED_INST=52;
	public static final int PROBLEM=53;
	public static final int PROBLEM_CONSTRAINT=54;
	public static final int PROBLEM_DOMAIN=55;
	public static final int PROBLEM_METRIC=56;
	public static final int PROBLEM_NAME=57;
	public static final int PROCESS=58;
	public static final int REQUIREMENTS=59;
	public static final int REQUIRE_KEY=60;
	public static final int SIN=61;
	public static final int TYPES=62;
	public static final int UNARY_MINUS=63;
	public static final int UNKNOWN=64;
	public static final int VARIABLE=65;
	public static final int WHEN_EFFECT=66;
	public static final int WHITESPACE=67;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public PddlParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public PddlParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[229+1];


	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return PddlParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g"; }


	private boolean wasError = false;
	public void reportError(RecognitionException e) {
		wasError = true;
		super.reportError(e);
	}
	public boolean invalidGrammar() {
		return wasError;
	}


	public static class pddlDoc_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "pddlDoc"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:105:1: pddlDoc : ( domain | problem );
	public final PddlParser.pddlDoc_return pddlDoc() throws RecognitionException  {
		PddlParser.pddlDoc_return retval = new PddlParser.pddlDoc_return();
		retval.start = input.LT(1);
		int pddlDoc_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope domain1 =null;
		ParserRuleReturnScope problem2 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:105:9: ( domain | problem )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==69) ) {
				int LA1_1 = input.LA(2);
				if ( (LA1_1==115) ) {
					int LA1_2 = input.LA(3);
					if ( (LA1_2==69) ) {
						int LA1_3 = input.LA(4);
						if ( (LA1_3==116) ) {
							alt1=1;
						}
						else if ( (LA1_3==134) ) {
							alt1=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 1, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:105:11: domain
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_domain_in_pddlDoc401);
					domain1=domain();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, domain1.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:105:20: problem
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_problem_in_pddlDoc405);
					problem2=problem();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, problem2.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 1, pddlDoc_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "pddlDoc"


	public static class domain_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "domain"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:109:1: domain : '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) ;
	public final PddlParser.domain_return domain() throws  RecognitionException{
		PddlParser.domain_return retval = new PddlParser.domain_return();
		retval.start = input.LT(1);
		int domain_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal3=null;
		Token string_literal4=null;
		Token char_literal14=null;
		ParserRuleReturnScope domainName5 =null;
		ParserRuleReturnScope requireDef6 =null;
		ParserRuleReturnScope typesDef7 =null;
		ParserRuleReturnScope constantsDef8 =null;
		ParserRuleReturnScope predicatesDef9 =null;
		ParserRuleReturnScope functionsDef10 =null;
		ParserRuleReturnScope free_functionsDef11 =null;
		ParserRuleReturnScope constraints12 =null;
		ParserRuleReturnScope structureDef13 =null;

		Object char_literal3_tree=null;
		Object string_literal4_tree=null;
		Object char_literal14_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_typesDef=new RewriteRuleSubtreeStream(adaptor,"rule typesDef");
		RewriteRuleSubtreeStream stream_requireDef=new RewriteRuleSubtreeStream(adaptor,"rule requireDef");
		RewriteRuleSubtreeStream stream_free_functionsDef=new RewriteRuleSubtreeStream(adaptor,"rule free_functionsDef");
		RewriteRuleSubtreeStream stream_domainName=new RewriteRuleSubtreeStream(adaptor,"rule domainName");
		RewriteRuleSubtreeStream stream_predicatesDef=new RewriteRuleSubtreeStream(adaptor,"rule predicatesDef");
		RewriteRuleSubtreeStream stream_constantsDef=new RewriteRuleSubtreeStream(adaptor,"rule constantsDef");
		RewriteRuleSubtreeStream stream_functionsDef=new RewriteRuleSubtreeStream(adaptor,"rule functionsDef");
		RewriteRuleSubtreeStream stream_constraints=new RewriteRuleSubtreeStream(adaptor,"rule constraints");
		RewriteRuleSubtreeStream stream_structureDef=new RewriteRuleSubtreeStream(adaptor,"rule structureDef");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:110:5: ( '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:110:7: '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')'
			{
			char_literal3=(Token)match(input,69,FOLLOW_69_in_domain420); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal3);

			string_literal4=(Token)match(input,115,FOLLOW_115_in_domain422); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_115.add(string_literal4);

			pushFollow(FOLLOW_domainName_in_domain424);
			domainName5=domainName();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_domainName.add(domainName5.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:111:7: ( requireDef )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==69) ) {
				int LA2_1 = input.LA(2);
				if ( (LA2_1==96) ) {
					alt2=1;
				}
			}
			switch (alt2) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:111:7: requireDef
					{
					pushFollow(FOLLOW_requireDef_in_domain432);
					requireDef6=requireDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_requireDef.add(requireDef6.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:112:7: ( typesDef )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==69) ) {
				int LA3_1 = input.LA(2);
				if ( (LA3_1==97) ) {
					alt3=1;
				}
			}
			switch (alt3) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:112:7: typesDef
					{
					pushFollow(FOLLOW_typesDef_in_domain441);
					typesDef7=typesDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_typesDef.add(typesDef7.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:113:7: ( constantsDef )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==69) ) {
				int LA4_1 = input.LA(2);
				if ( (LA4_1==77) ) {
					alt4=1;
				}
			}
			switch (alt4) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:113:7: constantsDef
					{
					pushFollow(FOLLOW_constantsDef_in_domain450);
					constantsDef8=constantsDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_constantsDef.add(constantsDef8.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:114:7: ( predicatesDef )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==69) ) {
				int LA5_1 = input.LA(2);
				if ( (LA5_1==94) ) {
					alt5=1;
				}
			}
			switch (alt5) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:114:7: predicatesDef
					{
					pushFollow(FOLLOW_predicatesDef_in_domain459);
					predicatesDef9=predicatesDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_predicatesDef.add(predicatesDef9.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:115:7: ( functionsDef )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==69) ) {
				int LA6_1 = input.LA(2);
				if ( (LA6_1==87) ) {
					alt6=1;
				}
			}
			switch (alt6) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:115:7: functionsDef
					{
					pushFollow(FOLLOW_functionsDef_in_domain468);
					functionsDef10=functionsDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_functionsDef.add(functionsDef10.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:116:7: ( free_functionsDef )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==69) ) {
				int LA7_1 = input.LA(2);
				if ( (LA7_1==86) ) {
					alt7=1;
				}
			}
			switch (alt7) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:116:7: free_functionsDef
					{
					pushFollow(FOLLOW_free_functionsDef_in_domain477);
					free_functionsDef11=free_functionsDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_free_functionsDef.add(free_functionsDef11.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:117:7: ( constraints )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==69) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==79) ) {
					alt8=1;
				}
			}
			switch (alt8) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:117:7: constraints
					{
					pushFollow(FOLLOW_constraints_in_domain486);
					constraints12=constraints();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_constraints.add(constraints12.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:118:7: ( structureDef )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==69) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:118:7: structureDef
					{
					pushFollow(FOLLOW_structureDef_in_domain495);
					structureDef13=structureDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_structureDef.add(structureDef13.getTree());
					}
					break;

				default :
					break loop9;
				}
			}

			char_literal14=(Token)match(input,70,FOLLOW_70_in_domain504); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal14);

			// AST REWRITE
			// elements: domainName, requireDef, predicatesDef, constraints, typesDef, functionsDef, constantsDef, free_functionsDef, structureDef
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 120:7: -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:120:10: ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DOMAIN, "DOMAIN"), root_1);
				adaptor.addChild(root_1, stream_domainName.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:120:30: ( requireDef )?
				if ( stream_requireDef.hasNext() ) {
					adaptor.addChild(root_1, stream_requireDef.nextTree());
				}
				stream_requireDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:120:42: ( typesDef )?
				if ( stream_typesDef.hasNext() ) {
					adaptor.addChild(root_1, stream_typesDef.nextTree());
				}
				stream_typesDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:121:17: ( constantsDef )?
				if ( stream_constantsDef.hasNext() ) {
					adaptor.addChild(root_1, stream_constantsDef.nextTree());
				}
				stream_constantsDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:121:31: ( predicatesDef )?
				if ( stream_predicatesDef.hasNext() ) {
					adaptor.addChild(root_1, stream_predicatesDef.nextTree());
				}
				stream_predicatesDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:121:46: ( functionsDef )?
				if ( stream_functionsDef.hasNext() ) {
					adaptor.addChild(root_1, stream_functionsDef.nextTree());
				}
				stream_functionsDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:121:60: ( free_functionsDef )?
				if ( stream_free_functionsDef.hasNext() ) {
					adaptor.addChild(root_1, stream_free_functionsDef.nextTree());
				}
				stream_free_functionsDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:122:17: ( constraints )?
				if ( stream_constraints.hasNext() ) {
					adaptor.addChild(root_1, stream_constraints.nextTree());
				}
				stream_constraints.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:122:30: ( structureDef )*
				while ( stream_structureDef.hasNext() ) {
					adaptor.addChild(root_1, stream_structureDef.nextTree());
				}
				stream_structureDef.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 2, domain_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "domain"


	public static class free_functionsDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "free_functionsDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:125:1: free_functionsDef : '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) ;
	public final PddlParser.free_functionsDef_return free_functionsDef() throws RecognitionException{
		PddlParser.free_functionsDef_return retval = new PddlParser.free_functionsDef_return();
		retval.start = input.LT(1);
		int free_functionsDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal15=null;
		Token string_literal16=null;
		Token char_literal18=null;
		ParserRuleReturnScope functionList17 =null;

		Object char_literal15_tree=null;
		Object string_literal16_tree=null;
		Object char_literal18_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
		RewriteRuleSubtreeStream stream_functionList=new RewriteRuleSubtreeStream(adaptor,"rule functionList");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:126:2: ( '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:126:4: '(' ':free_functions' functionList ')'
			{
			char_literal15=(Token)match(input,69,FOLLOW_69_in_free_functionsDef588); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal15);

			string_literal16=(Token)match(input,86,FOLLOW_86_in_free_functionsDef590); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_86.add(string_literal16);

			pushFollow(FOLLOW_functionList_in_free_functionsDef592);
			functionList17=functionList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_functionList.add(functionList17.getTree());
			char_literal18=(Token)match(input,70,FOLLOW_70_in_free_functionsDef594); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal18);

			// AST REWRITE
			// elements: functionList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 127:2: -> ^( FREE_FUNCTIONS functionList )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:127:5: ^( FREE_FUNCTIONS functionList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FREE_FUNCTIONS, "FREE_FUNCTIONS"), root_1);
				adaptor.addChild(root_1, stream_functionList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 3, free_functionsDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "free_functionsDef"


	public static class domainName_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "domainName"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:130:1: domainName : '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) ;
	public final PddlParser.domainName_return domainName() throws RecognitionException{
		PddlParser.domainName_return retval = new PddlParser.domainName_return();
		retval.start = input.LT(1);
		int domainName_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal19=null;
		Token string_literal20=null;
		Token NAME21=null;
		Token char_literal22=null;

		Object char_literal19_tree=null;
		Object string_literal20_tree=null;
		Object NAME21_tree=null;
		Object char_literal22_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:131:5: ( '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:131:7: '(' 'domain' NAME ')'
			{
			char_literal19=(Token)match(input,69,FOLLOW_69_in_domainName617); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal19);

			string_literal20=(Token)match(input,116,FOLLOW_116_in_domainName619); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_116.add(string_literal20);

			NAME21=(Token)match(input,NAME,FOLLOW_NAME_in_domainName621); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_NAME.add(NAME21);

			char_literal22=(Token)match(input,70,FOLLOW_70_in_domainName623); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal22);

			// AST REWRITE
			// elements: NAME
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 132:6: -> ^( DOMAIN_NAME NAME )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:132:9: ^( DOMAIN_NAME NAME )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DOMAIN_NAME, "DOMAIN_NAME"), root_1);
				adaptor.addChild(root_1, stream_NAME.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 4, domainName_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "domainName"


	public static class requireDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "requireDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:135:1: requireDef : '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) ;
	public final PddlParser.requireDef_return requireDef() throws RecognitionException{
		PddlParser.requireDef_return retval = new PddlParser.requireDef_return();
		retval.start = input.LT(1);
		int requireDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal23=null;
		Token string_literal24=null;
		Token REQUIRE_KEY25=null;
		Token char_literal26=null;

		Object char_literal23_tree=null;
		Object string_literal24_tree=null;
		Object REQUIRE_KEY25_tree=null;
		Object char_literal26_tree=null;
		RewriteRuleTokenStream stream_REQUIRE_KEY=new RewriteRuleTokenStream(adaptor,"token REQUIRE_KEY");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:136:2: ( '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:136:4: '(' ':requirements' ( REQUIRE_KEY )+ ')'
			{
			char_literal23=(Token)match(input,69,FOLLOW_69_in_requireDef650); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal23);

			string_literal24=(Token)match(input,96,FOLLOW_96_in_requireDef652); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_96.add(string_literal24);

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:136:24: ( REQUIRE_KEY )+
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==REQUIRE_KEY) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:136:24: REQUIRE_KEY
					{
					REQUIRE_KEY25=(Token)match(input,REQUIRE_KEY,FOLLOW_REQUIRE_KEY_in_requireDef654); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_REQUIRE_KEY.add(REQUIRE_KEY25);

					}
					break;

				default :
					if ( cnt10 >= 1 ) break loop10;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
			}

			char_literal26=(Token)match(input,70,FOLLOW_70_in_requireDef657); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal26);

			// AST REWRITE
			// elements: REQUIRE_KEY
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 137:2: -> ^( REQUIREMENTS ( REQUIRE_KEY )+ )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:137:5: ^( REQUIREMENTS ( REQUIRE_KEY )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(REQUIREMENTS, "REQUIREMENTS"), root_1);
				if ( !(stream_REQUIRE_KEY.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_REQUIRE_KEY.hasNext() ) {
					adaptor.addChild(root_1, stream_REQUIRE_KEY.nextNode());
				}
				stream_REQUIRE_KEY.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 5, requireDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "requireDef"


	public static class typesDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "typesDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:140:1: typesDef : '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) ;
	public final PddlParser.typesDef_return typesDef() throws RecognitionException{
		PddlParser.typesDef_return retval = new PddlParser.typesDef_return();
		retval.start = input.LT(1);
		int typesDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal27=null;
		Token string_literal28=null;
		Token char_literal30=null;
		ParserRuleReturnScope typedNameList29 =null;

		Object char_literal27_tree=null;
		Object string_literal28_tree=null;
		Object char_literal30_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");
		RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:141:2: ( '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:141:4: '(' ':types' typedNameList ')'
			{
			char_literal27=(Token)match(input,69,FOLLOW_69_in_typesDef678); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal27);

			string_literal28=(Token)match(input,97,FOLLOW_97_in_typesDef680); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_97.add(string_literal28);

			pushFollow(FOLLOW_typedNameList_in_typesDef682);
			typedNameList29=typedNameList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList29.getTree());
			char_literal30=(Token)match(input,70,FOLLOW_70_in_typesDef684); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal30);

			// AST REWRITE
			// elements: typedNameList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 142:4: -> ^( TYPES typedNameList )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:142:7: ^( TYPES typedNameList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPES, "TYPES"), root_1);
				adaptor.addChild(root_1, stream_typedNameList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 6, typesDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "typesDef"


	public static class typedNameList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "typedNameList"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:146:1: typedNameList : ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) ;
	public final PddlParser.typedNameList_return typedNameList() throws RecognitionException{
		PddlParser.typedNameList_return retval = new PddlParser.typedNameList_return();
		retval.start = input.LT(1);
		int typedNameList_StartIndex = input.index();

		Object root_0 = null;

		Token NAME31=null;
		Token NAME33=null;
		ParserRuleReturnScope singleTypeNameList32 =null;

		Object NAME31_tree=null;
		Object NAME33_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:5: ( ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
			{
			root_0 = (Object)adaptor.nil();


			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
			int alt14=2;
			alt14 = dfa14.predict(input);
			switch (alt14) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:8: ( NAME )*
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:8: ( NAME )*
					loop11:
					while (true) {
						int alt11=2;
						int LA11_0 = input.LA(1);
						if ( (LA11_0==NAME) ) {
							alt11=1;
						}

						switch (alt11) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:8: NAME
							{
							NAME31=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList711); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NAME31_tree = (Object)adaptor.create(NAME31);
							adaptor.addChild(root_0, NAME31_tree);
							}

							}
							break;

						default :
							break loop11;
						}
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:16: ( singleTypeNameList )+ ( NAME )*
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:16: ( singleTypeNameList )+
					int cnt12=0;
					loop12:
					while (true) {
						int alt12=2;
						alt12 = dfa12.predict(input);
						switch (alt12) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:16: singleTypeNameList
							{
							pushFollow(FOLLOW_singleTypeNameList_in_typedNameList716);
							singleTypeNameList32=singleTypeNameList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeNameList32.getTree());

							}
							break;

						default :
							if ( cnt12 >= 1 ) break loop12;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(12, input);
							throw eee;
						}
						cnt12++;
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:36: ( NAME )*
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==NAME) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:147:36: NAME
							{
							NAME33=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList719); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NAME33_tree = (Object)adaptor.create(NAME33);
							adaptor.addChild(root_0, NAME33_tree);
							}

							}
							break;

						default :
							break loop13;
						}
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 7, typedNameList_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "typedNameList"


	public static class singleTypeNameList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "singleTypeNameList"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:150:1: singleTypeNameList : ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ ;
	public final PddlParser.singleTypeNameList_return singleTypeNameList() throws RecognitionException{
		PddlParser.singleTypeNameList_return retval = new PddlParser.singleTypeNameList_return();
		retval.start = input.LT(1);
		int singleTypeNameList_StartIndex = input.index();

		Object root_0 = null;

		Token NAME34=null;
		Token char_literal35=null;
		ParserRuleReturnScope t =null;

		Object NAME34_tree=null;
		Object char_literal35_tree=null;
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
		RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:5: ( ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:7: ( ( NAME )+ '-' t= type )
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:7: ( ( NAME )+ '-' t= type )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:8: ( NAME )+ '-' t= type
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:8: ( NAME )+
			int cnt15=0;
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==NAME) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:151:8: NAME
					{
					NAME34=(Token)match(input,NAME,FOLLOW_NAME_in_singleTypeNameList739); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NAME.add(NAME34);

					}
					break;

				default :
					if ( cnt15 >= 1 ) break loop15;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(15, input);
					throw eee;
				}
				cnt15++;
			}

			char_literal35=(Token)match(input,73,FOLLOW_73_in_singleTypeNameList742); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_73.add(char_literal35);

			pushFollow(FOLLOW_type_in_singleTypeNameList746);
			t=type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_type.add(t.getTree());
			}

			// AST REWRITE
			// elements: t, NAME
			// token labels: 
			// rule labels: t, retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 152:4: -> ( ^( NAME $t) )+
			{
				if ( !(stream_t.hasNext()||stream_NAME.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_t.hasNext()||stream_NAME.hasNext() ) {
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:152:7: ^( NAME $t)
					{
					Object root_1 = (Object)adaptor.nil();
					root_1 = (Object)adaptor.becomeRoot(stream_NAME.nextNode(), root_1);
					adaptor.addChild(root_1, stream_t.nextTree());
					adaptor.addChild(root_0, root_1);
					}

				}
				stream_t.reset();
				stream_NAME.reset();

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 8, singleTypeNameList_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "singleTypeNameList"


	public static class type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:155:1: type : ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType );
	public final PddlParser.type_return type() throws RecognitionException{
		PddlParser.type_return retval = new PddlParser.type_return();
		retval.start = input.LT(1);
		int type_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal36=null;
		Token string_literal37=null;
		Token char_literal39=null;
		ParserRuleReturnScope primType38 =null;
		ParserRuleReturnScope primType40 =null;

		Object char_literal36_tree=null;
		Object string_literal37_tree=null;
		Object char_literal39_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_primType=new RewriteRuleSubtreeStream(adaptor,"rule primType");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:2: ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==69) ) {
				alt17=1;
			}
			else if ( (LA17_0==NAME) ) {
				alt17=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:4: ( '(' 'either' ( primType )+ ')' )
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:4: ( '(' 'either' ( primType )+ ')' )
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:6: '(' 'either' ( primType )+ ')'
					{
					char_literal36=(Token)match(input,69,FOLLOW_69_in_type773); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal36);

					string_literal37=(Token)match(input,117,FOLLOW_117_in_type775); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_117.add(string_literal37);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:19: ( primType )+
					int cnt16=0;
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( (LA16_0==NAME) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:156:19: primType
							{
							pushFollow(FOLLOW_primType_in_type777);
							primType38=primType();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_primType.add(primType38.getTree());
							}
							break;

						default :
							if ( cnt16 >= 1 ) break loop16;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(16, input);
							throw eee;
						}
						cnt16++;
					}

					char_literal39=(Token)match(input,70,FOLLOW_70_in_type780); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal39);

					}

					// AST REWRITE
					// elements: primType
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 157:4: -> ^( EITHER_TYPE ( primType )+ )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:157:7: ^( EITHER_TYPE ( primType )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EITHER_TYPE, "EITHER_TYPE"), root_1);
						if ( !(stream_primType.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_primType.hasNext() ) {
							adaptor.addChild(root_1, stream_primType.nextTree());
						}
						stream_primType.reset();

						adaptor.addChild(root_0, root_1);
						}

						adaptor.addChild(root_0, new String("debug"));
					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:158:4: primType
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_primType_in_type801);
					primType40=primType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, primType40.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 9, type_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "type"


	public static class primType_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "primType"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:161:1: primType : NAME ;
	public final PddlParser.primType_return primType() throws RecognitionException{
		PddlParser.primType_return retval = new PddlParser.primType_return();
		retval.start = input.LT(1);
		int primType_StartIndex = input.index();

		Object root_0 = null;

		Token NAME41=null;

		Object NAME41_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:161:10: ( NAME )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:161:12: NAME
			{
			root_0 = (Object)adaptor.nil();


			NAME41=(Token)match(input,NAME,FOLLOW_NAME_in_primType811); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME41_tree = (Object)adaptor.create(NAME41);
			adaptor.addChild(root_0, NAME41_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 10, primType_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "primType"


	public static class functionsDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "functionsDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:163:1: functionsDef : '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) ;
	public final PddlParser.functionsDef_return functionsDef() throws RecognitionException{
		PddlParser.functionsDef_return retval = new PddlParser.functionsDef_return();
		retval.start = input.LT(1);
		int functionsDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal42=null;
		Token string_literal43=null;
		Token char_literal45=null;
		ParserRuleReturnScope functionList44 =null;

		Object char_literal42_tree=null;
		Object string_literal43_tree=null;
		Object char_literal45_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
		RewriteRuleSubtreeStream stream_functionList=new RewriteRuleSubtreeStream(adaptor,"rule functionList");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:164:2: ( '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:164:4: '(' ':functions' functionList ')'
			{
			char_literal42=(Token)match(input,69,FOLLOW_69_in_functionsDef821); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal42);

			string_literal43=(Token)match(input,87,FOLLOW_87_in_functionsDef823); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_87.add(string_literal43);

			pushFollow(FOLLOW_functionList_in_functionsDef825);
			functionList44=functionList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_functionList.add(functionList44.getTree());
			char_literal45=(Token)match(input,70,FOLLOW_70_in_functionsDef827); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal45);

			// AST REWRITE
			// elements: functionList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 165:2: -> ^( FUNCTIONS functionList )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:165:5: ^( FUNCTIONS functionList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNCTIONS, "FUNCTIONS"), root_1);
				adaptor.addChild(root_1, stream_functionList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, functionsDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "functionsDef"


	public static class functionList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "functionList"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:168:1: functionList : ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* ;
	public final PddlParser.functionList_return functionList() throws RecognitionException{
		PddlParser.functionList_return retval = new PddlParser.functionList_return();
		retval.start = input.LT(1);
		int functionList_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal47=null;
		ParserRuleReturnScope atomicFunctionSkeleton46 =null;
		ParserRuleReturnScope functionType48 =null;

		Object char_literal47_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:2: ( ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
			{
			root_0 = (Object)adaptor.nil();


			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0==69) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:5: ( atomicFunctionSkeleton )+ ( '-' functionType )?
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:5: ( atomicFunctionSkeleton )+
					int cnt18=0;
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0==69) ) {
							int LA18_2 = input.LA(2);
							if ( (synpred18_Pddl()) ) {
								alt18=1;
							}

						}

						switch (alt18) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:5: atomicFunctionSkeleton
							{
							pushFollow(FOLLOW_atomicFunctionSkeleton_in_functionList848);
							atomicFunctionSkeleton46=atomicFunctionSkeleton();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicFunctionSkeleton46.getTree());

							}
							break;

						default :
							if ( cnt18 >= 1 ) break loop18;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(18, input);
							throw eee;
						}
						cnt18++;
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:29: ( '-' functionType )?
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==73) ) {
						alt19=1;
					}
					switch (alt19) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:30: '-' functionType
							{
							char_literal47=(Token)match(input,73,FOLLOW_73_in_functionList852); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal47_tree = (Object)adaptor.create(char_literal47);
							adaptor.addChild(root_0, char_literal47_tree);
							}

							pushFollow(FOLLOW_functionType_in_functionList854);
							functionType48=functionType();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, functionType48.getTree());

							}
							break;

					}

					}
					break;

				default :
					break loop20;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, functionList_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "functionList"


	public static class atomicFunctionSkeleton_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomicFunctionSkeleton"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:172:1: atomicFunctionSkeleton : '(' ! functionSymbol ^ typedVariableList ')' !;
	public final PddlParser.atomicFunctionSkeleton_return atomicFunctionSkeleton() throws RecognitionException{
		PddlParser.atomicFunctionSkeleton_return retval = new PddlParser.atomicFunctionSkeleton_return();
		retval.start = input.LT(1);
		int atomicFunctionSkeleton_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal49=null;
		Token char_literal52=null;
		ParserRuleReturnScope functionSymbol50 =null;
		ParserRuleReturnScope typedVariableList51 =null;

		Object char_literal49_tree=null;
		Object char_literal52_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:173:2: ( '(' ! functionSymbol ^ typedVariableList ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:173:4: '(' ! functionSymbol ^ typedVariableList ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal49=(Token)match(input,69,FOLLOW_69_in_atomicFunctionSkeleton870); if (state.failed) return retval;
			pushFollow(FOLLOW_functionSymbol_in_atomicFunctionSkeleton873);
			functionSymbol50=functionSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(functionSymbol50.getTree(), root_0);
			pushFollow(FOLLOW_typedVariableList_in_atomicFunctionSkeleton876);
			typedVariableList51=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList51.getTree());

			char_literal52=(Token)match(input,70,FOLLOW_70_in_atomicFunctionSkeleton878); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, atomicFunctionSkeleton_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "atomicFunctionSkeleton"


	public static class functionSymbol_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "functionSymbol"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:176:1: functionSymbol : ( NAME | '#t' );
	public final PddlParser.functionSymbol_return functionSymbol() throws RecognitionException{
		PddlParser.functionSymbol_return retval = new PddlParser.functionSymbol_return();
		retval.start = input.LT(1);
		int functionSymbol_StartIndex = input.index();

		Object root_0 = null;

		Token set53=null;

		Object set53_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:176:16: ( NAME | '#t' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set53=input.LT(1);
			if ( input.LA(1)==NAME||input.LA(1)==68 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set53));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 14, functionSymbol_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "functionSymbol"


	public static class functionType_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "functionType"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:178:1: functionType : 'number' ;
	public final PddlParser.functionType_return functionType() throws RecognitionException{
		PddlParser.functionType_return retval = new PddlParser.functionType_return();
		retval.start = input.LT(1);
		int functionType_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal54=null;

		Object string_literal54_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:178:14: ( 'number' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:178:16: 'number'
			{
			root_0 = (Object)adaptor.nil();


			string_literal54=(Token)match(input,129,FOLLOW_129_in_functionType901); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal54_tree = (Object)adaptor.create(string_literal54);
			adaptor.addChild(root_0, string_literal54_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 15, functionType_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "functionType"


	public static class constantsDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constantsDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:180:1: constantsDef : '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) ;
	public final PddlParser.constantsDef_return constantsDef() throws RecognitionException{
		PddlParser.constantsDef_return retval = new PddlParser.constantsDef_return();
		retval.start = input.LT(1);
		int constantsDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal55=null;
		Token string_literal56=null;
		Token char_literal58=null;
		ParserRuleReturnScope typedNameList57 =null;

		Object char_literal55_tree=null;
		Object string_literal56_tree=null;
		Object char_literal58_tree=null;
		RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:181:2: ( '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:181:4: '(' ':constants' typedNameList ')'
			{
			char_literal55=(Token)match(input,69,FOLLOW_69_in_constantsDef912); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal55);

			string_literal56=(Token)match(input,77,FOLLOW_77_in_constantsDef914); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_77.add(string_literal56);

			pushFollow(FOLLOW_typedNameList_in_constantsDef916);
			typedNameList57=typedNameList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList57.getTree());
			char_literal58=(Token)match(input,70,FOLLOW_70_in_constantsDef918); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal58);

			// AST REWRITE
			// elements: typedNameList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 182:2: -> ^( CONSTANTS typedNameList )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:182:5: ^( CONSTANTS typedNameList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONSTANTS, "CONSTANTS"), root_1);
				adaptor.addChild(root_1, stream_typedNameList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 16, constantsDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "constantsDef"


	public static class predicatesDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "predicatesDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:185:1: predicatesDef : '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) ;
	public final PddlParser.predicatesDef_return predicatesDef() throws RecognitionException{
		PddlParser.predicatesDef_return retval = new PddlParser.predicatesDef_return();
		retval.start = input.LT(1);
		int predicatesDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal59=null;
		Token string_literal60=null;
		Token char_literal62=null;
		ParserRuleReturnScope atomicFormulaSkeleton61 =null;

		Object char_literal59_tree=null;
		Object string_literal60_tree=null;
		Object char_literal62_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
		RewriteRuleSubtreeStream stream_atomicFormulaSkeleton=new RewriteRuleSubtreeStream(adaptor,"rule atomicFormulaSkeleton");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:186:2: ( '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:186:4: '(' ':predicates' ( atomicFormulaSkeleton )+ ')'
			{
			char_literal59=(Token)match(input,69,FOLLOW_69_in_predicatesDef938); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal59);

			string_literal60=(Token)match(input,94,FOLLOW_94_in_predicatesDef940); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_94.add(string_literal60);

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:186:22: ( atomicFormulaSkeleton )+
			int cnt21=0;
			loop21:
			while (true) {
				int alt21=2;
				int LA21_0 = input.LA(1);
				if ( (LA21_0==69) ) {
					alt21=1;
				}

				switch (alt21) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:186:22: atomicFormulaSkeleton
					{
					pushFollow(FOLLOW_atomicFormulaSkeleton_in_predicatesDef942);
					atomicFormulaSkeleton61=atomicFormulaSkeleton();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_atomicFormulaSkeleton.add(atomicFormulaSkeleton61.getTree());
					}
					break;

				default :
					if ( cnt21 >= 1 ) break loop21;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(21, input);
					throw eee;
				}
				cnt21++;
			}

			char_literal62=(Token)match(input,70,FOLLOW_70_in_predicatesDef945); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal62);

			// AST REWRITE
			// elements: atomicFormulaSkeleton
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 187:2: -> ^( PREDICATES ( atomicFormulaSkeleton )+ )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:187:5: ^( PREDICATES ( atomicFormulaSkeleton )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PREDICATES, "PREDICATES"), root_1);
				if ( !(stream_atomicFormulaSkeleton.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_atomicFormulaSkeleton.hasNext() ) {
					adaptor.addChild(root_1, stream_atomicFormulaSkeleton.nextTree());
				}
				stream_atomicFormulaSkeleton.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 17, predicatesDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "predicatesDef"


	public static class atomicFormulaSkeleton_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomicFormulaSkeleton"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:190:1: atomicFormulaSkeleton : '(' ! predicate ^ typedVariableList ')' !;
	public final PddlParser.atomicFormulaSkeleton_return atomicFormulaSkeleton() throws RecognitionException{
		PddlParser.atomicFormulaSkeleton_return retval = new PddlParser.atomicFormulaSkeleton_return();
		retval.start = input.LT(1);
		int atomicFormulaSkeleton_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal63=null;
		Token char_literal66=null;
		ParserRuleReturnScope predicate64 =null;
		ParserRuleReturnScope typedVariableList65 =null;

		Object char_literal63_tree=null;
		Object char_literal66_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:191:2: ( '(' ! predicate ^ typedVariableList ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:191:4: '(' ! predicate ^ typedVariableList ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal63=(Token)match(input,69,FOLLOW_69_in_atomicFormulaSkeleton966); if (state.failed) return retval;
			pushFollow(FOLLOW_predicate_in_atomicFormulaSkeleton969);
			predicate64=predicate();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(predicate64.getTree(), root_0);
			pushFollow(FOLLOW_typedVariableList_in_atomicFormulaSkeleton972);
			typedVariableList65=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList65.getTree());

			char_literal66=(Token)match(input,70,FOLLOW_70_in_atomicFormulaSkeleton974); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 18, atomicFormulaSkeleton_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "atomicFormulaSkeleton"


	public static class predicate_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "predicate"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:194:1: predicate : NAME ;
	public final PddlParser.predicate_return predicate() throws RecognitionException{
		PddlParser.predicate_return retval = new PddlParser.predicate_return();
		retval.start = input.LT(1);
		int predicate_StartIndex = input.index();

		Object root_0 = null;

		Token NAME67=null;

		Object NAME67_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:194:11: ( NAME )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:194:13: NAME
			{
			root_0 = (Object)adaptor.nil();


			NAME67=(Token)match(input,NAME,FOLLOW_NAME_in_predicate985); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME67_tree = (Object)adaptor.create(NAME67);
			adaptor.addChild(root_0, NAME67_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 19, predicate_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "predicate"


	public static class typedVariableList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "typedVariableList"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:197:1: typedVariableList : ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) ;
	public final PddlParser.typedVariableList_return typedVariableList() throws RecognitionException{
		PddlParser.typedVariableList_return retval = new PddlParser.typedVariableList_return();
		retval.start = input.LT(1);
		int typedVariableList_StartIndex = input.index();

		Object root_0 = null;

		Token VARIABLE68=null;
		Token VARIABLE70=null;
		ParserRuleReturnScope singleTypeVarList69 =null;

		Object VARIABLE68_tree=null;
		Object VARIABLE70_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:5: ( ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
			{
			root_0 = (Object)adaptor.nil();


			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
			int alt25=2;
			alt25 = dfa25.predict(input);
			switch (alt25) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:8: ( VARIABLE )*
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:8: ( VARIABLE )*
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==VARIABLE) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:8: VARIABLE
							{
							VARIABLE68=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList1000); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							VARIABLE68_tree = (Object)adaptor.create(VARIABLE68);
							adaptor.addChild(root_0, VARIABLE68_tree);
							}

							}
							break;

						default :
							break loop22;
						}
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:20: ( singleTypeVarList )+ ( VARIABLE )*
					{
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:20: ( singleTypeVarList )+
					int cnt23=0;
					loop23:
					while (true) {
						int alt23=2;
						alt23 = dfa23.predict(input);
						switch (alt23) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:20: singleTypeVarList
							{
							pushFollow(FOLLOW_singleTypeVarList_in_typedVariableList1005);
							singleTypeVarList69=singleTypeVarList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeVarList69.getTree());

							}
							break;

						default :
							if ( cnt23 >= 1 ) break loop23;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(23, input);
							throw eee;
						}
						cnt23++;
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:39: ( VARIABLE )*
					loop24:
					while (true) {
						int alt24=2;
						int LA24_0 = input.LA(1);
						if ( (LA24_0==VARIABLE) ) {
							alt24=1;
						}

						switch (alt24) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:198:39: VARIABLE
							{
							VARIABLE70=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList1008); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							VARIABLE70_tree = (Object)adaptor.create(VARIABLE70);
							adaptor.addChild(root_0, VARIABLE70_tree);
							}

							}
							break;

						default :
							break loop24;
						}
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 20, typedVariableList_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "typedVariableList"


	public static class singleTypeVarList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "singleTypeVarList"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:201:1: singleTypeVarList : ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ ;
	public final PddlParser.singleTypeVarList_return singleTypeVarList() throws RecognitionException{
		PddlParser.singleTypeVarList_return retval = new PddlParser.singleTypeVarList_return();
		retval.start = input.LT(1);
		int singleTypeVarList_StartIndex = input.index();

		Object root_0 = null;

		Token VARIABLE71=null;
		Token char_literal72=null;
		ParserRuleReturnScope t =null;

		Object VARIABLE71_tree=null;
		Object char_literal72_tree=null;
		RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:5: ( ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:7: ( ( VARIABLE )+ '-' t= type )
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:7: ( ( VARIABLE )+ '-' t= type )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:8: ( VARIABLE )+ '-' t= type
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:8: ( VARIABLE )+
			int cnt26=0;
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( (LA26_0==VARIABLE) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:202:8: VARIABLE
					{
					VARIABLE71=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_singleTypeVarList1028); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_VARIABLE.add(VARIABLE71);

					}
					break;

				default :
					if ( cnt26 >= 1 ) break loop26;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(26, input);
					throw eee;
				}
				cnt26++;
			}

			char_literal72=(Token)match(input,73,FOLLOW_73_in_singleTypeVarList1031); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_73.add(char_literal72);

			pushFollow(FOLLOW_type_in_singleTypeVarList1035);
			t=type();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_type.add(t.getTree());
			}

			// AST REWRITE
			// elements: VARIABLE, t
			// token labels: 
			// rule labels: t, retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 203:7: -> ( ^( VARIABLE $t) )+
			{
				if ( !(stream_VARIABLE.hasNext()||stream_t.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_VARIABLE.hasNext()||stream_t.hasNext() ) {
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:203:10: ^( VARIABLE $t)
					{
					Object root_1 = (Object)adaptor.nil();
					root_1 = (Object)adaptor.becomeRoot(stream_VARIABLE.nextNode(), root_1);
					adaptor.addChild(root_1, stream_t.nextTree());
					adaptor.addChild(root_0, root_1);
					}

				}
				stream_VARIABLE.reset();
				stream_t.reset();

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 21, singleTypeVarList_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "singleTypeVarList"


	public static class constraints_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constraints"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:206:1: constraints : '(' ! ':constraints' ^ conGD ')' !;
	public final PddlParser.constraints_return constraints() throws RecognitionException{
		PddlParser.constraints_return retval = new PddlParser.constraints_return();
		retval.start = input.LT(1);
		int constraints_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal73=null;
		Token string_literal74=null;
		Token char_literal76=null;
		ParserRuleReturnScope conGD75 =null;

		Object char_literal73_tree=null;
		Object string_literal74_tree=null;
		Object char_literal76_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:207:2: ( '(' ! ':constraints' ^ conGD ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:207:4: '(' ! ':constraints' ^ conGD ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal73=(Token)match(input,69,FOLLOW_69_in_constraints1066); if (state.failed) return retval;
			string_literal74=(Token)match(input,79,FOLLOW_79_in_constraints1069); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal74_tree = (Object)adaptor.create(string_literal74);
			root_0 = (Object)adaptor.becomeRoot(string_literal74_tree, root_0);
			}

			pushFollow(FOLLOW_conGD_in_constraints1072);
			conGD75=conGD();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD75.getTree());

			char_literal76=(Token)match(input,70,FOLLOW_70_in_constraints1074); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 22, constraints_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "constraints"


	public static class structureDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "structureDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:210:1: structureDef : ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef );
	public final PddlParser.structureDef_return structureDef() throws RecognitionException{
		PddlParser.structureDef_return retval = new PddlParser.structureDef_return();
		retval.start = input.LT(1);
		int structureDef_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope actionDef77 =null;
		ParserRuleReturnScope durativeActionDef78 =null;
		ParserRuleReturnScope derivedDef79 =null;
		ParserRuleReturnScope constraintDef80 =null;
		ParserRuleReturnScope processDef81 =null;
		ParserRuleReturnScope eventDef82 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:211:2: ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef )
			int alt27=6;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==69) ) {
				switch ( input.LA(2) ) {
				case 75:
					{
					alt27=1;
					}
					break;
				case 83:
					{
					alt27=2;
					}
					break;
				case 80:
					{
					alt27=3;
					}
					break;
				case 78:
					{
					alt27=4;
					}
					break;
				case 95:
					{
					alt27=5;
					}
					break;
				case 85:
					{
					alt27=6;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 27, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:211:4: actionDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_actionDef_in_structureDef1086);
					actionDef77=actionDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, actionDef77.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:212:4: durativeActionDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_durativeActionDef_in_structureDef1091);
					durativeActionDef78=durativeActionDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, durativeActionDef78.getTree());

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:213:4: derivedDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_derivedDef_in_structureDef1096);
					derivedDef79=derivedDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, derivedDef79.getTree());

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:214:4: constraintDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_constraintDef_in_structureDef1101);
					constraintDef80=constraintDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, constraintDef80.getTree());

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:215:4: processDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_processDef_in_structureDef1106);
					processDef81=processDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, processDef81.getTree());

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:216:4: eventDef
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_eventDef_in_structureDef1111);
					eventDef82=eventDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, eventDef82.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 23, structureDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "structureDef"


	public static class actionDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "actionDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:222:1: actionDef : '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) ;
	public final PddlParser.actionDef_return actionDef() throws RecognitionException{
		PddlParser.actionDef_return retval = new PddlParser.actionDef_return();
		retval.start = input.LT(1);
		int actionDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal83=null;
		Token string_literal84=null;
		Token string_literal86=null;
		Token char_literal87=null;
		Token char_literal89=null;
		Token char_literal91=null;
		ParserRuleReturnScope actionSymbol85 =null;
		ParserRuleReturnScope typedVariableList88 =null;
		ParserRuleReturnScope actionDefBody90 =null;

		Object char_literal83_tree=null;
		Object string_literal84_tree=null;
		Object string_literal86_tree=null;
		Object char_literal87_tree=null;
		Object char_literal89_tree=null;
		Object char_literal91_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
		RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:223:2: ( '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:223:4: '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
			{
			char_literal83=(Token)match(input,69,FOLLOW_69_in_actionDef1126); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal83);

			string_literal84=(Token)match(input,75,FOLLOW_75_in_actionDef1128); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_75.add(string_literal84);

			pushFollow(FOLLOW_actionSymbol_in_actionDef1130);
			actionSymbol85=actionSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol85.getTree());
			string_literal86=(Token)match(input,92,FOLLOW_92_in_actionDef1139); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_92.add(string_literal86);

			char_literal87=(Token)match(input,69,FOLLOW_69_in_actionDef1142); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal87);

			pushFollow(FOLLOW_typedVariableList_in_actionDef1144);
			typedVariableList88=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList88.getTree());
			char_literal89=(Token)match(input,70,FOLLOW_70_in_actionDef1146); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal89);

			pushFollow(FOLLOW_actionDefBody_in_actionDef1159);
			actionDefBody90=actionDefBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody90.getTree());
			char_literal91=(Token)match(input,70,FOLLOW_70_in_actionDef1161); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal91);

			// AST REWRITE
			// elements: actionSymbol, typedVariableList, actionDefBody
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 226:8: -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:226:11: ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ACTION, "ACTION"), root_1);
				adaptor.addChild(root_1, stream_actionSymbol.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:226:33: ( typedVariableList )?
				if ( stream_typedVariableList.hasNext() ) {
					adaptor.addChild(root_1, stream_typedVariableList.nextTree());
				}
				stream_typedVariableList.reset();

				adaptor.addChild(root_1, stream_actionDefBody.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 24, actionDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "actionDef"


	public static class eventDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "eventDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:228:1: eventDef : '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) ;
	public final PddlParser.eventDef_return eventDef() throws RecognitionException{
		PddlParser.eventDef_return retval = new PddlParser.eventDef_return();
		retval.start = input.LT(1);
		int eventDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal92=null;
		Token string_literal93=null;
		Token string_literal95=null;
		Token char_literal96=null;
		Token char_literal98=null;
		Token char_literal100=null;
		ParserRuleReturnScope actionSymbol94 =null;
		ParserRuleReturnScope typedVariableList97 =null;
		ParserRuleReturnScope actionDefBody99 =null;

		Object char_literal92_tree=null;
		Object string_literal93_tree=null;
		Object string_literal95_tree=null;
		Object char_literal96_tree=null;
		Object char_literal98_tree=null;
		Object char_literal100_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
		RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:229:2: ( '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:229:4: '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
			{
			char_literal92=(Token)match(input,69,FOLLOW_69_in_eventDef1194); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal92);

			string_literal93=(Token)match(input,85,FOLLOW_85_in_eventDef1196); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_85.add(string_literal93);

			pushFollow(FOLLOW_actionSymbol_in_eventDef1198);
			actionSymbol94=actionSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol94.getTree());
			string_literal95=(Token)match(input,92,FOLLOW_92_in_eventDef1207); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_92.add(string_literal95);

			char_literal96=(Token)match(input,69,FOLLOW_69_in_eventDef1210); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal96);

			pushFollow(FOLLOW_typedVariableList_in_eventDef1212);
			typedVariableList97=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList97.getTree());
			char_literal98=(Token)match(input,70,FOLLOW_70_in_eventDef1214); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal98);

			pushFollow(FOLLOW_actionDefBody_in_eventDef1227);
			actionDefBody99=actionDefBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody99.getTree());
			char_literal100=(Token)match(input,70,FOLLOW_70_in_eventDef1229); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal100);

			// AST REWRITE
			// elements: actionDefBody, actionSymbol, typedVariableList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 232:8: -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:232:11: ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT, "EVENT"), root_1);
				adaptor.addChild(root_1, stream_actionSymbol.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:232:32: ( typedVariableList )?
				if ( stream_typedVariableList.hasNext() ) {
					adaptor.addChild(root_1, stream_typedVariableList.nextTree());
				}
				stream_typedVariableList.reset();

				adaptor.addChild(root_1, stream_actionDefBody.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 25, eventDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "eventDef"


	public static class processDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "processDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:234:1: processDef : '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) ;
	public final PddlParser.processDef_return processDef() throws RecognitionException{
		PddlParser.processDef_return retval = new PddlParser.processDef_return();
		retval.start = input.LT(1);
		int processDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal101=null;
		Token string_literal102=null;
		Token string_literal104=null;
		Token char_literal105=null;
		Token char_literal107=null;
		Token char_literal109=null;
		ParserRuleReturnScope actionSymbol103 =null;
		ParserRuleReturnScope typedVariableList106 =null;
		ParserRuleReturnScope actionDefBody108 =null;

		Object char_literal101_tree=null;
		Object string_literal102_tree=null;
		Object string_literal104_tree=null;
		Object char_literal105_tree=null;
		Object char_literal107_tree=null;
		Object char_literal109_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
		RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:235:2: ( '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:235:4: '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
			{
			char_literal101=(Token)match(input,69,FOLLOW_69_in_processDef1262); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal101);

			string_literal102=(Token)match(input,95,FOLLOW_95_in_processDef1264); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_95.add(string_literal102);

			pushFollow(FOLLOW_actionSymbol_in_processDef1266);
			actionSymbol103=actionSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol103.getTree());
			string_literal104=(Token)match(input,92,FOLLOW_92_in_processDef1275); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_92.add(string_literal104);

			char_literal105=(Token)match(input,69,FOLLOW_69_in_processDef1278); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal105);

			pushFollow(FOLLOW_typedVariableList_in_processDef1280);
			typedVariableList106=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList106.getTree());
			char_literal107=(Token)match(input,70,FOLLOW_70_in_processDef1282); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal107);

			pushFollow(FOLLOW_actionDefBody_in_processDef1295);
			actionDefBody108=actionDefBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody108.getTree());
			char_literal109=(Token)match(input,70,FOLLOW_70_in_processDef1297); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal109);

			// AST REWRITE
			// elements: typedVariableList, actionSymbol, actionDefBody
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 238:8: -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:238:11: ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROCESS, "PROCESS"), root_1);
				adaptor.addChild(root_1, stream_actionSymbol.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:238:34: ( typedVariableList )?
				if ( stream_typedVariableList.hasNext() ) {
					adaptor.addChild(root_1, stream_typedVariableList.nextTree());
				}
				stream_typedVariableList.reset();

				adaptor.addChild(root_1, stream_actionDefBody.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 26, processDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "processDef"


	public static class constraintDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constraintDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:241:1: constraintDef : '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) ;
	public final PddlParser.constraintDef_return constraintDef() throws RecognitionException{
		PddlParser.constraintDef_return retval = new PddlParser.constraintDef_return();
		retval.start = input.LT(1);
		int constraintDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal110=null;
		Token string_literal111=null;
		Token string_literal113=null;
		Token char_literal114=null;
		Token char_literal116=null;
		Token char_literal118=null;
		ParserRuleReturnScope constraintSymbol112 =null;
		ParserRuleReturnScope typedVariableList115 =null;
		ParserRuleReturnScope constraintDefBody117 =null;

		Object char_literal110_tree=null;
		Object string_literal111_tree=null;
		Object string_literal113_tree=null;
		Object char_literal114_tree=null;
		Object char_literal116_tree=null;
		Object char_literal118_tree=null;
		RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_constraintDefBody=new RewriteRuleSubtreeStream(adaptor,"rule constraintDefBody");
		RewriteRuleSubtreeStream stream_constraintSymbol=new RewriteRuleSubtreeStream(adaptor,"rule constraintSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:242:2: ( '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:242:4: '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')'
			{
			char_literal110=(Token)match(input,69,FOLLOW_69_in_constraintDef1331); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal110);

			string_literal111=(Token)match(input,78,FOLLOW_78_in_constraintDef1333); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_78.add(string_literal111);

			pushFollow(FOLLOW_constraintSymbol_in_constraintDef1335);
			constraintSymbol112=constraintSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_constraintSymbol.add(constraintSymbol112.getTree());
			string_literal113=(Token)match(input,92,FOLLOW_92_in_constraintDef1344); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_92.add(string_literal113);

			char_literal114=(Token)match(input,69,FOLLOW_69_in_constraintDef1347); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal114);

			pushFollow(FOLLOW_typedVariableList_in_constraintDef1349);
			typedVariableList115=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList115.getTree());
			char_literal116=(Token)match(input,70,FOLLOW_70_in_constraintDef1351); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal116);

			pushFollow(FOLLOW_constraintDefBody_in_constraintDef1364);
			constraintDefBody117=constraintDefBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_constraintDefBody.add(constraintDefBody117.getTree());
			char_literal118=(Token)match(input,70,FOLLOW_70_in_constraintDef1366); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal118);

			// AST REWRITE
			// elements: constraintDefBody, constraintSymbol, typedVariableList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 245:8: -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:245:11: ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(GLOBAL_CONSTRAINT, "GLOBAL_CONSTRAINT"), root_1);
				adaptor.addChild(root_1, stream_constraintSymbol.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:245:48: ( typedVariableList )?
				if ( stream_typedVariableList.hasNext() ) {
					adaptor.addChild(root_1, stream_typedVariableList.nextTree());
				}
				stream_typedVariableList.reset();

				adaptor.addChild(root_1, stream_constraintDefBody.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 27, constraintDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "constraintDef"


	public static class actionSymbol_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "actionSymbol"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:249:1: actionSymbol : NAME ;
	public final PddlParser.actionSymbol_return actionSymbol() throws RecognitionException{
		PddlParser.actionSymbol_return retval = new PddlParser.actionSymbol_return();
		retval.start = input.LT(1);
		int actionSymbol_StartIndex = input.index();

		Object root_0 = null;

		Token NAME119=null;

		Object NAME119_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:249:14: ( NAME )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:249:16: NAME
			{
			root_0 = (Object)adaptor.nil();


			NAME119=(Token)match(input,NAME,FOLLOW_NAME_in_actionSymbol1400); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME119_tree = (Object)adaptor.create(NAME119);
			adaptor.addChild(root_0, NAME119_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 28, actionSymbol_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "actionSymbol"


	public static class constraintSymbol_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constraintSymbol"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:251:1: constraintSymbol : NAME ;
	public final PddlParser.constraintSymbol_return constraintSymbol() throws RecognitionException{
		PddlParser.constraintSymbol_return retval = new PddlParser.constraintSymbol_return();
		retval.start = input.LT(1);
		int constraintSymbol_StartIndex = input.index();

		Object root_0 = null;

		Token NAME120=null;

		Object NAME120_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:251:18: ( NAME )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:251:20: NAME
			{
			root_0 = (Object)adaptor.nil();


			NAME120=(Token)match(input,NAME,FOLLOW_NAME_in_constraintSymbol1409); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME120_tree = (Object)adaptor.create(NAME120);
			adaptor.addChild(root_0, NAME120_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 29, constraintSymbol_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "constraintSymbol"


	public static class actionDefBody_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "actionDefBody"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:256:1: actionDefBody : ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) ;
	public final PddlParser.actionDefBody_return actionDefBody() throws RecognitionException{
		PddlParser.actionDefBody_return retval = new PddlParser.actionDefBody_return();
		retval.start = input.LT(1);
		int actionDefBody_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal121=null;
		Token char_literal122=null;
		Token char_literal123=null;
		Token string_literal125=null;
		Token char_literal126=null;
		Token char_literal127=null;
		ParserRuleReturnScope goalDesc124 =null;
		ParserRuleReturnScope effect128 =null;

		Object string_literal121_tree=null;
		Object char_literal122_tree=null;
		Object char_literal123_tree=null;
		Object string_literal125_tree=null;
		Object char_literal126_tree=null;
		Object char_literal127_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
		RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
		RewriteRuleSubtreeStream stream_effect=new RewriteRuleSubtreeStream(adaptor,"rule effect");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:2: ( ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )?
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )?
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==93) ) {
				alt29=1;
			}
			switch (alt29) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:6: ':precondition' ( ( '(' ')' ) | goalDesc )
					{
					string_literal121=(Token)match(input,93,FOLLOW_93_in_actionDefBody1424); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_93.add(string_literal121);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:22: ( ( '(' ')' ) | goalDesc )
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==69) ) {
						int LA28_1 = input.LA(2);
						if ( (LA28_1==70) ) {
							alt28=1;
						}
						else if ( (LA28_1==NAME||(LA28_1 >= 98 && LA28_1 <= 102)||LA28_1==109||(LA28_1 >= 119 && LA28_1 <= 120)||LA28_1==123||LA28_1==128||(LA28_1 >= 130 && LA28_1 <= 131)) ) {
							alt28=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 28, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 28, 0, input);
						throw nvae;
					}

					switch (alt28) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:23: ( '(' ')' )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:23: ( '(' ')' )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:24: '(' ')'
							{
							char_literal122=(Token)match(input,69,FOLLOW_69_in_actionDefBody1428); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_69.add(char_literal122);

							char_literal123=(Token)match(input,70,FOLLOW_70_in_actionDefBody1430); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_70.add(char_literal123);

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:257:35: goalDesc
							{
							pushFollow(FOLLOW_goalDesc_in_actionDefBody1435);
							goalDesc124=goalDesc();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc124.getTree());
							}
							break;

					}

					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:4: ( ':effect' ( ( '(' ')' ) | effect ) )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==84) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:6: ':effect' ( ( '(' ')' ) | effect )
					{
					string_literal125=(Token)match(input,84,FOLLOW_84_in_actionDefBody1445); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_84.add(string_literal125);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:16: ( ( '(' ')' ) | effect )
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==69) ) {
						int LA30_1 = input.LA(2);
						if ( (LA30_1==70) ) {
							alt30=1;
						}
						else if ( (LA30_1==NAME||(LA30_1 >= 109 && LA30_1 <= 110)||LA30_1==114||LA30_1==120||LA30_1==124||LA30_1==128||(LA30_1 >= 135 && LA30_1 <= 136)||LA30_1==143) ) {
							alt30=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 30, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 30, 0, input);
						throw nvae;
					}

					switch (alt30) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:17: ( '(' ')' )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:17: ( '(' ')' )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:18: '(' ')'
							{
							char_literal126=(Token)match(input,69,FOLLOW_69_in_actionDefBody1449); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_69.add(char_literal126);

							char_literal127=(Token)match(input,70,FOLLOW_70_in_actionDefBody1451); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_70.add(char_literal127);

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:258:29: effect
							{
							pushFollow(FOLLOW_effect_in_actionDefBody1456);
							effect128=effect();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_effect.add(effect128.getTree());
							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: effect, goalDesc
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 259:4: -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:259:7: ^( PRECONDITION ( goalDesc )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:259:22: ( goalDesc )?
				if ( stream_goalDesc.hasNext() ) {
					adaptor.addChild(root_1, stream_goalDesc.nextTree());
				}
				stream_goalDesc.reset();

				adaptor.addChild(root_0, root_1);
				}

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:259:33: ^( EFFECT ( effect )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EFFECT, "EFFECT"), root_1);
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:259:42: ( effect )?
				if ( stream_effect.hasNext() ) {
					adaptor.addChild(root_1, stream_effect.nextTree());
				}
				stream_effect.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 30, actionDefBody_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "actionDefBody"


	public static class constraintDefBody_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constraintDefBody"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:262:1: constraintDefBody : ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) ;
	public final PddlParser.constraintDefBody_return constraintDefBody() throws RecognitionException{
		PddlParser.constraintDefBody_return retval = new PddlParser.constraintDefBody_return();
		retval.start = input.LT(1);
		int constraintDefBody_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal129=null;
		Token char_literal130=null;
		Token char_literal131=null;
		ParserRuleReturnScope goalDesc132 =null;

		Object string_literal129_tree=null;
		Object char_literal130_tree=null;
		Object char_literal131_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:2: ( ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
			{
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==76) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:6: ':condition' ( ( '(' ')' ) | goalDesc )
					{
					string_literal129=(Token)match(input,76,FOLLOW_76_in_constraintDefBody1491); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_76.add(string_literal129);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:19: ( ( '(' ')' ) | goalDesc )
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==69) ) {
						int LA32_1 = input.LA(2);
						if ( (LA32_1==70) ) {
							alt32=1;
						}
						else if ( (LA32_1==NAME||(LA32_1 >= 98 && LA32_1 <= 102)||LA32_1==109||(LA32_1 >= 119 && LA32_1 <= 120)||LA32_1==123||LA32_1==128||(LA32_1 >= 130 && LA32_1 <= 131)) ) {
							alt32=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 32, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 32, 0, input);
						throw nvae;
					}

					switch (alt32) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:20: ( '(' ')' )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:20: ( '(' ')' )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:21: '(' ')'
							{
							char_literal130=(Token)match(input,69,FOLLOW_69_in_constraintDefBody1495); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_69.add(char_literal130);

							char_literal131=(Token)match(input,70,FOLLOW_70_in_constraintDefBody1497); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_70.add(char_literal131);

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:263:32: goalDesc
							{
							pushFollow(FOLLOW_goalDesc_in_constraintDefBody1502);
							goalDesc132=goalDesc();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc132.getTree());
							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: goalDesc
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 264:4: -> ^( PRECONDITION ( goalDesc )? )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:264:7: ^( PRECONDITION ( goalDesc )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PRECONDITION, "PRECONDITION"), root_1);
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:264:22: ( goalDesc )?
				if ( stream_goalDesc.hasNext() ) {
					adaptor.addChild(root_1, stream_goalDesc.nextTree());
				}
				stream_goalDesc.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 31, constraintDefBody_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "constraintDefBody"


	public static class belief_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "belief"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:279:1: belief : goalDesc ( initEl )* -> ^( BELIEF goalDesc ( initEl )* ) ;
	public final PddlParser.belief_return belief() throws RecognitionException{
		PddlParser.belief_return retval = new PddlParser.belief_return();
		retval.start = input.LT(1);
		int belief_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope goalDesc133 =null;
		ParserRuleReturnScope initEl134 =null;

		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
		RewriteRuleSubtreeStream stream_initEl=new RewriteRuleSubtreeStream(adaptor,"rule initEl");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:280:2: ( goalDesc ( initEl )* -> ^( BELIEF goalDesc ( initEl )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:280:4: goalDesc ( initEl )*
			{
			pushFollow(FOLLOW_goalDesc_in_belief1540);
			goalDesc133=goalDesc();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc133.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:281:4: ( initEl )*
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==69) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:281:4: initEl
					{
					pushFollow(FOLLOW_initEl_in_belief1545);
					initEl134=initEl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_initEl.add(initEl134.getTree());
					}
					break;

				default :
					break loop34;
				}
			}

			// AST REWRITE
			// elements: initEl, goalDesc
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 281:12: -> ^( BELIEF goalDesc ( initEl )* )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:281:15: ^( BELIEF goalDesc ( initEl )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BELIEF, "BELIEF"), root_1);
				adaptor.addChild(root_1, stream_goalDesc.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:281:33: ( initEl )*
				while ( stream_initEl.hasNext() ) {
					adaptor.addChild(root_1, stream_initEl.nextTree());
				}
				stream_initEl.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 32, belief_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "belief"


	public static class goalDesc_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "goalDesc"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:284:1: goalDesc : ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) );
	public final PddlParser.goalDesc_return goalDesc() throws RecognitionException{
		PddlParser.goalDesc_return retval = new PddlParser.goalDesc_return();
		retval.start = input.LT(1);
		int goalDesc_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal136=null;
		Token string_literal137=null;
		Token char_literal139=null;
		Token char_literal140=null;
		Token string_literal141=null;
		Token char_literal143=null;
		Token char_literal144=null;
		Token string_literal145=null;
		Token char_literal147=null;
		Token char_literal148=null;
		Token string_literal149=null;
		Token char_literal151=null;
		Token char_literal152=null;
		Token string_literal153=null;
		Token char_literal156=null;
		Token char_literal157=null;
		Token string_literal158=null;
		Token char_literal159=null;
		Token char_literal161=null;
		Token char_literal163=null;
		Token char_literal164=null;
		Token string_literal165=null;
		Token char_literal166=null;
		Token char_literal168=null;
		Token char_literal170=null;
		ParserRuleReturnScope atomicTermFormula135 =null;
		ParserRuleReturnScope goalDesc138 =null;
		ParserRuleReturnScope goalDesc142 =null;
		ParserRuleReturnScope goalDesc146 =null;
		ParserRuleReturnScope goalDesc150 =null;
		ParserRuleReturnScope goalDesc154 =null;
		ParserRuleReturnScope goalDesc155 =null;
		ParserRuleReturnScope typedVariableList160 =null;
		ParserRuleReturnScope goalDesc162 =null;
		ParserRuleReturnScope typedVariableList167 =null;
		ParserRuleReturnScope goalDesc169 =null;
		ParserRuleReturnScope fComp171 =null;
		ParserRuleReturnScope equality172 =null;

		Object char_literal136_tree=null;
		Object string_literal137_tree=null;
		Object char_literal139_tree=null;
		Object char_literal140_tree=null;
		Object string_literal141_tree=null;
		Object char_literal143_tree=null;
		Object char_literal144_tree=null;
		Object string_literal145_tree=null;
		Object char_literal147_tree=null;
		Object char_literal148_tree=null;
		Object string_literal149_tree=null;
		Object char_literal151_tree=null;
		Object char_literal152_tree=null;
		Object string_literal153_tree=null;
		Object char_literal156_tree=null;
		Object char_literal157_tree=null;
		Object string_literal158_tree=null;
		Object char_literal159_tree=null;
		Object char_literal161_tree=null;
		Object char_literal163_tree=null;
		Object char_literal164_tree=null;
		Object string_literal165_tree=null;
		Object char_literal166_tree=null;
		Object char_literal168_tree=null;
		Object char_literal170_tree=null;
		RewriteRuleTokenStream stream_123=new RewriteRuleTokenStream(adaptor,"token 123");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_128=new RewriteRuleTokenStream(adaptor,"token 128");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_119=new RewriteRuleTokenStream(adaptor,"token 119");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleTokenStream stream_130=new RewriteRuleTokenStream(adaptor,"token 130");
		RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
		RewriteRuleTokenStream stream_120=new RewriteRuleTokenStream(adaptor,"token 120");
		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_fComp=new RewriteRuleSubtreeStream(adaptor,"rule fComp");
		RewriteRuleSubtreeStream stream_equality=new RewriteRuleSubtreeStream(adaptor,"rule equality");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:285:2: ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) )
			int alt38=10;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==69) ) {
				switch ( input.LA(2) ) {
				case 109:
					{
					alt38=2;
					}
					break;
				case 131:
					{
					alt38=3;
					}
					break;
				case 128:
					{
					alt38=4;
					}
					break;
				case 130:
					{
					alt38=5;
					}
					break;
				case 123:
					{
					alt38=6;
					}
					break;
				case 119:
					{
					alt38=7;
					}
					break;
				case 120:
					{
					alt38=8;
					}
					break;
				case 100:
					{
					switch ( input.LA(3) ) {
					case NAME:
						{
						switch ( input.LA(4) ) {
						case NAME:
							{
							int LA38_14 = input.LA(5);
							if ( (LA38_14==70) ) {
								int LA38_15 = input.LA(6);
								if ( (synpred51_Pddl()) ) {
									alt38=9;
								}
								else if ( (true) ) {
									alt38=10;
								}

							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 38, 14, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case NUMBER:
						case 68:
						case 69:
							{
							alt38=9;
							}
							break;
						case VARIABLE:
							{
							alt38=10;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 38, 12, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
						}
						break;
					case NUMBER:
					case 68:
					case 69:
						{
						alt38=9;
						}
						break;
					case VARIABLE:
						{
						alt38=10;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 9, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
					}
					break;
				case NAME:
					{
					alt38=1;
					}
					break;
				case 98:
				case 99:
				case 101:
				case 102:
					{
					alt38=9;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}

			switch (alt38) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:285:4: atomicTermFormula
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_atomicTermFormula_in_goalDesc1567);
					atomicTermFormula135=atomicTermFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula135.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:286:4: '(' 'and' ( goalDesc )* ')'
					{
					char_literal136=(Token)match(input,69,FOLLOW_69_in_goalDesc1572); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal136);

					string_literal137=(Token)match(input,109,FOLLOW_109_in_goalDesc1574); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_109.add(string_literal137);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:286:14: ( goalDesc )*
					loop35:
					while (true) {
						int alt35=2;
						int LA35_0 = input.LA(1);
						if ( (LA35_0==69) ) {
							alt35=1;
						}

						switch (alt35) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:286:14: goalDesc
							{
							pushFollow(FOLLOW_goalDesc_in_goalDesc1576);
							goalDesc138=goalDesc();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc138.getTree());
							}
							break;

						default :
							break loop35;
						}
					}

					char_literal139=(Token)match(input,70,FOLLOW_70_in_goalDesc1579); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal139);

					// AST REWRITE
					// elements: goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 287:12: -> ^( AND_GD ( goalDesc )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:287:15: ^( AND_GD ( goalDesc )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(AND_GD, "AND_GD"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:287:24: ( goalDesc )*
						while ( stream_goalDesc.hasNext() ) {
							adaptor.addChild(root_1, stream_goalDesc.nextTree());
						}
						stream_goalDesc.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:288:4: '(' 'or' ( goalDesc )* ')'
					{
					char_literal140=(Token)match(input,69,FOLLOW_69_in_goalDesc1604); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal140);

					string_literal141=(Token)match(input,131,FOLLOW_131_in_goalDesc1606); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_131.add(string_literal141);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:288:13: ( goalDesc )*
					loop36:
					while (true) {
						int alt36=2;
						int LA36_0 = input.LA(1);
						if ( (LA36_0==69) ) {
							alt36=1;
						}

						switch (alt36) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:288:13: goalDesc
							{
							pushFollow(FOLLOW_goalDesc_in_goalDesc1608);
							goalDesc142=goalDesc();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc142.getTree());
							}
							break;

						default :
							break loop36;
						}
					}

					char_literal143=(Token)match(input,70,FOLLOW_70_in_goalDesc1611); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal143);

					// AST REWRITE
					// elements: goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 289:12: -> ^( OR_GD ( goalDesc )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:289:15: ^( OR_GD ( goalDesc )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OR_GD, "OR_GD"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:289:23: ( goalDesc )*
						while ( stream_goalDesc.hasNext() ) {
							adaptor.addChild(root_1, stream_goalDesc.nextTree());
						}
						stream_goalDesc.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:290:4: '(' 'not' goalDesc ')'
					{
					char_literal144=(Token)match(input,69,FOLLOW_69_in_goalDesc1636); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal144);

					string_literal145=(Token)match(input,128,FOLLOW_128_in_goalDesc1638); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_128.add(string_literal145);

					pushFollow(FOLLOW_goalDesc_in_goalDesc1640);
					goalDesc146=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc146.getTree());
					char_literal147=(Token)match(input,70,FOLLOW_70_in_goalDesc1642); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal147);

					// AST REWRITE
					// elements: goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 291:12: -> ^( NOT_GD goalDesc )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:291:15: ^( NOT_GD goalDesc )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT_GD, "NOT_GD"), root_1);
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:292:4: '(' 'oneof' ( goalDesc )* ')'
					{
					char_literal148=(Token)match(input,69,FOLLOW_69_in_goalDesc1666); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal148);

					string_literal149=(Token)match(input,130,FOLLOW_130_in_goalDesc1668); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_130.add(string_literal149);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:292:17: ( goalDesc )*
					loop37:
					while (true) {
						int alt37=2;
						int LA37_0 = input.LA(1);
						if ( (LA37_0==69) ) {
							alt37=1;
						}

						switch (alt37) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:292:17: goalDesc
							{
							pushFollow(FOLLOW_goalDesc_in_goalDesc1671);
							goalDesc150=goalDesc();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc150.getTree());
							}
							break;

						default :
							break loop37;
						}
					}

					char_literal151=(Token)match(input,70,FOLLOW_70_in_goalDesc1674); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal151);

					// AST REWRITE
					// elements: goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 292:32: -> ^( ONEOF ( goalDesc )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:292:35: ^( ONEOF ( goalDesc )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ONEOF, "ONEOF"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:292:43: ( goalDesc )*
						while ( stream_goalDesc.hasNext() ) {
							adaptor.addChild(root_1, stream_goalDesc.nextTree());
						}
						stream_goalDesc.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:293:4: '(' 'imply' goalDesc goalDesc ')'
					{
					char_literal152=(Token)match(input,69,FOLLOW_69_in_goalDesc1689); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal152);

					string_literal153=(Token)match(input,123,FOLLOW_123_in_goalDesc1691); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_123.add(string_literal153);

					pushFollow(FOLLOW_goalDesc_in_goalDesc1693);
					goalDesc154=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc154.getTree());
					pushFollow(FOLLOW_goalDesc_in_goalDesc1695);
					goalDesc155=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc155.getTree());
					char_literal156=(Token)match(input,70,FOLLOW_70_in_goalDesc1697); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal156);

					// AST REWRITE
					// elements: goalDesc, goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 294:12: -> ^( IMPLY_GD goalDesc goalDesc )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:294:15: ^( IMPLY_GD goalDesc goalDesc )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IMPLY_GD, "IMPLY_GD"), root_1);
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 7 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:295:4: '(' 'exists' '(' typedVariableList ')' goalDesc ')'
					{
					char_literal157=(Token)match(input,69,FOLLOW_69_in_goalDesc1723); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal157);

					string_literal158=(Token)match(input,119,FOLLOW_119_in_goalDesc1725); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_119.add(string_literal158);

					char_literal159=(Token)match(input,69,FOLLOW_69_in_goalDesc1727); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal159);

					pushFollow(FOLLOW_typedVariableList_in_goalDesc1729);
					typedVariableList160=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList160.getTree());
					char_literal161=(Token)match(input,70,FOLLOW_70_in_goalDesc1731); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal161);

					pushFollow(FOLLOW_goalDesc_in_goalDesc1733);
					goalDesc162=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc162.getTree());
					char_literal163=(Token)match(input,70,FOLLOW_70_in_goalDesc1735); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal163);

					// AST REWRITE
					// elements: typedVariableList, goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 296:12: -> ^( EXISTS_GD typedVariableList goalDesc )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:296:15: ^( EXISTS_GD typedVariableList goalDesc )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXISTS_GD, "EXISTS_GD"), root_1);
						adaptor.addChild(root_1, stream_typedVariableList.nextTree());
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 8 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:297:4: '(' 'forall' '(' typedVariableList ')' goalDesc ')'
					{
					char_literal164=(Token)match(input,69,FOLLOW_69_in_goalDesc1761); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal164);

					string_literal165=(Token)match(input,120,FOLLOW_120_in_goalDesc1763); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_120.add(string_literal165);

					char_literal166=(Token)match(input,69,FOLLOW_69_in_goalDesc1765); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal166);

					pushFollow(FOLLOW_typedVariableList_in_goalDesc1767);
					typedVariableList167=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList167.getTree());
					char_literal168=(Token)match(input,70,FOLLOW_70_in_goalDesc1769); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal168);

					pushFollow(FOLLOW_goalDesc_in_goalDesc1771);
					goalDesc169=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc169.getTree());
					char_literal170=(Token)match(input,70,FOLLOW_70_in_goalDesc1773); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal170);

					// AST REWRITE
					// elements: typedVariableList, goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 298:12: -> ^( FORALL_GD typedVariableList goalDesc )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:298:15: ^( FORALL_GD typedVariableList goalDesc )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FORALL_GD, "FORALL_GD"), root_1);
						adaptor.addChild(root_1, stream_typedVariableList.nextTree());
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 9 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:299:7: fComp
					{
					pushFollow(FOLLOW_fComp_in_goalDesc1802);
					fComp171=fComp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fComp.add(fComp171.getTree());
					// AST REWRITE
					// elements: fComp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 300:15: -> ^( COMPARISON_GD fComp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:300:18: ^( COMPARISON_GD fComp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COMPARISON_GD, "COMPARISON_GD"), root_1);
						adaptor.addChild(root_1, stream_fComp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 10 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:301:4: equality
					{
					pushFollow(FOLLOW_equality_in_goalDesc1829);
					equality172=equality();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_equality.add(equality172.getTree());
					// AST REWRITE
					// elements: equality
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 302:6: -> ^( EQUALITY_CON equality )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:302:9: ^( EQUALITY_CON equality )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EQUALITY_CON, "EQUALITY_CON"), root_1);
						adaptor.addChild(root_1, stream_equality.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 33, goalDesc_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "goalDesc"


	public static class equality_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "equality"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:305:1: equality : '(' ! '=' term term ')' !;
	public final PddlParser.equality_return equality() throws RecognitionException{
		PddlParser.equality_return retval = new PddlParser.equality_return();
		retval.start = input.LT(1);
		int equality_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal173=null;
		Token char_literal174=null;
		Token char_literal177=null;
		ParserRuleReturnScope term175 =null;
		ParserRuleReturnScope term176 =null;

		Object char_literal173_tree=null;
		Object char_literal174_tree=null;
		Object char_literal177_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:306:2: ( '(' ! '=' term term ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:306:4: '(' ! '=' term term ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal173=(Token)match(input,69,FOLLOW_69_in_equality1857); if (state.failed) return retval;
			char_literal174=(Token)match(input,100,FOLLOW_100_in_equality1860); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal174_tree = (Object)adaptor.create(char_literal174);
			adaptor.addChild(root_0, char_literal174_tree);
			}

			pushFollow(FOLLOW_term_in_equality1862);
			term175=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, term175.getTree());

			pushFollow(FOLLOW_term_in_equality1864);
			term176=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, term176.getTree());

			char_literal177=(Token)match(input,70,FOLLOW_70_in_equality1866); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 34, equality_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "equality"


	public static class fComp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fComp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:308:1: fComp : '(' ! binaryComp fExp fExp ')' !;
	public final PddlParser.fComp_return fComp() throws RecognitionException{
		PddlParser.fComp_return retval = new PddlParser.fComp_return();
		retval.start = input.LT(1);
		int fComp_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal178=null;
		Token char_literal182=null;
		ParserRuleReturnScope binaryComp179 =null;
		ParserRuleReturnScope fExp180 =null;
		ParserRuleReturnScope fExp181 =null;

		Object char_literal178_tree=null;
		Object char_literal182_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:309:2: ( '(' ! binaryComp fExp fExp ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:309:4: '(' ! binaryComp fExp fExp ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal178=(Token)match(input,69,FOLLOW_69_in_fComp1877); if (state.failed) return retval;
			pushFollow(FOLLOW_binaryComp_in_fComp1880);
			binaryComp179=binaryComp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, binaryComp179.getTree());

			pushFollow(FOLLOW_fExp_in_fComp1882);
			fExp180=fExp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp180.getTree());

			pushFollow(FOLLOW_fExp_in_fComp1884);
			fExp181=fExp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp181.getTree());

			char_literal182=(Token)match(input,70,FOLLOW_70_in_fComp1886); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 35, fComp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fComp"


	public static class atomicTermFormula_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomicTermFormula"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:312:1: atomicTermFormula : '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) ;
	public final PddlParser.atomicTermFormula_return atomicTermFormula() throws RecognitionException{
		PddlParser.atomicTermFormula_return retval = new PddlParser.atomicTermFormula_return();
		retval.start = input.LT(1);
		int atomicTermFormula_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal183=null;
		Token char_literal186=null;
		ParserRuleReturnScope predicate184 =null;
		ParserRuleReturnScope term185 =null;

		Object char_literal183_tree=null;
		Object char_literal186_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:2: ( '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:4: '(' predicate ( term )* ')'
			{
			char_literal183=(Token)match(input,69,FOLLOW_69_in_atomicTermFormula1898); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal183);

			pushFollow(FOLLOW_predicate_in_atomicTermFormula1900);
			predicate184=predicate();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_predicate.add(predicate184.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:18: ( term )*
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0==NAME||LA39_0==VARIABLE) ) {
					alt39=1;
				}

				switch (alt39) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:18: term
					{
					pushFollow(FOLLOW_term_in_atomicTermFormula1902);
					term185=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term185.getTree());
					}
					break;

				default :
					break loop39;
				}
			}

			char_literal186=(Token)match(input,70,FOLLOW_70_in_atomicTermFormula1905); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal186);

			// AST REWRITE
			// elements: predicate, term
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 313:28: -> ^( PRED_HEAD predicate ( term )* )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:31: ^( PRED_HEAD predicate ( term )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PRED_HEAD, "PRED_HEAD"), root_1);
				adaptor.addChild(root_1, stream_predicate.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:313:53: ( term )*
				while ( stream_term.hasNext() ) {
					adaptor.addChild(root_1, stream_term.nextTree());
				}
				stream_term.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 36, atomicTermFormula_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "atomicTermFormula"


	public static class term_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "term"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:316:1: term : ( NAME | VARIABLE );
	public final PddlParser.term_return term() throws RecognitionException{
		PddlParser.term_return retval = new PddlParser.term_return();
		retval.start = input.LT(1);
		int term_StartIndex = input.index();

		Object root_0 = null;

		Token set187=null;

		Object set187_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:316:6: ( NAME | VARIABLE )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set187=input.LT(1);
			if ( input.LA(1)==NAME||input.LA(1)==VARIABLE ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set187));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 37, term_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "term"


	public static class durativeActionDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "durativeActionDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:320:1: durativeActionDef : '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) ;
	public final PddlParser.durativeActionDef_return durativeActionDef() throws RecognitionException{
		PddlParser.durativeActionDef_return retval = new PddlParser.durativeActionDef_return();
		retval.start = input.LT(1);
		int durativeActionDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal188=null;
		Token string_literal189=null;
		Token string_literal191=null;
		Token char_literal192=null;
		Token char_literal194=null;
		Token char_literal196=null;
		ParserRuleReturnScope actionSymbol190 =null;
		ParserRuleReturnScope typedVariableList193 =null;
		ParserRuleReturnScope daDefBody195 =null;

		Object char_literal188_tree=null;
		Object string_literal189_tree=null;
		Object string_literal191_tree=null;
		Object char_literal192_tree=null;
		Object char_literal194_tree=null;
		Object char_literal196_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
		RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
		RewriteRuleSubtreeStream stream_daDefBody=new RewriteRuleSubtreeStream(adaptor,"rule daDefBody");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:321:2: ( '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:321:4: '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')'
			{
			char_literal188=(Token)match(input,69,FOLLOW_69_in_durativeActionDef1942); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal188);

			string_literal189=(Token)match(input,83,FOLLOW_83_in_durativeActionDef1944); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_83.add(string_literal189);

			pushFollow(FOLLOW_actionSymbol_in_durativeActionDef1946);
			actionSymbol190=actionSymbol();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol190.getTree());
			string_literal191=(Token)match(input,92,FOLLOW_92_in_durativeActionDef1955); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_92.add(string_literal191);

			char_literal192=(Token)match(input,69,FOLLOW_69_in_durativeActionDef1958); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal192);

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:322:27: ( typedVariableList )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==VARIABLE) ) {
				alt40=1;
			}
			else if ( (LA40_0==70) ) {
				int LA40_2 = input.LA(2);
				if ( (synpred54_Pddl()) ) {
					alt40=1;
				}
			}
			switch (alt40) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:322:28: typedVariableList
					{
					pushFollow(FOLLOW_typedVariableList_in_durativeActionDef1961);
					typedVariableList193=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList193.getTree());
					}
					break;

			}

			char_literal194=(Token)match(input,70,FOLLOW_70_in_durativeActionDef1965); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal194);

			pushFollow(FOLLOW_daDefBody_in_durativeActionDef1978);
			daDefBody195=daDefBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_daDefBody.add(daDefBody195.getTree());
			char_literal196=(Token)match(input,70,FOLLOW_70_in_durativeActionDef1980); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal196);

			// AST REWRITE
			// elements: actionSymbol, daDefBody, typedVariableList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 324:8: -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:324:11: ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DURATIVE_ACTION, "DURATIVE_ACTION"), root_1);
				adaptor.addChild(root_1, stream_actionSymbol.nextTree());
				adaptor.addChild(root_1, stream_typedVariableList.nextTree());
				adaptor.addChild(root_1, stream_daDefBody.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 38, durativeActionDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "durativeActionDef"


	public static class daDefBody_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "daDefBody"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:327:1: daDefBody : ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) );
	public final PddlParser.daDefBody_return daDefBody() throws RecognitionException{
		PddlParser.daDefBody_return retval = new PddlParser.daDefBody_return();
		retval.start = input.LT(1);
		int daDefBody_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal197=null;
		Token string_literal199=null;
		Token char_literal200=null;
		Token char_literal201=null;
		Token string_literal203=null;
		Token char_literal204=null;
		Token char_literal205=null;
		ParserRuleReturnScope durationConstraint198 =null;
		ParserRuleReturnScope daGD202 =null;
		ParserRuleReturnScope daEffect206 =null;

		Object string_literal197_tree=null;
		Object string_literal199_tree=null;
		Object char_literal200_tree=null;
		Object char_literal201_tree=null;
		Object string_literal203_tree=null;
		Object char_literal204_tree=null;
		Object char_literal205_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:328:2: ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) )
			int alt43=3;
			switch ( input.LA(1) ) {
			case 82:
				{
				alt43=1;
				}
				break;
			case 76:
				{
				alt43=2;
				}
				break;
			case 84:
				{
				alt43=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}
			switch (alt43) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:328:4: ':duration' durationConstraint
					{
					root_0 = (Object)adaptor.nil();


					string_literal197=(Token)match(input,82,FOLLOW_82_in_daDefBody2013); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal197_tree = (Object)adaptor.create(string_literal197);
					adaptor.addChild(root_0, string_literal197_tree);
					}

					pushFollow(FOLLOW_durationConstraint_in_daDefBody2015);
					durationConstraint198=durationConstraint();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, durationConstraint198.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:4: ':condition' ( ( '(' ')' ) | daGD )
					{
					root_0 = (Object)adaptor.nil();


					string_literal199=(Token)match(input,76,FOLLOW_76_in_daDefBody2020); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal199_tree = (Object)adaptor.create(string_literal199);
					adaptor.addChild(root_0, string_literal199_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:17: ( ( '(' ')' ) | daGD )
					int alt41=2;
					int LA41_0 = input.LA(1);
					if ( (LA41_0==69) ) {
						int LA41_1 = input.LA(2);
						if ( (LA41_1==70) ) {
							alt41=1;
						}
						else if ( (LA41_1==109||LA41_1==111||LA41_1==120||(LA41_1 >= 132 && LA41_1 <= 133)) ) {
							alt41=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 41, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 41, 0, input);
						throw nvae;
					}

					switch (alt41) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:18: ( '(' ')' )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:18: ( '(' ')' )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:19: '(' ')'
							{
							char_literal200=(Token)match(input,69,FOLLOW_69_in_daDefBody2024); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal200_tree = (Object)adaptor.create(char_literal200);
							adaptor.addChild(root_0, char_literal200_tree);
							}

							char_literal201=(Token)match(input,70,FOLLOW_70_in_daDefBody2026); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal201_tree = (Object)adaptor.create(char_literal201);
							adaptor.addChild(root_0, char_literal201_tree);
							}

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:329:30: daGD
							{
							pushFollow(FOLLOW_daGD_in_daDefBody2031);
							daGD202=daGD();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD202.getTree());

							}
							break;

					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:7: ':effect' ( ( '(' ')' ) | daEffect )
					{
					root_0 = (Object)adaptor.nil();


					string_literal203=(Token)match(input,84,FOLLOW_84_in_daDefBody2040); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal203_tree = (Object)adaptor.create(string_literal203);
					adaptor.addChild(root_0, string_literal203_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:17: ( ( '(' ')' ) | daEffect )
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==69) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==70) ) {
							alt42=1;
						}
						else if ( ((LA42_1 >= 109 && LA42_1 <= 111)||LA42_1==114||LA42_1==120||LA42_1==124||(LA42_1 >= 135 && LA42_1 <= 136)||LA42_1==143) ) {
							alt42=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 42, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 42, 0, input);
						throw nvae;
					}

					switch (alt42) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:18: ( '(' ')' )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:18: ( '(' ')' )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:19: '(' ')'
							{
							char_literal204=(Token)match(input,69,FOLLOW_69_in_daDefBody2044); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal204_tree = (Object)adaptor.create(char_literal204);
							adaptor.addChild(root_0, char_literal204_tree);
							}

							char_literal205=(Token)match(input,70,FOLLOW_70_in_daDefBody2046); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal205_tree = (Object)adaptor.create(char_literal205);
							adaptor.addChild(root_0, char_literal205_tree);
							}

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:330:30: daEffect
							{
							pushFollow(FOLLOW_daEffect_in_daDefBody2051);
							daEffect206=daEffect();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect206.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 39, daDefBody_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "daDefBody"


	public static class daGD_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "daGD"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:333:1: daGD : ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' );
	public final PddlParser.daGD_return daGD() throws RecognitionException{
		PddlParser.daGD_return retval = new PddlParser.daGD_return();
		retval.start = input.LT(1);
		int daGD_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal208=null;
		Token string_literal209=null;
		Token char_literal211=null;
		Token char_literal212=null;
		Token string_literal213=null;
		Token char_literal214=null;
		Token char_literal216=null;
		Token char_literal218=null;
		ParserRuleReturnScope prefTimedGD207 =null;
		ParserRuleReturnScope daGD210 =null;
		ParserRuleReturnScope typedVariableList215 =null;
		ParserRuleReturnScope daGD217 =null;

		Object char_literal208_tree=null;
		Object string_literal209_tree=null;
		Object char_literal211_tree=null;
		Object char_literal212_tree=null;
		Object string_literal213_tree=null;
		Object char_literal214_tree=null;
		Object char_literal216_tree=null;
		Object char_literal218_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:334:2: ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' )
			int alt45=3;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==69) ) {
				switch ( input.LA(2) ) {
				case 111:
				case 132:
				case 133:
					{
					alt45=1;
					}
					break;
				case 109:
					{
					alt45=2;
					}
					break;
				case 120:
					{
					alt45=3;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 45, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:334:4: prefTimedGD
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_prefTimedGD_in_daGD2066);
					prefTimedGD207=prefTimedGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, prefTimedGD207.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:335:4: '(' 'and' ( daGD )* ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal208=(Token)match(input,69,FOLLOW_69_in_daGD2071); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal208_tree = (Object)adaptor.create(char_literal208);
					adaptor.addChild(root_0, char_literal208_tree);
					}

					string_literal209=(Token)match(input,109,FOLLOW_109_in_daGD2073); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal209_tree = (Object)adaptor.create(string_literal209);
					adaptor.addChild(root_0, string_literal209_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:335:14: ( daGD )*
					loop44:
					while (true) {
						int alt44=2;
						int LA44_0 = input.LA(1);
						if ( (LA44_0==69) ) {
							alt44=1;
						}

						switch (alt44) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:335:14: daGD
							{
							pushFollow(FOLLOW_daGD_in_daGD2075);
							daGD210=daGD();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD210.getTree());

							}
							break;

						default :
							break loop44;
						}
					}

					char_literal211=(Token)match(input,70,FOLLOW_70_in_daGD2078); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal211_tree = (Object)adaptor.create(char_literal211);
					adaptor.addChild(root_0, char_literal211_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:336:4: '(' 'forall' '(' typedVariableList ')' daGD ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal212=(Token)match(input,69,FOLLOW_69_in_daGD2083); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal212_tree = (Object)adaptor.create(char_literal212);
					adaptor.addChild(root_0, char_literal212_tree);
					}

					string_literal213=(Token)match(input,120,FOLLOW_120_in_daGD2085); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal213_tree = (Object)adaptor.create(string_literal213);
					adaptor.addChild(root_0, string_literal213_tree);
					}

					char_literal214=(Token)match(input,69,FOLLOW_69_in_daGD2087); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal214_tree = (Object)adaptor.create(char_literal214);
					adaptor.addChild(root_0, char_literal214_tree);
					}

					pushFollow(FOLLOW_typedVariableList_in_daGD2089);
					typedVariableList215=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList215.getTree());

					char_literal216=(Token)match(input,70,FOLLOW_70_in_daGD2091); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal216_tree = (Object)adaptor.create(char_literal216);
					adaptor.addChild(root_0, char_literal216_tree);
					}

					pushFollow(FOLLOW_daGD_in_daGD2093);
					daGD217=daGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD217.getTree());

					char_literal218=(Token)match(input,70,FOLLOW_70_in_daGD2095); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal218_tree = (Object)adaptor.create(char_literal218);
					adaptor.addChild(root_0, char_literal218_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 40, daGD_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "daGD"


	public static class prefTimedGD_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prefTimedGD"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:339:1: prefTimedGD : ( timedGD | '(' 'preference' ( NAME )? timedGD ')' );
	public final PddlParser.prefTimedGD_return prefTimedGD() throws RecognitionException{
		PddlParser.prefTimedGD_return retval = new PddlParser.prefTimedGD_return();
		retval.start = input.LT(1);
		int prefTimedGD_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal220=null;
		Token string_literal221=null;
		Token NAME222=null;
		Token char_literal224=null;
		ParserRuleReturnScope timedGD219 =null;
		ParserRuleReturnScope timedGD223 =null;

		Object char_literal220_tree=null;
		Object string_literal221_tree=null;
		Object NAME222_tree=null;
		Object char_literal224_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:340:2: ( timedGD | '(' 'preference' ( NAME )? timedGD ')' )
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==69) ) {
				int LA47_1 = input.LA(2);
				if ( (LA47_1==111||LA47_1==132) ) {
					alt47=1;
				}
				else if ( (LA47_1==133) ) {
					alt47=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}

			switch (alt47) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:340:4: timedGD
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_timedGD_in_prefTimedGD2106);
					timedGD219=timedGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timedGD219.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:341:4: '(' 'preference' ( NAME )? timedGD ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal220=(Token)match(input,69,FOLLOW_69_in_prefTimedGD2111); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal220_tree = (Object)adaptor.create(char_literal220);
					adaptor.addChild(root_0, char_literal220_tree);
					}

					string_literal221=(Token)match(input,133,FOLLOW_133_in_prefTimedGD2113); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal221_tree = (Object)adaptor.create(string_literal221);
					adaptor.addChild(root_0, string_literal221_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:341:21: ( NAME )?
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==NAME) ) {
						alt46=1;
					}
					switch (alt46) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:341:21: NAME
							{
							NAME222=(Token)match(input,NAME,FOLLOW_NAME_in_prefTimedGD2115); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NAME222_tree = (Object)adaptor.create(NAME222);
							adaptor.addChild(root_0, NAME222_tree);
							}

							}
							break;

					}

					pushFollow(FOLLOW_timedGD_in_prefTimedGD2118);
					timedGD223=timedGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timedGD223.getTree());

					char_literal224=(Token)match(input,70,FOLLOW_70_in_prefTimedGD2120); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal224_tree = (Object)adaptor.create(char_literal224);
					adaptor.addChild(root_0, char_literal224_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 41, prefTimedGD_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "prefTimedGD"


	public static class timedGD_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "timedGD"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:344:1: timedGD : ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' );
	public final PddlParser.timedGD_return timedGD() throws RecognitionException{
		PddlParser.timedGD_return retval = new PddlParser.timedGD_return();
		retval.start = input.LT(1);
		int timedGD_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal225=null;
		Token string_literal226=null;
		Token char_literal229=null;
		Token char_literal230=null;
		Token string_literal231=null;
		Token char_literal234=null;
		ParserRuleReturnScope timeSpecifier227 =null;
		ParserRuleReturnScope goalDesc228 =null;
		ParserRuleReturnScope interval232 =null;
		ParserRuleReturnScope goalDesc233 =null;

		Object char_literal225_tree=null;
		Object string_literal226_tree=null;
		Object char_literal229_tree=null;
		Object char_literal230_tree=null;
		Object string_literal231_tree=null;
		Object char_literal234_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:345:2: ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' )
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0==69) ) {
				int LA48_1 = input.LA(2);
				if ( (LA48_1==111) ) {
					alt48=1;
				}
				else if ( (LA48_1==132) ) {
					alt48=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 48, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}

			switch (alt48) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:345:4: '(' 'at' timeSpecifier goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal225=(Token)match(input,69,FOLLOW_69_in_timedGD2131); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal225_tree = (Object)adaptor.create(char_literal225);
					adaptor.addChild(root_0, char_literal225_tree);
					}

					string_literal226=(Token)match(input,111,FOLLOW_111_in_timedGD2133); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal226_tree = (Object)adaptor.create(string_literal226);
					adaptor.addChild(root_0, string_literal226_tree);
					}

					pushFollow(FOLLOW_timeSpecifier_in_timedGD2135);
					timeSpecifier227=timeSpecifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier227.getTree());

					pushFollow(FOLLOW_goalDesc_in_timedGD2137);
					goalDesc228=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc228.getTree());

					char_literal229=(Token)match(input,70,FOLLOW_70_in_timedGD2139); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal229_tree = (Object)adaptor.create(char_literal229);
					adaptor.addChild(root_0, char_literal229_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:346:4: '(' 'over' interval goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal230=(Token)match(input,69,FOLLOW_69_in_timedGD2144); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal230_tree = (Object)adaptor.create(char_literal230);
					adaptor.addChild(root_0, char_literal230_tree);
					}

					string_literal231=(Token)match(input,132,FOLLOW_132_in_timedGD2146); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal231_tree = (Object)adaptor.create(string_literal231);
					adaptor.addChild(root_0, string_literal231_tree);
					}

					pushFollow(FOLLOW_interval_in_timedGD2148);
					interval232=interval();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, interval232.getTree());

					pushFollow(FOLLOW_goalDesc_in_timedGD2150);
					goalDesc233=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc233.getTree());

					char_literal234=(Token)match(input,70,FOLLOW_70_in_timedGD2152); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal234_tree = (Object)adaptor.create(char_literal234);
					adaptor.addChild(root_0, char_literal234_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 42, timedGD_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "timedGD"


	public static class timeSpecifier_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "timeSpecifier"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:349:1: timeSpecifier : ( 'start' | 'end' );
	public final PddlParser.timeSpecifier_return timeSpecifier() throws RecognitionException{
		PddlParser.timeSpecifier_return retval = new PddlParser.timeSpecifier_return();
		retval.start = input.LT(1);
		int timeSpecifier_StartIndex = input.index();

		Object root_0 = null;

		Token set235=null;

		Object set235_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:349:15: ( 'start' | 'end' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set235=input.LT(1);
			if ( input.LA(1)==118||input.LA(1)==141 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set235));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 43, timeSpecifier_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "timeSpecifier"


	public static class interval_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "interval"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:350:1: interval : 'all' ;
	public final PddlParser.interval_return interval() throws RecognitionException{
		PddlParser.interval_return retval = new PddlParser.interval_return();
		retval.start = input.LT(1);
		int interval_StartIndex = input.index();

		Object root_0 = null;

		Token string_literal236=null;

		Object string_literal236_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:350:10: ( 'all' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:350:12: 'all'
			{
			root_0 = (Object)adaptor.nil();


			string_literal236=(Token)match(input,106,FOLLOW_106_in_interval2174); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal236_tree = (Object)adaptor.create(string_literal236);
			adaptor.addChild(root_0, string_literal236_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 44, interval_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "interval"


	public static class derivedDef_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "derivedDef"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:354:1: derivedDef : '(' ! ':derived' ^ typedVariableList goalDesc ')' !;
	public final PddlParser.derivedDef_return derivedDef() throws RecognitionException{
		PddlParser.derivedDef_return retval = new PddlParser.derivedDef_return();
		retval.start = input.LT(1);
		int derivedDef_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal237=null;
		Token string_literal238=null;
		Token char_literal241=null;
		ParserRuleReturnScope typedVariableList239 =null;
		ParserRuleReturnScope goalDesc240 =null;

		Object char_literal237_tree=null;
		Object string_literal238_tree=null;
		Object char_literal241_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:355:2: ( '(' ! ':derived' ^ typedVariableList goalDesc ')' !)
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:355:4: '(' ! ':derived' ^ typedVariableList goalDesc ')' !
			{
			root_0 = (Object)adaptor.nil();


			char_literal237=(Token)match(input,69,FOLLOW_69_in_derivedDef2187); if (state.failed) return retval;
			string_literal238=(Token)match(input,80,FOLLOW_80_in_derivedDef2190); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			string_literal238_tree = (Object)adaptor.create(string_literal238);
			root_0 = (Object)adaptor.becomeRoot(string_literal238_tree, root_0);
			}

			pushFollow(FOLLOW_typedVariableList_in_derivedDef2193);
			typedVariableList239=typedVariableList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList239.getTree());

			pushFollow(FOLLOW_goalDesc_in_derivedDef2195);
			goalDesc240=goalDesc();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc240.getTree());

			char_literal241=(Token)match(input,70,FOLLOW_70_in_derivedDef2197); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 45, derivedDef_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "derivedDef"


	public static class fExp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fExp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:360:1: fExp : ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead );
	public final PddlParser.fExp_return fExp() throws RecognitionException{
		PddlParser.fExp_return retval = new PddlParser.fExp_return();
		retval.start = input.LT(1);
		int fExp_StartIndex = input.index();

		Object root_0 = null;

		Token NUMBER242=null;
		Token char_literal243=null;
		Token char_literal247=null;
		Token char_literal248=null;
		Token char_literal249=null;
		Token char_literal251=null;
		Token char_literal252=null;
		Token string_literal253=null;
		Token char_literal255=null;
		Token char_literal256=null;
		Token string_literal257=null;
		Token char_literal259=null;
		Token char_literal260=null;
		Token string_literal261=null;
		Token char_literal263=null;
		ParserRuleReturnScope binaryOp244 =null;
		ParserRuleReturnScope fExp245 =null;
		ParserRuleReturnScope fExp2246 =null;
		ParserRuleReturnScope fExp250 =null;
		ParserRuleReturnScope fExp254 =null;
		ParserRuleReturnScope fExp258 =null;
		ParserRuleReturnScope fExp262 =null;
		ParserRuleReturnScope fHead264 =null;

		Object NUMBER242_tree=null;
		Object char_literal243_tree=null;
		Object char_literal247_tree=null;
		Object char_literal248_tree=null;
		Object char_literal249_tree=null;
		Object char_literal251_tree=null;
		Object char_literal252_tree=null;
		Object string_literal253_tree=null;
		Object char_literal255_tree=null;
		Object char_literal256_tree=null;
		Object string_literal257_tree=null;
		Object char_literal259_tree=null;
		Object char_literal260_tree=null;
		Object string_literal261_tree=null;
		Object char_literal263_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_113=new RewriteRuleTokenStream(adaptor,"token 113");
		RewriteRuleTokenStream stream_137=new RewriteRuleTokenStream(adaptor,"token 137");
		RewriteRuleTokenStream stream_105=new RewriteRuleTokenStream(adaptor,"token 105");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleSubtreeStream stream_binaryOp=new RewriteRuleSubtreeStream(adaptor,"rule binaryOp");
		RewriteRuleSubtreeStream stream_fExp2=new RewriteRuleSubtreeStream(adaptor,"rule fExp2");
		RewriteRuleSubtreeStream stream_fExp=new RewriteRuleSubtreeStream(adaptor,"rule fExp");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:361:2: ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead )
			int alt49=7;
			switch ( input.LA(1) ) {
			case NUMBER:
				{
				alt49=1;
				}
				break;
			case 69:
				{
				int LA49_2 = input.LA(2);
				if ( (synpred67_Pddl()) ) {
					alt49=2;
				}
				else if ( (synpred68_Pddl()) ) {
					alt49=3;
				}
				else if ( (synpred69_Pddl()) ) {
					alt49=4;
				}
				else if ( (synpred70_Pddl()) ) {
					alt49=5;
				}
				else if ( (synpred71_Pddl()) ) {
					alt49=6;
				}
				else if ( (true) ) {
					alt49=7;
				}

				}
				break;
			case NAME:
			case 68:
				{
				alt49=7;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 49, 0, input);
				throw nvae;
			}
			switch (alt49) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:361:4: NUMBER
					{
					root_0 = (Object)adaptor.nil();


					NUMBER242=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_fExp2212); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER242_tree = (Object)adaptor.create(NUMBER242);
					adaptor.addChild(root_0, NUMBER242_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:362:4: '(' binaryOp fExp fExp2 ')'
					{
					char_literal243=(Token)match(input,69,FOLLOW_69_in_fExp2217); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal243);

					pushFollow(FOLLOW_binaryOp_in_fExp2219);
					binaryOp244=binaryOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binaryOp.add(binaryOp244.getTree());
					pushFollow(FOLLOW_fExp_in_fExp2221);
					fExp245=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp245.getTree());
					pushFollow(FOLLOW_fExp2_in_fExp2223);
					fExp2246=fExp2();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp2.add(fExp2246.getTree());
					char_literal247=(Token)match(input,70,FOLLOW_70_in_fExp2225); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal247);

					// AST REWRITE
					// elements: binaryOp, fExp, fExp2
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 362:32: -> ^( BINARY_OP binaryOp fExp fExp2 )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:362:35: ^( BINARY_OP binaryOp fExp fExp2 )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BINARY_OP, "BINARY_OP"), root_1);
						adaptor.addChild(root_1, stream_binaryOp.nextTree());
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_1, stream_fExp2.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:363:4: '(' '-' fExp ')'
					{
					char_literal248=(Token)match(input,69,FOLLOW_69_in_fExp2242); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal248);

					char_literal249=(Token)match(input,73,FOLLOW_73_in_fExp2244); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_73.add(char_literal249);

					pushFollow(FOLLOW_fExp_in_fExp2246);
					fExp250=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp250.getTree());
					char_literal251=(Token)match(input,70,FOLLOW_70_in_fExp2248); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal251);

					// AST REWRITE
					// elements: fExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 363:21: -> ^( UNARY_MINUS fExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:363:24: ^( UNARY_MINUS fExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNARY_MINUS, "UNARY_MINUS"), root_1);
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:364:4: '(' 'sin' fExp ')'
					{
					char_literal252=(Token)match(input,69,FOLLOW_69_in_fExp2261); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal252);

					string_literal253=(Token)match(input,137,FOLLOW_137_in_fExp2263); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_137.add(string_literal253);

					pushFollow(FOLLOW_fExp_in_fExp2265);
					fExp254=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp254.getTree());
					char_literal255=(Token)match(input,70,FOLLOW_70_in_fExp2267); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal255);

					// AST REWRITE
					// elements: fExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 364:23: -> ^( SIN fExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:364:26: ^( SIN fExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SIN, "SIN"), root_1);
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:365:4: '(' 'cos' fExp ')'
					{
					char_literal256=(Token)match(input,69,FOLLOW_69_in_fExp2280); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal256);

					string_literal257=(Token)match(input,113,FOLLOW_113_in_fExp2282); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_113.add(string_literal257);

					pushFollow(FOLLOW_fExp_in_fExp2284);
					fExp258=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp258.getTree());
					char_literal259=(Token)match(input,70,FOLLOW_70_in_fExp2286); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal259);

					// AST REWRITE
					// elements: fExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 365:23: -> ^( COS fExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:365:26: ^( COS fExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(COS, "COS"), root_1);
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:366:4: '(' 'abs' fExp ')'
					{
					char_literal260=(Token)match(input,69,FOLLOW_69_in_fExp2299); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal260);

					string_literal261=(Token)match(input,105,FOLLOW_105_in_fExp2301); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_105.add(string_literal261);

					pushFollow(FOLLOW_fExp_in_fExp2303);
					fExp262=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp262.getTree());
					char_literal263=(Token)match(input,70,FOLLOW_70_in_fExp2305); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal263);

					// AST REWRITE
					// elements: fExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 366:23: -> ^( ABS fExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:366:26: ^( ABS fExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ABS, "ABS"), root_1);
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 7 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:367:4: fHead
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_fHead_in_fExp2318);
					fHead264=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead264.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 46, fExp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fExp"


	public static class fExp2_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fExp2"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:372:1: fExp2 : fExp ;
	public final PddlParser.fExp2_return fExp2() throws RecognitionException{
		PddlParser.fExp2_return retval = new PddlParser.fExp2_return();
		retval.start = input.LT(1);
		int fExp2_StartIndex = input.index();

		Object root_0 = null;

		ParserRuleReturnScope fExp265 =null;


		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:372:7: ( fExp )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:372:9: fExp
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_fExp_in_fExp22330);
			fExp265=fExp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp265.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 47, fExp2_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fExp2"


	public static class fHead_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fHead"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:374:1: fHead : ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) );
	public final PddlParser.fHead_return fHead() throws RecognitionException{
		PddlParser.fHead_return retval = new PddlParser.fHead_return();
		retval.start = input.LT(1);
		int fHead_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal266=null;
		Token char_literal269=null;
		ParserRuleReturnScope functionSymbol267 =null;
		ParserRuleReturnScope term268 =null;
		ParserRuleReturnScope functionSymbol270 =null;

		Object char_literal266_tree=null;
		Object char_literal269_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
		RewriteRuleSubtreeStream stream_functionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule functionSymbol");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:2: ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) )
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==69) ) {
				alt51=1;
			}
			else if ( (LA51_0==NAME||LA51_0==68) ) {
				alt51=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 51, 0, input);
				throw nvae;
			}

			switch (alt51) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:4: '(' functionSymbol ( term )* ')'
					{
					char_literal266=(Token)match(input,69,FOLLOW_69_in_fHead2340); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal266);

					pushFollow(FOLLOW_functionSymbol_in_fHead2342);
					functionSymbol267=functionSymbol();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_functionSymbol.add(functionSymbol267.getTree());
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:23: ( term )*
					loop50:
					while (true) {
						int alt50=2;
						int LA50_0 = input.LA(1);
						if ( (LA50_0==NAME||LA50_0==VARIABLE) ) {
							alt50=1;
						}

						switch (alt50) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:23: term
							{
							pushFollow(FOLLOW_term_in_fHead2344);
							term268=term();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_term.add(term268.getTree());
							}
							break;

						default :
							break loop50;
						}
					}

					char_literal269=(Token)match(input,70,FOLLOW_70_in_fHead2347); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal269);

					// AST REWRITE
					// elements: term, functionSymbol
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 375:33: -> ^( FUNC_HEAD functionSymbol ( term )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:36: ^( FUNC_HEAD functionSymbol ( term )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD"), root_1);
						adaptor.addChild(root_1, stream_functionSymbol.nextTree());
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:375:63: ( term )*
						while ( stream_term.hasNext() ) {
							adaptor.addChild(root_1, stream_term.nextTree());
						}
						stream_term.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:376:4: functionSymbol
					{
					pushFollow(FOLLOW_functionSymbol_in_fHead2363);
					functionSymbol270=functionSymbol();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_functionSymbol.add(functionSymbol270.getTree());
					// AST REWRITE
					// elements: functionSymbol
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 376:19: -> ^( FUNC_HEAD functionSymbol )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:376:22: ^( FUNC_HEAD functionSymbol )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD"), root_1);
						adaptor.addChild(root_1, stream_functionSymbol.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 48, fHead_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fHead"


	public static class effect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "effect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:379:1: effect : ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect );
	public final PddlParser.effect_return effect() throws RecognitionException{
		PddlParser.effect_return retval = new PddlParser.effect_return();
		retval.start = input.LT(1);
		int effect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal271=null;
		Token string_literal272=null;
		Token char_literal274=null;
		ParserRuleReturnScope cEffect273 =null;
		ParserRuleReturnScope cEffect275 =null;

		Object char_literal271_tree=null;
		Object string_literal272_tree=null;
		Object char_literal274_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleSubtreeStream stream_cEffect=new RewriteRuleSubtreeStream(adaptor,"rule cEffect");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:2: ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect )
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==69) ) {
				int LA53_1 = input.LA(2);
				if ( (LA53_1==109) ) {
					alt53=1;
				}
				else if ( (LA53_1==NAME||LA53_1==110||LA53_1==114||LA53_1==120||LA53_1==124||LA53_1==128||(LA53_1 >= 135 && LA53_1 <= 136)||LA53_1==143) ) {
					alt53=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 53, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}

			switch (alt53) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:4: '(' 'and' ( cEffect )* ')'
					{
					char_literal271=(Token)match(input,69,FOLLOW_69_in_effect2382); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal271);

					string_literal272=(Token)match(input,109,FOLLOW_109_in_effect2384); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_109.add(string_literal272);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:14: ( cEffect )*
					loop52:
					while (true) {
						int alt52=2;
						int LA52_0 = input.LA(1);
						if ( (LA52_0==69) ) {
							alt52=1;
						}

						switch (alt52) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:14: cEffect
							{
							pushFollow(FOLLOW_cEffect_in_effect2386);
							cEffect273=cEffect();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_cEffect.add(cEffect273.getTree());
							}
							break;

						default :
							break loop52;
						}
					}

					char_literal274=(Token)match(input,70,FOLLOW_70_in_effect2389); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal274);

					// AST REWRITE
					// elements: cEffect
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 380:27: -> ^( AND_EFFECT ( cEffect )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:30: ^( AND_EFFECT ( cEffect )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:380:43: ( cEffect )*
						while ( stream_cEffect.hasNext() ) {
							adaptor.addChild(root_1, stream_cEffect.nextTree());
						}
						stream_cEffect.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:381:4: cEffect
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_cEffect_in_effect2403);
					cEffect275=cEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, cEffect275.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 49, effect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "effect"


	public static class cEffect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cEffect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:384:1: cEffect : ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect );
	public final PddlParser.cEffect_return cEffect() throws RecognitionException{
		PddlParser.cEffect_return retval = new PddlParser.cEffect_return();
		retval.start = input.LT(1);
		int cEffect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal276=null;
		Token string_literal277=null;
		Token char_literal278=null;
		Token char_literal280=null;
		Token char_literal282=null;
		Token char_literal283=null;
		Token string_literal284=null;
		Token char_literal287=null;
		ParserRuleReturnScope typedVariableList279 =null;
		ParserRuleReturnScope effect281 =null;
		ParserRuleReturnScope goalDesc285 =null;
		ParserRuleReturnScope condEffect286 =null;
		ParserRuleReturnScope pEffect288 =null;

		Object char_literal276_tree=null;
		Object string_literal277_tree=null;
		Object char_literal278_tree=null;
		Object char_literal280_tree=null;
		Object char_literal282_tree=null;
		Object char_literal283_tree=null;
		Object string_literal284_tree=null;
		Object char_literal287_tree=null;
		RewriteRuleTokenStream stream_143=new RewriteRuleTokenStream(adaptor,"token 143");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_120=new RewriteRuleTokenStream(adaptor,"token 120");
		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
		RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
		RewriteRuleSubtreeStream stream_effect=new RewriteRuleSubtreeStream(adaptor,"rule effect");
		RewriteRuleSubtreeStream stream_condEffect=new RewriteRuleSubtreeStream(adaptor,"rule condEffect");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:385:2: ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect )
			int alt54=3;
			int LA54_0 = input.LA(1);
			if ( (LA54_0==69) ) {
				switch ( input.LA(2) ) {
				case 120:
					{
					alt54=1;
					}
					break;
				case 143:
					{
					alt54=2;
					}
					break;
				case NAME:
				case 110:
				case 114:
				case 124:
				case 128:
				case 135:
				case 136:
					{
					alt54=3;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 54, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 54, 0, input);
				throw nvae;
			}

			switch (alt54) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:385:4: '(' 'forall' '(' typedVariableList ')' effect ')'
					{
					char_literal276=(Token)match(input,69,FOLLOW_69_in_cEffect2414); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal276);

					string_literal277=(Token)match(input,120,FOLLOW_120_in_cEffect2416); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_120.add(string_literal277);

					char_literal278=(Token)match(input,69,FOLLOW_69_in_cEffect2418); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal278);

					pushFollow(FOLLOW_typedVariableList_in_cEffect2420);
					typedVariableList279=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList279.getTree());
					char_literal280=(Token)match(input,70,FOLLOW_70_in_cEffect2422); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal280);

					pushFollow(FOLLOW_effect_in_cEffect2424);
					effect281=effect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_effect.add(effect281.getTree());
					char_literal282=(Token)match(input,70,FOLLOW_70_in_cEffect2426); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal282);

					// AST REWRITE
					// elements: effect, typedVariableList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 386:4: -> ^( FORALL_EFFECT typedVariableList effect )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:386:7: ^( FORALL_EFFECT typedVariableList effect )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FORALL_EFFECT, "FORALL_EFFECT"), root_1);
						adaptor.addChild(root_1, stream_typedVariableList.nextTree());
						adaptor.addChild(root_1, stream_effect.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:387:4: '(' 'when' goalDesc condEffect ')'
					{
					char_literal283=(Token)match(input,69,FOLLOW_69_in_cEffect2444); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal283);

					string_literal284=(Token)match(input,143,FOLLOW_143_in_cEffect2446); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_143.add(string_literal284);

					pushFollow(FOLLOW_goalDesc_in_cEffect2448);
					goalDesc285=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc285.getTree());
					pushFollow(FOLLOW_condEffect_in_cEffect2450);
					condEffect286=condEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_condEffect.add(condEffect286.getTree());
					char_literal287=(Token)match(input,70,FOLLOW_70_in_cEffect2452); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal287);

					// AST REWRITE
					// elements: condEffect, goalDesc
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 388:4: -> ^( WHEN_EFFECT goalDesc condEffect )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:388:7: ^( WHEN_EFFECT goalDesc condEffect )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(WHEN_EFFECT, "WHEN_EFFECT"), root_1);
						adaptor.addChild(root_1, stream_goalDesc.nextTree());
						adaptor.addChild(root_1, stream_condEffect.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:389:4: pEffect
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_pEffect_in_cEffect2470);
					pEffect288=pEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect288.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 50, cEffect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "cEffect"


	public static class pEffect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "pEffect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:392:1: pEffect : ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula );
	public final PddlParser.pEffect_return pEffect() throws RecognitionException{
		PddlParser.pEffect_return retval = new PddlParser.pEffect_return();
		retval.start = input.LT(1);
		int pEffect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal289=null;
		Token char_literal293=null;
		Token char_literal294=null;
		Token string_literal295=null;
		Token char_literal297=null;
		ParserRuleReturnScope assignOp290 =null;
		ParserRuleReturnScope fHead291 =null;
		ParserRuleReturnScope fExp292 =null;
		ParserRuleReturnScope atomicTermFormula296 =null;
		ParserRuleReturnScope atomicTermFormula298 =null;

		Object char_literal289_tree=null;
		Object char_literal293_tree=null;
		Object char_literal294_tree=null;
		Object string_literal295_tree=null;
		Object char_literal297_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_128=new RewriteRuleTokenStream(adaptor,"token 128");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_atomicTermFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicTermFormula");
		RewriteRuleSubtreeStream stream_assignOp=new RewriteRuleSubtreeStream(adaptor,"rule assignOp");
		RewriteRuleSubtreeStream stream_fExp=new RewriteRuleSubtreeStream(adaptor,"rule fExp");
		RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:393:2: ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula )
			int alt55=3;
			int LA55_0 = input.LA(1);
			if ( (LA55_0==69) ) {
				switch ( input.LA(2) ) {
				case 128:
					{
					alt55=2;
					}
					break;
				case 110:
				case 114:
				case 124:
				case 135:
				case 136:
					{
					alt55=1;
					}
					break;
				case NAME:
					{
					alt55=3;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 55, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 55, 0, input);
				throw nvae;
			}

			switch (alt55) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:393:4: '(' assignOp fHead fExp ')'
					{
					char_literal289=(Token)match(input,69,FOLLOW_69_in_pEffect2481); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal289);

					pushFollow(FOLLOW_assignOp_in_pEffect2483);
					assignOp290=assignOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_assignOp.add(assignOp290.getTree());
					pushFollow(FOLLOW_fHead_in_pEffect2485);
					fHead291=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fHead.add(fHead291.getTree());
					pushFollow(FOLLOW_fExp_in_pEffect2487);
					fExp292=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fExp.add(fExp292.getTree());
					char_literal293=(Token)match(input,70,FOLLOW_70_in_pEffect2489); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal293);

					// AST REWRITE
					// elements: fExp, fHead, assignOp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 394:4: -> ^( ASSIGN_EFFECT assignOp fHead fExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:394:7: ^( ASSIGN_EFFECT assignOp fHead fExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ASSIGN_EFFECT, "ASSIGN_EFFECT"), root_1);
						adaptor.addChild(root_1, stream_assignOp.nextTree());
						adaptor.addChild(root_1, stream_fHead.nextTree());
						adaptor.addChild(root_1, stream_fExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:395:4: '(' 'not' atomicTermFormula ')'
					{
					char_literal294=(Token)match(input,69,FOLLOW_69_in_pEffect2509); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal294);

					string_literal295=(Token)match(input,128,FOLLOW_128_in_pEffect2511); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_128.add(string_literal295);

					pushFollow(FOLLOW_atomicTermFormula_in_pEffect2513);
					atomicTermFormula296=atomicTermFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_atomicTermFormula.add(atomicTermFormula296.getTree());
					char_literal297=(Token)match(input,70,FOLLOW_70_in_pEffect2515); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal297);

					// AST REWRITE
					// elements: atomicTermFormula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 396:4: -> ^( NOT_EFFECT atomicTermFormula )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:396:7: ^( NOT_EFFECT atomicTermFormula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT_EFFECT, "NOT_EFFECT"), root_1);
						adaptor.addChild(root_1, stream_atomicTermFormula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:397:4: atomicTermFormula
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_atomicTermFormula_in_pEffect2531);
					atomicTermFormula298=atomicTermFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula298.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 51, pEffect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "pEffect"


	public static class condEffect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "condEffect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:402:1: condEffect : ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect );
	public final PddlParser.condEffect_return condEffect() throws RecognitionException{
		PddlParser.condEffect_return retval = new PddlParser.condEffect_return();
		retval.start = input.LT(1);
		int condEffect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal299=null;
		Token string_literal300=null;
		Token char_literal302=null;
		ParserRuleReturnScope pEffect301 =null;
		ParserRuleReturnScope pEffect303 =null;

		Object char_literal299_tree=null;
		Object string_literal300_tree=null;
		Object char_literal302_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_109=new RewriteRuleTokenStream(adaptor,"token 109");
		RewriteRuleSubtreeStream stream_pEffect=new RewriteRuleSubtreeStream(adaptor,"rule pEffect");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:2: ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | pEffect )
			int alt57=2;
			int LA57_0 = input.LA(1);
			if ( (LA57_0==69) ) {
				int LA57_1 = input.LA(2);
				if ( (LA57_1==109) ) {
					alt57=1;
				}
				else if ( (LA57_1==NAME||LA57_1==110||LA57_1==114||LA57_1==124||LA57_1==128||(LA57_1 >= 135 && LA57_1 <= 136)) ) {
					alt57=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 57, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 57, 0, input);
				throw nvae;
			}

			switch (alt57) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:4: '(' 'and' ( pEffect )* ')'
					{
					char_literal299=(Token)match(input,69,FOLLOW_69_in_condEffect2544); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal299);

					string_literal300=(Token)match(input,109,FOLLOW_109_in_condEffect2546); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_109.add(string_literal300);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:14: ( pEffect )*
					loop56:
					while (true) {
						int alt56=2;
						int LA56_0 = input.LA(1);
						if ( (LA56_0==69) ) {
							alt56=1;
						}

						switch (alt56) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:14: pEffect
							{
							pushFollow(FOLLOW_pEffect_in_condEffect2548);
							pEffect301=pEffect();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_pEffect.add(pEffect301.getTree());
							}
							break;

						default :
							break loop56;
						}
					}

					char_literal302=(Token)match(input,70,FOLLOW_70_in_condEffect2551); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal302);

					// AST REWRITE
					// elements: pEffect
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 403:27: -> ^( AND_EFFECT ( pEffect )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:30: ^( AND_EFFECT ( pEffect )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(AND_EFFECT, "AND_EFFECT"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:403:43: ( pEffect )*
						while ( stream_pEffect.hasNext() ) {
							adaptor.addChild(root_1, stream_pEffect.nextTree());
						}
						stream_pEffect.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:404:4: pEffect
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_pEffect_in_condEffect2565);
					pEffect303=pEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect303.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 52, condEffect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "condEffect"


	public static class binaryOp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binaryOp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:409:1: binaryOp : ( '*' | '+' | '-' | '/' | '^' );
	public final PddlParser.binaryOp_return binaryOp() throws RecognitionException{
		PddlParser.binaryOp_return retval = new PddlParser.binaryOp_return();
		retval.start = input.LT(1);
		int binaryOp_StartIndex = input.index();

		Object root_0 = null;

		Token set304=null;

		Object set304_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:409:10: ( '*' | '+' | '-' | '/' | '^' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set304=input.LT(1);
			if ( (input.LA(1) >= 71 && input.LA(1) <= 74)||input.LA(1)==104 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set304));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 53, binaryOp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "binaryOp"


	public static class multiOp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multiOp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:411:1: multiOp : ( '*' | '+' );
	public final PddlParser.multiOp_return multiOp() throws RecognitionException{
		PddlParser.multiOp_return retval = new PddlParser.multiOp_return();
		retval.start = input.LT(1);
		int multiOp_StartIndex = input.index();

		Object root_0 = null;

		Token set305=null;

		Object set305_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:411:9: ( '*' | '+' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set305=input.LT(1);
			if ( (input.LA(1) >= 71 && input.LA(1) <= 72) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set305));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 54, multiOp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "multiOp"


	public static class binaryComp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binaryComp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:413:1: binaryComp : ( '>' | '<' | '=' | '>=' | '<=' );
	public final PddlParser.binaryComp_return binaryComp() throws RecognitionException{
		PddlParser.binaryComp_return retval = new PddlParser.binaryComp_return();
		retval.start = input.LT(1);
		int binaryComp_StartIndex = input.index();

		Object root_0 = null;

		Token set306=null;

		Object set306_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:413:12: ( '>' | '<' | '=' | '>=' | '<=' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set306=input.LT(1);
			if ( (input.LA(1) >= 98 && input.LA(1) <= 102) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set306));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 55, binaryComp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "binaryComp"


	public static class assignOp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignOp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:415:1: assignOp : ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' );
	public final PddlParser.assignOp_return assignOp() throws RecognitionException{
		PddlParser.assignOp_return retval = new PddlParser.assignOp_return();
		retval.start = input.LT(1);
		int assignOp_StartIndex = input.index();

		Object root_0 = null;

		Token set307=null;

		Object set307_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:415:10: ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set307=input.LT(1);
			if ( input.LA(1)==110||input.LA(1)==114||input.LA(1)==124||(input.LA(1) >= 135 && input.LA(1) <= 136) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set307));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 56, assignOp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "assignOp"


	public static class durationConstraint_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "durationConstraint"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:420:1: durationConstraint : ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint );
	public final PddlParser.durationConstraint_return durationConstraint() throws RecognitionException{
		PddlParser.durationConstraint_return retval = new PddlParser.durationConstraint_return();
		retval.start = input.LT(1);
		int durationConstraint_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal308=null;
		Token string_literal309=null;
		Token char_literal311=null;
		Token char_literal312=null;
		Token char_literal313=null;
		ParserRuleReturnScope simpleDurationConstraint310 =null;
		ParserRuleReturnScope simpleDurationConstraint314 =null;

		Object char_literal308_tree=null;
		Object string_literal309_tree=null;
		Object char_literal311_tree=null;
		Object char_literal312_tree=null;
		Object char_literal313_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:421:2: ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint )
			int alt59=3;
			int LA59_0 = input.LA(1);
			if ( (LA59_0==69) ) {
				switch ( input.LA(2) ) {
				case 109:
					{
					alt59=1;
					}
					break;
				case 70:
					{
					alt59=2;
					}
					break;
				case 99:
				case 100:
				case 102:
				case 111:
					{
					alt59=3;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 59, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 59, 0, input);
				throw nvae;
			}

			switch (alt59) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:421:4: '(' 'and' ( simpleDurationConstraint )+ ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal308=(Token)match(input,69,FOLLOW_69_in_durationConstraint2670); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal308_tree = (Object)adaptor.create(char_literal308);
					adaptor.addChild(root_0, char_literal308_tree);
					}

					string_literal309=(Token)match(input,109,FOLLOW_109_in_durationConstraint2672); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal309_tree = (Object)adaptor.create(string_literal309);
					adaptor.addChild(root_0, string_literal309_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:421:14: ( simpleDurationConstraint )+
					int cnt58=0;
					loop58:
					while (true) {
						int alt58=2;
						int LA58_0 = input.LA(1);
						if ( (LA58_0==69) ) {
							alt58=1;
						}

						switch (alt58) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:421:14: simpleDurationConstraint
							{
							pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2674);
							simpleDurationConstraint310=simpleDurationConstraint();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint310.getTree());

							}
							break;

						default :
							if ( cnt58 >= 1 ) break loop58;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(58, input);
							throw eee;
						}
						cnt58++;
					}

					char_literal311=(Token)match(input,70,FOLLOW_70_in_durationConstraint2677); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal311_tree = (Object)adaptor.create(char_literal311);
					adaptor.addChild(root_0, char_literal311_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:422:4: '(' ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal312=(Token)match(input,69,FOLLOW_69_in_durationConstraint2682); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal312_tree = (Object)adaptor.create(char_literal312);
					adaptor.addChild(root_0, char_literal312_tree);
					}

					char_literal313=(Token)match(input,70,FOLLOW_70_in_durationConstraint2684); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal313_tree = (Object)adaptor.create(char_literal313);
					adaptor.addChild(root_0, char_literal313_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:423:4: simpleDurationConstraint
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2689);
					simpleDurationConstraint314=simpleDurationConstraint();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint314.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 57, durationConstraint_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "durationConstraint"


	public static class simpleDurationConstraint_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "simpleDurationConstraint"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:426:1: simpleDurationConstraint : ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' );
	public final PddlParser.simpleDurationConstraint_return simpleDurationConstraint() throws RecognitionException{
		PddlParser.simpleDurationConstraint_return retval = new PddlParser.simpleDurationConstraint_return();
		retval.start = input.LT(1);
		int simpleDurationConstraint_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal315=null;
		Token string_literal317=null;
		Token char_literal319=null;
		Token char_literal320=null;
		Token string_literal321=null;
		Token char_literal324=null;
		ParserRuleReturnScope durOp316 =null;
		ParserRuleReturnScope durValue318 =null;
		ParserRuleReturnScope timeSpecifier322 =null;
		ParserRuleReturnScope simpleDurationConstraint323 =null;

		Object char_literal315_tree=null;
		Object string_literal317_tree=null;
		Object char_literal319_tree=null;
		Object char_literal320_tree=null;
		Object string_literal321_tree=null;
		Object char_literal324_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:427:2: ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' )
			int alt60=2;
			int LA60_0 = input.LA(1);
			if ( (LA60_0==69) ) {
				int LA60_1 = input.LA(2);
				if ( (LA60_1==111) ) {
					alt60=2;
				}
				else if ( ((LA60_1 >= 99 && LA60_1 <= 100)||LA60_1==102) ) {
					alt60=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 60, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 60, 0, input);
				throw nvae;
			}

			switch (alt60) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:427:4: '(' durOp '?duration' durValue ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal315=(Token)match(input,69,FOLLOW_69_in_simpleDurationConstraint2700); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal315_tree = (Object)adaptor.create(char_literal315);
					adaptor.addChild(root_0, char_literal315_tree);
					}

					pushFollow(FOLLOW_durOp_in_simpleDurationConstraint2702);
					durOp316=durOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, durOp316.getTree());

					string_literal317=(Token)match(input,103,FOLLOW_103_in_simpleDurationConstraint2704); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal317_tree = (Object)adaptor.create(string_literal317);
					adaptor.addChild(root_0, string_literal317_tree);
					}

					pushFollow(FOLLOW_durValue_in_simpleDurationConstraint2706);
					durValue318=durValue();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, durValue318.getTree());

					char_literal319=(Token)match(input,70,FOLLOW_70_in_simpleDurationConstraint2708); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal319_tree = (Object)adaptor.create(char_literal319);
					adaptor.addChild(root_0, char_literal319_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:428:4: '(' 'at' timeSpecifier simpleDurationConstraint ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal320=(Token)match(input,69,FOLLOW_69_in_simpleDurationConstraint2713); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal320_tree = (Object)adaptor.create(char_literal320);
					adaptor.addChild(root_0, char_literal320_tree);
					}

					string_literal321=(Token)match(input,111,FOLLOW_111_in_simpleDurationConstraint2715); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal321_tree = (Object)adaptor.create(string_literal321);
					adaptor.addChild(root_0, string_literal321_tree);
					}

					pushFollow(FOLLOW_timeSpecifier_in_simpleDurationConstraint2717);
					timeSpecifier322=timeSpecifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier322.getTree());

					pushFollow(FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2719);
					simpleDurationConstraint323=simpleDurationConstraint();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint323.getTree());

					char_literal324=(Token)match(input,70,FOLLOW_70_in_simpleDurationConstraint2721); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal324_tree = (Object)adaptor.create(char_literal324);
					adaptor.addChild(root_0, char_literal324_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 58, simpleDurationConstraint_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "simpleDurationConstraint"


	public static class durOp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "durOp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:431:1: durOp : ( '<=' | '>=' | '=' );
	public final PddlParser.durOp_return durOp() throws RecognitionException{
		PddlParser.durOp_return retval = new PddlParser.durOp_return();
		retval.start = input.LT(1);
		int durOp_StartIndex = input.index();

		Object root_0 = null;

		Token set325=null;

		Object set325_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:431:7: ( '<=' | '>=' | '=' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set325=input.LT(1);
			if ( (input.LA(1) >= 99 && input.LA(1) <= 100)||input.LA(1)==102 ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set325));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 59, durOp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "durOp"


	public static class durValue_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "durValue"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:1: durValue : ( NUMBER | fExp );
	public final PddlParser.durValue_return durValue() throws RecognitionException{
		PddlParser.durValue_return retval = new PddlParser.durValue_return();
		retval.start = input.LT(1);
		int durValue_StartIndex = input.index();

		Object root_0 = null;

		Token NUMBER326=null;
		ParserRuleReturnScope fExp327 =null;

		Object NUMBER326_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:10: ( NUMBER | fExp )
			int alt61=2;
			int LA61_0 = input.LA(1);
			if ( (LA61_0==NUMBER) ) {
				int LA61_1 = input.LA(2);
				if ( (synpred101_Pddl()) ) {
					alt61=1;
				}
				else if ( (true) ) {
					alt61=2;
				}

			}
			else if ( (LA61_0==NAME||(LA61_0 >= 68 && LA61_0 <= 69)) ) {
				alt61=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 61, 0, input);
				throw nvae;
			}

			switch (alt61) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:12: NUMBER
					{
					root_0 = (Object)adaptor.nil();


					NUMBER326=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_durValue2748); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER326_tree = (Object)adaptor.create(NUMBER326);
					adaptor.addChild(root_0, NUMBER326_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:21: fExp
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_fExp_in_durValue2752);
					fExp327=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp327.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 60, durValue_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "durValue"


	public static class daEffect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "daEffect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:435:1: daEffect : ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' );
	public final PddlParser.daEffect_return daEffect() throws RecognitionException{
		PddlParser.daEffect_return retval = new PddlParser.daEffect_return();
		retval.start = input.LT(1);
		int daEffect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal328=null;
		Token string_literal329=null;
		Token char_literal331=null;
		Token char_literal333=null;
		Token string_literal334=null;
		Token char_literal335=null;
		Token char_literal337=null;
		Token char_literal339=null;
		Token char_literal340=null;
		Token string_literal341=null;
		Token char_literal344=null;
		Token char_literal345=null;
		Token char_literal349=null;
		ParserRuleReturnScope daEffect330 =null;
		ParserRuleReturnScope timedEffect332 =null;
		ParserRuleReturnScope typedVariableList336 =null;
		ParserRuleReturnScope daEffect338 =null;
		ParserRuleReturnScope daGD342 =null;
		ParserRuleReturnScope timedEffect343 =null;
		ParserRuleReturnScope assignOp346 =null;
		ParserRuleReturnScope fHead347 =null;
		ParserRuleReturnScope fExpDA348 =null;

		Object char_literal328_tree=null;
		Object string_literal329_tree=null;
		Object char_literal331_tree=null;
		Object char_literal333_tree=null;
		Object string_literal334_tree=null;
		Object char_literal335_tree=null;
		Object char_literal337_tree=null;
		Object char_literal339_tree=null;
		Object char_literal340_tree=null;
		Object string_literal341_tree=null;
		Object char_literal344_tree=null;
		Object char_literal345_tree=null;
		Object char_literal349_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:2: ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' )
			int alt63=5;
			int LA63_0 = input.LA(1);
			if ( (LA63_0==69) ) {
				int LA63_1 = input.LA(2);
				if ( (synpred103_Pddl()) ) {
					alt63=1;
				}
				else if ( (synpred104_Pddl()) ) {
					alt63=2;
				}
				else if ( (synpred105_Pddl()) ) {
					alt63=3;
				}
				else if ( (synpred106_Pddl()) ) {
					alt63=4;
				}
				else if ( (true) ) {
					alt63=5;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 63, 0, input);
				throw nvae;
			}

			switch (alt63) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:4: '(' 'and' ( daEffect )* ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal328=(Token)match(input,69,FOLLOW_69_in_daEffect2762); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal328_tree = (Object)adaptor.create(char_literal328);
					adaptor.addChild(root_0, char_literal328_tree);
					}

					string_literal329=(Token)match(input,109,FOLLOW_109_in_daEffect2764); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal329_tree = (Object)adaptor.create(string_literal329);
					adaptor.addChild(root_0, string_literal329_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:14: ( daEffect )*
					loop62:
					while (true) {
						int alt62=2;
						int LA62_0 = input.LA(1);
						if ( (LA62_0==69) ) {
							alt62=1;
						}

						switch (alt62) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:14: daEffect
							{
							pushFollow(FOLLOW_daEffect_in_daEffect2766);
							daEffect330=daEffect();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect330.getTree());

							}
							break;

						default :
							break loop62;
						}
					}

					char_literal331=(Token)match(input,70,FOLLOW_70_in_daEffect2769); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal331_tree = (Object)adaptor.create(char_literal331);
					adaptor.addChild(root_0, char_literal331_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:437:4: timedEffect
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_timedEffect_in_daEffect2774);
					timedEffect332=timedEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timedEffect332.getTree());

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:438:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal333=(Token)match(input,69,FOLLOW_69_in_daEffect2779); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal333_tree = (Object)adaptor.create(char_literal333);
					adaptor.addChild(root_0, char_literal333_tree);
					}

					string_literal334=(Token)match(input,120,FOLLOW_120_in_daEffect2781); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal334_tree = (Object)adaptor.create(string_literal334);
					adaptor.addChild(root_0, string_literal334_tree);
					}

					char_literal335=(Token)match(input,69,FOLLOW_69_in_daEffect2783); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal335_tree = (Object)adaptor.create(char_literal335);
					adaptor.addChild(root_0, char_literal335_tree);
					}

					pushFollow(FOLLOW_typedVariableList_in_daEffect2785);
					typedVariableList336=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList336.getTree());

					char_literal337=(Token)match(input,70,FOLLOW_70_in_daEffect2787); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal337_tree = (Object)adaptor.create(char_literal337);
					adaptor.addChild(root_0, char_literal337_tree);
					}

					pushFollow(FOLLOW_daEffect_in_daEffect2789);
					daEffect338=daEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect338.getTree());

					char_literal339=(Token)match(input,70,FOLLOW_70_in_daEffect2791); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal339_tree = (Object)adaptor.create(char_literal339);
					adaptor.addChild(root_0, char_literal339_tree);
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:439:4: '(' 'when' daGD timedEffect ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal340=(Token)match(input,69,FOLLOW_69_in_daEffect2796); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal340_tree = (Object)adaptor.create(char_literal340);
					adaptor.addChild(root_0, char_literal340_tree);
					}

					string_literal341=(Token)match(input,143,FOLLOW_143_in_daEffect2798); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal341_tree = (Object)adaptor.create(string_literal341);
					adaptor.addChild(root_0, string_literal341_tree);
					}

					pushFollow(FOLLOW_daGD_in_daEffect2800);
					daGD342=daGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD342.getTree());

					pushFollow(FOLLOW_timedEffect_in_daEffect2802);
					timedEffect343=timedEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timedEffect343.getTree());

					char_literal344=(Token)match(input,70,FOLLOW_70_in_daEffect2804); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal344_tree = (Object)adaptor.create(char_literal344);
					adaptor.addChild(root_0, char_literal344_tree);
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:440:4: '(' assignOp fHead fExpDA ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal345=(Token)match(input,69,FOLLOW_69_in_daEffect2809); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal345_tree = (Object)adaptor.create(char_literal345);
					adaptor.addChild(root_0, char_literal345_tree);
					}

					pushFollow(FOLLOW_assignOp_in_daEffect2811);
					assignOp346=assignOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp346.getTree());

					pushFollow(FOLLOW_fHead_in_daEffect2813);
					fHead347=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead347.getTree());

					pushFollow(FOLLOW_fExpDA_in_daEffect2815);
					fExpDA348=fExpDA();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA348.getTree());

					char_literal349=(Token)match(input,70,FOLLOW_70_in_daEffect2817); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal349_tree = (Object)adaptor.create(char_literal349);
					adaptor.addChild(root_0, char_literal349_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 61, daEffect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "daEffect"


	public static class timedEffect_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "timedEffect"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:443:1: timedEffect : ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' );
	public final PddlParser.timedEffect_return timedEffect() throws RecognitionException{
		PddlParser.timedEffect_return retval = new PddlParser.timedEffect_return();
		retval.start = input.LT(1);
		int timedEffect_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal350=null;
		Token string_literal351=null;
		Token char_literal354=null;
		Token char_literal355=null;
		Token string_literal356=null;
		Token char_literal359=null;
		Token char_literal360=null;
		Token char_literal364=null;
		ParserRuleReturnScope timeSpecifier352 =null;
		ParserRuleReturnScope daEffect353 =null;
		ParserRuleReturnScope timeSpecifier357 =null;
		ParserRuleReturnScope fAssignDA358 =null;
		ParserRuleReturnScope assignOp361 =null;
		ParserRuleReturnScope fHead362 =null;
		ParserRuleReturnScope fExp363 =null;

		Object char_literal350_tree=null;
		Object string_literal351_tree=null;
		Object char_literal354_tree=null;
		Object char_literal355_tree=null;
		Object string_literal356_tree=null;
		Object char_literal359_tree=null;
		Object char_literal360_tree=null;
		Object char_literal364_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:444:2: ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' )
			int alt64=3;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==69) ) {
				int LA64_1 = input.LA(2);
				if ( (synpred107_Pddl()) ) {
					alt64=1;
				}
				else if ( (synpred108_Pddl()) ) {
					alt64=2;
				}
				else if ( (true) ) {
					alt64=3;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 64, 0, input);
				throw nvae;
			}

			switch (alt64) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:444:4: '(' 'at' timeSpecifier daEffect ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal350=(Token)match(input,69,FOLLOW_69_in_timedEffect2828); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal350_tree = (Object)adaptor.create(char_literal350);
					adaptor.addChild(root_0, char_literal350_tree);
					}

					string_literal351=(Token)match(input,111,FOLLOW_111_in_timedEffect2830); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal351_tree = (Object)adaptor.create(string_literal351);
					adaptor.addChild(root_0, string_literal351_tree);
					}

					pushFollow(FOLLOW_timeSpecifier_in_timedEffect2832);
					timeSpecifier352=timeSpecifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier352.getTree());

					pushFollow(FOLLOW_daEffect_in_timedEffect2834);
					daEffect353=daEffect();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect353.getTree());

					char_literal354=(Token)match(input,70,FOLLOW_70_in_timedEffect2836); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal354_tree = (Object)adaptor.create(char_literal354);
					adaptor.addChild(root_0, char_literal354_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:445:4: '(' 'at' timeSpecifier fAssignDA ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal355=(Token)match(input,69,FOLLOW_69_in_timedEffect2846); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal355_tree = (Object)adaptor.create(char_literal355);
					adaptor.addChild(root_0, char_literal355_tree);
					}

					string_literal356=(Token)match(input,111,FOLLOW_111_in_timedEffect2848); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal356_tree = (Object)adaptor.create(string_literal356);
					adaptor.addChild(root_0, string_literal356_tree);
					}

					pushFollow(FOLLOW_timeSpecifier_in_timedEffect2850);
					timeSpecifier357=timeSpecifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier357.getTree());

					pushFollow(FOLLOW_fAssignDA_in_timedEffect2852);
					fAssignDA358=fAssignDA();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fAssignDA358.getTree());

					char_literal359=(Token)match(input,70,FOLLOW_70_in_timedEffect2854); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal359_tree = (Object)adaptor.create(char_literal359);
					adaptor.addChild(root_0, char_literal359_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:446:4: '(' assignOp fHead fExp ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal360=(Token)match(input,69,FOLLOW_69_in_timedEffect2859); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal360_tree = (Object)adaptor.create(char_literal360);
					adaptor.addChild(root_0, char_literal360_tree);
					}

					pushFollow(FOLLOW_assignOp_in_timedEffect2861);
					assignOp361=assignOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp361.getTree());

					pushFollow(FOLLOW_fHead_in_timedEffect2863);
					fHead362=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead362.getTree());

					pushFollow(FOLLOW_fExp_in_timedEffect2865);
					fExp363=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp363.getTree());

					char_literal364=(Token)match(input,70,FOLLOW_70_in_timedEffect2867); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal364_tree = (Object)adaptor.create(char_literal364);
					adaptor.addChild(root_0, char_literal364_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 62, timedEffect_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "timedEffect"


	public static class fAssignDA_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fAssignDA"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:449:1: fAssignDA : '(' assignOp fHead fExpDA ')' ;
	public final PddlParser.fAssignDA_return fAssignDA() throws RecognitionException{
		PddlParser.fAssignDA_return retval = new PddlParser.fAssignDA_return();
		retval.start = input.LT(1);
		int fAssignDA_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal365=null;
		Token char_literal369=null;
		ParserRuleReturnScope assignOp366 =null;
		ParserRuleReturnScope fHead367 =null;
		ParserRuleReturnScope fExpDA368 =null;

		Object char_literal365_tree=null;
		Object char_literal369_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:450:2: ( '(' assignOp fHead fExpDA ')' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:450:4: '(' assignOp fHead fExpDA ')'
			{
			root_0 = (Object)adaptor.nil();


			char_literal365=(Token)match(input,69,FOLLOW_69_in_fAssignDA2887); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal365_tree = (Object)adaptor.create(char_literal365);
			adaptor.addChild(root_0, char_literal365_tree);
			}

			pushFollow(FOLLOW_assignOp_in_fAssignDA2889);
			assignOp366=assignOp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp366.getTree());

			pushFollow(FOLLOW_fHead_in_fAssignDA2891);
			fHead367=fHead();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead367.getTree());

			pushFollow(FOLLOW_fExpDA_in_fAssignDA2893);
			fExpDA368=fExpDA();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA368.getTree());

			char_literal369=(Token)match(input,70,FOLLOW_70_in_fAssignDA2895); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal369_tree = (Object)adaptor.create(char_literal369);
			adaptor.addChild(root_0, char_literal369_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 63, fAssignDA_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fAssignDA"


	public static class fExpDA_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fExpDA"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:453:1: fExpDA : ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp );
	public final PddlParser.fExpDA_return fExpDA() throws RecognitionException{
		PddlParser.fExpDA_return retval = new PddlParser.fExpDA_return();
		retval.start = input.LT(1);
		int fExpDA_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal370=null;
		Token char_literal374=null;
		Token char_literal376=null;
		Token string_literal377=null;
		ParserRuleReturnScope binaryOp371 =null;
		ParserRuleReturnScope fExpDA372 =null;
		ParserRuleReturnScope fExpDA373 =null;
		ParserRuleReturnScope fExpDA375 =null;
		ParserRuleReturnScope fExp378 =null;

		Object char_literal370_tree=null;
		Object char_literal374_tree=null;
		Object char_literal376_tree=null;
		Object string_literal377_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:2: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp )
			int alt66=3;
			switch ( input.LA(1) ) {
			case 69:
				{
				int LA66_1 = input.LA(2);
				if ( (synpred110_Pddl()) ) {
					alt66=1;
				}
				else if ( (true) ) {
					alt66=3;
				}

				}
				break;
			case 103:
				{
				alt66=2;
				}
				break;
			case NAME:
			case NUMBER:
			case 68:
				{
				alt66=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 66, 0, input);
				throw nvae;
			}
			switch (alt66) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal370=(Token)match(input,69,FOLLOW_69_in_fExpDA2906); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal370_tree = (Object)adaptor.create(char_literal370);
					adaptor.addChild(root_0, char_literal370_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
					int alt65=2;
					int LA65_0 = input.LA(1);
					if ( (LA65_0==73) ) {
						int LA65_1 = input.LA(2);
						if ( (synpred109_Pddl()) ) {
							alt65=1;
						}
						else if ( (true) ) {
							alt65=2;
						}

					}
					else if ( ((LA65_0 >= 71 && LA65_0 <= 72)||LA65_0==74||LA65_0==104) ) {
						alt65=1;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 65, 0, input);
						throw nvae;
					}

					switch (alt65) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:10: binaryOp fExpDA fExpDA
							{
							pushFollow(FOLLOW_binaryOp_in_fExpDA2910);
							binaryOp371=binaryOp();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, binaryOp371.getTree());

							pushFollow(FOLLOW_fExpDA_in_fExpDA2912);
							fExpDA372=fExpDA();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA372.getTree());

							pushFollow(FOLLOW_fExpDA_in_fExpDA2914);
							fExpDA373=fExpDA();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA373.getTree());

							}

							}
							break;
						case 2 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:36: ( '-' fExpDA )
							{
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:36: ( '-' fExpDA )
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:37: '-' fExpDA
							{
							char_literal374=(Token)match(input,73,FOLLOW_73_in_fExpDA2920); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							char_literal374_tree = (Object)adaptor.create(char_literal374);
							adaptor.addChild(root_0, char_literal374_tree);
							}

							pushFollow(FOLLOW_fExpDA_in_fExpDA2922);
							fExpDA375=fExpDA();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA375.getTree());

							}

							}
							break;

					}

					char_literal376=(Token)match(input,70,FOLLOW_70_in_fExpDA2926); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal376_tree = (Object)adaptor.create(char_literal376);
					adaptor.addChild(root_0, char_literal376_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:455:4: '?duration'
					{
					root_0 = (Object)adaptor.nil();


					string_literal377=(Token)match(input,103,FOLLOW_103_in_fExpDA2931); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal377_tree = (Object)adaptor.create(string_literal377);
					adaptor.addChild(root_0, string_literal377_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:456:4: fExp
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_fExp_in_fExpDA2936);
					fExp378=fExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp378.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 64, fExpDA_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "fExpDA"


	public static class problem_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "problem"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:461:1: problem : '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) ;
	public final PddlParser.problem_return problem() throws RecognitionException{
		PddlParser.problem_return retval = new PddlParser.problem_return();
		retval.start = input.LT(1);
		int problem_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal379=null;
		Token string_literal380=null;
		Token char_literal389=null;
		ParserRuleReturnScope problemDecl381 =null;
		ParserRuleReturnScope problemDomain382 =null;
		ParserRuleReturnScope requireDef383 =null;
		ParserRuleReturnScope objectDecl384 =null;
		ParserRuleReturnScope init385 =null;
		ParserRuleReturnScope goal386 =null;
		ParserRuleReturnScope probConstraints387 =null;
		ParserRuleReturnScope metricSpec388 =null;

		Object char_literal379_tree=null;
		Object string_literal380_tree=null;
		Object char_literal389_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_115=new RewriteRuleTokenStream(adaptor,"token 115");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_metricSpec=new RewriteRuleSubtreeStream(adaptor,"rule metricSpec");
		RewriteRuleSubtreeStream stream_init=new RewriteRuleSubtreeStream(adaptor,"rule init");
		RewriteRuleSubtreeStream stream_goal=new RewriteRuleSubtreeStream(adaptor,"rule goal");
		RewriteRuleSubtreeStream stream_requireDef=new RewriteRuleSubtreeStream(adaptor,"rule requireDef");
		RewriteRuleSubtreeStream stream_problemDecl=new RewriteRuleSubtreeStream(adaptor,"rule problemDecl");
		RewriteRuleSubtreeStream stream_objectDecl=new RewriteRuleSubtreeStream(adaptor,"rule objectDecl");
		RewriteRuleSubtreeStream stream_problemDomain=new RewriteRuleSubtreeStream(adaptor,"rule problemDomain");
		RewriteRuleSubtreeStream stream_probConstraints=new RewriteRuleSubtreeStream(adaptor,"rule probConstraints");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:462:2: ( '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:462:4: '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')'
			{
			char_literal379=(Token)match(input,69,FOLLOW_69_in_problem2950); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal379);

			string_literal380=(Token)match(input,115,FOLLOW_115_in_problem2952); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_115.add(string_literal380);

			pushFollow(FOLLOW_problemDecl_in_problem2954);
			problemDecl381=problemDecl();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_problemDecl.add(problemDecl381.getTree());
			pushFollow(FOLLOW_problemDomain_in_problem2959);
			problemDomain382=problemDomain();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_problemDomain.add(problemDomain382.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:464:7: ( requireDef )?
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==69) ) {
				int LA67_1 = input.LA(2);
				if ( (LA67_1==96) ) {
					alt67=1;
				}
			}
			switch (alt67) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:464:7: requireDef
					{
					pushFollow(FOLLOW_requireDef_in_problem2967);
					requireDef383=requireDef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_requireDef.add(requireDef383.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:465:7: ( objectDecl )?
			int alt68=2;
			int LA68_0 = input.LA(1);
			if ( (LA68_0==69) ) {
				int LA68_1 = input.LA(2);
				if ( (LA68_1==91) ) {
					alt68=1;
				}
			}
			switch (alt68) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:465:7: objectDecl
					{
					pushFollow(FOLLOW_objectDecl_in_problem2976);
					objectDecl384=objectDecl();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_objectDecl.add(objectDecl384.getTree());
					}
					break;

			}

			pushFollow(FOLLOW_init_in_problem2985);
			init385=init();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_init.add(init385.getTree());
			pushFollow(FOLLOW_goal_in_problem2993);
			goal386=goal();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_goal.add(goal386.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:468:7: ( probConstraints )?
			int alt69=2;
			int LA69_0 = input.LA(1);
			if ( (LA69_0==69) ) {
				int LA69_1 = input.LA(2);
				if ( (LA69_1==79) ) {
					alt69=1;
				}
			}
			switch (alt69) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:468:7: probConstraints
					{
					pushFollow(FOLLOW_probConstraints_in_problem3001);
					probConstraints387=probConstraints();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_probConstraints.add(probConstraints387.getTree());
					}
					break;

			}

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:469:7: ( metricSpec )?
			int alt70=2;
			int LA70_0 = input.LA(1);
			if ( (LA70_0==69) ) {
				alt70=1;
			}
			switch (alt70) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:469:7: metricSpec
					{
					pushFollow(FOLLOW_metricSpec_in_problem3010);
					metricSpec388=metricSpec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_metricSpec.add(metricSpec388.getTree());
					}
					break;

			}

			char_literal389=(Token)match(input,70,FOLLOW_70_in_problem3026); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal389);

			// AST REWRITE
			// elements: probConstraints, metricSpec, objectDecl, init, requireDef, goal, problemDecl, problemDomain
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 472:7: -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:472:10: ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROBLEM, "PROBLEM"), root_1);
				adaptor.addChild(root_1, stream_problemDecl.nextTree());
				adaptor.addChild(root_1, stream_problemDomain.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:472:46: ( requireDef )?
				if ( stream_requireDef.hasNext() ) {
					adaptor.addChild(root_1, stream_requireDef.nextTree());
				}
				stream_requireDef.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:472:58: ( objectDecl )?
				if ( stream_objectDecl.hasNext() ) {
					adaptor.addChild(root_1, stream_objectDecl.nextTree());
				}
				stream_objectDecl.reset();

				adaptor.addChild(root_1, stream_init.nextTree());
				adaptor.addChild(root_1, stream_goal.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:473:19: ( probConstraints )?
				if ( stream_probConstraints.hasNext() ) {
					adaptor.addChild(root_1, stream_probConstraints.nextTree());
				}
				stream_probConstraints.reset();

				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:473:36: ( metricSpec )?
				if ( stream_metricSpec.hasNext() ) {
					adaptor.addChild(root_1, stream_metricSpec.nextTree());
				}
				stream_metricSpec.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 65, problem_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "problem"


	public static class problemDecl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "problemDecl"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:476:1: problemDecl : '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) ;
	public final PddlParser.problemDecl_return problemDecl() throws RecognitionException{
		PddlParser.problemDecl_return retval = new PddlParser.problemDecl_return();
		retval.start = input.LT(1);
		int problemDecl_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal390=null;
		Token string_literal391=null;
		Token NAME392=null;
		Token char_literal393=null;

		Object char_literal390_tree=null;
		Object string_literal391_tree=null;
		Object NAME392_tree=null;
		Object char_literal393_tree=null;
		RewriteRuleTokenStream stream_134=new RewriteRuleTokenStream(adaptor,"token 134");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:477:5: ( '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:477:7: '(' 'problem' NAME ')'
			{
			char_literal390=(Token)match(input,69,FOLLOW_69_in_problemDecl3083); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal390);

			string_literal391=(Token)match(input,134,FOLLOW_134_in_problemDecl3085); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_134.add(string_literal391);

			NAME392=(Token)match(input,NAME,FOLLOW_NAME_in_problemDecl3087); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_NAME.add(NAME392);

			char_literal393=(Token)match(input,70,FOLLOW_70_in_problemDecl3089); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal393);

			// AST REWRITE
			// elements: NAME
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 478:5: -> ^( PROBLEM_NAME NAME )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:478:8: ^( PROBLEM_NAME NAME )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROBLEM_NAME, "PROBLEM_NAME"), root_1);
				adaptor.addChild(root_1, stream_NAME.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 66, problemDecl_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "problemDecl"


	public static class problemDomain_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "problemDomain"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:481:1: problemDomain : '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) ;
	public final PddlParser.problemDomain_return problemDomain() throws RecognitionException{
		PddlParser.problemDomain_return retval = new PddlParser.problemDomain_return();
		retval.start = input.LT(1);
		int problemDomain_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal394=null;
		Token string_literal395=null;
		Token NAME396=null;
		Token char_literal397=null;

		Object char_literal394_tree=null;
		Object string_literal395_tree=null;
		Object NAME396_tree=null;
		Object char_literal397_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
		RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:482:2: ( '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:482:4: '(' ':domain' NAME ')'
			{
			char_literal394=(Token)match(input,69,FOLLOW_69_in_problemDomain3115); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal394);

			string_literal395=(Token)match(input,81,FOLLOW_81_in_problemDomain3117); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_81.add(string_literal395);

			NAME396=(Token)match(input,NAME,FOLLOW_NAME_in_problemDomain3119); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_NAME.add(NAME396);

			char_literal397=(Token)match(input,70,FOLLOW_70_in_problemDomain3121); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal397);

			// AST REWRITE
			// elements: NAME
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 483:2: -> ^( PROBLEM_DOMAIN NAME )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:483:5: ^( PROBLEM_DOMAIN NAME )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROBLEM_DOMAIN, "PROBLEM_DOMAIN"), root_1);
				adaptor.addChild(root_1, stream_NAME.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 67, problemDomain_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "problemDomain"


	public static class objectDecl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "objectDecl"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:486:1: objectDecl : '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) ;
	public final PddlParser.objectDecl_return objectDecl() throws RecognitionException{
		PddlParser.objectDecl_return retval = new PddlParser.objectDecl_return();
		retval.start = input.LT(1);
		int objectDecl_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal398=null;
		Token string_literal399=null;
		Token char_literal401=null;
		ParserRuleReturnScope typedNameList400 =null;

		Object char_literal398_tree=null;
		Object string_literal399_tree=null;
		Object char_literal401_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:487:2: ( '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:487:4: '(' ':objects' typedNameList ')'
			{
			char_literal398=(Token)match(input,69,FOLLOW_69_in_objectDecl3141); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal398);

			string_literal399=(Token)match(input,91,FOLLOW_91_in_objectDecl3143); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_91.add(string_literal399);

			pushFollow(FOLLOW_typedNameList_in_objectDecl3145);
			typedNameList400=typedNameList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList400.getTree());
			char_literal401=(Token)match(input,70,FOLLOW_70_in_objectDecl3147); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal401);

			// AST REWRITE
			// elements: typedNameList
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 488:2: -> ^( OBJECTS typedNameList )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:488:5: ^( OBJECTS typedNameList )
				{
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(OBJECTS, "OBJECTS"), root_1);

                                if (stream_typedNameList.hasNext()) {//This is a hack to handle empty lists. Something could be probably done in the parser
                                    adaptor.addChild(root_1, stream_typedNameList.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                         			}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 68, objectDecl_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "objectDecl"


	public static class init_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "init"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:491:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) );
	public final PddlParser.init_return init() throws RecognitionException{
		PddlParser.init_return retval = new PddlParser.init_return();
		retval.start = input.LT(1);
		int init_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal402=null;
		Token string_literal403=null;
		Token char_literal405=null;
		Token char_literal406=null;
		Token string_literal407=null;
		Token char_literal409=null;
		ParserRuleReturnScope initEl404 =null;
		ParserRuleReturnScope belief408 =null;

		Object char_literal402_tree=null;
		Object string_literal403_tree=null;
		Object char_literal405_tree=null;
		Object char_literal406_tree=null;
		Object string_literal407_tree=null;
		Object char_literal409_tree=null;
		RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_initEl=new RewriteRuleSubtreeStream(adaptor,"rule initEl");
		RewriteRuleSubtreeStream stream_belief=new RewriteRuleSubtreeStream(adaptor,"rule belief");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:2: ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) )
			int alt72=2;
			alt72 = dfa72.predict(input);
			switch (alt72) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:4: '(' ':init' ( initEl )* ')'
					{
					char_literal402=(Token)match(input,69,FOLLOW_69_in_init3167); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal402);

					string_literal403=(Token)match(input,89,FOLLOW_89_in_init3169); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_89.add(string_literal403);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:16: ( initEl )*
					loop71:
					while (true) {
						int alt71=2;
						int LA71_0 = input.LA(1);
						if ( (LA71_0==69) ) {
							alt71=1;
						}

						switch (alt71) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:16: initEl
							{
							pushFollow(FOLLOW_initEl_in_init3171);
							initEl404=initEl();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_initEl.add(initEl404.getTree());
							}
							break;

						default :
							break loop71;
						}
					}

					char_literal405=(Token)match(input,70,FOLLOW_70_in_init3174); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal405);

					// AST REWRITE
					// elements: initEl
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 493:2: -> ^( INIT ( initEl )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:493:5: ^( INIT ( initEl )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INIT, "INIT"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:493:12: ( initEl )*
						while ( stream_initEl.hasNext() ) {
							adaptor.addChild(root_1, stream_initEl.nextTree());
						}
						stream_initEl.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:494:4: '(' ':init' belief ')'
					{
					char_literal406=(Token)match(input,69,FOLLOW_69_in_init3189); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal406);

					string_literal407=(Token)match(input,89,FOLLOW_89_in_init3191); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_89.add(string_literal407);

					pushFollow(FOLLOW_belief_in_init3193);
					belief408=belief();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_belief.add(belief408.getTree());
					char_literal409=(Token)match(input,70,FOLLOW_70_in_init3195); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal409);

					// AST REWRITE
					// elements: belief
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 494:27: -> ^( FORMULAINIT belief )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:494:30: ^( FORMULAINIT belief )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FORMULAINIT, "FORMULAINIT"), root_1);
						adaptor.addChild(root_1, stream_belief.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 69, init_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "init"


	public static class initEl_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "initEl"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:497:1: initEl : ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) );
	public final PddlParser.initEl_return initEl() throws RecognitionException{
		PddlParser.initEl_return retval = new PddlParser.initEl_return();
		retval.start = input.LT(1);
		int initEl_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal411=null;
		Token char_literal412=null;
		Token NUMBER414=null;
		Token char_literal415=null;
		Token char_literal416=null;
		Token string_literal417=null;
		Token NUMBER418=null;
		Token char_literal420=null;
		Token char_literal421=null;
		Token string_literal422=null;
		Token char_literal424=null;
		Token char_literal425=null;
		Token string_literal426=null;
		Token char_literal428=null;
		Token char_literal429=null;
		Token string_literal430=null;
		Token char_literal432=null;
		ParserRuleReturnScope nameLiteral410 =null;
		ParserRuleReturnScope fHead413 =null;
		ParserRuleReturnScope nameLiteral419 =null;
		ParserRuleReturnScope atomicNameFormula423 =null;
		ParserRuleReturnScope atomicNameFormula427 =null;
		ParserRuleReturnScope nameLiteral431 =null;

		Object char_literal411_tree=null;
		Object char_literal412_tree=null;
		Object NUMBER414_tree=null;
		Object char_literal415_tree=null;
		Object char_literal416_tree=null;
		Object string_literal417_tree=null;
		Object NUMBER418_tree=null;
		Object char_literal420_tree=null;
		Object char_literal421_tree=null;
		Object string_literal422_tree=null;
		Object char_literal424_tree=null;
		Object char_literal425_tree=null;
		Object string_literal426_tree=null;
		Object char_literal428_tree=null;
		Object char_literal429_tree=null;
		Object string_literal430_tree=null;
		Object char_literal432_tree=null;
		RewriteRuleTokenStream stream_100=new RewriteRuleTokenStream(adaptor,"token 100");
		RewriteRuleTokenStream stream_111=new RewriteRuleTokenStream(adaptor,"token 111");
		RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_130=new RewriteRuleTokenStream(adaptor,"token 130");
		RewriteRuleTokenStream stream_142=new RewriteRuleTokenStream(adaptor,"token 142");
		RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
		RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");
		RewriteRuleSubtreeStream stream_atomicNameFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicNameFormula");
		RewriteRuleSubtreeStream stream_nameLiteral=new RewriteRuleSubtreeStream(adaptor,"rule nameLiteral");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:498:2: ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) )
			int alt75=6;
			int LA75_0 = input.LA(1);
			if ( (LA75_0==69) ) {
				switch ( input.LA(2) ) {
				case NAME:
				case 128:
					{
					alt75=1;
					}
					break;
				case 100:
					{
					alt75=2;
					}
					break;
				case 111:
					{
					alt75=3;
					}
					break;
				case 142:
					{
					alt75=4;
					}
					break;
				case 130:
					{
					alt75=5;
					}
					break;
				case 131:
					{
					alt75=6;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 75, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 75, 0, input);
				throw nvae;
			}

			switch (alt75) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:498:4: nameLiteral
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_nameLiteral_in_initEl3214);
					nameLiteral410=nameLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, nameLiteral410.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:499:4: '(' '=' fHead NUMBER ')'
					{
					char_literal411=(Token)match(input,69,FOLLOW_69_in_initEl3219); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal411);

					char_literal412=(Token)match(input,100,FOLLOW_100_in_initEl3221); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_100.add(char_literal412);

					pushFollow(FOLLOW_fHead_in_initEl3223);
					fHead413=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_fHead.add(fHead413.getTree());
					NUMBER414=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_initEl3225); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NUMBER.add(NUMBER414);

					char_literal415=(Token)match(input,70,FOLLOW_70_in_initEl3227); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal415);

					// AST REWRITE
					// elements: NUMBER, fHead
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 499:37: -> ^( INIT_EQ fHead NUMBER )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:499:40: ^( INIT_EQ fHead NUMBER )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INIT_EQ, "INIT_EQ"), root_1);
						adaptor.addChild(root_1, stream_fHead.nextTree());
						adaptor.addChild(root_1, stream_NUMBER.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:500:4: '(' 'at' NUMBER nameLiteral ')'
					{
					char_literal416=(Token)match(input,69,FOLLOW_69_in_initEl3250); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal416);

					string_literal417=(Token)match(input,111,FOLLOW_111_in_initEl3252); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_111.add(string_literal417);

					NUMBER418=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_initEl3254); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NUMBER.add(NUMBER418);

					pushFollow(FOLLOW_nameLiteral_in_initEl3256);
					nameLiteral419=nameLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_nameLiteral.add(nameLiteral419.getTree());
					char_literal420=(Token)match(input,70,FOLLOW_70_in_initEl3258); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal420);

					// AST REWRITE
					// elements: nameLiteral, NUMBER
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 500:37: -> ^( INIT_AT NUMBER nameLiteral )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:500:40: ^( INIT_AT NUMBER nameLiteral )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INIT_AT, "INIT_AT"), root_1);
						adaptor.addChild(root_1, stream_NUMBER.nextNode());
						adaptor.addChild(root_1, stream_nameLiteral.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:501:4: '(' 'unknown' atomicNameFormula ')'
					{
					char_literal421=(Token)match(input,69,FOLLOW_69_in_initEl3274); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal421);

					string_literal422=(Token)match(input,142,FOLLOW_142_in_initEl3276); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_142.add(string_literal422);

					pushFollow(FOLLOW_atomicNameFormula_in_initEl3279);
					atomicNameFormula423=atomicNameFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula423.getTree());
					char_literal424=(Token)match(input,70,FOLLOW_70_in_initEl3281); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal424);

					// AST REWRITE
					// elements: atomicNameFormula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 501:42: -> ^( UNKNOWN atomicNameFormula )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:501:45: ^( UNKNOWN atomicNameFormula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNKNOWN, "UNKNOWN"), root_1);
						adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:502:4: '(' 'oneof' ( atomicNameFormula )* ')'
					{
					char_literal425=(Token)match(input,69,FOLLOW_69_in_initEl3295); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal425);

					string_literal426=(Token)match(input,130,FOLLOW_130_in_initEl3297); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_130.add(string_literal426);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:502:17: ( atomicNameFormula )*
					loop73:
					while (true) {
						int alt73=2;
						int LA73_0 = input.LA(1);
						if ( (LA73_0==69) ) {
							alt73=1;
						}

						switch (alt73) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:502:17: atomicNameFormula
							{
							pushFollow(FOLLOW_atomicNameFormula_in_initEl3300);
							atomicNameFormula427=atomicNameFormula();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula427.getTree());
							}
							break;

						default :
							break loop73;
						}
					}

					char_literal428=(Token)match(input,70,FOLLOW_70_in_initEl3303); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal428);

					// AST REWRITE
					// elements: atomicNameFormula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 502:41: -> ^( ONEOF ( atomicNameFormula )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:502:44: ^( ONEOF ( atomicNameFormula )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ONEOF, "ONEOF"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:502:52: ( atomicNameFormula )*
						while ( stream_atomicNameFormula.hasNext() ) {
							adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
						}
						stream_atomicNameFormula.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:503:4: '(' 'or' ( nameLiteral )* ')'
					{
					char_literal429=(Token)match(input,69,FOLLOW_69_in_initEl3318); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal429);

					string_literal430=(Token)match(input,131,FOLLOW_131_in_initEl3320); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_131.add(string_literal430);

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:503:14: ( nameLiteral )*
					loop74:
					while (true) {
						int alt74=2;
						int LA74_0 = input.LA(1);
						if ( (LA74_0==69) ) {
							alt74=1;
						}

						switch (alt74) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:503:14: nameLiteral
							{
							pushFollow(FOLLOW_nameLiteral_in_initEl3323);
							nameLiteral431=nameLiteral();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_nameLiteral.add(nameLiteral431.getTree());
							}
							break;

						default :
							break loop74;
						}
					}

					char_literal432=(Token)match(input,70,FOLLOW_70_in_initEl3326); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal432);

					// AST REWRITE
					// elements: nameLiteral
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 503:32: -> ^( OR_GD ( nameLiteral )* )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:503:35: ^( OR_GD ( nameLiteral )* )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OR_GD, "OR_GD"), root_1);
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:503:43: ( nameLiteral )*
						while ( stream_nameLiteral.hasNext() ) {
							adaptor.addChild(root_1, stream_nameLiteral.nextTree());
						}
						stream_nameLiteral.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 70, initEl_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "initEl"


	public static class nameLiteral_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "nameLiteral"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:506:1: nameLiteral : ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) );
	public final PddlParser.nameLiteral_return nameLiteral() throws RecognitionException{
		PddlParser.nameLiteral_return retval = new PddlParser.nameLiteral_return();
		retval.start = input.LT(1);
		int nameLiteral_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal434=null;
		Token string_literal435=null;
		Token char_literal437=null;
		ParserRuleReturnScope atomicNameFormula433 =null;
		ParserRuleReturnScope atomicNameFormula436 =null;

		Object char_literal434_tree=null;
		Object string_literal435_tree=null;
		Object char_literal437_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_128=new RewriteRuleTokenStream(adaptor,"token 128");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_atomicNameFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicNameFormula");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:507:2: ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) )
			int alt76=2;
			int LA76_0 = input.LA(1);
			if ( (LA76_0==69) ) {
				int LA76_1 = input.LA(2);
				if ( (LA76_1==128) ) {
					alt76=2;
				}
				else if ( (LA76_1==NAME) ) {
					alt76=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 76, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 76, 0, input);
				throw nvae;
			}

			switch (alt76) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:507:4: atomicNameFormula
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3347);
					atomicNameFormula433=atomicNameFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicNameFormula433.getTree());

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:508:4: '(' 'not' atomicNameFormula ')'
					{
					char_literal434=(Token)match(input,69,FOLLOW_69_in_nameLiteral3352); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal434);

					string_literal435=(Token)match(input,128,FOLLOW_128_in_nameLiteral3354); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_128.add(string_literal435);

					pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3356);
					atomicNameFormula436=atomicNameFormula();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula436.getTree());
					char_literal437=(Token)match(input,70,FOLLOW_70_in_nameLiteral3358); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal437);

					// AST REWRITE
					// elements: atomicNameFormula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 508:36: -> ^( NOT_PRED_INIT atomicNameFormula )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:508:39: ^( NOT_PRED_INIT atomicNameFormula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT_PRED_INIT, "NOT_PRED_INIT"), root_1);
						adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 71, nameLiteral_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "nameLiteral"


	public static class atomicNameFormula_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomicNameFormula"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:511:1: atomicNameFormula : '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) ;
	public final PddlParser.atomicNameFormula_return atomicNameFormula() throws RecognitionException{
		PddlParser.atomicNameFormula_return retval = new PddlParser.atomicNameFormula_return();
		retval.start = input.LT(1);
		int atomicNameFormula_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal438=null;
		Token NAME440=null;
		Token char_literal441=null;
		ParserRuleReturnScope predicate439 =null;

		Object char_literal438_tree=null;
		Object NAME440_tree=null;
		Object char_literal441_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
		RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:2: ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:4: '(' predicate ( NAME )* ')'
			{
			char_literal438=(Token)match(input,69,FOLLOW_69_in_atomicNameFormula3377); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal438);

			pushFollow(FOLLOW_predicate_in_atomicNameFormula3379);
			predicate439=predicate();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_predicate.add(predicate439.getTree());
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:18: ( NAME )*
			loop77:
			while (true) {
				int alt77=2;
				int LA77_0 = input.LA(1);
				if ( (LA77_0==NAME) ) {
					alt77=1;
				}

				switch (alt77) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:18: NAME
					{
					NAME440=(Token)match(input,NAME,FOLLOW_NAME_in_atomicNameFormula3381); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NAME.add(NAME440);

					}
					break;

				default :
					break loop77;
				}
			}

			char_literal441=(Token)match(input,70,FOLLOW_70_in_atomicNameFormula3384); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal441);

			// AST REWRITE
			// elements: NAME, predicate
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 512:28: -> ^( PRED_INST predicate ( NAME )* )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:31: ^( PRED_INST predicate ( NAME )* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PRED_INST, "PRED_INST"), root_1);
				adaptor.addChild(root_1, stream_predicate.nextTree());
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:512:53: ( NAME )*
				while ( stream_NAME.hasNext() ) {
					adaptor.addChild(root_1, stream_NAME.nextNode());
				}
				stream_NAME.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 72, atomicNameFormula_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "atomicNameFormula"


	public static class goal_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "goal"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:519:1: goal : '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) ;
	public final PddlParser.goal_return goal() throws RecognitionException{
		PddlParser.goal_return retval = new PddlParser.goal_return();
		retval.start = input.LT(1);
		int goal_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal442=null;
		Token string_literal443=null;
		Token char_literal445=null;
		ParserRuleReturnScope goalDesc444 =null;

		Object char_literal442_tree=null;
		Object string_literal443_tree=null;
		Object char_literal445_tree=null;
		RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:519:6: ( '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:519:8: '(' ':goal' goalDesc ')'
			{
			char_literal442=(Token)match(input,69,FOLLOW_69_in_goal3409); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal442);

			string_literal443=(Token)match(input,88,FOLLOW_88_in_goal3411); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_88.add(string_literal443);

			pushFollow(FOLLOW_goalDesc_in_goal3413);
			goalDesc444=goalDesc();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc444.getTree());
			char_literal445=(Token)match(input,70,FOLLOW_70_in_goal3416); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal445);

			// AST REWRITE
			// elements: goalDesc
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 519:34: -> ^( GOAL goalDesc )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:519:37: ^( GOAL goalDesc )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(GOAL, "GOAL"), root_1);
				adaptor.addChild(root_1, stream_goalDesc.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 73, goal_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "goal"


	public static class probConstraints_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "probConstraints"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:521:1: probConstraints : '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) ;
	public final PddlParser.probConstraints_return probConstraints() throws RecognitionException{
		PddlParser.probConstraints_return retval = new PddlParser.probConstraints_return();
		retval.start = input.LT(1);
		int probConstraints_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal446=null;
		Token string_literal447=null;
		Token char_literal449=null;
		ParserRuleReturnScope prefConGD448 =null;

		Object char_literal446_tree=null;
		Object string_literal447_tree=null;
		Object char_literal449_tree=null;
		RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_prefConGD=new RewriteRuleSubtreeStream(adaptor,"rule prefConGD");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:522:2: ( '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:522:4: '(' ':constraints' prefConGD ')'
			{
			char_literal446=(Token)match(input,69,FOLLOW_69_in_probConstraints3434); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal446);

			string_literal447=(Token)match(input,79,FOLLOW_79_in_probConstraints3436); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_79.add(string_literal447);

			pushFollow(FOLLOW_prefConGD_in_probConstraints3439);
			prefConGD448=prefConGD();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_prefConGD.add(prefConGD448.getTree());
			char_literal449=(Token)match(input,70,FOLLOW_70_in_probConstraints3441); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal449);

			// AST REWRITE
			// elements: prefConGD
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 523:4: -> ^( PROBLEM_CONSTRAINT prefConGD )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:523:7: ^( PROBLEM_CONSTRAINT prefConGD )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROBLEM_CONSTRAINT, "PROBLEM_CONSTRAINT"), root_1);
				adaptor.addChild(root_1, stream_prefConGD.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 74, probConstraints_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "probConstraints"


	public static class prefConGD_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prefConGD"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:526:1: prefConGD : ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD );
	public final PddlParser.prefConGD_return prefConGD() throws RecognitionException{
		PddlParser.prefConGD_return retval = new PddlParser.prefConGD_return();
		retval.start = input.LT(1);
		int prefConGD_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal450=null;
		Token string_literal451=null;
		Token char_literal453=null;
		Token char_literal454=null;
		Token string_literal455=null;
		Token char_literal456=null;
		Token char_literal458=null;
		Token char_literal460=null;
		Token char_literal461=null;
		Token string_literal462=null;
		Token NAME463=null;
		Token char_literal465=null;
		ParserRuleReturnScope prefConGD452 =null;
		ParserRuleReturnScope typedVariableList457 =null;
		ParserRuleReturnScope prefConGD459 =null;
		ParserRuleReturnScope conGD464 =null;
		ParserRuleReturnScope conGD466 =null;

		Object char_literal450_tree=null;
		Object string_literal451_tree=null;
		Object char_literal453_tree=null;
		Object char_literal454_tree=null;
		Object string_literal455_tree=null;
		Object char_literal456_tree=null;
		Object char_literal458_tree=null;
		Object char_literal460_tree=null;
		Object char_literal461_tree=null;
		Object string_literal462_tree=null;
		Object NAME463_tree=null;
		Object char_literal465_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:2: ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD )
			int alt80=4;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==69) ) {
				int LA80_1 = input.LA(2);
				if ( (synpred128_Pddl()) ) {
					alt80=1;
				}
				else if ( (synpred129_Pddl()) ) {
					alt80=2;
				}
				else if ( (synpred131_Pddl()) ) {
					alt80=3;
				}
				else if ( (true) ) {
					alt80=4;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 80, 0, input);
				throw nvae;
			}

			switch (alt80) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:4: '(' 'and' ( prefConGD )* ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal450=(Token)match(input,69,FOLLOW_69_in_prefConGD3463); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal450_tree = (Object)adaptor.create(char_literal450);
					adaptor.addChild(root_0, char_literal450_tree);
					}

					string_literal451=(Token)match(input,109,FOLLOW_109_in_prefConGD3465); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal451_tree = (Object)adaptor.create(string_literal451);
					adaptor.addChild(root_0, string_literal451_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:14: ( prefConGD )*
					loop78:
					while (true) {
						int alt78=2;
						int LA78_0 = input.LA(1);
						if ( (LA78_0==69) ) {
							alt78=1;
						}

						switch (alt78) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:14: prefConGD
							{
							pushFollow(FOLLOW_prefConGD_in_prefConGD3467);
							prefConGD452=prefConGD();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, prefConGD452.getTree());

							}
							break;

						default :
							break loop78;
						}
					}

					char_literal453=(Token)match(input,70,FOLLOW_70_in_prefConGD3470); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal453_tree = (Object)adaptor.create(char_literal453);
					adaptor.addChild(root_0, char_literal453_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:528:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal454=(Token)match(input,69,FOLLOW_69_in_prefConGD3475); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal454_tree = (Object)adaptor.create(char_literal454);
					adaptor.addChild(root_0, char_literal454_tree);
					}

					string_literal455=(Token)match(input,120,FOLLOW_120_in_prefConGD3477); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal455_tree = (Object)adaptor.create(string_literal455);
					adaptor.addChild(root_0, string_literal455_tree);
					}

					char_literal456=(Token)match(input,69,FOLLOW_69_in_prefConGD3479); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal456_tree = (Object)adaptor.create(char_literal456);
					adaptor.addChild(root_0, char_literal456_tree);
					}

					pushFollow(FOLLOW_typedVariableList_in_prefConGD3481);
					typedVariableList457=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList457.getTree());

					char_literal458=(Token)match(input,70,FOLLOW_70_in_prefConGD3483); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal458_tree = (Object)adaptor.create(char_literal458);
					adaptor.addChild(root_0, char_literal458_tree);
					}

					pushFollow(FOLLOW_prefConGD_in_prefConGD3485);
					prefConGD459=prefConGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, prefConGD459.getTree());

					char_literal460=(Token)match(input,70,FOLLOW_70_in_prefConGD3487); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal460_tree = (Object)adaptor.create(char_literal460);
					adaptor.addChild(root_0, char_literal460_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:4: '(' 'preference' ( NAME )? conGD ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal461=(Token)match(input,69,FOLLOW_69_in_prefConGD3492); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal461_tree = (Object)adaptor.create(char_literal461);
					adaptor.addChild(root_0, char_literal461_tree);
					}

					string_literal462=(Token)match(input,133,FOLLOW_133_in_prefConGD3494); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal462_tree = (Object)adaptor.create(string_literal462);
					adaptor.addChild(root_0, string_literal462_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:21: ( NAME )?
					int alt79=2;
					int LA79_0 = input.LA(1);
					if ( (LA79_0==NAME) ) {
						alt79=1;
					}
					switch (alt79) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:21: NAME
							{
							NAME463=(Token)match(input,NAME,FOLLOW_NAME_in_prefConGD3496); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NAME463_tree = (Object)adaptor.create(NAME463);
							adaptor.addChild(root_0, NAME463_tree);
							}

							}
							break;

					}

					pushFollow(FOLLOW_conGD_in_prefConGD3499);
					conGD464=conGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD464.getTree());

					char_literal465=(Token)match(input,70,FOLLOW_70_in_prefConGD3501); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal465_tree = (Object)adaptor.create(char_literal465);
					adaptor.addChild(root_0, char_literal465_tree);
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:530:4: conGD
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_conGD_in_prefConGD3506);
					conGD466=conGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD466.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 75, prefConGD_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "prefConGD"


	public static class metricSpec_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "metricSpec"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:533:1: metricSpec : '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) ;
	public final PddlParser.metricSpec_return metricSpec() throws RecognitionException{
		PddlParser.metricSpec_return retval = new PddlParser.metricSpec_return();
		retval.start = input.LT(1);
		int metricSpec_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal467=null;
		Token string_literal468=null;
		Token char_literal471=null;
		ParserRuleReturnScope optimization469 =null;
		ParserRuleReturnScope metricFExp470 =null;

		Object char_literal467_tree=null;
		Object string_literal468_tree=null;
		Object char_literal471_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleSubtreeStream stream_metricFExp=new RewriteRuleSubtreeStream(adaptor,"rule metricFExp");
		RewriteRuleSubtreeStream stream_optimization=new RewriteRuleSubtreeStream(adaptor,"rule optimization");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:534:2: ( '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:534:4: '(' ':metric' optimization metricFExp ')'
			{
			char_literal467=(Token)match(input,69,FOLLOW_69_in_metricSpec3517); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_69.add(char_literal467);

			string_literal468=(Token)match(input,90,FOLLOW_90_in_metricSpec3519); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_90.add(string_literal468);

			pushFollow(FOLLOW_optimization_in_metricSpec3521);
			optimization469=optimization();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_optimization.add(optimization469.getTree());
			pushFollow(FOLLOW_metricFExp_in_metricSpec3523);
			metricFExp470=metricFExp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp470.getTree());
			char_literal471=(Token)match(input,70,FOLLOW_70_in_metricSpec3525); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_70.add(char_literal471);

			// AST REWRITE
			// elements: metricFExp, optimization
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 535:4: -> ^( PROBLEM_METRIC optimization metricFExp )
			{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:535:7: ^( PROBLEM_METRIC optimization metricFExp )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROBLEM_METRIC, "PROBLEM_METRIC"), root_1);
				adaptor.addChild(root_1, stream_optimization.nextTree());
				adaptor.addChild(root_1, stream_metricFExp.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 76, metricSpec_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "metricSpec"


	public static class optimization_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "optimization"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:538:1: optimization : ( 'minimize' | 'maximize' );
	public final PddlParser.optimization_return optimization() throws RecognitionException{
		PddlParser.optimization_return retval = new PddlParser.optimization_return();
		retval.start = input.LT(1);
		int optimization_StartIndex = input.index();

		Object root_0 = null;

		Token set472=null;

		Object set472_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:538:14: ( 'minimize' | 'maximize' )
			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:
			{
			root_0 = (Object)adaptor.nil();


			set472=input.LT(1);
			if ( (input.LA(1) >= 126 && input.LA(1) <= 127) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set472));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 77, optimization_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "optimization"


	public static class metricFExp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "metricFExp"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:540:1: metricFExp : ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' );
	public final PddlParser.metricFExp_return metricFExp() throws RecognitionException{
		PddlParser.metricFExp_return retval = new PddlParser.metricFExp_return();
		retval.start = input.LT(1);
		int metricFExp_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal473=null;
		Token char_literal477=null;
		Token char_literal478=null;
		Token char_literal482=null;
		Token char_literal483=null;
		Token char_literal484=null;
		Token char_literal486=null;
		Token NUMBER487=null;
		Token char_literal489=null;
		Token string_literal490=null;
		Token NAME491=null;
		Token char_literal492=null;
		ParserRuleReturnScope binaryOp474 =null;
		ParserRuleReturnScope metricFExp475 =null;
		ParserRuleReturnScope metricFExp476 =null;
		ParserRuleReturnScope multiOp479 =null;
		ParserRuleReturnScope metricFExp480 =null;
		ParserRuleReturnScope metricFExp481 =null;
		ParserRuleReturnScope metricFExp485 =null;
		ParserRuleReturnScope fHead488 =null;

		Object char_literal473_tree=null;
		Object char_literal477_tree=null;
		Object char_literal478_tree=null;
		Object char_literal482_tree=null;
		Object char_literal483_tree=null;
		Object char_literal484_tree=null;
		Object char_literal486_tree=null;
		Object NUMBER487_tree=null;
		Object char_literal489_tree=null;
		Object string_literal490_tree=null;
		Object NAME491_tree=null;
		Object char_literal492_tree=null;
		RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
		RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
		RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
		RewriteRuleSubtreeStream stream_binaryOp=new RewriteRuleSubtreeStream(adaptor,"rule binaryOp");
		RewriteRuleSubtreeStream stream_metricFExp=new RewriteRuleSubtreeStream(adaptor,"rule metricFExp");
		RewriteRuleSubtreeStream stream_multiOp=new RewriteRuleSubtreeStream(adaptor,"rule multiOp");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:541:2: ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' )
			int alt82=6;
			switch ( input.LA(1) ) {
			case 69:
				{
				int LA82_1 = input.LA(2);
				if ( (synpred133_Pddl()) ) {
					alt82=1;
				}
				else if ( (synpred135_Pddl()) ) {
					alt82=2;
				}
				else if ( (synpred136_Pddl()) ) {
					alt82=3;
				}
				else if ( (synpred138_Pddl()) ) {
					alt82=5;
				}
				else if ( (true) ) {
					alt82=6;
				}

				}
				break;
			case NUMBER:
				{
				alt82=4;
				}
				break;
			case NAME:
			case 68:
				{
				alt82=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 82, 0, input);
				throw nvae;
			}
			switch (alt82) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:541:4: '(' binaryOp metricFExp metricFExp ')'
					{
					char_literal473=(Token)match(input,69,FOLLOW_69_in_metricFExp3562); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal473);

					pushFollow(FOLLOW_binaryOp_in_metricFExp3564);
					binaryOp474=binaryOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binaryOp.add(binaryOp474.getTree());
					pushFollow(FOLLOW_metricFExp_in_metricFExp3566);
					metricFExp475=metricFExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp475.getTree());
					pushFollow(FOLLOW_metricFExp_in_metricFExp3568);
					metricFExp476=metricFExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp476.getTree());
					char_literal477=(Token)match(input,70,FOLLOW_70_in_metricFExp3570); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal477);

					// AST REWRITE
					// elements: binaryOp, metricFExp, metricFExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 542:6: -> ^( BINARY_OP binaryOp metricFExp metricFExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:542:9: ^( BINARY_OP binaryOp metricFExp metricFExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BINARY_OP, "BINARY_OP"), root_1);
						adaptor.addChild(root_1, stream_binaryOp.nextTree());
						adaptor.addChild(root_1, stream_metricFExp.nextTree());
						adaptor.addChild(root_1, stream_metricFExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:4: '(' multiOp metricFExp ( metricFExp )+ ')'
					{
					char_literal478=(Token)match(input,69,FOLLOW_69_in_metricFExp3592); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal478);

					pushFollow(FOLLOW_multiOp_in_metricFExp3594);
					multiOp479=multiOp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_multiOp.add(multiOp479.getTree());
					pushFollow(FOLLOW_metricFExp_in_metricFExp3596);
					metricFExp480=metricFExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp480.getTree());
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:27: ( metricFExp )+
					int cnt81=0;
					loop81:
					while (true) {
						int alt81=2;
						int LA81_0 = input.LA(1);
						if ( (LA81_0==NAME||LA81_0==NUMBER||(LA81_0 >= 68 && LA81_0 <= 69)) ) {
							alt81=1;
						}

						switch (alt81) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:27: metricFExp
							{
							pushFollow(FOLLOW_metricFExp_in_metricFExp3598);
							metricFExp481=metricFExp();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp481.getTree());
							}
							break;

						default :
							if ( cnt81 >= 1 ) break loop81;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(81, input);
							throw eee;
						}
						cnt81++;
					}

					char_literal482=(Token)match(input,70,FOLLOW_70_in_metricFExp3601); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal482);

					// AST REWRITE
					// elements: metricFExp, metricFExp, multiOp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 544:7: -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:544:10: ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MULTI_OP, "MULTI_OP"), root_1);
						adaptor.addChild(root_1, stream_multiOp.nextTree());
						adaptor.addChild(root_1, stream_metricFExp.nextTree());
						if ( !(stream_metricFExp.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_metricFExp.hasNext() ) {
							adaptor.addChild(root_1, stream_metricFExp.nextTree());
						}
						stream_metricFExp.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:545:4: '(' '-' metricFExp ')'
					{
					char_literal483=(Token)match(input,69,FOLLOW_69_in_metricFExp3625); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_69.add(char_literal483);

					char_literal484=(Token)match(input,73,FOLLOW_73_in_metricFExp3627); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_73.add(char_literal484);

					pushFollow(FOLLOW_metricFExp_in_metricFExp3629);
					metricFExp485=metricFExp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp485.getTree());
					char_literal486=(Token)match(input,70,FOLLOW_70_in_metricFExp3631); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_70.add(char_literal486);

					// AST REWRITE
					// elements: metricFExp
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 546:4: -> ^( MINUS_OP metricFExp )
					{
						// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:546:7: ^( MINUS_OP metricFExp )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS_OP, "MINUS_OP"), root_1);
						adaptor.addChild(root_1, stream_metricFExp.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:547:4: NUMBER
					{
					root_0 = (Object)adaptor.nil();


					NUMBER487=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_metricFExp3648); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER487_tree = (Object)adaptor.create(NUMBER487);
					adaptor.addChild(root_0, NUMBER487_tree);
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:548:4: fHead
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_fHead_in_metricFExp3653);
					fHead488=fHead();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead488.getTree());

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:550:4: '(' 'is-violated' NAME ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal489=(Token)match(input,69,FOLLOW_69_in_metricFExp3668); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal489_tree = (Object)adaptor.create(char_literal489);
					adaptor.addChild(root_0, char_literal489_tree);
					}

					string_literal490=(Token)match(input,125,FOLLOW_125_in_metricFExp3670); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal490_tree = (Object)adaptor.create(string_literal490);
					adaptor.addChild(root_0, string_literal490_tree);
					}

					NAME491=(Token)match(input,NAME,FOLLOW_NAME_in_metricFExp3672); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NAME491_tree = (Object)adaptor.create(NAME491);
					adaptor.addChild(root_0, NAME491_tree);
					}

					char_literal492=(Token)match(input,70,FOLLOW_70_in_metricFExp3674); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal492_tree = (Object)adaptor.create(char_literal492);
					adaptor.addChild(root_0, char_literal492_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 78, metricFExp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "metricFExp"


	public static class conGD_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "conGD"
	// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:555:1: conGD : ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' );
	public final PddlParser.conGD_return conGD() throws RecognitionException{
		PddlParser.conGD_return retval = new PddlParser.conGD_return();
		retval.start = input.LT(1);
		int conGD_StartIndex = input.index();

		Object root_0 = null;

		Token char_literal493=null;
		Token string_literal494=null;
		Token char_literal496=null;
		Token char_literal497=null;
		Token string_literal498=null;
		Token char_literal499=null;
		Token char_literal501=null;
		Token char_literal503=null;
		Token char_literal504=null;
		Token string_literal505=null;
		Token string_literal506=null;
		Token char_literal508=null;
		Token char_literal509=null;
		Token string_literal510=null;
		Token char_literal512=null;
		Token char_literal513=null;
		Token string_literal514=null;
		Token char_literal516=null;
		Token char_literal517=null;
		Token string_literal518=null;
		Token NUMBER519=null;
		Token char_literal521=null;
		Token char_literal522=null;
		Token string_literal523=null;
		Token char_literal525=null;
		Token char_literal526=null;
		Token string_literal527=null;
		Token char_literal530=null;
		Token char_literal531=null;
		Token string_literal532=null;
		Token char_literal535=null;
		Token char_literal536=null;
		Token string_literal537=null;
		Token NUMBER538=null;
		Token char_literal541=null;
		Token char_literal542=null;
		Token string_literal543=null;
		Token NUMBER544=null;
		Token NUMBER545=null;
		Token char_literal547=null;
		Token char_literal548=null;
		Token string_literal549=null;
		Token NUMBER550=null;
		Token char_literal552=null;
		ParserRuleReturnScope conGD495 =null;
		ParserRuleReturnScope typedVariableList500 =null;
		ParserRuleReturnScope conGD502 =null;
		ParserRuleReturnScope goalDesc507 =null;
		ParserRuleReturnScope goalDesc511 =null;
		ParserRuleReturnScope goalDesc515 =null;
		ParserRuleReturnScope goalDesc520 =null;
		ParserRuleReturnScope goalDesc524 =null;
		ParserRuleReturnScope goalDesc528 =null;
		ParserRuleReturnScope goalDesc529 =null;
		ParserRuleReturnScope goalDesc533 =null;
		ParserRuleReturnScope goalDesc534 =null;
		ParserRuleReturnScope goalDesc539 =null;
		ParserRuleReturnScope goalDesc540 =null;
		ParserRuleReturnScope goalDesc546 =null;
		ParserRuleReturnScope goalDesc551 =null;

		Object char_literal493_tree=null;
		Object string_literal494_tree=null;
		Object char_literal496_tree=null;
		Object char_literal497_tree=null;
		Object string_literal498_tree=null;
		Object char_literal499_tree=null;
		Object char_literal501_tree=null;
		Object char_literal503_tree=null;
		Object char_literal504_tree=null;
		Object string_literal505_tree=null;
		Object string_literal506_tree=null;
		Object char_literal508_tree=null;
		Object char_literal509_tree=null;
		Object string_literal510_tree=null;
		Object char_literal512_tree=null;
		Object char_literal513_tree=null;
		Object string_literal514_tree=null;
		Object char_literal516_tree=null;
		Object char_literal517_tree=null;
		Object string_literal518_tree=null;
		Object NUMBER519_tree=null;
		Object char_literal521_tree=null;
		Object char_literal522_tree=null;
		Object string_literal523_tree=null;
		Object char_literal525_tree=null;
		Object char_literal526_tree=null;
		Object string_literal527_tree=null;
		Object char_literal530_tree=null;
		Object char_literal531_tree=null;
		Object string_literal532_tree=null;
		Object char_literal535_tree=null;
		Object char_literal536_tree=null;
		Object string_literal537_tree=null;
		Object NUMBER538_tree=null;
		Object char_literal541_tree=null;
		Object char_literal542_tree=null;
		Object string_literal543_tree=null;
		Object NUMBER544_tree=null;
		Object NUMBER545_tree=null;
		Object char_literal547_tree=null;
		Object char_literal548_tree=null;
		Object string_literal549_tree=null;
		Object NUMBER550_tree=null;
		Object char_literal552_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }

			// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:556:2: ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' )
			int alt84=12;
			int LA84_0 = input.LA(1);
			if ( (LA84_0==69) ) {
				switch ( input.LA(2) ) {
				case 109:
					{
					alt84=1;
					}
					break;
				case 120:
					{
					alt84=2;
					}
					break;
				case 111:
					{
					alt84=3;
					}
					break;
				case 107:
					{
					alt84=4;
					}
					break;
				case 138:
					{
					alt84=5;
					}
					break;
				case 144:
					{
					alt84=6;
					}
					break;
				case 112:
					{
					alt84=7;
					}
					break;
				case 139:
					{
					alt84=8;
					}
					break;
				case 140:
					{
					alt84=9;
					}
					break;
				case 108:
					{
					alt84=10;
					}
					break;
				case 122:
					{
					alt84=11;
					}
					break;
				case 121:
					{
					alt84=12;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 84, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 84, 0, input);
				throw nvae;
			}

			switch (alt84) {
				case 1 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:556:4: '(' 'and' ( conGD )* ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal493=(Token)match(input,69,FOLLOW_69_in_conGD3688); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal493_tree = (Object)adaptor.create(char_literal493);
					adaptor.addChild(root_0, char_literal493_tree);
					}

					string_literal494=(Token)match(input,109,FOLLOW_109_in_conGD3690); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal494_tree = (Object)adaptor.create(string_literal494);
					adaptor.addChild(root_0, string_literal494_tree);
					}

					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:556:14: ( conGD )*
					loop83:
					while (true) {
						int alt83=2;
						int LA83_0 = input.LA(1);
						if ( (LA83_0==69) ) {
							alt83=1;
						}

						switch (alt83) {
						case 1 :
							// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:556:14: conGD
							{
							pushFollow(FOLLOW_conGD_in_conGD3692);
							conGD495=conGD();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD495.getTree());

							}
							break;

						default :
							break loop83;
						}
					}

					char_literal496=(Token)match(input,70,FOLLOW_70_in_conGD3695); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal496_tree = (Object)adaptor.create(char_literal496);
					adaptor.addChild(root_0, char_literal496_tree);
					}

					}
					break;
				case 2 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:557:4: '(' 'forall' '(' typedVariableList ')' conGD ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal497=(Token)match(input,69,FOLLOW_69_in_conGD3700); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal497_tree = (Object)adaptor.create(char_literal497);
					adaptor.addChild(root_0, char_literal497_tree);
					}

					string_literal498=(Token)match(input,120,FOLLOW_120_in_conGD3702); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal498_tree = (Object)adaptor.create(string_literal498);
					adaptor.addChild(root_0, string_literal498_tree);
					}

					char_literal499=(Token)match(input,69,FOLLOW_69_in_conGD3704); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal499_tree = (Object)adaptor.create(char_literal499);
					adaptor.addChild(root_0, char_literal499_tree);
					}

					pushFollow(FOLLOW_typedVariableList_in_conGD3706);
					typedVariableList500=typedVariableList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList500.getTree());

					char_literal501=(Token)match(input,70,FOLLOW_70_in_conGD3708); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal501_tree = (Object)adaptor.create(char_literal501);
					adaptor.addChild(root_0, char_literal501_tree);
					}

					pushFollow(FOLLOW_conGD_in_conGD3710);
					conGD502=conGD();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD502.getTree());

					char_literal503=(Token)match(input,70,FOLLOW_70_in_conGD3712); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal503_tree = (Object)adaptor.create(char_literal503);
					adaptor.addChild(root_0, char_literal503_tree);
					}

					}
					break;
				case 3 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:558:4: '(' 'at' 'end' goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal504=(Token)match(input,69,FOLLOW_69_in_conGD3717); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal504_tree = (Object)adaptor.create(char_literal504);
					adaptor.addChild(root_0, char_literal504_tree);
					}

					string_literal505=(Token)match(input,111,FOLLOW_111_in_conGD3719); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal505_tree = (Object)adaptor.create(string_literal505);
					adaptor.addChild(root_0, string_literal505_tree);
					}

					string_literal506=(Token)match(input,118,FOLLOW_118_in_conGD3721); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal506_tree = (Object)adaptor.create(string_literal506);
					adaptor.addChild(root_0, string_literal506_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3723);
					goalDesc507=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc507.getTree());

					char_literal508=(Token)match(input,70,FOLLOW_70_in_conGD3725); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal508_tree = (Object)adaptor.create(char_literal508);
					adaptor.addChild(root_0, char_literal508_tree);
					}

					}
					break;
				case 4 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:559:25: '(' 'always' goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal509=(Token)match(input,69,FOLLOW_69_in_conGD3751); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal509_tree = (Object)adaptor.create(char_literal509);
					adaptor.addChild(root_0, char_literal509_tree);
					}

					string_literal510=(Token)match(input,107,FOLLOW_107_in_conGD3753); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal510_tree = (Object)adaptor.create(string_literal510);
					adaptor.addChild(root_0, string_literal510_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3755);
					goalDesc511=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc511.getTree());

					char_literal512=(Token)match(input,70,FOLLOW_70_in_conGD3757); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal512_tree = (Object)adaptor.create(char_literal512);
					adaptor.addChild(root_0, char_literal512_tree);
					}

					}
					break;
				case 5 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:560:4: '(' 'sometime' goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal513=(Token)match(input,69,FOLLOW_69_in_conGD3762); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal513_tree = (Object)adaptor.create(char_literal513);
					adaptor.addChild(root_0, char_literal513_tree);
					}

					string_literal514=(Token)match(input,138,FOLLOW_138_in_conGD3764); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal514_tree = (Object)adaptor.create(string_literal514);
					adaptor.addChild(root_0, string_literal514_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3766);
					goalDesc515=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc515.getTree());

					char_literal516=(Token)match(input,70,FOLLOW_70_in_conGD3768); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal516_tree = (Object)adaptor.create(char_literal516);
					adaptor.addChild(root_0, char_literal516_tree);
					}

					}
					break;
				case 6 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:561:5: '(' 'within' NUMBER goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal517=(Token)match(input,69,FOLLOW_69_in_conGD3774); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal517_tree = (Object)adaptor.create(char_literal517);
					adaptor.addChild(root_0, char_literal517_tree);
					}

					string_literal518=(Token)match(input,144,FOLLOW_144_in_conGD3776); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal518_tree = (Object)adaptor.create(string_literal518);
					adaptor.addChild(root_0, string_literal518_tree);
					}

					NUMBER519=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3778); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER519_tree = (Object)adaptor.create(NUMBER519);
					adaptor.addChild(root_0, NUMBER519_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3780);
					goalDesc520=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc520.getTree());

					char_literal521=(Token)match(input,70,FOLLOW_70_in_conGD3782); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal521_tree = (Object)adaptor.create(char_literal521);
					adaptor.addChild(root_0, char_literal521_tree);
					}

					}
					break;
				case 7 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:562:4: '(' 'at-most-once' goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal522=(Token)match(input,69,FOLLOW_69_in_conGD3787); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal522_tree = (Object)adaptor.create(char_literal522);
					adaptor.addChild(root_0, char_literal522_tree);
					}

					string_literal523=(Token)match(input,112,FOLLOW_112_in_conGD3789); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal523_tree = (Object)adaptor.create(string_literal523);
					adaptor.addChild(root_0, string_literal523_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3791);
					goalDesc524=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc524.getTree());

					char_literal525=(Token)match(input,70,FOLLOW_70_in_conGD3793); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal525_tree = (Object)adaptor.create(char_literal525);
					adaptor.addChild(root_0, char_literal525_tree);
					}

					}
					break;
				case 8 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:563:4: '(' 'sometime-after' goalDesc goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal526=(Token)match(input,69,FOLLOW_69_in_conGD3798); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal526_tree = (Object)adaptor.create(char_literal526);
					adaptor.addChild(root_0, char_literal526_tree);
					}

					string_literal527=(Token)match(input,139,FOLLOW_139_in_conGD3800); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal527_tree = (Object)adaptor.create(string_literal527);
					adaptor.addChild(root_0, string_literal527_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3802);
					goalDesc528=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc528.getTree());

					pushFollow(FOLLOW_goalDesc_in_conGD3804);
					goalDesc529=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc529.getTree());

					char_literal530=(Token)match(input,70,FOLLOW_70_in_conGD3806); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal530_tree = (Object)adaptor.create(char_literal530);
					adaptor.addChild(root_0, char_literal530_tree);
					}

					}
					break;
				case 9 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:564:4: '(' 'sometime-before' goalDesc goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal531=(Token)match(input,69,FOLLOW_69_in_conGD3811); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal531_tree = (Object)adaptor.create(char_literal531);
					adaptor.addChild(root_0, char_literal531_tree);
					}

					string_literal532=(Token)match(input,140,FOLLOW_140_in_conGD3813); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal532_tree = (Object)adaptor.create(string_literal532);
					adaptor.addChild(root_0, string_literal532_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3815);
					goalDesc533=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc533.getTree());

					pushFollow(FOLLOW_goalDesc_in_conGD3817);
					goalDesc534=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc534.getTree());

					char_literal535=(Token)match(input,70,FOLLOW_70_in_conGD3819); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal535_tree = (Object)adaptor.create(char_literal535);
					adaptor.addChild(root_0, char_literal535_tree);
					}

					}
					break;
				case 10 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:565:4: '(' 'always-within' NUMBER goalDesc goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal536=(Token)match(input,69,FOLLOW_69_in_conGD3824); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal536_tree = (Object)adaptor.create(char_literal536);
					adaptor.addChild(root_0, char_literal536_tree);
					}

					string_literal537=(Token)match(input,108,FOLLOW_108_in_conGD3826); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal537_tree = (Object)adaptor.create(string_literal537);
					adaptor.addChild(root_0, string_literal537_tree);
					}

					NUMBER538=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3828); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER538_tree = (Object)adaptor.create(NUMBER538);
					adaptor.addChild(root_0, NUMBER538_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3830);
					goalDesc539=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc539.getTree());

					pushFollow(FOLLOW_goalDesc_in_conGD3832);
					goalDesc540=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc540.getTree());

					char_literal541=(Token)match(input,70,FOLLOW_70_in_conGD3834); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal541_tree = (Object)adaptor.create(char_literal541);
					adaptor.addChild(root_0, char_literal541_tree);
					}

					}
					break;
				case 11 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:566:4: '(' 'hold-during' NUMBER NUMBER goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal542=(Token)match(input,69,FOLLOW_69_in_conGD3839); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal542_tree = (Object)adaptor.create(char_literal542);
					adaptor.addChild(root_0, char_literal542_tree);
					}

					string_literal543=(Token)match(input,122,FOLLOW_122_in_conGD3841); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal543_tree = (Object)adaptor.create(string_literal543);
					adaptor.addChild(root_0, string_literal543_tree);
					}

					NUMBER544=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3843); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER544_tree = (Object)adaptor.create(NUMBER544);
					adaptor.addChild(root_0, NUMBER544_tree);
					}

					NUMBER545=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3845); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER545_tree = (Object)adaptor.create(NUMBER545);
					adaptor.addChild(root_0, NUMBER545_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3847);
					goalDesc546=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc546.getTree());

					char_literal547=(Token)match(input,70,FOLLOW_70_in_conGD3849); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal547_tree = (Object)adaptor.create(char_literal547);
					adaptor.addChild(root_0, char_literal547_tree);
					}

					}
					break;
				case 12 :
					// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:567:4: '(' 'hold-after' NUMBER goalDesc ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal548=(Token)match(input,69,FOLLOW_69_in_conGD3854); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal548_tree = (Object)adaptor.create(char_literal548);
					adaptor.addChild(root_0, char_literal548_tree);
					}

					string_literal549=(Token)match(input,121,FOLLOW_121_in_conGD3856); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					string_literal549_tree = (Object)adaptor.create(string_literal549);
					adaptor.addChild(root_0, string_literal549_tree);
					}

					NUMBER550=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3858); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NUMBER550_tree = (Object)adaptor.create(NUMBER550);
					adaptor.addChild(root_0, NUMBER550_tree);
					}

					pushFollow(FOLLOW_goalDesc_in_conGD3860);
					goalDesc551=goalDesc();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc551.getTree());

					char_literal552=(Token)match(input,70,FOLLOW_70_in_conGD3862); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal552_tree = (Object)adaptor.create(char_literal552);
					adaptor.addChild(root_0, char_literal552_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 79, conGD_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "conGD"

	// $ANTLR start synpred18_Pddl
	public final void synpred18_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:5: ( atomicFunctionSkeleton )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:169:5: atomicFunctionSkeleton
		{
		pushFollow(FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl848);
		atomicFunctionSkeleton();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred18_Pddl

	// $ANTLR start synpred51_Pddl
	public final void synpred51_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:299:7: ( fComp )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:299:7: fComp
		{
		pushFollow(FOLLOW_fComp_in_synpred51_Pddl1802);
		fComp();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred51_Pddl

	// $ANTLR start synpred54_Pddl
	public final void synpred54_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:322:28: ( typedVariableList )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:322:28: typedVariableList
		{
		pushFollow(FOLLOW_typedVariableList_in_synpred54_Pddl1961);
		typedVariableList();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred54_Pddl

	// $ANTLR start synpred67_Pddl
	public final void synpred67_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:362:4: ( '(' binaryOp fExp fExp2 ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:362:4: '(' binaryOp fExp fExp2 ')'
		{
		match(input,69,FOLLOW_69_in_synpred67_Pddl2217); if (state.failed) return;
		pushFollow(FOLLOW_binaryOp_in_synpred67_Pddl2219);
		binaryOp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_fExp_in_synpred67_Pddl2221);
		fExp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_fExp2_in_synpred67_Pddl2223);
		fExp2();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred67_Pddl2225); if (state.failed) return;
		}

	}
	// $ANTLR end synpred67_Pddl

	// $ANTLR start synpred68_Pddl
	public final void synpred68_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:363:4: ( '(' '-' fExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:363:4: '(' '-' fExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred68_Pddl2242); if (state.failed) return;
		match(input,73,FOLLOW_73_in_synpred68_Pddl2244); if (state.failed) return;
		pushFollow(FOLLOW_fExp_in_synpred68_Pddl2246);
		fExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred68_Pddl2248); if (state.failed) return;
		}

	}
	// $ANTLR end synpred68_Pddl

	// $ANTLR start synpred69_Pddl
	public final void synpred69_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:364:4: ( '(' 'sin' fExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:364:4: '(' 'sin' fExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred69_Pddl2261); if (state.failed) return;
		match(input,137,FOLLOW_137_in_synpred69_Pddl2263); if (state.failed) return;
		pushFollow(FOLLOW_fExp_in_synpred69_Pddl2265);
		fExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred69_Pddl2267); if (state.failed) return;
		}

	}
	// $ANTLR end synpred69_Pddl

	// $ANTLR start synpred70_Pddl
	public final void synpred70_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:365:4: ( '(' 'cos' fExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:365:4: '(' 'cos' fExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred70_Pddl2280); if (state.failed) return;
		match(input,113,FOLLOW_113_in_synpred70_Pddl2282); if (state.failed) return;
		pushFollow(FOLLOW_fExp_in_synpred70_Pddl2284);
		fExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred70_Pddl2286); if (state.failed) return;
		}

	}
	// $ANTLR end synpred70_Pddl

	// $ANTLR start synpred71_Pddl
	public final void synpred71_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:366:4: ( '(' 'abs' fExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:366:4: '(' 'abs' fExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred71_Pddl2299); if (state.failed) return;
		match(input,105,FOLLOW_105_in_synpred71_Pddl2301); if (state.failed) return;
		pushFollow(FOLLOW_fExp_in_synpred71_Pddl2303);
		fExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred71_Pddl2305); if (state.failed) return;
		}

	}
	// $ANTLR end synpred71_Pddl

	// $ANTLR start synpred101_Pddl
	public final void synpred101_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:12: ( NUMBER )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:433:12: NUMBER
		{
		match(input,NUMBER,FOLLOW_NUMBER_in_synpred101_Pddl2748); if (state.failed) return;
		}

	}
	// $ANTLR end synpred101_Pddl

	// $ANTLR start synpred103_Pddl
	public final void synpred103_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:4: ( '(' 'and' ( daEffect )* ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:4: '(' 'and' ( daEffect )* ')'
		{
		match(input,69,FOLLOW_69_in_synpred103_Pddl2762); if (state.failed) return;
		match(input,109,FOLLOW_109_in_synpred103_Pddl2764); if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:14: ( daEffect )*
		loop102:
		while (true) {
			int alt102=2;
			int LA102_0 = input.LA(1);
			if ( (LA102_0==69) ) {
				alt102=1;
			}

			switch (alt102) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:436:14: daEffect
				{
				pushFollow(FOLLOW_daEffect_in_synpred103_Pddl2766);
				daEffect();
				state._fsp--;
				if (state.failed) return;
				}
				break;

			default :
				break loop102;
			}
		}

		match(input,70,FOLLOW_70_in_synpred103_Pddl2769); if (state.failed) return;
		}

	}
	// $ANTLR end synpred103_Pddl

	// $ANTLR start synpred104_Pddl
	public final void synpred104_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:437:4: ( timedEffect )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:437:4: timedEffect
		{
		pushFollow(FOLLOW_timedEffect_in_synpred104_Pddl2774);
		timedEffect();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred104_Pddl

	// $ANTLR start synpred105_Pddl
	public final void synpred105_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:438:4: ( '(' 'forall' '(' typedVariableList ')' daEffect ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:438:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
		{
		match(input,69,FOLLOW_69_in_synpred105_Pddl2779); if (state.failed) return;
		match(input,120,FOLLOW_120_in_synpred105_Pddl2781); if (state.failed) return;
		match(input,69,FOLLOW_69_in_synpred105_Pddl2783); if (state.failed) return;
		pushFollow(FOLLOW_typedVariableList_in_synpred105_Pddl2785);
		typedVariableList();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred105_Pddl2787); if (state.failed) return;
		pushFollow(FOLLOW_daEffect_in_synpred105_Pddl2789);
		daEffect();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred105_Pddl2791); if (state.failed) return;
		}

	}
	// $ANTLR end synpred105_Pddl

	// $ANTLR start synpred106_Pddl
	public final void synpred106_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:439:4: ( '(' 'when' daGD timedEffect ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:439:4: '(' 'when' daGD timedEffect ')'
		{
		match(input,69,FOLLOW_69_in_synpred106_Pddl2796); if (state.failed) return;
		match(input,143,FOLLOW_143_in_synpred106_Pddl2798); if (state.failed) return;
		pushFollow(FOLLOW_daGD_in_synpred106_Pddl2800);
		daGD();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_timedEffect_in_synpred106_Pddl2802);
		timedEffect();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred106_Pddl2804); if (state.failed) return;
		}

	}
	// $ANTLR end synpred106_Pddl

	// $ANTLR start synpred107_Pddl
	public final void synpred107_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:444:4: ( '(' 'at' timeSpecifier daEffect ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:444:4: '(' 'at' timeSpecifier daEffect ')'
		{
		match(input,69,FOLLOW_69_in_synpred107_Pddl2828); if (state.failed) return;
		match(input,111,FOLLOW_111_in_synpred107_Pddl2830); if (state.failed) return;
		pushFollow(FOLLOW_timeSpecifier_in_synpred107_Pddl2832);
		timeSpecifier();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_daEffect_in_synpred107_Pddl2834);
		daEffect();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred107_Pddl2836); if (state.failed) return;
		}

	}
	// $ANTLR end synpred107_Pddl

	// $ANTLR start synpred108_Pddl
	public final void synpred108_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:445:4: ( '(' 'at' timeSpecifier fAssignDA ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:445:4: '(' 'at' timeSpecifier fAssignDA ')'
		{
		match(input,69,FOLLOW_69_in_synpred108_Pddl2846); if (state.failed) return;
		match(input,111,FOLLOW_111_in_synpred108_Pddl2848); if (state.failed) return;
		pushFollow(FOLLOW_timeSpecifier_in_synpred108_Pddl2850);
		timeSpecifier();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_fAssignDA_in_synpred108_Pddl2852);
		fAssignDA();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred108_Pddl2854); if (state.failed) return;
		}

	}
	// $ANTLR end synpred108_Pddl

	// $ANTLR start synpred109_Pddl
	public final void synpred109_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( ( binaryOp fExpDA fExpDA ) )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
		{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:10: binaryOp fExpDA fExpDA
		{
		pushFollow(FOLLOW_binaryOp_in_synpred109_Pddl2910);
		binaryOp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_fExpDA_in_synpred109_Pddl2912);
		fExpDA();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_fExpDA_in_synpred109_Pddl2914);
		fExpDA();
		state._fsp--;
		if (state.failed) return;
		}

		}

	}
	// $ANTLR end synpred109_Pddl

	// $ANTLR start synpred110_Pddl
	public final void synpred110_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:4: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
		{
		match(input,69,FOLLOW_69_in_synpred110_Pddl2906); if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
		int alt103=2;
		int LA103_0 = input.LA(1);
		if ( (LA103_0==73) ) {
			int LA103_1 = input.LA(2);
			if ( (synpred109_Pddl()) ) {
				alt103=1;
			}
			else if ( (true) ) {
				alt103=2;
			}

		}
		else if ( ((LA103_0 >= 71 && LA103_0 <= 72)||LA103_0==74||LA103_0==104) ) {
			alt103=1;
		}

		else {
			if (state.backtracking>0) {state.failed=true; return;}
			NoViableAltException nvae =
				new NoViableAltException("", 103, 0, input);
			throw nvae;
		}

		switch (alt103) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
				{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:9: ( binaryOp fExpDA fExpDA )
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:10: binaryOp fExpDA fExpDA
				{
				pushFollow(FOLLOW_binaryOp_in_synpred110_Pddl2910);
				binaryOp();
				state._fsp--;
				if (state.failed) return;
				pushFollow(FOLLOW_fExpDA_in_synpred110_Pddl2912);
				fExpDA();
				state._fsp--;
				if (state.failed) return;
				pushFollow(FOLLOW_fExpDA_in_synpred110_Pddl2914);
				fExpDA();
				state._fsp--;
				if (state.failed) return;
				}

				}
				break;
			case 2 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:36: ( '-' fExpDA )
				{
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:36: ( '-' fExpDA )
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:454:37: '-' fExpDA
				{
				match(input,73,FOLLOW_73_in_synpred110_Pddl2920); if (state.failed) return;
				pushFollow(FOLLOW_fExpDA_in_synpred110_Pddl2922);
				fExpDA();
				state._fsp--;
				if (state.failed) return;
				}

				}
				break;

		}

		match(input,70,FOLLOW_70_in_synpred110_Pddl2926); if (state.failed) return;
		}

	}
	// $ANTLR end synpred110_Pddl

	// $ANTLR start synpred117_Pddl
	public final void synpred117_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:4: ( '(' ':init' ( initEl )* ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:4: '(' ':init' ( initEl )* ')'
		{
		match(input,69,FOLLOW_69_in_synpred117_Pddl3167); if (state.failed) return;
		match(input,89,FOLLOW_89_in_synpred117_Pddl3169); if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:16: ( initEl )*
		loop104:
		while (true) {
			int alt104=2;
			int LA104_0 = input.LA(1);
			if ( (LA104_0==69) ) {
				alt104=1;
			}

			switch (alt104) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:492:16: initEl
				{
				pushFollow(FOLLOW_initEl_in_synpred117_Pddl3171);
				initEl();
				state._fsp--;
				if (state.failed) return;
				}
				break;

			default :
				break loop104;
			}
		}

		match(input,70,FOLLOW_70_in_synpred117_Pddl3174); if (state.failed) return;
		}

	}
	// $ANTLR end synpred117_Pddl

	// $ANTLR start synpred128_Pddl
	public final void synpred128_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:4: ( '(' 'and' ( prefConGD )* ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:4: '(' 'and' ( prefConGD )* ')'
		{
		match(input,69,FOLLOW_69_in_synpred128_Pddl3463); if (state.failed) return;
		match(input,109,FOLLOW_109_in_synpred128_Pddl3465); if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:14: ( prefConGD )*
		loop106:
		while (true) {
			int alt106=2;
			int LA106_0 = input.LA(1);
			if ( (LA106_0==69) ) {
				alt106=1;
			}

			switch (alt106) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:527:14: prefConGD
				{
				pushFollow(FOLLOW_prefConGD_in_synpred128_Pddl3467);
				prefConGD();
				state._fsp--;
				if (state.failed) return;
				}
				break;

			default :
				break loop106;
			}
		}

		match(input,70,FOLLOW_70_in_synpred128_Pddl3470); if (state.failed) return;
		}

	}
	// $ANTLR end synpred128_Pddl

	// $ANTLR start synpred129_Pddl
	public final void synpred129_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:528:4: ( '(' 'forall' '(' typedVariableList ')' prefConGD ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:528:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
		{
		match(input,69,FOLLOW_69_in_synpred129_Pddl3475); if (state.failed) return;
		match(input,120,FOLLOW_120_in_synpred129_Pddl3477); if (state.failed) return;
		match(input,69,FOLLOW_69_in_synpred129_Pddl3479); if (state.failed) return;
		pushFollow(FOLLOW_typedVariableList_in_synpred129_Pddl3481);
		typedVariableList();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred129_Pddl3483); if (state.failed) return;
		pushFollow(FOLLOW_prefConGD_in_synpred129_Pddl3485);
		prefConGD();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred129_Pddl3487); if (state.failed) return;
		}

	}
	// $ANTLR end synpred129_Pddl

	// $ANTLR start synpred131_Pddl
	public final void synpred131_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:4: ( '(' 'preference' ( NAME )? conGD ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:4: '(' 'preference' ( NAME )? conGD ')'
		{
		match(input,69,FOLLOW_69_in_synpred131_Pddl3492); if (state.failed) return;
		match(input,133,FOLLOW_133_in_synpred131_Pddl3494); if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:21: ( NAME )?
		int alt107=2;
		int LA107_0 = input.LA(1);
		if ( (LA107_0==NAME) ) {
			alt107=1;
		}
		switch (alt107) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:529:21: NAME
				{
				match(input,NAME,FOLLOW_NAME_in_synpred131_Pddl3496); if (state.failed) return;
				}
				break;

		}

		pushFollow(FOLLOW_conGD_in_synpred131_Pddl3499);
		conGD();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred131_Pddl3501); if (state.failed) return;
		}

	}
	// $ANTLR end synpred131_Pddl

	// $ANTLR start synpred133_Pddl
	public final void synpred133_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:541:4: ( '(' binaryOp metricFExp metricFExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:541:4: '(' binaryOp metricFExp metricFExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred133_Pddl3562); if (state.failed) return;
		pushFollow(FOLLOW_binaryOp_in_synpred133_Pddl3564);
		binaryOp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_metricFExp_in_synpred133_Pddl3566);
		metricFExp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_metricFExp_in_synpred133_Pddl3568);
		metricFExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred133_Pddl3570); if (state.failed) return;
		}

	}
	// $ANTLR end synpred133_Pddl

	// $ANTLR start synpred135_Pddl
	public final void synpred135_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:4: ( '(' multiOp metricFExp ( metricFExp )+ ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:4: '(' multiOp metricFExp ( metricFExp )+ ')'
		{
		match(input,69,FOLLOW_69_in_synpred135_Pddl3592); if (state.failed) return;
		pushFollow(FOLLOW_multiOp_in_synpred135_Pddl3594);
		multiOp();
		state._fsp--;
		if (state.failed) return;
		pushFollow(FOLLOW_metricFExp_in_synpred135_Pddl3596);
		metricFExp();
		state._fsp--;
		if (state.failed) return;
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:27: ( metricFExp )+
		int cnt108=0;
		loop108:
		while (true) {
			int alt108=2;
			int LA108_0 = input.LA(1);
			if ( (LA108_0==NAME||LA108_0==NUMBER||(LA108_0 >= 68 && LA108_0 <= 69)) ) {
				alt108=1;
			}

			switch (alt108) {
			case 1 :
				// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:543:27: metricFExp
				{
				pushFollow(FOLLOW_metricFExp_in_synpred135_Pddl3598);
				metricFExp();
				state._fsp--;
				if (state.failed) return;
				}
				break;

			default :
				if ( cnt108 >= 1 ) break loop108;
				if (state.backtracking>0) {state.failed=true; return;}
				EarlyExitException eee = new EarlyExitException(108, input);
				throw eee;
			}
			cnt108++;
		}

		match(input,70,FOLLOW_70_in_synpred135_Pddl3601); if (state.failed) return;
		}

	}
	// $ANTLR end synpred135_Pddl

	// $ANTLR start synpred136_Pddl
	public final void synpred136_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:545:4: ( '(' '-' metricFExp ')' )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:545:4: '(' '-' metricFExp ')'
		{
		match(input,69,FOLLOW_69_in_synpred136_Pddl3625); if (state.failed) return;
		match(input,73,FOLLOW_73_in_synpred136_Pddl3627); if (state.failed) return;
		pushFollow(FOLLOW_metricFExp_in_synpred136_Pddl3629);
		metricFExp();
		state._fsp--;
		if (state.failed) return;
		match(input,70,FOLLOW_70_in_synpred136_Pddl3631); if (state.failed) return;
		}

	}
	// $ANTLR end synpred136_Pddl

	// $ANTLR start synpred138_Pddl
	public final void synpred138_Pddl_fragment() throws RecognitionException{
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:548:4: ( fHead )
		// /home/enrico/Dropbox/plan_exec_2_0/PPMAJAL2/grammar/Pddl.g:548:4: fHead
		{
		pushFollow(FOLLOW_fHead_in_synpred138_Pddl3653);
		fHead();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred138_Pddl

	// Delegated rules

	public final boolean synpred129_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred129_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred71_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred71_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred109_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred109_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred101_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred101_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred106_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred106_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred135_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred135_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred54_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred54_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred138_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred138_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred51_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred51_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred103_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred103_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred104_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred104_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred131_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred131_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred107_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred107_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred69_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred69_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred108_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred108_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred105_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred105_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred136_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred136_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred70_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred70_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred133_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred133_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred68_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred68_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred117_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred117_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred18_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred18_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred67_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred67_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred128_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred128_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred110_Pddl() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred110_Pddl_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA14 dfa14 = new DFA14(this);
	protected DFA12 dfa12 = new DFA12(this);
	protected DFA25 dfa25 = new DFA25(this);
	protected DFA23 dfa23 = new DFA23(this);
	protected DFA72 dfa72 = new DFA72(this);
	static final String DFA14_eotS =
		"\4\uffff";
	static final String DFA14_eofS =
		"\4\uffff";
	static final String DFA14_minS =
		"\2\51\2\uffff";
	static final String DFA14_maxS =
		"\1\106\1\111\2\uffff";
	static final String DFA14_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA14_specialS =
		"\4\uffff}>";
	static final String[] DFA14_transitionS = {
			"\1\1\34\uffff\1\2",
			"\1\1\34\uffff\1\2\2\uffff\1\3",
			"",
			""
	};

	static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
	static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
	static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
	static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
	static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
	static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
	static final short[][] DFA14_transition;

	static {
		int numStates = DFA14_transitionS.length;
		DFA14_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
		}
	}

	protected class DFA14 extends DFA {

		public DFA14(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 14;
			this.eot = DFA14_eot;
			this.eof = DFA14_eof;
			this.min = DFA14_min;
			this.max = DFA14_max;
			this.accept = DFA14_accept;
			this.special = DFA14_special;
			this.transition = DFA14_transition;
		}
		@Override
		public String getDescription() {
			return "147:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )";
		}
	}

	static final String DFA12_eotS =
		"\4\uffff";
	static final String DFA12_eofS =
		"\4\uffff";
	static final String DFA12_minS =
		"\2\51\2\uffff";
	static final String DFA12_maxS =
		"\1\106\1\111\2\uffff";
	static final String DFA12_acceptS =
		"\2\uffff\1\2\1\1";
	static final String DFA12_specialS =
		"\4\uffff}>";
	static final String[] DFA12_transitionS = {
			"\1\1\34\uffff\1\2",
			"\1\1\34\uffff\1\2\2\uffff\1\3",
			"",
			""
	};

	static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
	static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
	static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
	static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
	static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
	static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
	static final short[][] DFA12_transition;

	static {
		int numStates = DFA12_transitionS.length;
		DFA12_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
		}
	}

	protected class DFA12 extends DFA {

		public DFA12(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 12;
			this.eot = DFA12_eot;
			this.eof = DFA12_eof;
			this.min = DFA12_min;
			this.max = DFA12_max;
			this.accept = DFA12_accept;
			this.special = DFA12_special;
			this.transition = DFA12_transition;
		}
		@Override
		public String getDescription() {
			return "()+ loopback of 147:16: ( singleTypeNameList )+";
		}
	}

	static final String DFA25_eotS =
		"\4\uffff";
	static final String DFA25_eofS =
		"\2\2\2\uffff";
	static final String DFA25_minS =
		"\2\101\2\uffff";
	static final String DFA25_maxS =
		"\1\106\1\111\2\uffff";
	static final String DFA25_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA25_specialS =
		"\4\uffff}>";
	static final String[] DFA25_transitionS = {
			"\1\1\3\uffff\2\2",
			"\1\1\3\uffff\2\2\2\uffff\1\3",
			"",
			""
	};

	static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
	static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
	static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
	static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
	static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
	static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
	static final short[][] DFA25_transition;

	static {
		int numStates = DFA25_transitionS.length;
		DFA25_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
		}
	}

	protected class DFA25 extends DFA {

		public DFA25(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 25;
			this.eot = DFA25_eot;
			this.eof = DFA25_eof;
			this.min = DFA25_min;
			this.max = DFA25_max;
			this.accept = DFA25_accept;
			this.special = DFA25_special;
			this.transition = DFA25_transition;
		}
		@Override
		public String getDescription() {
			return "198:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )";
		}
	}

	static final String DFA23_eotS =
		"\4\uffff";
	static final String DFA23_eofS =
		"\2\2\2\uffff";
	static final String DFA23_minS =
		"\2\101\2\uffff";
	static final String DFA23_maxS =
		"\1\106\1\111\2\uffff";
	static final String DFA23_acceptS =
		"\2\uffff\1\2\1\1";
	static final String DFA23_specialS =
		"\4\uffff}>";
	static final String[] DFA23_transitionS = {
			"\1\1\3\uffff\2\2",
			"\1\1\3\uffff\2\2\2\uffff\1\3",
			"",
			""
	};

	static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
	static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
	static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
	static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
	static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
	static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
	static final short[][] DFA23_transition;

	static {
		int numStates = DFA23_transitionS.length;
		DFA23_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
		}
	}

	protected class DFA23 extends DFA {

		public DFA23(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 23;
			this.eot = DFA23_eot;
			this.eof = DFA23_eof;
			this.min = DFA23_min;
			this.max = DFA23_max;
			this.accept = DFA23_accept;
			this.special = DFA23_special;
			this.transition = DFA23_transition;
		}
		@Override
		public String getDescription() {
			return "()+ loopback of 198:20: ( singleTypeVarList )+";
		}
	}

	static final String DFA72_eotS =
		"\155\uffff";
	static final String DFA72_eofS =
		"\155\uffff";
	static final String DFA72_minS =
		"\1\105\1\131\1\105\1\51\1\uffff\1\105\1\51\2\105\1\uffff\6\51\1\105\1"+
		"\51\1\105\1\51\1\105\2\51\1\106\1\51\1\0\1\51\1\105\1\51\2\0\1\51\1\106"+
		"\2\51\1\105\1\51\2\105\1\51\1\55\3\105\3\51\2\105\1\0\2\51\1\55\1\105"+
		"\2\51\1\105\1\51\1\105\1\51\1\105\1\51\1\0\2\51\1\106\3\51\1\0\1\105\1"+
		"\51\2\0\1\51\1\106\1\51\1\106\1\51\1\55\2\105\2\51\1\106\1\51\1\105\2"+
		"\51\3\105\1\0\2\51\1\106\1\105\1\51\1\0\1\51\1\105\1\0\1\51\1\106\1\51"+
		"\1\106\1\0\1\105\1\106";
	static final String DFA72_maxS =
		"\1\105\1\131\1\106\1\u008e\1\uffff\2\105\2\106\1\uffff\1\106\1\u0083\1"+
		"\u0089\2\105\1\u0083\1\106\1\u0083\7\106\1\0\1\u008e\1\105\1\106\2\0\3"+
		"\106\1\105\3\106\2\105\1\55\1\105\3\106\1\u0083\3\106\1\0\1\51\1\104\1"+
		"\55\1\105\2\51\1\106\1\u0080\4\106\1\0\3\106\1\u0080\2\106\1\0\1\105\1"+
		"\106\2\0\5\106\1\55\1\106\1\105\5\106\1\51\4\106\1\0\1\51\4\106\1\0\2"+
		"\106\1\0\4\106\1\0\2\106";
	static final String DFA72_acceptS =
		"\4\uffff\1\1\4\uffff\1\2\143\uffff";
	static final String DFA72_specialS =
		"\31\uffff\1\1\3\uffff\1\0\1\3\22\uffff\1\4\14\uffff\1\5\6\uffff\1\6\2"+
		"\uffff\1\10\1\11\22\uffff\1\12\5\uffff\1\13\2\uffff\1\7\4\uffff\1\2\2"+
		"\uffff}>";
	static final String[] DFA72_transitionS = {
			"\1\1",
			"\1\2",
			"\1\3\1\4",
			"\1\12\70\uffff\2\11\1\6\2\11\6\uffff\1\11\1\uffff\1\4\7\uffff\2\11\2"+
			"\uffff\1\11\4\uffff\1\5\1\uffff\1\7\1\10\12\uffff\1\4",
			"",
			"\1\13",
			"\1\15\3\uffff\1\11\23\uffff\1\11\2\uffff\1\16\1\14",
			"\1\17\1\20",
			"\1\21\1\22",
			"",
			"\1\23\27\uffff\1\11\4\uffff\1\24",
			"\1\25\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"+
			"\1\11\1\uffff\2\11",
			"\1\26\32\uffff\1\26\2\uffff\4\11\35\uffff\2\11\7\uffff\1\11\27\uffff"+
			"\1\11",
			"\1\11\3\uffff\1\27\23\uffff\1\11\2\uffff\2\11",
			"\1\11\3\uffff\1\27\26\uffff\2\11",
			"\1\30\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"+
			"\1\11\1\uffff\2\11",
			"\1\32\1\31",
			"\1\34\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"+
			"\1\33\1\uffff\2\11",
			"\1\32\1\35",
			"\1\23\27\uffff\1\11\4\uffff\1\24",
			"\1\32\1\36",
			"\1\37\27\uffff\1\11\4\uffff\1\40",
			"\1\41\27\uffff\1\41\4\uffff\1\42",
			"\1\43",
			"\1\44\27\uffff\1\11\4\uffff\1\45",
			"\1\uffff",
			"\1\54\72\uffff\1\47\12\uffff\1\50\20\uffff\1\46\1\uffff\1\52\1\53\12"+
			"\uffff\1\51",
			"\1\55",
			"\1\56\27\uffff\1\11\4\uffff\1\57",
			"\1\uffff",
			"\1\uffff",
			"\1\37\27\uffff\1\11\4\uffff\1\40",
			"\1\60",
			"\1\41\27\uffff\1\41\4\uffff\1\42",
			"\1\11\3\uffff\1\27\26\uffff\2\11",
			"\1\32\1\61",
			"\1\44\27\uffff\1\11\4\uffff\1\45",
			"\1\17\1\20",
			"\1\62",
			"\1\64\32\uffff\1\64\1\63",
			"\1\65",
			"\1\66",
			"\1\67\1\70",
			"\1\71\1\72",
			"\1\73\34\uffff\1\74",
			"\1\75\70\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11\4\uffff"+
			"\1\11\1\uffff\2\11",
			"\1\56\27\uffff\1\11\4\uffff\1\57",
			"\1\21\1\22",
			"\1\32\1\76",
			"\1\uffff",
			"\1\77",
			"\1\100\32\uffff\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\32\1\105",
			"\1\107\126\uffff\1\106",
			"\1\32\1\110",
			"\1\73\34\uffff\1\74",
			"\1\32\1\111",
			"\1\112\27\uffff\1\11\4\uffff\1\113",
			"\1\uffff",
			"\1\114\34\uffff\1\115",
			"\1\116\27\uffff\1\116\4\uffff\1\117",
			"\1\120",
			"\1\122\126\uffff\1\121",
			"\1\123\34\uffff\1\124",
			"\1\125\34\uffff\1\126",
			"\1\uffff",
			"\1\127",
			"\1\130\34\uffff\1\131",
			"\1\uffff",
			"\1\uffff",
			"\1\112\27\uffff\1\11\4\uffff\1\113",
			"\1\132",
			"\1\114\34\uffff\1\115",
			"\1\133",
			"\1\116\27\uffff\1\116\4\uffff\1\117",
			"\1\101",
			"\1\32\1\134",
			"\1\135",
			"\1\136\34\uffff\1\137",
			"\1\123\34\uffff\1\124",
			"\1\140",
			"\1\125\34\uffff\1\126",
			"\1\67\1\70",
			"\1\141",
			"\1\130\34\uffff\1\131",
			"\1\71\1\72",
			"\1\21\1\22",
			"\1\32\1\142",
			"\1\uffff",
			"\1\143",
			"\1\136\34\uffff\1\137",
			"\1\144",
			"\1\32\1\145",
			"\1\146\34\uffff\1\147",
			"\1\uffff",
			"\1\150\34\uffff\1\151",
			"\1\32\1\152",
			"\1\uffff",
			"\1\146\34\uffff\1\147",
			"\1\153",
			"\1\150\34\uffff\1\151",
			"\1\154",
			"\1\uffff",
			"\1\71\1\72",
			"\1\144"
	};

	static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
	static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
	static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
	static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
	static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
	static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
	static final short[][] DFA72_transition;

	static {
		int numStates = DFA72_transitionS.length;
		DFA72_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
		}
	}

	protected class DFA72 extends DFA {

		public DFA72(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 72;
			this.eot = DFA72_eot;
			this.eof = DFA72_eof;
			this.min = DFA72_min;
			this.max = DFA72_max;
			this.accept = DFA72_accept;
			this.special = DFA72_special;
			this.transition = DFA72_transition;
		}
		@Override
		public String getDescription() {
			return "491:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			TokenStream input = (TokenStream)_input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA72_29 = input.LA(1);
						 
						int index72_29 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_29);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA72_25 = input.LA(1);
						 
						int index72_25 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_25);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA72_106 = input.LA(1);
						 
						int index72_106 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_106);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA72_30 = input.LA(1);
						 
						int index72_30 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_30);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA72_49 = input.LA(1);
						 
						int index72_49 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_49);
						if ( s>=0 ) return s;
						break;

					case 5 : 
						int LA72_62 = input.LA(1);
						 
						int index72_62 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_62);
						if ( s>=0 ) return s;
						break;

					case 6 : 
						int LA72_69 = input.LA(1);
						 
						int index72_69 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_69);
						if ( s>=0 ) return s;
						break;

					case 7 : 
						int LA72_101 = input.LA(1);
						 
						int index72_101 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_101);
						if ( s>=0 ) return s;
						break;

					case 8 : 
						int LA72_72 = input.LA(1);
						 
						int index72_72 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_72);
						if ( s>=0 ) return s;
						break;

					case 9 : 
						int LA72_73 = input.LA(1);
						 
						int index72_73 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_73);
						if ( s>=0 ) return s;
						break;

					case 10 : 
						int LA72_92 = input.LA(1);
						 
						int index72_92 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_92);
						if ( s>=0 ) return s;
						break;

					case 11 : 
						int LA72_98 = input.LA(1);
						 
						int index72_98 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred117_Pddl()) ) {s = 4;}
						else if ( (true) ) {s = 9;}
						 
						input.seek(index72_98);
						if ( s>=0 ) return s;
						break;
			}
			if (state.backtracking>0) {state.failed=true; return -1;}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 72, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_domain_in_pddlDoc401 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_problem_in_pddlDoc405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_domain420 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
	public static final BitSet FOLLOW_115_in_domain422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_domainName_in_domain424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_requireDef_in_domain432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_typesDef_in_domain441 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_constantsDef_in_domain450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_predicatesDef_in_domain459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_functionsDef_in_domain468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_free_functionsDef_in_domain477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_constraints_in_domain486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_structureDef_in_domain495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_domain504 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_free_functionsDef588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_86_in_free_functionsDef590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_functionList_in_free_functionsDef592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_free_functionsDef594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_domainName617 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
	public static final BitSet FOLLOW_116_in_domainName619 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_NAME_in_domainName621 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_domainName623 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_requireDef650 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_96_in_requireDef652 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_REQUIRE_KEY_in_requireDef654 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_requireDef657 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_typesDef678 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_97_in_typesDef680 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_typedNameList_in_typesDef682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_typesDef684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_typedNameList711 = new BitSet(new long[]{0x0000020000000002L});
	public static final BitSet FOLLOW_singleTypeNameList_in_typedNameList716 = new BitSet(new long[]{0x0000020000000002L});
	public static final BitSet FOLLOW_NAME_in_typedNameList719 = new BitSet(new long[]{0x0000020000000002L});
	public static final BitSet FOLLOW_NAME_in_singleTypeNameList739 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_singleTypeNameList742 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_type_in_singleTypeNameList746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_type773 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
	public static final BitSet FOLLOW_117_in_type775 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_primType_in_type777 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_type780 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_primType_in_type801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_primType811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_functionsDef821 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_87_in_functionsDef823 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_functionList_in_functionsDef825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_functionsDef827 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atomicFunctionSkeleton_in_functionList848 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000220L});
	public static final BitSet FOLLOW_73_in_functionList852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_functionType_in_functionList854 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_atomicFunctionSkeleton870 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_functionSymbol_in_atomicFunctionSkeleton873 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_atomicFunctionSkeleton876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_atomicFunctionSkeleton878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_129_in_functionType901 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_constantsDef912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_77_in_constantsDef914 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_typedNameList_in_constantsDef916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constantsDef918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_predicatesDef938 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_94_in_predicatesDef940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_atomicFormulaSkeleton_in_predicatesDef942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_predicatesDef945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_atomicFormulaSkeleton966 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_predicate_in_atomicFormulaSkeleton969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_atomicFormulaSkeleton972 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_atomicFormulaSkeleton974 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_predicate985 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_typedVariableList1000 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_singleTypeVarList_in_typedVariableList1005 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_typedVariableList1008 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
	public static final BitSet FOLLOW_VARIABLE_in_singleTypeVarList1028 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000202L});
	public static final BitSet FOLLOW_73_in_singleTypeVarList1031 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_type_in_singleTypeVarList1035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_constraints1066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_constraints1069 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_conGD_in_constraints1072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constraints1074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_actionDef_in_structureDef1086 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_durativeActionDef_in_structureDef1091 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_derivedDef_in_structureDef1096 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constraintDef_in_structureDef1101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_processDef_in_structureDef1106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_eventDef_in_structureDef1111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_actionDef1126 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_75_in_actionDef1128 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_actionSymbol_in_actionDef1130 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_actionDef1139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_actionDef1142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_actionDef1144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_actionDef1146 = new BitSet(new long[]{0x0000000000000000L,0x0000000020100040L});
	public static final BitSet FOLLOW_actionDefBody_in_actionDef1159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_actionDef1161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_eventDef1194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_85_in_eventDef1196 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_actionSymbol_in_eventDef1198 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_eventDef1207 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_eventDef1210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_eventDef1212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_eventDef1214 = new BitSet(new long[]{0x0000000000000000L,0x0000000020100040L});
	public static final BitSet FOLLOW_actionDefBody_in_eventDef1227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_eventDef1229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_processDef1262 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_95_in_processDef1264 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_actionSymbol_in_processDef1266 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_processDef1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_processDef1278 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_processDef1280 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_processDef1282 = new BitSet(new long[]{0x0000000000000000L,0x0000000020100040L});
	public static final BitSet FOLLOW_actionDefBody_in_processDef1295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_processDef1297 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_constraintDef1331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_78_in_constraintDef1333 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_constraintSymbol_in_constraintDef1335 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_constraintDef1344 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_constraintDef1347 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_constraintDef1349 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constraintDef1351 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001040L});
	public static final BitSet FOLLOW_constraintDefBody_in_constraintDef1364 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constraintDef1366 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_actionSymbol1400 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_constraintSymbol1409 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_93_in_actionDefBody1424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_actionDefBody1428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_actionDefBody1430 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
	public static final BitSet FOLLOW_goalDesc_in_actionDefBody1435 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
	public static final BitSet FOLLOW_84_in_actionDefBody1445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_actionDefBody1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_actionDefBody1451 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_effect_in_actionDefBody1456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_constraintDefBody1491 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_constraintDefBody1495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_constraintDefBody1497 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_goalDesc_in_constraintDefBody1502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_goalDesc_in_belief1540 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
	public static final BitSet FOLLOW_initEl_in_belief1545 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
	public static final BitSet FOLLOW_atomicTermFormula_in_goalDesc1567 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1572 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_goalDesc1574 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_goalDesc1579 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_131_in_goalDesc1606 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1608 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_goalDesc1611 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_128_in_goalDesc1638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1642 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_130_in_goalDesc1668 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_goalDesc1674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1689 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_123_in_goalDesc1691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1723 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_119_in_goalDesc1725 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_goalDesc1727 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_goalDesc1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1735 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goalDesc1761 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_goalDesc1763 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_goalDesc1765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_goalDesc1767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goalDesc1771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goalDesc1773 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fComp_in_goalDesc1802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_equality_in_goalDesc1829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_equality1857 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_equality1860 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_equality1862 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_equality1864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_equality1866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fComp1877 = new BitSet(new long[]{0x0000000000000000L,0x0000007C00000000L});
	public static final BitSet FOLLOW_binaryComp_in_fComp1880 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fComp1882 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fComp1884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fComp1886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_atomicTermFormula1898 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_predicate_in_atomicTermFormula1900 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_term_in_atomicTermFormula1902 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_70_in_atomicTermFormula1905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_durativeActionDef1942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_83_in_durativeActionDef1944 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_actionSymbol_in_durativeActionDef1946 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_92_in_durativeActionDef1955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_durativeActionDef1958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_durativeActionDef1961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_durativeActionDef1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000141000L});
	public static final BitSet FOLLOW_daDefBody_in_durativeActionDef1978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_durativeActionDef1980 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_82_in_daDefBody2013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_durationConstraint_in_daDefBody2015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_76_in_daDefBody2020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_daDefBody2024 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daDefBody2026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_daGD_in_daDefBody2031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_84_in_daDefBody2040 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_daDefBody2044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daDefBody2046 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_daEffect_in_daDefBody2051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prefTimedGD_in_daGD2066 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daGD2071 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_daGD2073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_daGD_in_daGD2075 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_daGD2078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daGD2083 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_daGD2085 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_daGD2087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_daGD2089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daGD2091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daGD_in_daGD2093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daGD2095 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_timedGD_in_prefTimedGD2106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_prefTimedGD2111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_133_in_prefTimedGD2113 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_prefTimedGD2115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_timedGD_in_prefTimedGD2118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_prefTimedGD2120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_timedGD2131 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_timedGD2133 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_timedGD2135 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_timedGD2137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_timedGD2139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_timedGD2144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_132_in_timedGD2146 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_interval_in_timedGD2148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_timedGD2150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_timedGD2152 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_106_in_interval2174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_derivedDef2187 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_80_in_derivedDef2190 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000022L});
	public static final BitSet FOLLOW_typedVariableList_in_derivedDef2193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_derivedDef2195 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_derivedDef2197 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_fExp2212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExp2217 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_fExp2219 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fExp2221 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp2_in_fExp2223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExp2225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExp2242 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_fExp2244 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fExp2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExp2248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExp2261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_137_in_fExp2263 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fExp2265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExp2267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExp2280 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_fExp2282 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fExp2284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExp2286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExp2299 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_fExp2301 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_fExp2303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExp2305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fHead_in_fExp2318 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fExp_in_fExp22330 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fHead2340 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_functionSymbol_in_fHead2342 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_term_in_fHead2344 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_70_in_fHead2347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionSymbol_in_fHead2363 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_effect2382 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_effect2384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_cEffect_in_effect2386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_effect2389 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cEffect_in_effect2403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_cEffect2414 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_cEffect2416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_cEffect2418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_cEffect2420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_cEffect2422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_effect_in_cEffect2424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_cEffect2426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_cEffect2444 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_143_in_cEffect2446 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_cEffect2448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_condEffect_in_cEffect2450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_cEffect2452 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_pEffect_in_cEffect2470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_pEffect2481 = new BitSet(new long[]{0x0000000000000000L,0x1004400000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_assignOp_in_pEffect2483 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fHead_in_pEffect2485 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_pEffect2487 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_pEffect2489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_pEffect2509 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_128_in_pEffect2511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_pEffect2515 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_condEffect2544 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_condEffect2546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_pEffect_in_condEffect2548 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_condEffect2551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_pEffect_in_condEffect2565 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_durationConstraint2670 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_durationConstraint2672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_durationConstraint2677 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_durationConstraint2682 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_durationConstraint2684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_simpleDurationConstraint2700 = new BitSet(new long[]{0x0000000000000000L,0x0000005800000000L});
	public static final BitSet FOLLOW_durOp_in_simpleDurationConstraint2702 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_103_in_simpleDurationConstraint2704 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_durValue_in_simpleDurationConstraint2706 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_simpleDurationConstraint2708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_simpleDurationConstraint2713 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_simpleDurationConstraint2715 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_simpleDurationConstraint2717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_simpleDurationConstraint2721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_durValue2748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fExp_in_durValue2752 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daEffect2762 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_daEffect2764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_daEffect_in_daEffect2766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_daEffect2769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_timedEffect_in_daEffect2774 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daEffect2779 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_daEffect2781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_daEffect2783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_daEffect2785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daEffect2787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daEffect_in_daEffect2789 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daEffect2791 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daEffect2796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_143_in_daEffect2798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daGD_in_daEffect2800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_timedEffect_in_daEffect2802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daEffect2804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_daEffect2809 = new BitSet(new long[]{0x0000000000000000L,0x1004400000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_assignOp_in_daEffect2811 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fHead_in_daEffect2813 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_daEffect2815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_daEffect2817 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_timedEffect2828 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_timedEffect2830 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daEffect_in_timedEffect2834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_timedEffect2836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_timedEffect2846 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_timedEffect2848 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fAssignDA_in_timedEffect2852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_timedEffect2854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_timedEffect2859 = new BitSet(new long[]{0x0000000000000000L,0x1004400000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_assignOp_in_timedEffect2861 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fHead_in_timedEffect2863 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_timedEffect2865 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_timedEffect2867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fAssignDA2887 = new BitSet(new long[]{0x0000000000000000L,0x1004400000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_assignOp_in_fAssignDA2889 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fHead_in_fAssignDA2891 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_fAssignDA2893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fAssignDA2895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_fExpDA2906 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_fExpDA2910 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_fExpDA2912 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_fExpDA2914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_73_in_fExpDA2920 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_fExpDA2922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_fExpDA2926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_103_in_fExpDA2931 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fExp_in_fExpDA2936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_problem2950 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
	public static final BitSet FOLLOW_115_in_problem2952 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_problemDecl_in_problem2954 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_problemDomain_in_problem2959 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_requireDef_in_problem2967 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_objectDecl_in_problem2976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_init_in_problem2985 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goal_in_problem2993 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_probConstraints_in_problem3001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_metricSpec_in_problem3010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_problem3026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_problemDecl3083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_134_in_problemDecl3085 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_NAME_in_problemDecl3087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_problemDecl3089 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_problemDomain3115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_81_in_problemDomain3117 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_NAME_in_problemDomain3119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_problemDomain3121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_objectDecl3141 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
	public static final BitSet FOLLOW_91_in_objectDecl3143 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_typedNameList_in_objectDecl3145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_objectDecl3147 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_init3167 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_init3169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_initEl_in_init3171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_init3174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_init3189 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_init3191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_belief_in_init3193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_init3195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nameLiteral_in_initEl3214 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_initEl3219 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_100_in_initEl3221 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fHead_in_initEl3223 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_initEl3225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_initEl3227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_initEl3250 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_initEl3252 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_initEl3254 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_nameLiteral_in_initEl3256 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_initEl3258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_initEl3274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_142_in_initEl3276 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_atomicNameFormula_in_initEl3279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_initEl3281 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_initEl3295 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_130_in_initEl3297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_atomicNameFormula_in_initEl3300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_initEl3303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_initEl3318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_131_in_initEl3320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_nameLiteral_in_initEl3323 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_initEl3326 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_nameLiteral3352 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_128_in_nameLiteral3354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_nameLiteral3358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_atomicNameFormula3377 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_predicate_in_atomicNameFormula3379 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NAME_in_atomicNameFormula3381 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_atomicNameFormula3384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_goal3409 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_88_in_goal3411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_goal3413 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_goal3416 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_probConstraints3434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_79_in_probConstraints3436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_prefConGD_in_probConstraints3439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_probConstraints3441 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_prefConGD3463 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_prefConGD3465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_prefConGD_in_prefConGD3467 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_prefConGD3470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_prefConGD3475 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_prefConGD3477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_prefConGD3479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_prefConGD3481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_prefConGD3483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_prefConGD_in_prefConGD3485 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_prefConGD3487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_prefConGD3492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_133_in_prefConGD3494 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_prefConGD3496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_conGD_in_prefConGD3499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_prefConGD3501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conGD_in_prefConGD3506 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_metricSpec3517 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_90_in_metricSpec3519 = new BitSet(new long[]{0x0000000000000000L,0xC000000000000000L});
	public static final BitSet FOLLOW_optimization_in_metricSpec3521 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricSpec3523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_metricSpec3525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_metricFExp3562 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_metricFExp3564 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricFExp3566 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricFExp3568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_metricFExp3570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_metricFExp3592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_multiOp_in_metricFExp3594 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricFExp3596 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricFExp3598 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000070L});
	public static final BitSet FOLLOW_70_in_metricFExp3601 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_metricFExp3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_metricFExp3627 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_metricFExp3629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_metricFExp3631 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_metricFExp3648 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fHead_in_metricFExp3653 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_metricFExp3668 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L});
	public static final BitSet FOLLOW_125_in_metricFExp3670 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_NAME_in_metricFExp3672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_metricFExp3674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3688 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_conGD3690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_conGD_in_conGD3692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_conGD3695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3700 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_conGD3702 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_conGD3704 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_conGD3706 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3708 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_conGD_in_conGD3710 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3712 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3717 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_conGD3719 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
	public static final BitSet FOLLOW_118_in_conGD3721 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3725 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3751 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_107_in_conGD3753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3755 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3762 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_138_in_conGD3764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3774 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_144_in_conGD3776 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_conGD3778 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3787 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
	public static final BitSet FOLLOW_112_in_conGD3789 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3791 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_139_in_conGD3800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3804 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3806 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_140_in_conGD3813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3815 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3817 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3819 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3824 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_108_in_conGD3826 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_conGD3828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3830 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3834 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3839 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
	public static final BitSet FOLLOW_122_in_conGD3841 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_conGD3843 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_conGD3845 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3849 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_conGD3854 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
	public static final BitSet FOLLOW_121_in_conGD3856 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_NUMBER_in_conGD3858 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_goalDesc_in_conGD3860 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_conGD3862 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fComp_in_synpred51_Pddl1802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_typedVariableList_in_synpred54_Pddl1961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred67_Pddl2217 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_synpred67_Pddl2219 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_synpred67_Pddl2221 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp2_in_synpred67_Pddl2223 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred67_Pddl2225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred68_Pddl2242 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_synpred68_Pddl2244 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_synpred68_Pddl2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred68_Pddl2248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred69_Pddl2261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_137_in_synpred69_Pddl2263 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_synpred69_Pddl2265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred69_Pddl2267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred70_Pddl2280 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_113_in_synpred70_Pddl2282 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_synpred70_Pddl2284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred70_Pddl2286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred71_Pddl2299 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_105_in_synpred71_Pddl2301 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_fExp_in_synpred71_Pddl2303 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred71_Pddl2305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NUMBER_in_synpred101_Pddl2748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred103_Pddl2762 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_synpred103_Pddl2764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_daEffect_in_synpred103_Pddl2766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_synpred103_Pddl2769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_timedEffect_in_synpred104_Pddl2774 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred105_Pddl2779 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_synpred105_Pddl2781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_synpred105_Pddl2783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_synpred105_Pddl2785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred105_Pddl2787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daEffect_in_synpred105_Pddl2789 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred105_Pddl2791 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred106_Pddl2796 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_143_in_synpred106_Pddl2798 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daGD_in_synpred106_Pddl2800 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_timedEffect_in_synpred106_Pddl2802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred106_Pddl2804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred107_Pddl2828 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_synpred107_Pddl2830 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_synpred107_Pddl2832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_daEffect_in_synpred107_Pddl2834 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred107_Pddl2836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred108_Pddl2846 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
	public static final BitSet FOLLOW_111_in_synpred108_Pddl2848 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_timeSpecifier_in_synpred108_Pddl2850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_fAssignDA_in_synpred108_Pddl2852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred108_Pddl2854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_binaryOp_in_synpred109_Pddl2910 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_synpred109_Pddl2912 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_synpred109_Pddl2914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred110_Pddl2906 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_synpred110_Pddl2910 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_synpred110_Pddl2912 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_synpred110_Pddl2914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_73_in_synpred110_Pddl2920 = new BitSet(new long[]{0x0000220000000000L,0x0000008000000030L});
	public static final BitSet FOLLOW_fExpDA_in_synpred110_Pddl2922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred110_Pddl2926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred117_Pddl3167 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
	public static final BitSet FOLLOW_89_in_synpred117_Pddl3169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_initEl_in_synpred117_Pddl3171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_synpred117_Pddl3174 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred128_Pddl3463 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
	public static final BitSet FOLLOW_109_in_synpred128_Pddl3465 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_prefConGD_in_synpred128_Pddl3467 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000060L});
	public static final BitSet FOLLOW_70_in_synpred128_Pddl3470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred129_Pddl3475 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_120_in_synpred129_Pddl3477 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_69_in_synpred129_Pddl3479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000042L});
	public static final BitSet FOLLOW_typedVariableList_in_synpred129_Pddl3481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred129_Pddl3483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_prefConGD_in_synpred129_Pddl3485 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred129_Pddl3487 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred131_Pddl3492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_133_in_synpred131_Pddl3494 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_synpred131_Pddl3496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_conGD_in_synpred131_Pddl3499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred131_Pddl3501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred133_Pddl3562 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000780L});
	public static final BitSet FOLLOW_binaryOp_in_synpred133_Pddl3564 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_synpred133_Pddl3566 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_synpred133_Pddl3568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred133_Pddl3570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred135_Pddl3592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
	public static final BitSet FOLLOW_multiOp_in_synpred135_Pddl3594 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_synpred135_Pddl3596 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_synpred135_Pddl3598 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000070L});
	public static final BitSet FOLLOW_70_in_synpred135_Pddl3601 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_69_in_synpred136_Pddl3625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_73_in_synpred136_Pddl3627 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000030L});
	public static final BitSet FOLLOW_metricFExp_in_synpred136_Pddl3629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_70_in_synpred136_Pddl3631 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fHead_in_synpred138_Pddl3653 = new BitSet(new long[]{0x0000000000000002L});
}
