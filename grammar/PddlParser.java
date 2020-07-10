// $ANTLR 3.4 Pddl.g 2020-07-10 23:51:48
 package com.hstairs.ppmajal.parser; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class PddlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACTION", "AND_EFFECT", "AND_GD", "ANY_CHAR", "ASSIGN_EFFECT", "BELIEF", "BINARY_OP", "COMPARISON_GD", "CONSTANTS", "CONSTRAINT", "COS", "DIGIT", "DOMAIN", "DOMAIN_NAME", "DURATIVE_ACTION", "EFFECT", "EITHER_TYPE", "EQUALITY_CON", "EVENT", "EXISTS_GD", "FORALL_EFFECT", "FORALL_GD", "FORMULAINIT", "FREE_FUNCTIONS", "FUNCTIONS", "FUNC_HEAD", "GLOBAL_CONSTRAINT", "GOAL", "IMPLY_GD", "INIT", "INIT_AT", "INIT_EQ", "LETTER", "LINE_COMMENT", "MINUS_OP", "MULTI_OP", "NAME", "NOT_EFFECT", "NOT_GD", "NOT_PRED_INIT", "NUMBER", "OBJECTS", "ONEOF", "ONEOF_EFFECT", "OR_GD", "PRECONDITION", "PREDICATES", "PRED_HEAD", "PRED_INST", "PROBLEM", "PROBLEM_CONSTRAINT", "PROBLEM_DOMAIN", "PROBLEM_METRIC", "PROBLEM_NAME", "PROCESS", "REQUIREMENTS", "REQUIRE_KEY", "SIN", "TYPES", "UNARY_MINUS", "UNKNOWN", "VARIABLE", "WHEN_EFFECT", "WHITESPACE", "'#t'", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "':action'", "':condition'", "':constants'", "':constraint'", "':constraints'", "':derived'", "':domain'", "':duration'", "':durative-action'", "':effect'", "':event'", "':free_functions'", "':functions'", "':goal'", "':init'", "':metric'", "':objects'", "':parameters'", "':precondition'", "':predicates'", "':process'", "':requirements'", "':types'", "'<'", "'<='", "'='", "'>'", "'>='", "'?duration'", "'^'", "'abs'", "'all'", "'always'", "'always-within'", "'and'", "'assign'", "'at'", "'at-most-once'", "'cos'", "'decrease'", "'define'", "'domain'", "'either'", "'end'", "'exists'", "'forall'", "'hold-after'", "'hold-during'", "'imply'", "'increase'", "'is-violated'", "'maximize'", "'minimize'", "'not'", "'number'", "'oneof'", "'or'", "'over'", "'preference'", "'problem'", "'scale-down'", "'scale-up'", "'sin'", "'sometime'", "'sometime-after'", "'sometime-before'", "'start'", "'unknown'", "'when'", "'within'"
    };

    public static final int EOF=-1;
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
    public static final int T__145=145;
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
    public static final int ONEOF_EFFECT=48;
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
    public static final int TYPES=63;
    public static final int UNARY_MINUS=64;
    public static final int UNKNOWN=65;
    public static final int VARIABLE=66;
    public static final int WHEN_EFFECT=67;
    public static final int WHITESPACE=68;

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
        this.state.ruleMemo = new HashMap[233+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return PddlParser.tokenNames; }
    public String getGrammarFileName() { return "Pddl.g"; }


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pddlDoc"
    // Pddl.g:104:1: pddlDoc : ( domain | problem );
    public final PddlParser.pddlDoc_return pddlDoc() throws RecognitionException {
        PddlParser.pddlDoc_return retval = new PddlParser.pddlDoc_return();
        retval.start = input.LT(1);

        int pddlDoc_StartIndex = input.index();

        Object root_0 = null;

        PddlParser.domain_return domain1 =null;

        PddlParser.problem_return problem2 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // Pddl.g:104:9: ( domain | problem )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==70) ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1==116) ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2==70) ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3==117) ) {
                            alt1=1;
                        }
                        else if ( (LA1_3==135) ) {
                            alt1=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 1, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

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
                    // Pddl.g:104:11: domain
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_domain_in_pddlDoc405);
                    domain1=domain();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, domain1.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:104:20: problem
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_problem_in_pddlDoc409);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "domain"
    // Pddl.g:108:1: domain : '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) ;
    public final PddlParser.domain_return domain() throws RecognitionException {
        PddlParser.domain_return retval = new PddlParser.domain_return();
        retval.start = input.LT(1);

        int domain_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal3=null;
        Token string_literal4=null;
        Token char_literal14=null;
        PddlParser.domainName_return domainName5 =null;

        PddlParser.requireDef_return requireDef6 =null;

        PddlParser.typesDef_return typesDef7 =null;

        PddlParser.constantsDef_return constantsDef8 =null;

        PddlParser.predicatesDef_return predicatesDef9 =null;

        PddlParser.functionsDef_return functionsDef10 =null;

        PddlParser.free_functionsDef_return free_functionsDef11 =null;

        PddlParser.constraints_return constraints12 =null;

        PddlParser.structureDef_return structureDef13 =null;


        Object char_literal3_tree=null;
        Object string_literal4_tree=null;
        Object char_literal14_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
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

            // Pddl.g:109:5: ( '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')' -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ) )
            // Pddl.g:109:7: '(' 'define' domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* ')'
            {
            char_literal3=(Token)match(input,70,FOLLOW_70_in_domain424); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal3);


            string_literal4=(Token)match(input,116,FOLLOW_116_in_domain426); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_116.add(string_literal4);


            pushFollow(FOLLOW_domainName_in_domain428);
            domainName5=domainName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_domainName.add(domainName5.getTree());

            // Pddl.g:110:7: ( requireDef )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==70) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==97) ) {
                    alt2=1;
                }
            }
            switch (alt2) {
                case 1 :
                    // Pddl.g:110:7: requireDef
                    {
                    pushFollow(FOLLOW_requireDef_in_domain436);
                    requireDef6=requireDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_requireDef.add(requireDef6.getTree());

                    }
                    break;

            }


            // Pddl.g:111:7: ( typesDef )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==70) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==98) ) {
                    alt3=1;
                }
            }
            switch (alt3) {
                case 1 :
                    // Pddl.g:111:7: typesDef
                    {
                    pushFollow(FOLLOW_typesDef_in_domain445);
                    typesDef7=typesDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typesDef.add(typesDef7.getTree());

                    }
                    break;

            }


            // Pddl.g:112:7: ( constantsDef )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==70) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==78) ) {
                    alt4=1;
                }
            }
            switch (alt4) {
                case 1 :
                    // Pddl.g:112:7: constantsDef
                    {
                    pushFollow(FOLLOW_constantsDef_in_domain454);
                    constantsDef8=constantsDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constantsDef.add(constantsDef8.getTree());

                    }
                    break;

            }


            // Pddl.g:113:7: ( predicatesDef )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==70) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==95) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // Pddl.g:113:7: predicatesDef
                    {
                    pushFollow(FOLLOW_predicatesDef_in_domain463);
                    predicatesDef9=predicatesDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_predicatesDef.add(predicatesDef9.getTree());

                    }
                    break;

            }


            // Pddl.g:114:7: ( functionsDef )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==70) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==88) ) {
                    alt6=1;
                }
            }
            switch (alt6) {
                case 1 :
                    // Pddl.g:114:7: functionsDef
                    {
                    pushFollow(FOLLOW_functionsDef_in_domain472);
                    functionsDef10=functionsDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionsDef.add(functionsDef10.getTree());

                    }
                    break;

            }


            // Pddl.g:115:7: ( free_functionsDef )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==70) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==87) ) {
                    alt7=1;
                }
            }
            switch (alt7) {
                case 1 :
                    // Pddl.g:115:7: free_functionsDef
                    {
                    pushFollow(FOLLOW_free_functionsDef_in_domain481);
                    free_functionsDef11=free_functionsDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_free_functionsDef.add(free_functionsDef11.getTree());

                    }
                    break;

            }


            // Pddl.g:116:7: ( constraints )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==70) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==80) ) {
                    alt8=1;
                }
            }
            switch (alt8) {
                case 1 :
                    // Pddl.g:116:7: constraints
                    {
                    pushFollow(FOLLOW_constraints_in_domain490);
                    constraints12=constraints();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constraints.add(constraints12.getTree());

                    }
                    break;

            }


            // Pddl.g:117:7: ( structureDef )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==70) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Pddl.g:117:7: structureDef
            	    {
            	    pushFollow(FOLLOW_structureDef_in_domain499);
            	    structureDef13=structureDef();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_structureDef.add(structureDef13.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            char_literal14=(Token)match(input,71,FOLLOW_71_in_domain508); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal14);


            // AST REWRITE
            // elements: typesDef, structureDef, requireDef, constraints, constantsDef, functionsDef, domainName, free_functionsDef, predicatesDef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 119:7: -> ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
            {
                // Pddl.g:119:10: ^( DOMAIN domainName ( requireDef )? ( typesDef )? ( constantsDef )? ( predicatesDef )? ( functionsDef )? ( free_functionsDef )? ( constraints )? ( structureDef )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DOMAIN, "DOMAIN")
                , root_1);

                adaptor.addChild(root_1, stream_domainName.nextTree());

                // Pddl.g:119:30: ( requireDef )?
                if ( stream_requireDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_requireDef.nextTree());

                }
                stream_requireDef.reset();

                // Pddl.g:119:42: ( typesDef )?
                if ( stream_typesDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_typesDef.nextTree());

                }
                stream_typesDef.reset();

                // Pddl.g:120:17: ( constantsDef )?
                if ( stream_constantsDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_constantsDef.nextTree());

                }
                stream_constantsDef.reset();

                // Pddl.g:120:31: ( predicatesDef )?
                if ( stream_predicatesDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_predicatesDef.nextTree());

                }
                stream_predicatesDef.reset();

                // Pddl.g:120:46: ( functionsDef )?
                if ( stream_functionsDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_functionsDef.nextTree());

                }
                stream_functionsDef.reset();

                // Pddl.g:120:60: ( free_functionsDef )?
                if ( stream_free_functionsDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_free_functionsDef.nextTree());

                }
                stream_free_functionsDef.reset();

                // Pddl.g:121:17: ( constraints )?
                if ( stream_constraints.hasNext() ) {
                    adaptor.addChild(root_1, stream_constraints.nextTree());

                }
                stream_constraints.reset();

                // Pddl.g:121:30: ( structureDef )*
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "free_functionsDef"
    // Pddl.g:124:1: free_functionsDef : '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) ;
    public final PddlParser.free_functionsDef_return free_functionsDef() throws RecognitionException {
        PddlParser.free_functionsDef_return retval = new PddlParser.free_functionsDef_return();
        retval.start = input.LT(1);

        int free_functionsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal15=null;
        Token string_literal16=null;
        Token char_literal18=null;
        PddlParser.functionList_return functionList17 =null;


        Object char_literal15_tree=null;
        Object string_literal16_tree=null;
        Object char_literal18_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
        RewriteRuleSubtreeStream stream_functionList=new RewriteRuleSubtreeStream(adaptor,"rule functionList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // Pddl.g:125:2: ( '(' ':free_functions' functionList ')' -> ^( FREE_FUNCTIONS functionList ) )
            // Pddl.g:125:4: '(' ':free_functions' functionList ')'
            {
            char_literal15=(Token)match(input,70,FOLLOW_70_in_free_functionsDef592); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal15);


            string_literal16=(Token)match(input,87,FOLLOW_87_in_free_functionsDef594); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_87.add(string_literal16);


            pushFollow(FOLLOW_functionList_in_free_functionsDef596);
            functionList17=functionList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_functionList.add(functionList17.getTree());

            char_literal18=(Token)match(input,71,FOLLOW_71_in_free_functionsDef598); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal18);


            // AST REWRITE
            // elements: functionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 126:2: -> ^( FREE_FUNCTIONS functionList )
            {
                // Pddl.g:126:5: ^( FREE_FUNCTIONS functionList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FREE_FUNCTIONS, "FREE_FUNCTIONS")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "domainName"
    // Pddl.g:129:1: domainName : '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) ;
    public final PddlParser.domainName_return domainName() throws RecognitionException {
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
        RewriteRuleTokenStream stream_117=new RewriteRuleTokenStream(adaptor,"token 117");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // Pddl.g:130:5: ( '(' 'domain' NAME ')' -> ^( DOMAIN_NAME NAME ) )
            // Pddl.g:130:7: '(' 'domain' NAME ')'
            {
            char_literal19=(Token)match(input,70,FOLLOW_70_in_domainName621); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal19);


            string_literal20=(Token)match(input,117,FOLLOW_117_in_domainName623); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_117.add(string_literal20);


            NAME21=(Token)match(input,NAME,FOLLOW_NAME_in_domainName625); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME21);


            char_literal22=(Token)match(input,71,FOLLOW_71_in_domainName627); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal22);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 131:6: -> ^( DOMAIN_NAME NAME )
            {
                // Pddl.g:131:9: ^( DOMAIN_NAME NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DOMAIN_NAME, "DOMAIN_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "requireDef"
    // Pddl.g:134:1: requireDef : '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) ;
    public final PddlParser.requireDef_return requireDef() throws RecognitionException {
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
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_97=new RewriteRuleTokenStream(adaptor,"token 97");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // Pddl.g:135:2: ( '(' ':requirements' ( REQUIRE_KEY )+ ')' -> ^( REQUIREMENTS ( REQUIRE_KEY )+ ) )
            // Pddl.g:135:4: '(' ':requirements' ( REQUIRE_KEY )+ ')'
            {
            char_literal23=(Token)match(input,70,FOLLOW_70_in_requireDef654); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal23);


            string_literal24=(Token)match(input,97,FOLLOW_97_in_requireDef656); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_97.add(string_literal24);


            // Pddl.g:135:24: ( REQUIRE_KEY )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==REQUIRE_KEY) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Pddl.g:135:24: REQUIRE_KEY
            	    {
            	    REQUIRE_KEY25=(Token)match(input,REQUIRE_KEY,FOLLOW_REQUIRE_KEY_in_requireDef658); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_REQUIRE_KEY.add(REQUIRE_KEY25);


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            char_literal26=(Token)match(input,71,FOLLOW_71_in_requireDef661); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal26);


            // AST REWRITE
            // elements: REQUIRE_KEY
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 136:2: -> ^( REQUIREMENTS ( REQUIRE_KEY )+ )
            {
                // Pddl.g:136:5: ^( REQUIREMENTS ( REQUIRE_KEY )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(REQUIREMENTS, "REQUIREMENTS")
                , root_1);

                if ( !(stream_REQUIRE_KEY.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_REQUIRE_KEY.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_REQUIRE_KEY.nextNode()
                    );

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typesDef"
    // Pddl.g:139:1: typesDef : '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) ;
    public final PddlParser.typesDef_return typesDef() throws RecognitionException {
        PddlParser.typesDef_return retval = new PddlParser.typesDef_return();
        retval.start = input.LT(1);

        int typesDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal27=null;
        Token string_literal28=null;
        Token char_literal30=null;
        PddlParser.typedNameList_return typedNameList29 =null;


        Object char_literal27_tree=null;
        Object string_literal28_tree=null;
        Object char_literal30_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_98=new RewriteRuleTokenStream(adaptor,"token 98");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // Pddl.g:140:2: ( '(' ':types' typedNameList ')' -> ^( TYPES typedNameList ) )
            // Pddl.g:140:4: '(' ':types' typedNameList ')'
            {
            char_literal27=(Token)match(input,70,FOLLOW_70_in_typesDef682); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal27);


            string_literal28=(Token)match(input,98,FOLLOW_98_in_typesDef684); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_98.add(string_literal28);


            pushFollow(FOLLOW_typedNameList_in_typesDef686);
            typedNameList29=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList29.getTree());

            char_literal30=(Token)match(input,71,FOLLOW_71_in_typesDef688); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal30);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 141:4: -> ^( TYPES typedNameList )
            {
                // Pddl.g:141:7: ^( TYPES typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(TYPES, "TYPES")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typedNameList"
    // Pddl.g:145:1: typedNameList : ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) ;
    public final PddlParser.typedNameList_return typedNameList() throws RecognitionException {
        PddlParser.typedNameList_return retval = new PddlParser.typedNameList_return();
        retval.start = input.LT(1);

        int typedNameList_StartIndex = input.index();

        Object root_0 = null;

        Token NAME31=null;
        Token NAME33=null;
        PddlParser.singleTypeNameList_return singleTypeNameList32 =null;


        Object NAME31_tree=null;
        Object NAME33_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // Pddl.g:146:5: ( ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* ) )
            // Pddl.g:146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            {
            root_0 = (Object)adaptor.nil();


            // Pddl.g:146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // Pddl.g:146:8: ( NAME )*
                    {
                    // Pddl.g:146:8: ( NAME )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==NAME) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // Pddl.g:146:8: NAME
                    	    {
                    	    NAME31=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList715); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NAME31_tree = 
                    	    (Object)adaptor.create(NAME31)
                    	    ;
                    	    adaptor.addChild(root_0, NAME31_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Pddl.g:146:16: ( singleTypeNameList )+ ( NAME )*
                    {
                    // Pddl.g:146:16: ( singleTypeNameList )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        alt12 = dfa12.predict(input);
                        switch (alt12) {
                    	case 1 :
                    	    // Pddl.g:146:16: singleTypeNameList
                    	    {
                    	    pushFollow(FOLLOW_singleTypeNameList_in_typedNameList720);
                    	    singleTypeNameList32=singleTypeNameList();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeNameList32.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);


                    // Pddl.g:146:36: ( NAME )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==NAME) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // Pddl.g:146:36: NAME
                    	    {
                    	    NAME33=(Token)match(input,NAME,FOLLOW_NAME_in_typedNameList723); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NAME33_tree = 
                    	    (Object)adaptor.create(NAME33)
                    	    ;
                    	    adaptor.addChild(root_0, NAME33_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleTypeNameList"
    // Pddl.g:149:1: singleTypeNameList : ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ ;
    public final PddlParser.singleTypeNameList_return singleTypeNameList() throws RecognitionException {
        PddlParser.singleTypeNameList_return retval = new PddlParser.singleTypeNameList_return();
        retval.start = input.LT(1);

        int singleTypeNameList_StartIndex = input.index();

        Object root_0 = null;

        Token NAME34=null;
        Token char_literal35=null;
        PddlParser.type_return t =null;


        Object NAME34_tree=null;
        Object char_literal35_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // Pddl.g:150:5: ( ( ( NAME )+ '-' t= type ) -> ( ^( NAME $t) )+ )
            // Pddl.g:150:7: ( ( NAME )+ '-' t= type )
            {
            // Pddl.g:150:7: ( ( NAME )+ '-' t= type )
            // Pddl.g:150:8: ( NAME )+ '-' t= type
            {
            // Pddl.g:150:8: ( NAME )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==NAME) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Pddl.g:150:8: NAME
            	    {
            	    NAME34=(Token)match(input,NAME,FOLLOW_NAME_in_singleTypeNameList743); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NAME.add(NAME34);


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            char_literal35=(Token)match(input,74,FOLLOW_74_in_singleTypeNameList746); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_74.add(char_literal35);


            pushFollow(FOLLOW_type_in_singleTypeNameList750);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());

            }


            // AST REWRITE
            // elements: NAME, t
            // token labels: 
            // rule labels: t, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 151:4: -> ( ^( NAME $t) )+
            {
                if ( !(stream_NAME.hasNext()||stream_t.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_NAME.hasNext()||stream_t.hasNext() ) {
                    // Pddl.g:151:7: ^( NAME $t)
                    {
                    Object root_1 = (Object)adaptor.nil();
                    root_1 = (Object)adaptor.becomeRoot(
                    stream_NAME.nextNode()
                    , root_1);

                    adaptor.addChild(root_1, stream_t.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_NAME.reset();
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
            if ( state.backtracking>0 ) { memoize(input, 8, singleTypeNameList_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "singleTypeNameList"


    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // Pddl.g:154:1: type : ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType );
    public final PddlParser.type_return type() throws RecognitionException {
        PddlParser.type_return retval = new PddlParser.type_return();
        retval.start = input.LT(1);

        int type_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal36=null;
        Token string_literal37=null;
        Token char_literal39=null;
        PddlParser.primType_return primType38 =null;

        PddlParser.primType_return primType40 =null;


        Object char_literal36_tree=null;
        Object string_literal37_tree=null;
        Object char_literal39_tree=null;
        RewriteRuleTokenStream stream_118=new RewriteRuleTokenStream(adaptor,"token 118");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_primType=new RewriteRuleSubtreeStream(adaptor,"rule primType");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // Pddl.g:155:2: ( ( '(' 'either' ( primType )+ ')' ) -> ^( EITHER_TYPE ( primType )+ ) | primType )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==70) ) {
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
                    // Pddl.g:155:4: ( '(' 'either' ( primType )+ ')' )
                    {
                    // Pddl.g:155:4: ( '(' 'either' ( primType )+ ')' )
                    // Pddl.g:155:6: '(' 'either' ( primType )+ ')'
                    {
                    char_literal36=(Token)match(input,70,FOLLOW_70_in_type777); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal36);


                    string_literal37=(Token)match(input,118,FOLLOW_118_in_type779); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_118.add(string_literal37);


                    // Pddl.g:155:19: ( primType )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==NAME) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // Pddl.g:155:19: primType
                    	    {
                    	    pushFollow(FOLLOW_primType_in_type781);
                    	    primType38=primType();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_primType.add(primType38.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt16 >= 1 ) break loop16;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(16, input);
                                throw eee;
                        }
                        cnt16++;
                    } while (true);


                    char_literal39=(Token)match(input,71,FOLLOW_71_in_type784); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal39);


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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 156:4: -> ^( EITHER_TYPE ( primType )+ )
                    {
                        // Pddl.g:156:7: ^( EITHER_TYPE ( primType )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(EITHER_TYPE, "EITHER_TYPE")
                        , root_1);

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
                    // Pddl.g:157:4: primType
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_primType_in_type805);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primType"
    // Pddl.g:160:1: primType : NAME ;
    public final PddlParser.primType_return primType() throws RecognitionException {
        PddlParser.primType_return retval = new PddlParser.primType_return();
        retval.start = input.LT(1);

        int primType_StartIndex = input.index();

        Object root_0 = null;

        Token NAME41=null;

        Object NAME41_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // Pddl.g:160:10: ( NAME )
            // Pddl.g:160:12: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME41=(Token)match(input,NAME,FOLLOW_NAME_in_primType815); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME41_tree = 
            (Object)adaptor.create(NAME41)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionsDef"
    // Pddl.g:162:1: functionsDef : '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) ;
    public final PddlParser.functionsDef_return functionsDef() throws RecognitionException {
        PddlParser.functionsDef_return retval = new PddlParser.functionsDef_return();
        retval.start = input.LT(1);

        int functionsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal42=null;
        Token string_literal43=null;
        Token char_literal45=null;
        PddlParser.functionList_return functionList44 =null;


        Object char_literal42_tree=null;
        Object string_literal43_tree=null;
        Object char_literal45_tree=null;
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_functionList=new RewriteRuleSubtreeStream(adaptor,"rule functionList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // Pddl.g:163:2: ( '(' ':functions' functionList ')' -> ^( FUNCTIONS functionList ) )
            // Pddl.g:163:4: '(' ':functions' functionList ')'
            {
            char_literal42=(Token)match(input,70,FOLLOW_70_in_functionsDef825); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal42);


            string_literal43=(Token)match(input,88,FOLLOW_88_in_functionsDef827); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_88.add(string_literal43);


            pushFollow(FOLLOW_functionList_in_functionsDef829);
            functionList44=functionList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_functionList.add(functionList44.getTree());

            char_literal45=(Token)match(input,71,FOLLOW_71_in_functionsDef831); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal45);


            // AST REWRITE
            // elements: functionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 164:2: -> ^( FUNCTIONS functionList )
            {
                // Pddl.g:164:5: ^( FUNCTIONS functionList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FUNCTIONS, "FUNCTIONS")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionList"
    // Pddl.g:167:1: functionList : ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* ;
    public final PddlParser.functionList_return functionList() throws RecognitionException {
        PddlParser.functionList_return retval = new PddlParser.functionList_return();
        retval.start = input.LT(1);

        int functionList_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal47=null;
        PddlParser.atomicFunctionSkeleton_return atomicFunctionSkeleton46 =null;

        PddlParser.functionType_return functionType48 =null;


        Object char_literal47_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // Pddl.g:168:2: ( ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )* )
            // Pddl.g:168:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            {
            root_0 = (Object)adaptor.nil();


            // Pddl.g:168:4: ( ( atomicFunctionSkeleton )+ ( '-' functionType )? )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==70) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // Pddl.g:168:5: ( atomicFunctionSkeleton )+ ( '-' functionType )?
            	    {
            	    // Pddl.g:168:5: ( atomicFunctionSkeleton )+
            	    int cnt18=0;
            	    loop18:
            	    do {
            	        int alt18=2;
            	        int LA18_0 = input.LA(1);

            	        if ( (LA18_0==70) ) {
            	            int LA18_2 = input.LA(2);

            	            if ( (synpred18_Pddl()) ) {
            	                alt18=1;
            	            }


            	        }


            	        switch (alt18) {
            	    	case 1 :
            	    	    // Pddl.g:168:5: atomicFunctionSkeleton
            	    	    {
            	    	    pushFollow(FOLLOW_atomicFunctionSkeleton_in_functionList852);
            	    	    atomicFunctionSkeleton46=atomicFunctionSkeleton();

            	    	    state._fsp--;
            	    	    if (state.failed) return retval;
            	    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicFunctionSkeleton46.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt18 >= 1 ) break loop18;
            	    	    if (state.backtracking>0) {state.failed=true; return retval;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(18, input);
            	                throw eee;
            	        }
            	        cnt18++;
            	    } while (true);


            	    // Pddl.g:168:29: ( '-' functionType )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==74) ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // Pddl.g:168:30: '-' functionType
            	            {
            	            char_literal47=(Token)match(input,74,FOLLOW_74_in_functionList856); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal47_tree = 
            	            (Object)adaptor.create(char_literal47)
            	            ;
            	            adaptor.addChild(root_0, char_literal47_tree);
            	            }

            	            pushFollow(FOLLOW_functionType_in_functionList858);
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
            } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicFunctionSkeleton"
    // Pddl.g:171:1: atomicFunctionSkeleton : '(' ! functionSymbol ^ typedVariableList ')' !;
    public final PddlParser.atomicFunctionSkeleton_return atomicFunctionSkeleton() throws RecognitionException {
        PddlParser.atomicFunctionSkeleton_return retval = new PddlParser.atomicFunctionSkeleton_return();
        retval.start = input.LT(1);

        int atomicFunctionSkeleton_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal49=null;
        Token char_literal52=null;
        PddlParser.functionSymbol_return functionSymbol50 =null;

        PddlParser.typedVariableList_return typedVariableList51 =null;


        Object char_literal49_tree=null;
        Object char_literal52_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // Pddl.g:172:2: ( '(' ! functionSymbol ^ typedVariableList ')' !)
            // Pddl.g:172:4: '(' ! functionSymbol ^ typedVariableList ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal49=(Token)match(input,70,FOLLOW_70_in_atomicFunctionSkeleton874); if (state.failed) return retval;

            pushFollow(FOLLOW_functionSymbol_in_atomicFunctionSkeleton877);
            functionSymbol50=functionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(functionSymbol50.getTree(), root_0);

            pushFollow(FOLLOW_typedVariableList_in_atomicFunctionSkeleton880);
            typedVariableList51=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList51.getTree());

            char_literal52=(Token)match(input,71,FOLLOW_71_in_atomicFunctionSkeleton882); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionSymbol"
    // Pddl.g:175:1: functionSymbol : ( NAME | '#t' );
    public final PddlParser.functionSymbol_return functionSymbol() throws RecognitionException {
        PddlParser.functionSymbol_return retval = new PddlParser.functionSymbol_return();
        retval.start = input.LT(1);

        int functionSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token set53=null;

        Object set53_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // Pddl.g:175:16: ( NAME | '#t' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set53=(Token)input.LT(1);

            if ( input.LA(1)==NAME||input.LA(1)==69 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set53)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionType"
    // Pddl.g:177:1: functionType : 'number' ;
    public final PddlParser.functionType_return functionType() throws RecognitionException {
        PddlParser.functionType_return retval = new PddlParser.functionType_return();
        retval.start = input.LT(1);

        int functionType_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal54=null;

        Object string_literal54_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // Pddl.g:177:14: ( 'number' )
            // Pddl.g:177:16: 'number'
            {
            root_0 = (Object)adaptor.nil();


            string_literal54=(Token)match(input,130,FOLLOW_130_in_functionType905); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal54_tree = 
            (Object)adaptor.create(string_literal54)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantsDef"
    // Pddl.g:179:1: constantsDef : '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) ;
    public final PddlParser.constantsDef_return constantsDef() throws RecognitionException {
        PddlParser.constantsDef_return retval = new PddlParser.constantsDef_return();
        retval.start = input.LT(1);

        int constantsDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal55=null;
        Token string_literal56=null;
        Token char_literal58=null;
        PddlParser.typedNameList_return typedNameList57 =null;


        Object char_literal55_tree=null;
        Object string_literal56_tree=null;
        Object char_literal58_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // Pddl.g:180:2: ( '(' ':constants' typedNameList ')' -> ^( CONSTANTS typedNameList ) )
            // Pddl.g:180:4: '(' ':constants' typedNameList ')'
            {
            char_literal55=(Token)match(input,70,FOLLOW_70_in_constantsDef916); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal55);


            string_literal56=(Token)match(input,78,FOLLOW_78_in_constantsDef918); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_78.add(string_literal56);


            pushFollow(FOLLOW_typedNameList_in_constantsDef920);
            typedNameList57=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList57.getTree());

            char_literal58=(Token)match(input,71,FOLLOW_71_in_constantsDef922); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal58);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 181:2: -> ^( CONSTANTS typedNameList )
            {
                // Pddl.g:181:5: ^( CONSTANTS typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CONSTANTS, "CONSTANTS")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predicatesDef"
    // Pddl.g:184:1: predicatesDef : '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) ;
    public final PddlParser.predicatesDef_return predicatesDef() throws RecognitionException {
        PddlParser.predicatesDef_return retval = new PddlParser.predicatesDef_return();
        retval.start = input.LT(1);

        int predicatesDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal59=null;
        Token string_literal60=null;
        Token char_literal62=null;
        PddlParser.atomicFormulaSkeleton_return atomicFormulaSkeleton61 =null;


        Object char_literal59_tree=null;
        Object string_literal60_tree=null;
        Object char_literal62_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_95=new RewriteRuleTokenStream(adaptor,"token 95");
        RewriteRuleSubtreeStream stream_atomicFormulaSkeleton=new RewriteRuleSubtreeStream(adaptor,"rule atomicFormulaSkeleton");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // Pddl.g:185:2: ( '(' ':predicates' ( atomicFormulaSkeleton )+ ')' -> ^( PREDICATES ( atomicFormulaSkeleton )+ ) )
            // Pddl.g:185:4: '(' ':predicates' ( atomicFormulaSkeleton )+ ')'
            {
            char_literal59=(Token)match(input,70,FOLLOW_70_in_predicatesDef942); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal59);


            string_literal60=(Token)match(input,95,FOLLOW_95_in_predicatesDef944); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_95.add(string_literal60);


            // Pddl.g:185:22: ( atomicFormulaSkeleton )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==70) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // Pddl.g:185:22: atomicFormulaSkeleton
            	    {
            	    pushFollow(FOLLOW_atomicFormulaSkeleton_in_predicatesDef946);
            	    atomicFormulaSkeleton61=atomicFormulaSkeleton();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_atomicFormulaSkeleton.add(atomicFormulaSkeleton61.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            char_literal62=(Token)match(input,71,FOLLOW_71_in_predicatesDef949); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal62);


            // AST REWRITE
            // elements: atomicFormulaSkeleton
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 186:2: -> ^( PREDICATES ( atomicFormulaSkeleton )+ )
            {
                // Pddl.g:186:5: ^( PREDICATES ( atomicFormulaSkeleton )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PREDICATES, "PREDICATES")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicFormulaSkeleton"
    // Pddl.g:189:1: atomicFormulaSkeleton : '(' ! predicate ^ typedVariableList ')' !;
    public final PddlParser.atomicFormulaSkeleton_return atomicFormulaSkeleton() throws RecognitionException {
        PddlParser.atomicFormulaSkeleton_return retval = new PddlParser.atomicFormulaSkeleton_return();
        retval.start = input.LT(1);

        int atomicFormulaSkeleton_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal63=null;
        Token char_literal66=null;
        PddlParser.predicate_return predicate64 =null;

        PddlParser.typedVariableList_return typedVariableList65 =null;


        Object char_literal63_tree=null;
        Object char_literal66_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // Pddl.g:190:2: ( '(' ! predicate ^ typedVariableList ')' !)
            // Pddl.g:190:4: '(' ! predicate ^ typedVariableList ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal63=(Token)match(input,70,FOLLOW_70_in_atomicFormulaSkeleton970); if (state.failed) return retval;

            pushFollow(FOLLOW_predicate_in_atomicFormulaSkeleton973);
            predicate64=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(predicate64.getTree(), root_0);

            pushFollow(FOLLOW_typedVariableList_in_atomicFormulaSkeleton976);
            typedVariableList65=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList65.getTree());

            char_literal66=(Token)match(input,71,FOLLOW_71_in_atomicFormulaSkeleton978); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "predicate"
    // Pddl.g:193:1: predicate : NAME ;
    public final PddlParser.predicate_return predicate() throws RecognitionException {
        PddlParser.predicate_return retval = new PddlParser.predicate_return();
        retval.start = input.LT(1);

        int predicate_StartIndex = input.index();

        Object root_0 = null;

        Token NAME67=null;

        Object NAME67_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // Pddl.g:193:11: ( NAME )
            // Pddl.g:193:13: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME67=(Token)match(input,NAME,FOLLOW_NAME_in_predicate989); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME67_tree = 
            (Object)adaptor.create(NAME67)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typedVariableList"
    // Pddl.g:196:1: typedVariableList : ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) ;
    public final PddlParser.typedVariableList_return typedVariableList() throws RecognitionException {
        PddlParser.typedVariableList_return retval = new PddlParser.typedVariableList_return();
        retval.start = input.LT(1);

        int typedVariableList_StartIndex = input.index();

        Object root_0 = null;

        Token VARIABLE68=null;
        Token VARIABLE70=null;
        PddlParser.singleTypeVarList_return singleTypeVarList69 =null;


        Object VARIABLE68_tree=null;
        Object VARIABLE70_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // Pddl.g:197:5: ( ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* ) )
            // Pddl.g:197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            {
            root_0 = (Object)adaptor.nil();


            // Pddl.g:197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )
            int alt25=2;
            alt25 = dfa25.predict(input);
            switch (alt25) {
                case 1 :
                    // Pddl.g:197:8: ( VARIABLE )*
                    {
                    // Pddl.g:197:8: ( VARIABLE )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==VARIABLE) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // Pddl.g:197:8: VARIABLE
                    	    {
                    	    VARIABLE68=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList1004); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    VARIABLE68_tree = 
                    	    (Object)adaptor.create(VARIABLE68)
                    	    ;
                    	    adaptor.addChild(root_0, VARIABLE68_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Pddl.g:197:20: ( singleTypeVarList )+ ( VARIABLE )*
                    {
                    // Pddl.g:197:20: ( singleTypeVarList )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        alt23 = dfa23.predict(input);
                        switch (alt23) {
                    	case 1 :
                    	    // Pddl.g:197:20: singleTypeVarList
                    	    {
                    	    pushFollow(FOLLOW_singleTypeVarList_in_typedVariableList1009);
                    	    singleTypeVarList69=singleTypeVarList();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, singleTypeVarList69.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt23 >= 1 ) break loop23;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
                    } while (true);


                    // Pddl.g:197:39: ( VARIABLE )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==VARIABLE) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // Pddl.g:197:39: VARIABLE
                    	    {
                    	    VARIABLE70=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_typedVariableList1012); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    VARIABLE70_tree = 
                    	    (Object)adaptor.create(VARIABLE70)
                    	    ;
                    	    adaptor.addChild(root_0, VARIABLE70_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "singleTypeVarList"
    // Pddl.g:200:1: singleTypeVarList : ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ ;
    public final PddlParser.singleTypeVarList_return singleTypeVarList() throws RecognitionException {
        PddlParser.singleTypeVarList_return retval = new PddlParser.singleTypeVarList_return();
        retval.start = input.LT(1);

        int singleTypeVarList_StartIndex = input.index();

        Object root_0 = null;

        Token VARIABLE71=null;
        Token char_literal72=null;
        PddlParser.type_return t =null;


        Object VARIABLE71_tree=null;
        Object char_literal72_tree=null;
        RewriteRuleTokenStream stream_VARIABLE=new RewriteRuleTokenStream(adaptor,"token VARIABLE");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // Pddl.g:201:5: ( ( ( VARIABLE )+ '-' t= type ) -> ( ^( VARIABLE $t) )+ )
            // Pddl.g:201:7: ( ( VARIABLE )+ '-' t= type )
            {
            // Pddl.g:201:7: ( ( VARIABLE )+ '-' t= type )
            // Pddl.g:201:8: ( VARIABLE )+ '-' t= type
            {
            // Pddl.g:201:8: ( VARIABLE )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==VARIABLE) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // Pddl.g:201:8: VARIABLE
            	    {
            	    VARIABLE71=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_singleTypeVarList1032); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_VARIABLE.add(VARIABLE71);


            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);


            char_literal72=(Token)match(input,74,FOLLOW_74_in_singleTypeVarList1035); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_74.add(char_literal72);


            pushFollow(FOLLOW_type_in_singleTypeVarList1039);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());

            }


            // AST REWRITE
            // elements: t, VARIABLE
            // token labels: 
            // rule labels: t, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 202:7: -> ( ^( VARIABLE $t) )+
            {
                if ( !(stream_t.hasNext()||stream_VARIABLE.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_t.hasNext()||stream_VARIABLE.hasNext() ) {
                    // Pddl.g:202:10: ^( VARIABLE $t)
                    {
                    Object root_1 = (Object)adaptor.nil();
                    root_1 = (Object)adaptor.becomeRoot(
                    stream_VARIABLE.nextNode()
                    , root_1);

                    adaptor.addChild(root_1, stream_t.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_t.reset();
                stream_VARIABLE.reset();

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constraints"
    // Pddl.g:205:1: constraints : '(' ! ':constraints' ^ conGD ')' !;
    public final PddlParser.constraints_return constraints() throws RecognitionException {
        PddlParser.constraints_return retval = new PddlParser.constraints_return();
        retval.start = input.LT(1);

        int constraints_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal73=null;
        Token string_literal74=null;
        Token char_literal76=null;
        PddlParser.conGD_return conGD75 =null;


        Object char_literal73_tree=null;
        Object string_literal74_tree=null;
        Object char_literal76_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // Pddl.g:206:2: ( '(' ! ':constraints' ^ conGD ')' !)
            // Pddl.g:206:4: '(' ! ':constraints' ^ conGD ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal73=(Token)match(input,70,FOLLOW_70_in_constraints1070); if (state.failed) return retval;

            string_literal74=(Token)match(input,80,FOLLOW_80_in_constraints1073); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal74_tree = 
            (Object)adaptor.create(string_literal74)
            ;
            root_0 = (Object)adaptor.becomeRoot(string_literal74_tree, root_0);
            }

            pushFollow(FOLLOW_conGD_in_constraints1076);
            conGD75=conGD();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD75.getTree());

            char_literal76=(Token)match(input,71,FOLLOW_71_in_constraints1078); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structureDef"
    // Pddl.g:209:1: structureDef : ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef );
    public final PddlParser.structureDef_return structureDef() throws RecognitionException {
        PddlParser.structureDef_return retval = new PddlParser.structureDef_return();
        retval.start = input.LT(1);

        int structureDef_StartIndex = input.index();

        Object root_0 = null;

        PddlParser.actionDef_return actionDef77 =null;

        PddlParser.durativeActionDef_return durativeActionDef78 =null;

        PddlParser.derivedDef_return derivedDef79 =null;

        PddlParser.constraintDef_return constraintDef80 =null;

        PddlParser.processDef_return processDef81 =null;

        PddlParser.eventDef_return eventDef82 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

            // Pddl.g:210:2: ( actionDef | durativeActionDef | derivedDef | constraintDef | processDef | eventDef )
            int alt27=6;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==70) ) {
                switch ( input.LA(2) ) {
                case 76:
                    {
                    alt27=1;
                    }
                    break;
                case 84:
                    {
                    alt27=2;
                    }
                    break;
                case 81:
                    {
                    alt27=3;
                    }
                    break;
                case 79:
                    {
                    alt27=4;
                    }
                    break;
                case 96:
                    {
                    alt27=5;
                    }
                    break;
                case 86:
                    {
                    alt27=6;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;

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
                    // Pddl.g:210:4: actionDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_actionDef_in_structureDef1090);
                    actionDef77=actionDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, actionDef77.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:211:4: durativeActionDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_durativeActionDef_in_structureDef1095);
                    durativeActionDef78=durativeActionDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, durativeActionDef78.getTree());

                    }
                    break;
                case 3 :
                    // Pddl.g:212:4: derivedDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_derivedDef_in_structureDef1100);
                    derivedDef79=derivedDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, derivedDef79.getTree());

                    }
                    break;
                case 4 :
                    // Pddl.g:213:4: constraintDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_constraintDef_in_structureDef1105);
                    constraintDef80=constraintDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constraintDef80.getTree());

                    }
                    break;
                case 5 :
                    // Pddl.g:214:4: processDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_processDef_in_structureDef1110);
                    processDef81=processDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, processDef81.getTree());

                    }
                    break;
                case 6 :
                    // Pddl.g:215:4: eventDef
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_eventDef_in_structureDef1115);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionDef"
    // Pddl.g:221:1: actionDef : '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.actionDef_return actionDef() throws RecognitionException {
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
        PddlParser.actionSymbol_return actionSymbol85 =null;

        PddlParser.typedVariableList_return typedVariableList88 =null;

        PddlParser.actionDefBody_return actionDefBody90 =null;


        Object char_literal83_tree=null;
        Object string_literal84_tree=null;
        Object string_literal86_tree=null;
        Object char_literal87_tree=null;
        Object char_literal89_tree=null;
        Object char_literal91_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

            // Pddl.g:222:2: ( '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:222:4: '(' ':action' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
            char_literal83=(Token)match(input,70,FOLLOW_70_in_actionDef1130); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal83);


            string_literal84=(Token)match(input,76,FOLLOW_76_in_actionDef1132); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_76.add(string_literal84);


            pushFollow(FOLLOW_actionSymbol_in_actionDef1134);
            actionSymbol85=actionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol85.getTree());

            string_literal86=(Token)match(input,93,FOLLOW_93_in_actionDef1143); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_93.add(string_literal86);


            char_literal87=(Token)match(input,70,FOLLOW_70_in_actionDef1146); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal87);


            pushFollow(FOLLOW_typedVariableList_in_actionDef1148);
            typedVariableList88=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList88.getTree());

            char_literal89=(Token)match(input,71,FOLLOW_71_in_actionDef1150); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal89);


            pushFollow(FOLLOW_actionDefBody_in_actionDef1163);
            actionDefBody90=actionDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody90.getTree());

            char_literal91=(Token)match(input,71,FOLLOW_71_in_actionDef1165); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal91);


            // AST REWRITE
            // elements: actionSymbol, typedVariableList, actionDefBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 225:8: -> ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
            {
                // Pddl.g:225:11: ^( ACTION actionSymbol ( typedVariableList )? actionDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ACTION, "ACTION")
                , root_1);

                adaptor.addChild(root_1, stream_actionSymbol.nextTree());

                // Pddl.g:225:33: ( typedVariableList )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "eventDef"
    // Pddl.g:227:1: eventDef : '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.eventDef_return eventDef() throws RecognitionException {
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
        PddlParser.actionSymbol_return actionSymbol94 =null;

        PddlParser.typedVariableList_return typedVariableList97 =null;

        PddlParser.actionDefBody_return actionDefBody99 =null;


        Object char_literal92_tree=null;
        Object string_literal93_tree=null;
        Object string_literal95_tree=null;
        Object char_literal96_tree=null;
        Object char_literal98_tree=null;
        Object char_literal100_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }

            // Pddl.g:228:2: ( '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:228:4: '(' ':event' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
            char_literal92=(Token)match(input,70,FOLLOW_70_in_eventDef1198); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal92);


            string_literal93=(Token)match(input,86,FOLLOW_86_in_eventDef1200); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(string_literal93);


            pushFollow(FOLLOW_actionSymbol_in_eventDef1202);
            actionSymbol94=actionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol94.getTree());

            string_literal95=(Token)match(input,93,FOLLOW_93_in_eventDef1211); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_93.add(string_literal95);


            char_literal96=(Token)match(input,70,FOLLOW_70_in_eventDef1214); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal96);


            pushFollow(FOLLOW_typedVariableList_in_eventDef1216);
            typedVariableList97=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList97.getTree());

            char_literal98=(Token)match(input,71,FOLLOW_71_in_eventDef1218); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal98);


            pushFollow(FOLLOW_actionDefBody_in_eventDef1231);
            actionDefBody99=actionDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody99.getTree());

            char_literal100=(Token)match(input,71,FOLLOW_71_in_eventDef1233); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal100);


            // AST REWRITE
            // elements: actionSymbol, actionDefBody, typedVariableList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 231:8: -> ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
            {
                // Pddl.g:231:11: ^( EVENT actionSymbol ( typedVariableList )? actionDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(EVENT, "EVENT")
                , root_1);

                adaptor.addChild(root_1, stream_actionSymbol.nextTree());

                // Pddl.g:231:32: ( typedVariableList )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "processDef"
    // Pddl.g:233:1: processDef : '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) ;
    public final PddlParser.processDef_return processDef() throws RecognitionException {
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
        PddlParser.actionSymbol_return actionSymbol103 =null;

        PddlParser.typedVariableList_return typedVariableList106 =null;

        PddlParser.actionDefBody_return actionDefBody108 =null;


        Object char_literal101_tree=null;
        Object string_literal102_tree=null;
        Object string_literal104_tree=null;
        Object char_literal105_tree=null;
        Object char_literal107_tree=null;
        Object char_literal109_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_96=new RewriteRuleTokenStream(adaptor,"token 96");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionDefBody=new RewriteRuleSubtreeStream(adaptor,"rule actionDefBody");
        RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }

            // Pddl.g:234:2: ( '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')' -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody ) )
            // Pddl.g:234:4: '(' ':process' actionSymbol ':parameters' '(' typedVariableList ')' actionDefBody ')'
            {
            char_literal101=(Token)match(input,70,FOLLOW_70_in_processDef1266); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal101);


            string_literal102=(Token)match(input,96,FOLLOW_96_in_processDef1268); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_96.add(string_literal102);


            pushFollow(FOLLOW_actionSymbol_in_processDef1270);
            actionSymbol103=actionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol103.getTree());

            string_literal104=(Token)match(input,93,FOLLOW_93_in_processDef1279); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_93.add(string_literal104);


            char_literal105=(Token)match(input,70,FOLLOW_70_in_processDef1282); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal105);


            pushFollow(FOLLOW_typedVariableList_in_processDef1284);
            typedVariableList106=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList106.getTree());

            char_literal107=(Token)match(input,71,FOLLOW_71_in_processDef1286); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal107);


            pushFollow(FOLLOW_actionDefBody_in_processDef1299);
            actionDefBody108=actionDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionDefBody.add(actionDefBody108.getTree());

            char_literal109=(Token)match(input,71,FOLLOW_71_in_processDef1301); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal109);


            // AST REWRITE
            // elements: typedVariableList, actionSymbol, actionDefBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 237:8: -> ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
            {
                // Pddl.g:237:11: ^( PROCESS actionSymbol ( typedVariableList )? actionDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROCESS, "PROCESS")
                , root_1);

                adaptor.addChild(root_1, stream_actionSymbol.nextTree());

                // Pddl.g:237:34: ( typedVariableList )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constraintDef"
    // Pddl.g:240:1: constraintDef : '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) ;
    public final PddlParser.constraintDef_return constraintDef() throws RecognitionException {
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
        PddlParser.constraintSymbol_return constraintSymbol112 =null;

        PddlParser.typedVariableList_return typedVariableList115 =null;

        PddlParser.constraintDefBody_return constraintDefBody117 =null;


        Object char_literal110_tree=null;
        Object string_literal111_tree=null;
        Object string_literal113_tree=null;
        Object char_literal114_tree=null;
        Object char_literal116_tree=null;
        Object char_literal118_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_constraintDefBody=new RewriteRuleSubtreeStream(adaptor,"rule constraintDefBody");
        RewriteRuleSubtreeStream stream_constraintSymbol=new RewriteRuleSubtreeStream(adaptor,"rule constraintSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }

            // Pddl.g:241:2: ( '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')' -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody ) )
            // Pddl.g:241:4: '(' ':constraint' constraintSymbol ':parameters' '(' typedVariableList ')' constraintDefBody ')'
            {
            char_literal110=(Token)match(input,70,FOLLOW_70_in_constraintDef1335); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal110);


            string_literal111=(Token)match(input,79,FOLLOW_79_in_constraintDef1337); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_79.add(string_literal111);


            pushFollow(FOLLOW_constraintSymbol_in_constraintDef1339);
            constraintSymbol112=constraintSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraintSymbol.add(constraintSymbol112.getTree());

            string_literal113=(Token)match(input,93,FOLLOW_93_in_constraintDef1348); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_93.add(string_literal113);


            char_literal114=(Token)match(input,70,FOLLOW_70_in_constraintDef1351); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal114);


            pushFollow(FOLLOW_typedVariableList_in_constraintDef1353);
            typedVariableList115=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList115.getTree());

            char_literal116=(Token)match(input,71,FOLLOW_71_in_constraintDef1355); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal116);


            pushFollow(FOLLOW_constraintDefBody_in_constraintDef1368);
            constraintDefBody117=constraintDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_constraintDefBody.add(constraintDefBody117.getTree());

            char_literal118=(Token)match(input,71,FOLLOW_71_in_constraintDef1370); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal118);


            // AST REWRITE
            // elements: typedVariableList, constraintSymbol, constraintDefBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 244:8: -> ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
            {
                // Pddl.g:244:11: ^( GLOBAL_CONSTRAINT constraintSymbol ( typedVariableList )? constraintDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(GLOBAL_CONSTRAINT, "GLOBAL_CONSTRAINT")
                , root_1);

                adaptor.addChild(root_1, stream_constraintSymbol.nextTree());

                // Pddl.g:244:48: ( typedVariableList )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionSymbol"
    // Pddl.g:248:1: actionSymbol : NAME ;
    public final PddlParser.actionSymbol_return actionSymbol() throws RecognitionException {
        PddlParser.actionSymbol_return retval = new PddlParser.actionSymbol_return();
        retval.start = input.LT(1);

        int actionSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token NAME119=null;

        Object NAME119_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }

            // Pddl.g:248:14: ( NAME )
            // Pddl.g:248:16: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME119=(Token)match(input,NAME,FOLLOW_NAME_in_actionSymbol1404); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME119_tree = 
            (Object)adaptor.create(NAME119)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constraintSymbol"
    // Pddl.g:250:1: constraintSymbol : NAME ;
    public final PddlParser.constraintSymbol_return constraintSymbol() throws RecognitionException {
        PddlParser.constraintSymbol_return retval = new PddlParser.constraintSymbol_return();
        retval.start = input.LT(1);

        int constraintSymbol_StartIndex = input.index();

        Object root_0 = null;

        Token NAME120=null;

        Object NAME120_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }

            // Pddl.g:250:18: ( NAME )
            // Pddl.g:250:20: NAME
            {
            root_0 = (Object)adaptor.nil();


            NAME120=(Token)match(input,NAME,FOLLOW_NAME_in_constraintSymbol1413); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME120_tree = 
            (Object)adaptor.create(NAME120)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actionDefBody"
    // Pddl.g:255:1: actionDefBody : ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) ;
    public final PddlParser.actionDefBody_return actionDefBody() throws RecognitionException {
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
        PddlParser.goalDesc_return goalDesc124 =null;

        PddlParser.effect_return effect128 =null;


        Object string_literal121_tree=null;
        Object char_literal122_tree=null;
        Object char_literal123_tree=null;
        Object string_literal125_tree=null;
        Object char_literal126_tree=null;
        Object char_literal127_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_94=new RewriteRuleTokenStream(adaptor,"token 94");
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        RewriteRuleSubtreeStream stream_effect=new RewriteRuleSubtreeStream(adaptor,"rule effect");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }

            // Pddl.g:256:2: ( ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )? -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? ) )
            // Pddl.g:256:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )? ( ':effect' ( ( '(' ')' ) | effect ) )?
            {
            // Pddl.g:256:4: ( ':precondition' ( ( '(' ')' ) | goalDesc ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==94) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // Pddl.g:256:6: ':precondition' ( ( '(' ')' ) | goalDesc )
                    {
                    string_literal121=(Token)match(input,94,FOLLOW_94_in_actionDefBody1428); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_94.add(string_literal121);


                    // Pddl.g:256:22: ( ( '(' ')' ) | goalDesc )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==70) ) {
                        int LA28_1 = input.LA(2);

                        if ( (LA28_1==71) ) {
                            alt28=1;
                        }
                        else if ( (LA28_1==NAME||(LA28_1 >= 99 && LA28_1 <= 103)||LA28_1==110||(LA28_1 >= 120 && LA28_1 <= 121)||LA28_1==124||LA28_1==129||(LA28_1 >= 131 && LA28_1 <= 132)) ) {
                            alt28=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 28, 1, input);

                            throw nvae;

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
                            // Pddl.g:256:23: ( '(' ')' )
                            {
                            // Pddl.g:256:23: ( '(' ')' )
                            // Pddl.g:256:24: '(' ')'
                            {
                            char_literal122=(Token)match(input,70,FOLLOW_70_in_actionDefBody1432); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_70.add(char_literal122);


                            char_literal123=(Token)match(input,71,FOLLOW_71_in_actionDefBody1434); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_71.add(char_literal123);


                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:256:35: goalDesc
                            {
                            pushFollow(FOLLOW_goalDesc_in_actionDefBody1439);
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


            // Pddl.g:257:4: ( ':effect' ( ( '(' ')' ) | effect ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==85) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // Pddl.g:257:6: ':effect' ( ( '(' ')' ) | effect )
                    {
                    string_literal125=(Token)match(input,85,FOLLOW_85_in_actionDefBody1449); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_85.add(string_literal125);


                    // Pddl.g:257:16: ( ( '(' ')' ) | effect )
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==70) ) {
                        int LA30_1 = input.LA(2);

                        if ( (LA30_1==71) ) {
                            alt30=1;
                        }
                        else if ( (LA30_1==NAME||(LA30_1 >= 110 && LA30_1 <= 111)||LA30_1==115||LA30_1==121||LA30_1==125||LA30_1==129||LA30_1==131||(LA30_1 >= 136 && LA30_1 <= 137)||LA30_1==144) ) {
                            alt30=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 30, 1, input);

                            throw nvae;

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
                            // Pddl.g:257:17: ( '(' ')' )
                            {
                            // Pddl.g:257:17: ( '(' ')' )
                            // Pddl.g:257:18: '(' ')'
                            {
                            char_literal126=(Token)match(input,70,FOLLOW_70_in_actionDefBody1453); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_70.add(char_literal126);


                            char_literal127=(Token)match(input,71,FOLLOW_71_in_actionDefBody1455); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_71.add(char_literal127);


                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:257:29: effect
                            {
                            pushFollow(FOLLOW_effect_in_actionDefBody1460);
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
            // elements: goalDesc, effect
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 258:4: -> ^( PRECONDITION ( goalDesc )? ) ^( EFFECT ( effect )? )
            {
                // Pddl.g:258:7: ^( PRECONDITION ( goalDesc )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRECONDITION, "PRECONDITION")
                , root_1);

                // Pddl.g:258:22: ( goalDesc )?
                if ( stream_goalDesc.hasNext() ) {
                    adaptor.addChild(root_1, stream_goalDesc.nextTree());

                }
                stream_goalDesc.reset();

                adaptor.addChild(root_0, root_1);
                }

                // Pddl.g:258:33: ^( EFFECT ( effect )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(EFFECT, "EFFECT")
                , root_1);

                // Pddl.g:258:42: ( effect )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constraintDefBody"
    // Pddl.g:261:1: constraintDefBody : ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) ;
    public final PddlParser.constraintDefBody_return constraintDefBody() throws RecognitionException {
        PddlParser.constraintDefBody_return retval = new PddlParser.constraintDefBody_return();
        retval.start = input.LT(1);

        int constraintDefBody_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal129=null;
        Token char_literal130=null;
        Token char_literal131=null;
        PddlParser.goalDesc_return goalDesc132 =null;


        Object string_literal129_tree=null;
        Object char_literal130_tree=null;
        Object char_literal131_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }

            // Pddl.g:262:2: ( ( ':condition' ( ( '(' ')' ) | goalDesc ) )? -> ^( PRECONDITION ( goalDesc )? ) )
            // Pddl.g:262:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
            {
            // Pddl.g:262:4: ( ':condition' ( ( '(' ')' ) | goalDesc ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==77) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // Pddl.g:262:6: ':condition' ( ( '(' ')' ) | goalDesc )
                    {
                    string_literal129=(Token)match(input,77,FOLLOW_77_in_constraintDefBody1495); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_77.add(string_literal129);


                    // Pddl.g:262:19: ( ( '(' ')' ) | goalDesc )
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==70) ) {
                        int LA32_1 = input.LA(2);

                        if ( (LA32_1==71) ) {
                            alt32=1;
                        }
                        else if ( (LA32_1==NAME||(LA32_1 >= 99 && LA32_1 <= 103)||LA32_1==110||(LA32_1 >= 120 && LA32_1 <= 121)||LA32_1==124||LA32_1==129||(LA32_1 >= 131 && LA32_1 <= 132)) ) {
                            alt32=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 32, 1, input);

                            throw nvae;

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
                            // Pddl.g:262:20: ( '(' ')' )
                            {
                            // Pddl.g:262:20: ( '(' ')' )
                            // Pddl.g:262:21: '(' ')'
                            {
                            char_literal130=(Token)match(input,70,FOLLOW_70_in_constraintDefBody1499); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_70.add(char_literal130);


                            char_literal131=(Token)match(input,71,FOLLOW_71_in_constraintDefBody1501); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_71.add(char_literal131);


                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:262:32: goalDesc
                            {
                            pushFollow(FOLLOW_goalDesc_in_constraintDefBody1506);
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
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 263:4: -> ^( PRECONDITION ( goalDesc )? )
            {
                // Pddl.g:263:7: ^( PRECONDITION ( goalDesc )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRECONDITION, "PRECONDITION")
                , root_1);

                // Pddl.g:263:22: ( goalDesc )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "belief"
    // Pddl.g:278:1: belief : goalDesc ( initEl )* -> ^( BELIEF goalDesc ( initEl )* ) ;
    public final PddlParser.belief_return belief() throws RecognitionException {
        PddlParser.belief_return retval = new PddlParser.belief_return();
        retval.start = input.LT(1);

        int belief_StartIndex = input.index();

        Object root_0 = null;

        PddlParser.goalDesc_return goalDesc133 =null;

        PddlParser.initEl_return initEl134 =null;


        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        RewriteRuleSubtreeStream stream_initEl=new RewriteRuleSubtreeStream(adaptor,"rule initEl");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }

            // Pddl.g:279:2: ( goalDesc ( initEl )* -> ^( BELIEF goalDesc ( initEl )* ) )
            // Pddl.g:279:4: goalDesc ( initEl )*
            {
            pushFollow(FOLLOW_goalDesc_in_belief1544);
            goalDesc133=goalDesc();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc133.getTree());

            // Pddl.g:280:4: ( initEl )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==70) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // Pddl.g:280:4: initEl
            	    {
            	    pushFollow(FOLLOW_initEl_in_belief1549);
            	    initEl134=initEl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_initEl.add(initEl134.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            // AST REWRITE
            // elements: initEl, goalDesc
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 280:12: -> ^( BELIEF goalDesc ( initEl )* )
            {
                // Pddl.g:280:15: ^( BELIEF goalDesc ( initEl )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(BELIEF, "BELIEF")
                , root_1);

                adaptor.addChild(root_1, stream_goalDesc.nextTree());

                // Pddl.g:280:33: ( initEl )*
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "goalDesc"
    // Pddl.g:283:1: goalDesc : ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) );
    public final PddlParser.goalDesc_return goalDesc() throws RecognitionException {
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
        PddlParser.atomicTermFormula_return atomicTermFormula135 =null;

        PddlParser.goalDesc_return goalDesc138 =null;

        PddlParser.goalDesc_return goalDesc142 =null;

        PddlParser.goalDesc_return goalDesc146 =null;

        PddlParser.goalDesc_return goalDesc150 =null;

        PddlParser.goalDesc_return goalDesc154 =null;

        PddlParser.goalDesc_return goalDesc155 =null;

        PddlParser.typedVariableList_return typedVariableList160 =null;

        PddlParser.goalDesc_return goalDesc162 =null;

        PddlParser.typedVariableList_return typedVariableList167 =null;

        PddlParser.goalDesc_return goalDesc169 =null;

        PddlParser.fComp_return fComp171 =null;

        PddlParser.equality_return equality172 =null;


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
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_132=new RewriteRuleTokenStream(adaptor,"token 132");
        RewriteRuleTokenStream stream_121=new RewriteRuleTokenStream(adaptor,"token 121");
        RewriteRuleTokenStream stream_124=new RewriteRuleTokenStream(adaptor,"token 124");
        RewriteRuleTokenStream stream_129=new RewriteRuleTokenStream(adaptor,"token 129");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
        RewriteRuleTokenStream stream_120=new RewriteRuleTokenStream(adaptor,"token 120");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_fComp=new RewriteRuleSubtreeStream(adaptor,"rule fComp");
        RewriteRuleSubtreeStream stream_equality=new RewriteRuleSubtreeStream(adaptor,"rule equality");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }

            // Pddl.g:284:2: ( atomicTermFormula | '(' 'and' ( goalDesc )* ')' -> ^( AND_GD ( goalDesc )* ) | '(' 'or' ( goalDesc )* ')' -> ^( OR_GD ( goalDesc )* ) | '(' 'not' goalDesc ')' -> ^( NOT_GD goalDesc ) | '(' 'oneof' ( goalDesc )* ')' -> ^( ONEOF ( goalDesc )* ) | '(' 'imply' goalDesc goalDesc ')' -> ^( IMPLY_GD goalDesc goalDesc ) | '(' 'exists' '(' typedVariableList ')' goalDesc ')' -> ^( EXISTS_GD typedVariableList goalDesc ) | '(' 'forall' '(' typedVariableList ')' goalDesc ')' -> ^( FORALL_GD typedVariableList goalDesc ) | fComp -> ^( COMPARISON_GD fComp ) | equality -> ^( EQUALITY_CON equality ) )
            int alt38=10;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==70) ) {
                switch ( input.LA(2) ) {
                case 110:
                    {
                    alt38=2;
                    }
                    break;
                case 132:
                    {
                    alt38=3;
                    }
                    break;
                case 129:
                    {
                    alt38=4;
                    }
                    break;
                case 131:
                    {
                    alt38=5;
                    }
                    break;
                case 124:
                    {
                    alt38=6;
                    }
                    break;
                case 120:
                    {
                    alt38=7;
                    }
                    break;
                case 121:
                    {
                    alt38=8;
                    }
                    break;
                case 101:
                    {
                    switch ( input.LA(3) ) {
                    case NAME:
                        {
                        switch ( input.LA(4) ) {
                        case NAME:
                            {
                            int LA38_14 = input.LA(5);

                            if ( (LA38_14==71) ) {
                                int LA38_15 = input.LA(6);

                                if ( (synpred51_Pddl()) ) {
                                    alt38=9;
                                }
                                else if ( (true) ) {
                                    alt38=10;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 38, 15, input);

                                    throw nvae;

                                }
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 38, 14, input);

                                throw nvae;

                            }
                            }
                            break;
                        case NUMBER:
                        case 69:
                        case 70:
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
                            NoViableAltException nvae =
                                new NoViableAltException("", 38, 12, input);

                            throw nvae;

                        }

                        }
                        break;
                    case NUMBER:
                    case 69:
                    case 70:
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
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 9, input);

                        throw nvae;

                    }

                    }
                    break;
                case NAME:
                    {
                    alt38=1;
                    }
                    break;
                case 99:
                case 100:
                case 102:
                case 103:
                    {
                    alt38=9;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;

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
                    // Pddl.g:284:4: atomicTermFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicTermFormula_in_goalDesc1571);
                    atomicTermFormula135=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula135.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:285:4: '(' 'and' ( goalDesc )* ')'
                    {
                    char_literal136=(Token)match(input,70,FOLLOW_70_in_goalDesc1576); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal136);


                    string_literal137=(Token)match(input,110,FOLLOW_110_in_goalDesc1578); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_110.add(string_literal137);


                    // Pddl.g:285:14: ( goalDesc )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==70) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // Pddl.g:285:14: goalDesc
                    	    {
                    	    pushFollow(FOLLOW_goalDesc_in_goalDesc1580);
                    	    goalDesc138=goalDesc();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc138.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);


                    char_literal139=(Token)match(input,71,FOLLOW_71_in_goalDesc1583); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal139);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 286:12: -> ^( AND_GD ( goalDesc )* )
                    {
                        // Pddl.g:286:15: ^( AND_GD ( goalDesc )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_GD, "AND_GD")
                        , root_1);

                        // Pddl.g:286:24: ( goalDesc )*
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
                    // Pddl.g:287:4: '(' 'or' ( goalDesc )* ')'
                    {
                    char_literal140=(Token)match(input,70,FOLLOW_70_in_goalDesc1608); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal140);


                    string_literal141=(Token)match(input,132,FOLLOW_132_in_goalDesc1610); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_132.add(string_literal141);


                    // Pddl.g:287:13: ( goalDesc )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==70) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // Pddl.g:287:13: goalDesc
                    	    {
                    	    pushFollow(FOLLOW_goalDesc_in_goalDesc1612);
                    	    goalDesc142=goalDesc();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc142.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    char_literal143=(Token)match(input,71,FOLLOW_71_in_goalDesc1615); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal143);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 288:12: -> ^( OR_GD ( goalDesc )* )
                    {
                        // Pddl.g:288:15: ^( OR_GD ( goalDesc )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OR_GD, "OR_GD")
                        , root_1);

                        // Pddl.g:288:23: ( goalDesc )*
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
                    // Pddl.g:289:4: '(' 'not' goalDesc ')'
                    {
                    char_literal144=(Token)match(input,70,FOLLOW_70_in_goalDesc1640); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal144);


                    string_literal145=(Token)match(input,129,FOLLOW_129_in_goalDesc1642); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_129.add(string_literal145);


                    pushFollow(FOLLOW_goalDesc_in_goalDesc1644);
                    goalDesc146=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc146.getTree());

                    char_literal147=(Token)match(input,71,FOLLOW_71_in_goalDesc1646); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal147);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 290:12: -> ^( NOT_GD goalDesc )
                    {
                        // Pddl.g:290:15: ^( NOT_GD goalDesc )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_GD, "NOT_GD")
                        , root_1);

                        adaptor.addChild(root_1, stream_goalDesc.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:291:4: '(' 'oneof' ( goalDesc )* ')'
                    {
                    char_literal148=(Token)match(input,70,FOLLOW_70_in_goalDesc1670); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal148);


                    string_literal149=(Token)match(input,131,FOLLOW_131_in_goalDesc1672); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_131.add(string_literal149);


                    // Pddl.g:291:17: ( goalDesc )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==70) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // Pddl.g:291:17: goalDesc
                    	    {
                    	    pushFollow(FOLLOW_goalDesc_in_goalDesc1675);
                    	    goalDesc150=goalDesc();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc150.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    char_literal151=(Token)match(input,71,FOLLOW_71_in_goalDesc1678); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal151);


                    // AST REWRITE
                    // elements: goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 291:32: -> ^( ONEOF ( goalDesc )* )
                    {
                        // Pddl.g:291:35: ^( ONEOF ( goalDesc )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ONEOF, "ONEOF")
                        , root_1);

                        // Pddl.g:291:43: ( goalDesc )*
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
                    // Pddl.g:292:4: '(' 'imply' goalDesc goalDesc ')'
                    {
                    char_literal152=(Token)match(input,70,FOLLOW_70_in_goalDesc1693); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal152);


                    string_literal153=(Token)match(input,124,FOLLOW_124_in_goalDesc1695); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_124.add(string_literal153);


                    pushFollow(FOLLOW_goalDesc_in_goalDesc1697);
                    goalDesc154=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc154.getTree());

                    pushFollow(FOLLOW_goalDesc_in_goalDesc1699);
                    goalDesc155=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc155.getTree());

                    char_literal156=(Token)match(input,71,FOLLOW_71_in_goalDesc1701); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal156);


                    // AST REWRITE
                    // elements: goalDesc, goalDesc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 293:12: -> ^( IMPLY_GD goalDesc goalDesc )
                    {
                        // Pddl.g:293:15: ^( IMPLY_GD goalDesc goalDesc )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(IMPLY_GD, "IMPLY_GD")
                        , root_1);

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
                    // Pddl.g:294:4: '(' 'exists' '(' typedVariableList ')' goalDesc ')'
                    {
                    char_literal157=(Token)match(input,70,FOLLOW_70_in_goalDesc1727); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal157);


                    string_literal158=(Token)match(input,120,FOLLOW_120_in_goalDesc1729); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_120.add(string_literal158);


                    char_literal159=(Token)match(input,70,FOLLOW_70_in_goalDesc1731); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal159);


                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1733);
                    typedVariableList160=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList160.getTree());

                    char_literal161=(Token)match(input,71,FOLLOW_71_in_goalDesc1735); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal161);


                    pushFollow(FOLLOW_goalDesc_in_goalDesc1737);
                    goalDesc162=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc162.getTree());

                    char_literal163=(Token)match(input,71,FOLLOW_71_in_goalDesc1739); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal163);


                    // AST REWRITE
                    // elements: goalDesc, typedVariableList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 295:12: -> ^( EXISTS_GD typedVariableList goalDesc )
                    {
                        // Pddl.g:295:15: ^( EXISTS_GD typedVariableList goalDesc )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(EXISTS_GD, "EXISTS_GD")
                        , root_1);

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
                    // Pddl.g:296:4: '(' 'forall' '(' typedVariableList ')' goalDesc ')'
                    {
                    char_literal164=(Token)match(input,70,FOLLOW_70_in_goalDesc1765); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal164);


                    string_literal165=(Token)match(input,121,FOLLOW_121_in_goalDesc1767); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_121.add(string_literal165);


                    char_literal166=(Token)match(input,70,FOLLOW_70_in_goalDesc1769); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal166);


                    pushFollow(FOLLOW_typedVariableList_in_goalDesc1771);
                    typedVariableList167=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList167.getTree());

                    char_literal168=(Token)match(input,71,FOLLOW_71_in_goalDesc1773); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal168);


                    pushFollow(FOLLOW_goalDesc_in_goalDesc1775);
                    goalDesc169=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc169.getTree());

                    char_literal170=(Token)match(input,71,FOLLOW_71_in_goalDesc1777); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal170);


                    // AST REWRITE
                    // elements: goalDesc, typedVariableList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 297:12: -> ^( FORALL_GD typedVariableList goalDesc )
                    {
                        // Pddl.g:297:15: ^( FORALL_GD typedVariableList goalDesc )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FORALL_GD, "FORALL_GD")
                        , root_1);

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
                    // Pddl.g:298:7: fComp
                    {
                    pushFollow(FOLLOW_fComp_in_goalDesc1806);
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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 299:15: -> ^( COMPARISON_GD fComp )
                    {
                        // Pddl.g:299:18: ^( COMPARISON_GD fComp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(COMPARISON_GD, "COMPARISON_GD")
                        , root_1);

                        adaptor.addChild(root_1, stream_fComp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 10 :
                    // Pddl.g:300:4: equality
                    {
                    pushFollow(FOLLOW_equality_in_goalDesc1833);
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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 301:6: -> ^( EQUALITY_CON equality )
                    {
                        // Pddl.g:301:9: ^( EQUALITY_CON equality )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(EQUALITY_CON, "EQUALITY_CON")
                        , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "equality"
    // Pddl.g:304:1: equality : '(' ! '=' term term ')' !;
    public final PddlParser.equality_return equality() throws RecognitionException {
        PddlParser.equality_return retval = new PddlParser.equality_return();
        retval.start = input.LT(1);

        int equality_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal173=null;
        Token char_literal174=null;
        Token char_literal177=null;
        PddlParser.term_return term175 =null;

        PddlParser.term_return term176 =null;


        Object char_literal173_tree=null;
        Object char_literal174_tree=null;
        Object char_literal177_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

            // Pddl.g:305:2: ( '(' ! '=' term term ')' !)
            // Pddl.g:305:4: '(' ! '=' term term ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal173=(Token)match(input,70,FOLLOW_70_in_equality1861); if (state.failed) return retval;

            char_literal174=(Token)match(input,101,FOLLOW_101_in_equality1864); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal174_tree = 
            (Object)adaptor.create(char_literal174)
            ;
            adaptor.addChild(root_0, char_literal174_tree);
            }

            pushFollow(FOLLOW_term_in_equality1866);
            term175=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term175.getTree());

            pushFollow(FOLLOW_term_in_equality1868);
            term176=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term176.getTree());

            char_literal177=(Token)match(input,71,FOLLOW_71_in_equality1870); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fComp"
    // Pddl.g:307:1: fComp : '(' ! binaryComp fExp fExp ')' !;
    public final PddlParser.fComp_return fComp() throws RecognitionException {
        PddlParser.fComp_return retval = new PddlParser.fComp_return();
        retval.start = input.LT(1);

        int fComp_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal178=null;
        Token char_literal182=null;
        PddlParser.binaryComp_return binaryComp179 =null;

        PddlParser.fExp_return fExp180 =null;

        PddlParser.fExp_return fExp181 =null;


        Object char_literal178_tree=null;
        Object char_literal182_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

            // Pddl.g:308:2: ( '(' ! binaryComp fExp fExp ')' !)
            // Pddl.g:308:4: '(' ! binaryComp fExp fExp ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal178=(Token)match(input,70,FOLLOW_70_in_fComp1881); if (state.failed) return retval;

            pushFollow(FOLLOW_binaryComp_in_fComp1884);
            binaryComp179=binaryComp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, binaryComp179.getTree());

            pushFollow(FOLLOW_fExp_in_fComp1886);
            fExp180=fExp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp180.getTree());

            pushFollow(FOLLOW_fExp_in_fComp1888);
            fExp181=fExp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp181.getTree());

            char_literal182=(Token)match(input,71,FOLLOW_71_in_fComp1890); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicTermFormula"
    // Pddl.g:311:1: atomicTermFormula : '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) ;
    public final PddlParser.atomicTermFormula_return atomicTermFormula() throws RecognitionException {
        PddlParser.atomicTermFormula_return retval = new PddlParser.atomicTermFormula_return();
        retval.start = input.LT(1);

        int atomicTermFormula_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal183=null;
        Token char_literal186=null;
        PddlParser.predicate_return predicate184 =null;

        PddlParser.term_return term185 =null;


        Object char_literal183_tree=null;
        Object char_literal186_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

            // Pddl.g:312:2: ( '(' predicate ( term )* ')' -> ^( PRED_HEAD predicate ( term )* ) )
            // Pddl.g:312:4: '(' predicate ( term )* ')'
            {
            char_literal183=(Token)match(input,70,FOLLOW_70_in_atomicTermFormula1902); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal183);


            pushFollow(FOLLOW_predicate_in_atomicTermFormula1904);
            predicate184=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_predicate.add(predicate184.getTree());

            // Pddl.g:312:18: ( term )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==NAME||LA39_0==VARIABLE) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // Pddl.g:312:18: term
            	    {
            	    pushFollow(FOLLOW_term_in_atomicTermFormula1906);
            	    term185=term();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_term.add(term185.getTree());

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            char_literal186=(Token)match(input,71,FOLLOW_71_in_atomicTermFormula1909); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal186);


            // AST REWRITE
            // elements: term, predicate
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 312:28: -> ^( PRED_HEAD predicate ( term )* )
            {
                // Pddl.g:312:31: ^( PRED_HEAD predicate ( term )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRED_HEAD, "PRED_HEAD")
                , root_1);

                adaptor.addChild(root_1, stream_predicate.nextTree());

                // Pddl.g:312:53: ( term )*
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "term"
    // Pddl.g:315:1: term : ( NAME | VARIABLE );
    public final PddlParser.term_return term() throws RecognitionException {
        PddlParser.term_return retval = new PddlParser.term_return();
        retval.start = input.LT(1);

        int term_StartIndex = input.index();

        Object root_0 = null;

        Token set187=null;

        Object set187_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }

            // Pddl.g:315:6: ( NAME | VARIABLE )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set187=(Token)input.LT(1);

            if ( input.LA(1)==NAME||input.LA(1)==VARIABLE ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set187)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "durativeActionDef"
    // Pddl.g:319:1: durativeActionDef : '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) ;
    public final PddlParser.durativeActionDef_return durativeActionDef() throws RecognitionException {
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
        PddlParser.actionSymbol_return actionSymbol190 =null;

        PddlParser.typedVariableList_return typedVariableList193 =null;

        PddlParser.daDefBody_return daDefBody195 =null;


        Object char_literal188_tree=null;
        Object string_literal189_tree=null;
        Object string_literal191_tree=null;
        Object char_literal192_tree=null;
        Object char_literal194_tree=null;
        Object char_literal196_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_daDefBody=new RewriteRuleSubtreeStream(adaptor,"rule daDefBody");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_actionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule actionSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

            // Pddl.g:320:2: ( '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')' -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody ) )
            // Pddl.g:320:4: '(' ':durative-action' actionSymbol ':parameters' '(' ( typedVariableList )? ')' daDefBody ')'
            {
            char_literal188=(Token)match(input,70,FOLLOW_70_in_durativeActionDef1946); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal188);


            string_literal189=(Token)match(input,84,FOLLOW_84_in_durativeActionDef1948); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(string_literal189);


            pushFollow(FOLLOW_actionSymbol_in_durativeActionDef1950);
            actionSymbol190=actionSymbol();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_actionSymbol.add(actionSymbol190.getTree());

            string_literal191=(Token)match(input,93,FOLLOW_93_in_durativeActionDef1959); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_93.add(string_literal191);


            char_literal192=(Token)match(input,70,FOLLOW_70_in_durativeActionDef1962); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal192);


            // Pddl.g:321:27: ( typedVariableList )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==VARIABLE) ) {
                alt40=1;
            }
            else if ( (LA40_0==71) ) {
                int LA40_2 = input.LA(2);

                if ( (synpred54_Pddl()) ) {
                    alt40=1;
                }
            }
            switch (alt40) {
                case 1 :
                    // Pddl.g:321:28: typedVariableList
                    {
                    pushFollow(FOLLOW_typedVariableList_in_durativeActionDef1965);
                    typedVariableList193=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList193.getTree());

                    }
                    break;

            }


            char_literal194=(Token)match(input,71,FOLLOW_71_in_durativeActionDef1969); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal194);


            pushFollow(FOLLOW_daDefBody_in_durativeActionDef1982);
            daDefBody195=daDefBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_daDefBody.add(daDefBody195.getTree());

            char_literal196=(Token)match(input,71,FOLLOW_71_in_durativeActionDef1984); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal196);


            // AST REWRITE
            // elements: daDefBody, actionSymbol, typedVariableList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 323:8: -> ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
            {
                // Pddl.g:323:11: ^( DURATIVE_ACTION actionSymbol typedVariableList daDefBody )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(DURATIVE_ACTION, "DURATIVE_ACTION")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "daDefBody"
    // Pddl.g:326:1: daDefBody : ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) );
    public final PddlParser.daDefBody_return daDefBody() throws RecognitionException {
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
        PddlParser.durationConstraint_return durationConstraint198 =null;

        PddlParser.daGD_return daGD202 =null;

        PddlParser.daEffect_return daEffect206 =null;


        Object string_literal197_tree=null;
        Object string_literal199_tree=null;
        Object char_literal200_tree=null;
        Object char_literal201_tree=null;
        Object string_literal203_tree=null;
        Object char_literal204_tree=null;
        Object char_literal205_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

            // Pddl.g:327:2: ( ':duration' durationConstraint | ':condition' ( ( '(' ')' ) | daGD ) | ':effect' ( ( '(' ')' ) | daEffect ) )
            int alt43=3;
            switch ( input.LA(1) ) {
            case 83:
                {
                alt43=1;
                }
                break;
            case 77:
                {
                alt43=2;
                }
                break;
            case 85:
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
                    // Pddl.g:327:4: ':duration' durationConstraint
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal197=(Token)match(input,83,FOLLOW_83_in_daDefBody2017); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal197_tree = 
                    (Object)adaptor.create(string_literal197)
                    ;
                    adaptor.addChild(root_0, string_literal197_tree);
                    }

                    pushFollow(FOLLOW_durationConstraint_in_daDefBody2019);
                    durationConstraint198=durationConstraint();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, durationConstraint198.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:328:4: ':condition' ( ( '(' ')' ) | daGD )
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal199=(Token)match(input,77,FOLLOW_77_in_daDefBody2024); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal199_tree = 
                    (Object)adaptor.create(string_literal199)
                    ;
                    adaptor.addChild(root_0, string_literal199_tree);
                    }

                    // Pddl.g:328:17: ( ( '(' ')' ) | daGD )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==70) ) {
                        int LA41_1 = input.LA(2);

                        if ( (LA41_1==71) ) {
                            alt41=1;
                        }
                        else if ( (LA41_1==110||LA41_1==112||LA41_1==121||(LA41_1 >= 133 && LA41_1 <= 134)) ) {
                            alt41=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 41, 1, input);

                            throw nvae;

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
                            // Pddl.g:328:18: ( '(' ')' )
                            {
                            // Pddl.g:328:18: ( '(' ')' )
                            // Pddl.g:328:19: '(' ')'
                            {
                            char_literal200=(Token)match(input,70,FOLLOW_70_in_daDefBody2028); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal200_tree = 
                            (Object)adaptor.create(char_literal200)
                            ;
                            adaptor.addChild(root_0, char_literal200_tree);
                            }

                            char_literal201=(Token)match(input,71,FOLLOW_71_in_daDefBody2030); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal201_tree = 
                            (Object)adaptor.create(char_literal201)
                            ;
                            adaptor.addChild(root_0, char_literal201_tree);
                            }

                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:328:30: daGD
                            {
                            pushFollow(FOLLOW_daGD_in_daDefBody2035);
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
                    // Pddl.g:329:7: ':effect' ( ( '(' ')' ) | daEffect )
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal203=(Token)match(input,85,FOLLOW_85_in_daDefBody2044); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal203_tree = 
                    (Object)adaptor.create(string_literal203)
                    ;
                    adaptor.addChild(root_0, string_literal203_tree);
                    }

                    // Pddl.g:329:17: ( ( '(' ')' ) | daEffect )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==70) ) {
                        int LA42_1 = input.LA(2);

                        if ( (LA42_1==71) ) {
                            alt42=1;
                        }
                        else if ( ((LA42_1 >= 110 && LA42_1 <= 112)||LA42_1==115||LA42_1==121||LA42_1==125||(LA42_1 >= 136 && LA42_1 <= 137)||LA42_1==144) ) {
                            alt42=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 42, 1, input);

                            throw nvae;

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
                            // Pddl.g:329:18: ( '(' ')' )
                            {
                            // Pddl.g:329:18: ( '(' ')' )
                            // Pddl.g:329:19: '(' ')'
                            {
                            char_literal204=(Token)match(input,70,FOLLOW_70_in_daDefBody2048); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal204_tree = 
                            (Object)adaptor.create(char_literal204)
                            ;
                            adaptor.addChild(root_0, char_literal204_tree);
                            }

                            char_literal205=(Token)match(input,71,FOLLOW_71_in_daDefBody2050); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal205_tree = 
                            (Object)adaptor.create(char_literal205)
                            ;
                            adaptor.addChild(root_0, char_literal205_tree);
                            }

                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:329:30: daEffect
                            {
                            pushFollow(FOLLOW_daEffect_in_daDefBody2055);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "daGD"
    // Pddl.g:332:1: daGD : ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' );
    public final PddlParser.daGD_return daGD() throws RecognitionException {
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
        PddlParser.prefTimedGD_return prefTimedGD207 =null;

        PddlParser.daGD_return daGD210 =null;

        PddlParser.typedVariableList_return typedVariableList215 =null;

        PddlParser.daGD_return daGD217 =null;


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

            // Pddl.g:333:2: ( prefTimedGD | '(' 'and' ( daGD )* ')' | '(' 'forall' '(' typedVariableList ')' daGD ')' )
            int alt45=3;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==70) ) {
                switch ( input.LA(2) ) {
                case 112:
                case 133:
                case 134:
                    {
                    alt45=1;
                    }
                    break;
                case 110:
                    {
                    alt45=2;
                    }
                    break;
                case 121:
                    {
                    alt45=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;

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
                    // Pddl.g:333:4: prefTimedGD
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_prefTimedGD_in_daGD2070);
                    prefTimedGD207=prefTimedGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, prefTimedGD207.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:334:4: '(' 'and' ( daGD )* ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal208=(Token)match(input,70,FOLLOW_70_in_daGD2075); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal208_tree = 
                    (Object)adaptor.create(char_literal208)
                    ;
                    adaptor.addChild(root_0, char_literal208_tree);
                    }

                    string_literal209=(Token)match(input,110,FOLLOW_110_in_daGD2077); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal209_tree = 
                    (Object)adaptor.create(string_literal209)
                    ;
                    adaptor.addChild(root_0, string_literal209_tree);
                    }

                    // Pddl.g:334:14: ( daGD )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==70) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // Pddl.g:334:14: daGD
                    	    {
                    	    pushFollow(FOLLOW_daGD_in_daGD2079);
                    	    daGD210=daGD();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD210.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);


                    char_literal211=(Token)match(input,71,FOLLOW_71_in_daGD2082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal211_tree = 
                    (Object)adaptor.create(char_literal211)
                    ;
                    adaptor.addChild(root_0, char_literal211_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:335:4: '(' 'forall' '(' typedVariableList ')' daGD ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal212=(Token)match(input,70,FOLLOW_70_in_daGD2087); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal212_tree = 
                    (Object)adaptor.create(char_literal212)
                    ;
                    adaptor.addChild(root_0, char_literal212_tree);
                    }

                    string_literal213=(Token)match(input,121,FOLLOW_121_in_daGD2089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal213_tree = 
                    (Object)adaptor.create(string_literal213)
                    ;
                    adaptor.addChild(root_0, string_literal213_tree);
                    }

                    char_literal214=(Token)match(input,70,FOLLOW_70_in_daGD2091); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal214_tree = 
                    (Object)adaptor.create(char_literal214)
                    ;
                    adaptor.addChild(root_0, char_literal214_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daGD2093);
                    typedVariableList215=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList215.getTree());

                    char_literal216=(Token)match(input,71,FOLLOW_71_in_daGD2095); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal216_tree = 
                    (Object)adaptor.create(char_literal216)
                    ;
                    adaptor.addChild(root_0, char_literal216_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daGD2097);
                    daGD217=daGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD217.getTree());

                    char_literal218=(Token)match(input,71,FOLLOW_71_in_daGD2099); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal218_tree = 
                    (Object)adaptor.create(char_literal218)
                    ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prefTimedGD"
    // Pddl.g:338:1: prefTimedGD : ( timedGD | '(' 'preference' ( NAME )? timedGD ')' );
    public final PddlParser.prefTimedGD_return prefTimedGD() throws RecognitionException {
        PddlParser.prefTimedGD_return retval = new PddlParser.prefTimedGD_return();
        retval.start = input.LT(1);

        int prefTimedGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal220=null;
        Token string_literal221=null;
        Token NAME222=null;
        Token char_literal224=null;
        PddlParser.timedGD_return timedGD219 =null;

        PddlParser.timedGD_return timedGD223 =null;


        Object char_literal220_tree=null;
        Object string_literal221_tree=null;
        Object NAME222_tree=null;
        Object char_literal224_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

            // Pddl.g:339:2: ( timedGD | '(' 'preference' ( NAME )? timedGD ')' )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==70) ) {
                int LA47_1 = input.LA(2);

                if ( (LA47_1==112||LA47_1==133) ) {
                    alt47=1;
                }
                else if ( (LA47_1==134) ) {
                    alt47=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 1, input);

                    throw nvae;

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
                    // Pddl.g:339:4: timedGD
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2110);
                    timedGD219=timedGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timedGD219.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:340:4: '(' 'preference' ( NAME )? timedGD ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal220=(Token)match(input,70,FOLLOW_70_in_prefTimedGD2115); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal220_tree = 
                    (Object)adaptor.create(char_literal220)
                    ;
                    adaptor.addChild(root_0, char_literal220_tree);
                    }

                    string_literal221=(Token)match(input,134,FOLLOW_134_in_prefTimedGD2117); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal221_tree = 
                    (Object)adaptor.create(string_literal221)
                    ;
                    adaptor.addChild(root_0, string_literal221_tree);
                    }

                    // Pddl.g:340:21: ( NAME )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==NAME) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // Pddl.g:340:21: NAME
                            {
                            NAME222=(Token)match(input,NAME,FOLLOW_NAME_in_prefTimedGD2119); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NAME222_tree = 
                            (Object)adaptor.create(NAME222)
                            ;
                            adaptor.addChild(root_0, NAME222_tree);
                            }

                            }
                            break;

                    }


                    pushFollow(FOLLOW_timedGD_in_prefTimedGD2122);
                    timedGD223=timedGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timedGD223.getTree());

                    char_literal224=(Token)match(input,71,FOLLOW_71_in_prefTimedGD2124); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal224_tree = 
                    (Object)adaptor.create(char_literal224)
                    ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "timedGD"
    // Pddl.g:343:1: timedGD : ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' );
    public final PddlParser.timedGD_return timedGD() throws RecognitionException {
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
        PddlParser.timeSpecifier_return timeSpecifier227 =null;

        PddlParser.goalDesc_return goalDesc228 =null;

        PddlParser.interval_return interval232 =null;

        PddlParser.goalDesc_return goalDesc233 =null;


        Object char_literal225_tree=null;
        Object string_literal226_tree=null;
        Object char_literal229_tree=null;
        Object char_literal230_tree=null;
        Object string_literal231_tree=null;
        Object char_literal234_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

            // Pddl.g:344:2: ( '(' 'at' timeSpecifier goalDesc ')' | '(' 'over' interval goalDesc ')' )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==70) ) {
                int LA48_1 = input.LA(2);

                if ( (LA48_1==112) ) {
                    alt48=1;
                }
                else if ( (LA48_1==133) ) {
                    alt48=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 48, 1, input);

                    throw nvae;

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
                    // Pddl.g:344:4: '(' 'at' timeSpecifier goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal225=(Token)match(input,70,FOLLOW_70_in_timedGD2135); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal225_tree = 
                    (Object)adaptor.create(char_literal225)
                    ;
                    adaptor.addChild(root_0, char_literal225_tree);
                    }

                    string_literal226=(Token)match(input,112,FOLLOW_112_in_timedGD2137); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal226_tree = 
                    (Object)adaptor.create(string_literal226)
                    ;
                    adaptor.addChild(root_0, string_literal226_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedGD2139);
                    timeSpecifier227=timeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier227.getTree());

                    pushFollow(FOLLOW_goalDesc_in_timedGD2141);
                    goalDesc228=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc228.getTree());

                    char_literal229=(Token)match(input,71,FOLLOW_71_in_timedGD2143); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal229_tree = 
                    (Object)adaptor.create(char_literal229)
                    ;
                    adaptor.addChild(root_0, char_literal229_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:345:4: '(' 'over' interval goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal230=(Token)match(input,70,FOLLOW_70_in_timedGD2148); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal230_tree = 
                    (Object)adaptor.create(char_literal230)
                    ;
                    adaptor.addChild(root_0, char_literal230_tree);
                    }

                    string_literal231=(Token)match(input,133,FOLLOW_133_in_timedGD2150); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal231_tree = 
                    (Object)adaptor.create(string_literal231)
                    ;
                    adaptor.addChild(root_0, string_literal231_tree);
                    }

                    pushFollow(FOLLOW_interval_in_timedGD2152);
                    interval232=interval();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interval232.getTree());

                    pushFollow(FOLLOW_goalDesc_in_timedGD2154);
                    goalDesc233=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc233.getTree());

                    char_literal234=(Token)match(input,71,FOLLOW_71_in_timedGD2156); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal234_tree = 
                    (Object)adaptor.create(char_literal234)
                    ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "timeSpecifier"
    // Pddl.g:348:1: timeSpecifier : ( 'start' | 'end' );
    public final PddlParser.timeSpecifier_return timeSpecifier() throws RecognitionException {
        PddlParser.timeSpecifier_return retval = new PddlParser.timeSpecifier_return();
        retval.start = input.LT(1);

        int timeSpecifier_StartIndex = input.index();

        Object root_0 = null;

        Token set235=null;

        Object set235_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

            // Pddl.g:348:15: ( 'start' | 'end' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set235=(Token)input.LT(1);

            if ( input.LA(1)==119||input.LA(1)==142 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set235)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interval"
    // Pddl.g:349:1: interval : 'all' ;
    public final PddlParser.interval_return interval() throws RecognitionException {
        PddlParser.interval_return retval = new PddlParser.interval_return();
        retval.start = input.LT(1);

        int interval_StartIndex = input.index();

        Object root_0 = null;

        Token string_literal236=null;

        Object string_literal236_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // Pddl.g:349:10: ( 'all' )
            // Pddl.g:349:12: 'all'
            {
            root_0 = (Object)adaptor.nil();


            string_literal236=(Token)match(input,107,FOLLOW_107_in_interval2178); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal236_tree = 
            (Object)adaptor.create(string_literal236)
            ;
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "derivedDef"
    // Pddl.g:353:1: derivedDef : '(' ! ':derived' ^ typedVariableList goalDesc ')' !;
    public final PddlParser.derivedDef_return derivedDef() throws RecognitionException {
        PddlParser.derivedDef_return retval = new PddlParser.derivedDef_return();
        retval.start = input.LT(1);

        int derivedDef_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal237=null;
        Token string_literal238=null;
        Token char_literal241=null;
        PddlParser.typedVariableList_return typedVariableList239 =null;

        PddlParser.goalDesc_return goalDesc240 =null;


        Object char_literal237_tree=null;
        Object string_literal238_tree=null;
        Object char_literal241_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

            // Pddl.g:354:2: ( '(' ! ':derived' ^ typedVariableList goalDesc ')' !)
            // Pddl.g:354:4: '(' ! ':derived' ^ typedVariableList goalDesc ')' !
            {
            root_0 = (Object)adaptor.nil();


            char_literal237=(Token)match(input,70,FOLLOW_70_in_derivedDef2191); if (state.failed) return retval;

            string_literal238=(Token)match(input,81,FOLLOW_81_in_derivedDef2194); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal238_tree = 
            (Object)adaptor.create(string_literal238)
            ;
            root_0 = (Object)adaptor.becomeRoot(string_literal238_tree, root_0);
            }

            pushFollow(FOLLOW_typedVariableList_in_derivedDef2197);
            typedVariableList239=typedVariableList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList239.getTree());

            pushFollow(FOLLOW_goalDesc_in_derivedDef2199);
            goalDesc240=goalDesc();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc240.getTree());

            char_literal241=(Token)match(input,71,FOLLOW_71_in_derivedDef2201); if (state.failed) return retval;

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fExp"
    // Pddl.g:359:1: fExp : ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead );
    public final PddlParser.fExp_return fExp() throws RecognitionException {
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
        PddlParser.binaryOp_return binaryOp244 =null;

        PddlParser.fExp_return fExp245 =null;

        PddlParser.fExp2_return fExp2246 =null;

        PddlParser.fExp_return fExp250 =null;

        PddlParser.fExp_return fExp254 =null;

        PddlParser.fExp_return fExp258 =null;

        PddlParser.fExp_return fExp262 =null;

        PddlParser.fHead_return fHead264 =null;


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
        RewriteRuleTokenStream stream_114=new RewriteRuleTokenStream(adaptor,"token 114");
        RewriteRuleTokenStream stream_138=new RewriteRuleTokenStream(adaptor,"token 138");
        RewriteRuleTokenStream stream_106=new RewriteRuleTokenStream(adaptor,"token 106");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_binaryOp=new RewriteRuleSubtreeStream(adaptor,"rule binaryOp");
        RewriteRuleSubtreeStream stream_fExp2=new RewriteRuleSubtreeStream(adaptor,"rule fExp2");
        RewriteRuleSubtreeStream stream_fExp=new RewriteRuleSubtreeStream(adaptor,"rule fExp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

            // Pddl.g:360:2: ( NUMBER | '(' binaryOp fExp fExp2 ')' -> ^( BINARY_OP binaryOp fExp fExp2 ) | '(' '-' fExp ')' -> ^( UNARY_MINUS fExp ) | '(' 'sin' fExp ')' -> ^( SIN fExp ) | '(' 'cos' fExp ')' -> ^( COS fExp ) | '(' 'abs' fExp ')' -> ^( ABS fExp ) | fHead )
            int alt49=7;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt49=1;
                }
                break;
            case 70:
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
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 2, input);

                    throw nvae;

                }
                }
                break;
            case NAME:
            case 69:
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
                    // Pddl.g:360:4: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER242=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_fExp2216); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER242_tree = 
                    (Object)adaptor.create(NUMBER242)
                    ;
                    adaptor.addChild(root_0, NUMBER242_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:361:4: '(' binaryOp fExp fExp2 ')'
                    {
                    char_literal243=(Token)match(input,70,FOLLOW_70_in_fExp2221); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal243);


                    pushFollow(FOLLOW_binaryOp_in_fExp2223);
                    binaryOp244=binaryOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binaryOp.add(binaryOp244.getTree());

                    pushFollow(FOLLOW_fExp_in_fExp2225);
                    fExp245=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp245.getTree());

                    pushFollow(FOLLOW_fExp2_in_fExp2227);
                    fExp2246=fExp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp2.add(fExp2246.getTree());

                    char_literal247=(Token)match(input,71,FOLLOW_71_in_fExp2229); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal247);


                    // AST REWRITE
                    // elements: fExp, binaryOp, fExp2
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 361:32: -> ^( BINARY_OP binaryOp fExp fExp2 )
                    {
                        // Pddl.g:361:35: ^( BINARY_OP binaryOp fExp fExp2 )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(BINARY_OP, "BINARY_OP")
                        , root_1);

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
                    // Pddl.g:362:4: '(' '-' fExp ')'
                    {
                    char_literal248=(Token)match(input,70,FOLLOW_70_in_fExp2246); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal248);


                    char_literal249=(Token)match(input,74,FOLLOW_74_in_fExp2248); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(char_literal249);


                    pushFollow(FOLLOW_fExp_in_fExp2250);
                    fExp250=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp250.getTree());

                    char_literal251=(Token)match(input,71,FOLLOW_71_in_fExp2252); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal251);


                    // AST REWRITE
                    // elements: fExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 362:21: -> ^( UNARY_MINUS fExp )
                    {
                        // Pddl.g:362:24: ^( UNARY_MINUS fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(UNARY_MINUS, "UNARY_MINUS")
                        , root_1);

                        adaptor.addChild(root_1, stream_fExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:363:4: '(' 'sin' fExp ')'
                    {
                    char_literal252=(Token)match(input,70,FOLLOW_70_in_fExp2265); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal252);


                    string_literal253=(Token)match(input,138,FOLLOW_138_in_fExp2267); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_138.add(string_literal253);


                    pushFollow(FOLLOW_fExp_in_fExp2269);
                    fExp254=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp254.getTree());

                    char_literal255=(Token)match(input,71,FOLLOW_71_in_fExp2271); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal255);


                    // AST REWRITE
                    // elements: fExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 363:23: -> ^( SIN fExp )
                    {
                        // Pddl.g:363:26: ^( SIN fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(SIN, "SIN")
                        , root_1);

                        adaptor.addChild(root_1, stream_fExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:364:4: '(' 'cos' fExp ')'
                    {
                    char_literal256=(Token)match(input,70,FOLLOW_70_in_fExp2284); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal256);


                    string_literal257=(Token)match(input,114,FOLLOW_114_in_fExp2286); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_114.add(string_literal257);


                    pushFollow(FOLLOW_fExp_in_fExp2288);
                    fExp258=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp258.getTree());

                    char_literal259=(Token)match(input,71,FOLLOW_71_in_fExp2290); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal259);


                    // AST REWRITE
                    // elements: fExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 364:23: -> ^( COS fExp )
                    {
                        // Pddl.g:364:26: ^( COS fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(COS, "COS")
                        , root_1);

                        adaptor.addChild(root_1, stream_fExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // Pddl.g:365:4: '(' 'abs' fExp ')'
                    {
                    char_literal260=(Token)match(input,70,FOLLOW_70_in_fExp2303); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal260);


                    string_literal261=(Token)match(input,106,FOLLOW_106_in_fExp2305); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_106.add(string_literal261);


                    pushFollow(FOLLOW_fExp_in_fExp2307);
                    fExp262=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp262.getTree());

                    char_literal263=(Token)match(input,71,FOLLOW_71_in_fExp2309); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal263);


                    // AST REWRITE
                    // elements: fExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 365:23: -> ^( ABS fExp )
                    {
                        // Pddl.g:365:26: ^( ABS fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ABS, "ABS")
                        , root_1);

                        adaptor.addChild(root_1, stream_fExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // Pddl.g:366:4: fHead
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_fHead_in_fExp2322);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fExp2"
    // Pddl.g:371:1: fExp2 : fExp ;
    public final PddlParser.fExp2_return fExp2() throws RecognitionException {
        PddlParser.fExp2_return retval = new PddlParser.fExp2_return();
        retval.start = input.LT(1);

        int fExp2_StartIndex = input.index();

        Object root_0 = null;

        PddlParser.fExp_return fExp265 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }

            // Pddl.g:371:7: ( fExp )
            // Pddl.g:371:9: fExp
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_fExp_in_fExp22334);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fHead"
    // Pddl.g:373:1: fHead : ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) );
    public final PddlParser.fHead_return fHead() throws RecognitionException {
        PddlParser.fHead_return retval = new PddlParser.fHead_return();
        retval.start = input.LT(1);

        int fHead_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal266=null;
        Token char_literal269=null;
        PddlParser.functionSymbol_return functionSymbol267 =null;

        PddlParser.term_return term268 =null;

        PddlParser.functionSymbol_return functionSymbol270 =null;


        Object char_literal266_tree=null;
        Object char_literal269_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        RewriteRuleSubtreeStream stream_functionSymbol=new RewriteRuleSubtreeStream(adaptor,"rule functionSymbol");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }

            // Pddl.g:374:2: ( '(' functionSymbol ( term )* ')' -> ^( FUNC_HEAD functionSymbol ( term )* ) | functionSymbol -> ^( FUNC_HEAD functionSymbol ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==70) ) {
                alt51=1;
            }
            else if ( (LA51_0==NAME||LA51_0==69) ) {
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
                    // Pddl.g:374:4: '(' functionSymbol ( term )* ')'
                    {
                    char_literal266=(Token)match(input,70,FOLLOW_70_in_fHead2344); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal266);


                    pushFollow(FOLLOW_functionSymbol_in_fHead2346);
                    functionSymbol267=functionSymbol();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionSymbol.add(functionSymbol267.getTree());

                    // Pddl.g:374:23: ( term )*
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==NAME||LA50_0==VARIABLE) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // Pddl.g:374:23: term
                    	    {
                    	    pushFollow(FOLLOW_term_in_fHead2348);
                    	    term268=term();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term.add(term268.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop50;
                        }
                    } while (true);


                    char_literal269=(Token)match(input,71,FOLLOW_71_in_fHead2351); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal269);


                    // AST REWRITE
                    // elements: functionSymbol, term
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 374:33: -> ^( FUNC_HEAD functionSymbol ( term )* )
                    {
                        // Pddl.g:374:36: ^( FUNC_HEAD functionSymbol ( term )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD")
                        , root_1);

                        adaptor.addChild(root_1, stream_functionSymbol.nextTree());

                        // Pddl.g:374:63: ( term )*
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
                    // Pddl.g:375:4: functionSymbol
                    {
                    pushFollow(FOLLOW_functionSymbol_in_fHead2367);
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
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 375:19: -> ^( FUNC_HEAD functionSymbol )
                    {
                        // Pddl.g:375:22: ^( FUNC_HEAD functionSymbol )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FUNC_HEAD, "FUNC_HEAD")
                        , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "effect"
    // Pddl.g:378:1: effect : ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect );
    public final PddlParser.effect_return effect() throws RecognitionException {
        PddlParser.effect_return retval = new PddlParser.effect_return();
        retval.start = input.LT(1);

        int effect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal271=null;
        Token string_literal272=null;
        Token char_literal274=null;
        PddlParser.cEffect_return cEffect273 =null;

        PddlParser.cEffect_return cEffect275 =null;


        Object char_literal271_tree=null;
        Object string_literal272_tree=null;
        Object char_literal274_tree=null;
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_cEffect=new RewriteRuleSubtreeStream(adaptor,"rule cEffect");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }

            // Pddl.g:379:2: ( '(' 'and' ( cEffect )* ')' -> ^( AND_EFFECT ( cEffect )* ) | cEffect )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==70) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==110) ) {
                    alt53=1;
                }
                else if ( (LA53_1==NAME||LA53_1==111||LA53_1==115||LA53_1==121||LA53_1==125||LA53_1==129||LA53_1==131||(LA53_1 >= 136 && LA53_1 <= 137)||LA53_1==144) ) {
                    alt53=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;

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
                    // Pddl.g:379:4: '(' 'and' ( cEffect )* ')'
                    {
                    char_literal271=(Token)match(input,70,FOLLOW_70_in_effect2386); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal271);


                    string_literal272=(Token)match(input,110,FOLLOW_110_in_effect2388); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_110.add(string_literal272);


                    // Pddl.g:379:14: ( cEffect )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==70) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // Pddl.g:379:14: cEffect
                    	    {
                    	    pushFollow(FOLLOW_cEffect_in_effect2390);
                    	    cEffect273=cEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_cEffect.add(cEffect273.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    char_literal274=(Token)match(input,71,FOLLOW_71_in_effect2393); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal274);


                    // AST REWRITE
                    // elements: cEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 379:27: -> ^( AND_EFFECT ( cEffect )* )
                    {
                        // Pddl.g:379:30: ^( AND_EFFECT ( cEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_EFFECT, "AND_EFFECT")
                        , root_1);

                        // Pddl.g:379:43: ( cEffect )*
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
                    // Pddl.g:380:4: cEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_cEffect_in_effect2407);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "cEffect"
    // Pddl.g:383:1: cEffect : ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect | '(' 'oneof' ( condEffect )* ')' -> ^( ONEOF_EFFECT ( condEffect )* ) );
    public final PddlParser.cEffect_return cEffect() throws RecognitionException {
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
        Token char_literal289=null;
        Token string_literal290=null;
        Token char_literal292=null;
        PddlParser.typedVariableList_return typedVariableList279 =null;

        PddlParser.effect_return effect281 =null;

        PddlParser.goalDesc_return goalDesc285 =null;

        PddlParser.condEffect_return condEffect286 =null;

        PddlParser.pEffect_return pEffect288 =null;

        PddlParser.condEffect_return condEffect291 =null;


        Object char_literal276_tree=null;
        Object string_literal277_tree=null;
        Object char_literal278_tree=null;
        Object char_literal280_tree=null;
        Object char_literal282_tree=null;
        Object char_literal283_tree=null;
        Object string_literal284_tree=null;
        Object char_literal287_tree=null;
        Object char_literal289_tree=null;
        Object string_literal290_tree=null;
        Object char_literal292_tree=null;
        RewriteRuleTokenStream stream_121=new RewriteRuleTokenStream(adaptor,"token 121");
        RewriteRuleTokenStream stream_144=new RewriteRuleTokenStream(adaptor,"token 144");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        RewriteRuleSubtreeStream stream_typedVariableList=new RewriteRuleSubtreeStream(adaptor,"rule typedVariableList");
        RewriteRuleSubtreeStream stream_effect=new RewriteRuleSubtreeStream(adaptor,"rule effect");
        RewriteRuleSubtreeStream stream_condEffect=new RewriteRuleSubtreeStream(adaptor,"rule condEffect");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }

            // Pddl.g:384:2: ( '(' 'forall' '(' typedVariableList ')' effect ')' -> ^( FORALL_EFFECT typedVariableList effect ) | '(' 'when' goalDesc condEffect ')' -> ^( WHEN_EFFECT goalDesc condEffect ) | pEffect | '(' 'oneof' ( condEffect )* ')' -> ^( ONEOF_EFFECT ( condEffect )* ) )
            int alt55=4;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==70) ) {
                switch ( input.LA(2) ) {
                case 121:
                    {
                    alt55=1;
                    }
                    break;
                case 144:
                    {
                    alt55=2;
                    }
                    break;
                case NAME:
                case 111:
                case 115:
                case 125:
                case 129:
                case 136:
                case 137:
                    {
                    alt55=3;
                    }
                    break;
                case 131:
                    {
                    alt55=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

                    throw nvae;

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
                    // Pddl.g:384:4: '(' 'forall' '(' typedVariableList ')' effect ')'
                    {
                    char_literal276=(Token)match(input,70,FOLLOW_70_in_cEffect2418); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal276);


                    string_literal277=(Token)match(input,121,FOLLOW_121_in_cEffect2420); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_121.add(string_literal277);


                    char_literal278=(Token)match(input,70,FOLLOW_70_in_cEffect2422); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal278);


                    pushFollow(FOLLOW_typedVariableList_in_cEffect2424);
                    typedVariableList279=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typedVariableList.add(typedVariableList279.getTree());

                    char_literal280=(Token)match(input,71,FOLLOW_71_in_cEffect2426); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal280);


                    pushFollow(FOLLOW_effect_in_cEffect2428);
                    effect281=effect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_effect.add(effect281.getTree());

                    char_literal282=(Token)match(input,71,FOLLOW_71_in_cEffect2430); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal282);


                    // AST REWRITE
                    // elements: typedVariableList, effect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 385:4: -> ^( FORALL_EFFECT typedVariableList effect )
                    {
                        // Pddl.g:385:7: ^( FORALL_EFFECT typedVariableList effect )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FORALL_EFFECT, "FORALL_EFFECT")
                        , root_1);

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
                    // Pddl.g:386:4: '(' 'when' goalDesc condEffect ')'
                    {
                    char_literal283=(Token)match(input,70,FOLLOW_70_in_cEffect2448); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal283);


                    string_literal284=(Token)match(input,144,FOLLOW_144_in_cEffect2450); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_144.add(string_literal284);


                    pushFollow(FOLLOW_goalDesc_in_cEffect2452);
                    goalDesc285=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc285.getTree());

                    pushFollow(FOLLOW_condEffect_in_cEffect2454);
                    condEffect286=condEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_condEffect.add(condEffect286.getTree());

                    char_literal287=(Token)match(input,71,FOLLOW_71_in_cEffect2456); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal287);


                    // AST REWRITE
                    // elements: goalDesc, condEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 387:4: -> ^( WHEN_EFFECT goalDesc condEffect )
                    {
                        // Pddl.g:387:7: ^( WHEN_EFFECT goalDesc condEffect )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(WHEN_EFFECT, "WHEN_EFFECT")
                        , root_1);

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
                    // Pddl.g:388:4: pEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pEffect_in_cEffect2474);
                    pEffect288=pEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect288.getTree());

                    }
                    break;
                case 4 :
                    // Pddl.g:389:4: '(' 'oneof' ( condEffect )* ')'
                    {
                    char_literal289=(Token)match(input,70,FOLLOW_70_in_cEffect2479); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal289);


                    string_literal290=(Token)match(input,131,FOLLOW_131_in_cEffect2481); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_131.add(string_literal290);


                    // Pddl.g:389:16: ( condEffect )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==70) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // Pddl.g:389:16: condEffect
                    	    {
                    	    pushFollow(FOLLOW_condEffect_in_cEffect2483);
                    	    condEffect291=condEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_condEffect.add(condEffect291.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);


                    char_literal292=(Token)match(input,71,FOLLOW_71_in_cEffect2486); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal292);


                    // AST REWRITE
                    // elements: condEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 389:32: -> ^( ONEOF_EFFECT ( condEffect )* )
                    {
                        // Pddl.g:389:35: ^( ONEOF_EFFECT ( condEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ONEOF_EFFECT, "ONEOF_EFFECT")
                        , root_1);

                        // Pddl.g:389:50: ( condEffect )*
                        while ( stream_condEffect.hasNext() ) {
                            adaptor.addChild(root_1, stream_condEffect.nextTree());

                        }
                        stream_condEffect.reset();

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
            if ( state.backtracking>0 ) { memoize(input, 50, cEffect_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "cEffect"


    public static class pEffect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pEffect"
    // Pddl.g:392:1: pEffect : ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula );
    public final PddlParser.pEffect_return pEffect() throws RecognitionException {
        PddlParser.pEffect_return retval = new PddlParser.pEffect_return();
        retval.start = input.LT(1);

        int pEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal293=null;
        Token char_literal297=null;
        Token char_literal298=null;
        Token string_literal299=null;
        Token char_literal301=null;
        PddlParser.assignOp_return assignOp294 =null;

        PddlParser.fHead_return fHead295 =null;

        PddlParser.fExp_return fExp296 =null;

        PddlParser.atomicTermFormula_return atomicTermFormula300 =null;

        PddlParser.atomicTermFormula_return atomicTermFormula302 =null;


        Object char_literal293_tree=null;
        Object char_literal297_tree=null;
        Object char_literal298_tree=null;
        Object string_literal299_tree=null;
        Object char_literal301_tree=null;
        RewriteRuleTokenStream stream_129=new RewriteRuleTokenStream(adaptor,"token 129");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_atomicTermFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicTermFormula");
        RewriteRuleSubtreeStream stream_assignOp=new RewriteRuleSubtreeStream(adaptor,"rule assignOp");
        RewriteRuleSubtreeStream stream_fExp=new RewriteRuleSubtreeStream(adaptor,"rule fExp");
        RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }

            // Pddl.g:393:2: ( '(' assignOp fHead fExp ')' -> ^( ASSIGN_EFFECT assignOp fHead fExp ) | '(' 'not' atomicTermFormula ')' -> ^( NOT_EFFECT atomicTermFormula ) | atomicTermFormula )
            int alt56=3;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==70) ) {
                switch ( input.LA(2) ) {
                case 129:
                    {
                    alt56=2;
                    }
                    break;
                case 111:
                case 115:
                case 125:
                case 136:
                case 137:
                    {
                    alt56=1;
                    }
                    break;
                case NAME:
                    {
                    alt56=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // Pddl.g:393:4: '(' assignOp fHead fExp ')'
                    {
                    char_literal293=(Token)match(input,70,FOLLOW_70_in_pEffect2506); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal293);


                    pushFollow(FOLLOW_assignOp_in_pEffect2508);
                    assignOp294=assignOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignOp.add(assignOp294.getTree());

                    pushFollow(FOLLOW_fHead_in_pEffect2510);
                    fHead295=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fHead.add(fHead295.getTree());

                    pushFollow(FOLLOW_fExp_in_pEffect2512);
                    fExp296=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fExp.add(fExp296.getTree());

                    char_literal297=(Token)match(input,71,FOLLOW_71_in_pEffect2514); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal297);


                    // AST REWRITE
                    // elements: assignOp, fExp, fHead
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 394:4: -> ^( ASSIGN_EFFECT assignOp fHead fExp )
                    {
                        // Pddl.g:394:7: ^( ASSIGN_EFFECT assignOp fHead fExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ASSIGN_EFFECT, "ASSIGN_EFFECT")
                        , root_1);

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
                    // Pddl.g:395:4: '(' 'not' atomicTermFormula ')'
                    {
                    char_literal298=(Token)match(input,70,FOLLOW_70_in_pEffect2534); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal298);


                    string_literal299=(Token)match(input,129,FOLLOW_129_in_pEffect2536); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_129.add(string_literal299);


                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2538);
                    atomicTermFormula300=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atomicTermFormula.add(atomicTermFormula300.getTree());

                    char_literal301=(Token)match(input,71,FOLLOW_71_in_pEffect2540); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal301);


                    // AST REWRITE
                    // elements: atomicTermFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 396:4: -> ^( NOT_EFFECT atomicTermFormula )
                    {
                        // Pddl.g:396:7: ^( NOT_EFFECT atomicTermFormula )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_EFFECT, "NOT_EFFECT")
                        , root_1);

                        adaptor.addChild(root_1, stream_atomicTermFormula.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:397:4: atomicTermFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicTermFormula_in_pEffect2556);
                    atomicTermFormula302=atomicTermFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicTermFormula302.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "condEffect"
    // Pddl.g:402:1: condEffect : ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | '(' 'oneof' ( condEffect )* ')' -> ^( ONEOF_EFFECT ( condEffect )* ) | pEffect );
    public final PddlParser.condEffect_return condEffect() throws RecognitionException {
        PddlParser.condEffect_return retval = new PddlParser.condEffect_return();
        retval.start = input.LT(1);

        int condEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal303=null;
        Token string_literal304=null;
        Token char_literal306=null;
        Token char_literal307=null;
        Token string_literal308=null;
        Token char_literal310=null;
        PddlParser.pEffect_return pEffect305 =null;

        PddlParser.condEffect_return condEffect309 =null;

        PddlParser.pEffect_return pEffect311 =null;


        Object char_literal303_tree=null;
        Object string_literal304_tree=null;
        Object char_literal306_tree=null;
        Object char_literal307_tree=null;
        Object string_literal308_tree=null;
        Object char_literal310_tree=null;
        RewriteRuleTokenStream stream_110=new RewriteRuleTokenStream(adaptor,"token 110");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
        RewriteRuleSubtreeStream stream_pEffect=new RewriteRuleSubtreeStream(adaptor,"rule pEffect");
        RewriteRuleSubtreeStream stream_condEffect=new RewriteRuleSubtreeStream(adaptor,"rule condEffect");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }

            // Pddl.g:403:2: ( '(' 'and' ( pEffect )* ')' -> ^( AND_EFFECT ( pEffect )* ) | '(' 'oneof' ( condEffect )* ')' -> ^( ONEOF_EFFECT ( condEffect )* ) | pEffect )
            int alt59=3;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==70) ) {
                switch ( input.LA(2) ) {
                case 110:
                    {
                    alt59=1;
                    }
                    break;
                case 131:
                    {
                    alt59=2;
                    }
                    break;
                case NAME:
                case 111:
                case 115:
                case 125:
                case 129:
                case 136:
                case 137:
                    {
                    alt59=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 59, 1, input);

                    throw nvae;

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
                    // Pddl.g:403:4: '(' 'and' ( pEffect )* ')'
                    {
                    char_literal303=(Token)match(input,70,FOLLOW_70_in_condEffect2569); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal303);


                    string_literal304=(Token)match(input,110,FOLLOW_110_in_condEffect2571); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_110.add(string_literal304);


                    // Pddl.g:403:14: ( pEffect )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==70) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // Pddl.g:403:14: pEffect
                    	    {
                    	    pushFollow(FOLLOW_pEffect_in_condEffect2573);
                    	    pEffect305=pEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_pEffect.add(pEffect305.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);


                    char_literal306=(Token)match(input,71,FOLLOW_71_in_condEffect2576); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal306);


                    // AST REWRITE
                    // elements: pEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 403:27: -> ^( AND_EFFECT ( pEffect )* )
                    {
                        // Pddl.g:403:30: ^( AND_EFFECT ( pEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(AND_EFFECT, "AND_EFFECT")
                        , root_1);

                        // Pddl.g:403:43: ( pEffect )*
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
                    // Pddl.g:404:4: '(' 'oneof' ( condEffect )* ')'
                    {
                    char_literal307=(Token)match(input,70,FOLLOW_70_in_condEffect2590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal307);


                    string_literal308=(Token)match(input,131,FOLLOW_131_in_condEffect2592); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_131.add(string_literal308);


                    // Pddl.g:404:16: ( condEffect )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==70) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // Pddl.g:404:16: condEffect
                    	    {
                    	    pushFollow(FOLLOW_condEffect_in_condEffect2594);
                    	    condEffect309=condEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_condEffect.add(condEffect309.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);


                    char_literal310=(Token)match(input,71,FOLLOW_71_in_condEffect2597); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal310);


                    // AST REWRITE
                    // elements: condEffect
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 404:32: -> ^( ONEOF_EFFECT ( condEffect )* )
                    {
                        // Pddl.g:404:35: ^( ONEOF_EFFECT ( condEffect )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ONEOF_EFFECT, "ONEOF_EFFECT")
                        , root_1);

                        // Pddl.g:404:50: ( condEffect )*
                        while ( stream_condEffect.hasNext() ) {
                            adaptor.addChild(root_1, stream_condEffect.nextTree());

                        }
                        stream_condEffect.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:405:4: pEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_pEffect_in_condEffect2611);
                    pEffect311=pEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pEffect311.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binaryOp"
    // Pddl.g:410:1: binaryOp : ( '*' | '+' | '-' | '/' | '^' );
    public final PddlParser.binaryOp_return binaryOp() throws RecognitionException {
        PddlParser.binaryOp_return retval = new PddlParser.binaryOp_return();
        retval.start = input.LT(1);

        int binaryOp_StartIndex = input.index();

        Object root_0 = null;

        Token set312=null;

        Object set312_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }

            // Pddl.g:410:10: ( '*' | '+' | '-' | '/' | '^' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set312=(Token)input.LT(1);

            if ( (input.LA(1) >= 72 && input.LA(1) <= 75)||input.LA(1)==105 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set312)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiOp"
    // Pddl.g:412:1: multiOp : ( '*' | '+' );
    public final PddlParser.multiOp_return multiOp() throws RecognitionException {
        PddlParser.multiOp_return retval = new PddlParser.multiOp_return();
        retval.start = input.LT(1);

        int multiOp_StartIndex = input.index();

        Object root_0 = null;

        Token set313=null;

        Object set313_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }

            // Pddl.g:412:9: ( '*' | '+' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set313=(Token)input.LT(1);

            if ( (input.LA(1) >= 72 && input.LA(1) <= 73) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set313)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binaryComp"
    // Pddl.g:414:1: binaryComp : ( '>' | '<' | '=' | '>=' | '<=' );
    public final PddlParser.binaryComp_return binaryComp() throws RecognitionException {
        PddlParser.binaryComp_return retval = new PddlParser.binaryComp_return();
        retval.start = input.LT(1);

        int binaryComp_StartIndex = input.index();

        Object root_0 = null;

        Token set314=null;

        Object set314_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }

            // Pddl.g:414:12: ( '>' | '<' | '=' | '>=' | '<=' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set314=(Token)input.LT(1);

            if ( (input.LA(1) >= 99 && input.LA(1) <= 103) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set314)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignOp"
    // Pddl.g:416:1: assignOp : ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' );
    public final PddlParser.assignOp_return assignOp() throws RecognitionException {
        PddlParser.assignOp_return retval = new PddlParser.assignOp_return();
        retval.start = input.LT(1);

        int assignOp_StartIndex = input.index();

        Object root_0 = null;

        Token set315=null;

        Object set315_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }

            // Pddl.g:416:10: ( 'assign' | 'scale-up' | 'scale-down' | 'increase' | 'decrease' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set315=(Token)input.LT(1);

            if ( input.LA(1)==111||input.LA(1)==115||input.LA(1)==125||(input.LA(1) >= 136 && input.LA(1) <= 137) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set315)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "durationConstraint"
    // Pddl.g:421:1: durationConstraint : ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint );
    public final PddlParser.durationConstraint_return durationConstraint() throws RecognitionException {
        PddlParser.durationConstraint_return retval = new PddlParser.durationConstraint_return();
        retval.start = input.LT(1);

        int durationConstraint_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal316=null;
        Token string_literal317=null;
        Token char_literal319=null;
        Token char_literal320=null;
        Token char_literal321=null;
        PddlParser.simpleDurationConstraint_return simpleDurationConstraint318 =null;

        PddlParser.simpleDurationConstraint_return simpleDurationConstraint322 =null;


        Object char_literal316_tree=null;
        Object string_literal317_tree=null;
        Object char_literal319_tree=null;
        Object char_literal320_tree=null;
        Object char_literal321_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }

            // Pddl.g:422:2: ( '(' 'and' ( simpleDurationConstraint )+ ')' | '(' ')' | simpleDurationConstraint )
            int alt61=3;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==70) ) {
                switch ( input.LA(2) ) {
                case 110:
                    {
                    alt61=1;
                    }
                    break;
                case 71:
                    {
                    alt61=2;
                    }
                    break;
                case 100:
                case 101:
                case 103:
                case 112:
                    {
                    alt61=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // Pddl.g:422:4: '(' 'and' ( simpleDurationConstraint )+ ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal316=(Token)match(input,70,FOLLOW_70_in_durationConstraint2716); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal316_tree = 
                    (Object)adaptor.create(char_literal316)
                    ;
                    adaptor.addChild(root_0, char_literal316_tree);
                    }

                    string_literal317=(Token)match(input,110,FOLLOW_110_in_durationConstraint2718); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal317_tree = 
                    (Object)adaptor.create(string_literal317)
                    ;
                    adaptor.addChild(root_0, string_literal317_tree);
                    }

                    // Pddl.g:422:14: ( simpleDurationConstraint )+
                    int cnt60=0;
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==70) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // Pddl.g:422:14: simpleDurationConstraint
                    	    {
                    	    pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2720);
                    	    simpleDurationConstraint318=simpleDurationConstraint();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint318.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt60 >= 1 ) break loop60;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(60, input);
                                throw eee;
                        }
                        cnt60++;
                    } while (true);


                    char_literal319=(Token)match(input,71,FOLLOW_71_in_durationConstraint2723); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal319_tree = 
                    (Object)adaptor.create(char_literal319)
                    ;
                    adaptor.addChild(root_0, char_literal319_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:423:4: '(' ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal320=(Token)match(input,70,FOLLOW_70_in_durationConstraint2728); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal320_tree = 
                    (Object)adaptor.create(char_literal320)
                    ;
                    adaptor.addChild(root_0, char_literal320_tree);
                    }

                    char_literal321=(Token)match(input,71,FOLLOW_71_in_durationConstraint2730); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal321_tree = 
                    (Object)adaptor.create(char_literal321)
                    ;
                    adaptor.addChild(root_0, char_literal321_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:424:4: simpleDurationConstraint
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_simpleDurationConstraint_in_durationConstraint2735);
                    simpleDurationConstraint322=simpleDurationConstraint();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint322.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "simpleDurationConstraint"
    // Pddl.g:427:1: simpleDurationConstraint : ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' );
    public final PddlParser.simpleDurationConstraint_return simpleDurationConstraint() throws RecognitionException {
        PddlParser.simpleDurationConstraint_return retval = new PddlParser.simpleDurationConstraint_return();
        retval.start = input.LT(1);

        int simpleDurationConstraint_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal323=null;
        Token string_literal325=null;
        Token char_literal327=null;
        Token char_literal328=null;
        Token string_literal329=null;
        Token char_literal332=null;
        PddlParser.durOp_return durOp324 =null;

        PddlParser.durValue_return durValue326 =null;

        PddlParser.timeSpecifier_return timeSpecifier330 =null;

        PddlParser.simpleDurationConstraint_return simpleDurationConstraint331 =null;


        Object char_literal323_tree=null;
        Object string_literal325_tree=null;
        Object char_literal327_tree=null;
        Object char_literal328_tree=null;
        Object string_literal329_tree=null;
        Object char_literal332_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }

            // Pddl.g:428:2: ( '(' durOp '?duration' durValue ')' | '(' 'at' timeSpecifier simpleDurationConstraint ')' )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==70) ) {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==112) ) {
                    alt62=2;
                }
                else if ( ((LA62_1 >= 100 && LA62_1 <= 101)||LA62_1==103) ) {
                    alt62=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // Pddl.g:428:4: '(' durOp '?duration' durValue ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal323=(Token)match(input,70,FOLLOW_70_in_simpleDurationConstraint2746); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal323_tree = 
                    (Object)adaptor.create(char_literal323)
                    ;
                    adaptor.addChild(root_0, char_literal323_tree);
                    }

                    pushFollow(FOLLOW_durOp_in_simpleDurationConstraint2748);
                    durOp324=durOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, durOp324.getTree());

                    string_literal325=(Token)match(input,104,FOLLOW_104_in_simpleDurationConstraint2750); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal325_tree = 
                    (Object)adaptor.create(string_literal325)
                    ;
                    adaptor.addChild(root_0, string_literal325_tree);
                    }

                    pushFollow(FOLLOW_durValue_in_simpleDurationConstraint2752);
                    durValue326=durValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, durValue326.getTree());

                    char_literal327=(Token)match(input,71,FOLLOW_71_in_simpleDurationConstraint2754); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal327_tree = 
                    (Object)adaptor.create(char_literal327)
                    ;
                    adaptor.addChild(root_0, char_literal327_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:429:4: '(' 'at' timeSpecifier simpleDurationConstraint ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal328=(Token)match(input,70,FOLLOW_70_in_simpleDurationConstraint2759); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal328_tree = 
                    (Object)adaptor.create(char_literal328)
                    ;
                    adaptor.addChild(root_0, char_literal328_tree);
                    }

                    string_literal329=(Token)match(input,112,FOLLOW_112_in_simpleDurationConstraint2761); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal329_tree = 
                    (Object)adaptor.create(string_literal329)
                    ;
                    adaptor.addChild(root_0, string_literal329_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_simpleDurationConstraint2763);
                    timeSpecifier330=timeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier330.getTree());

                    pushFollow(FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2765);
                    simpleDurationConstraint331=simpleDurationConstraint();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simpleDurationConstraint331.getTree());

                    char_literal332=(Token)match(input,71,FOLLOW_71_in_simpleDurationConstraint2767); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal332_tree = 
                    (Object)adaptor.create(char_literal332)
                    ;
                    adaptor.addChild(root_0, char_literal332_tree);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "durOp"
    // Pddl.g:432:1: durOp : ( '<=' | '>=' | '=' );
    public final PddlParser.durOp_return durOp() throws RecognitionException {
        PddlParser.durOp_return retval = new PddlParser.durOp_return();
        retval.start = input.LT(1);

        int durOp_StartIndex = input.index();

        Object root_0 = null;

        Token set333=null;

        Object set333_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }

            // Pddl.g:432:7: ( '<=' | '>=' | '=' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set333=(Token)input.LT(1);

            if ( (input.LA(1) >= 100 && input.LA(1) <= 101)||input.LA(1)==103 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set333)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "durValue"
    // Pddl.g:434:1: durValue : ( NUMBER | fExp );
    public final PddlParser.durValue_return durValue() throws RecognitionException {
        PddlParser.durValue_return retval = new PddlParser.durValue_return();
        retval.start = input.LT(1);

        int durValue_StartIndex = input.index();

        Object root_0 = null;

        Token NUMBER334=null;
        PddlParser.fExp_return fExp335 =null;


        Object NUMBER334_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }

            // Pddl.g:434:10: ( NUMBER | fExp )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==NUMBER) ) {
                int LA63_1 = input.LA(2);

                if ( (synpred105_Pddl()) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA63_0==NAME||(LA63_0 >= 69 && LA63_0 <= 70)) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // Pddl.g:434:12: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER334=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_durValue2794); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER334_tree = 
                    (Object)adaptor.create(NUMBER334)
                    ;
                    adaptor.addChild(root_0, NUMBER334_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:434:21: fExp
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_fExp_in_durValue2798);
                    fExp335=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp335.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "daEffect"
    // Pddl.g:436:1: daEffect : ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' );
    public final PddlParser.daEffect_return daEffect() throws RecognitionException {
        PddlParser.daEffect_return retval = new PddlParser.daEffect_return();
        retval.start = input.LT(1);

        int daEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal336=null;
        Token string_literal337=null;
        Token char_literal339=null;
        Token char_literal341=null;
        Token string_literal342=null;
        Token char_literal343=null;
        Token char_literal345=null;
        Token char_literal347=null;
        Token char_literal348=null;
        Token string_literal349=null;
        Token char_literal352=null;
        Token char_literal353=null;
        Token char_literal357=null;
        PddlParser.daEffect_return daEffect338 =null;

        PddlParser.timedEffect_return timedEffect340 =null;

        PddlParser.typedVariableList_return typedVariableList344 =null;

        PddlParser.daEffect_return daEffect346 =null;

        PddlParser.daGD_return daGD350 =null;

        PddlParser.timedEffect_return timedEffect351 =null;

        PddlParser.assignOp_return assignOp354 =null;

        PddlParser.fHead_return fHead355 =null;

        PddlParser.fExpDA_return fExpDA356 =null;


        Object char_literal336_tree=null;
        Object string_literal337_tree=null;
        Object char_literal339_tree=null;
        Object char_literal341_tree=null;
        Object string_literal342_tree=null;
        Object char_literal343_tree=null;
        Object char_literal345_tree=null;
        Object char_literal347_tree=null;
        Object char_literal348_tree=null;
        Object string_literal349_tree=null;
        Object char_literal352_tree=null;
        Object char_literal353_tree=null;
        Object char_literal357_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }

            // Pddl.g:437:2: ( '(' 'and' ( daEffect )* ')' | timedEffect | '(' 'forall' '(' typedVariableList ')' daEffect ')' | '(' 'when' daGD timedEffect ')' | '(' assignOp fHead fExpDA ')' )
            int alt65=5;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==70) ) {
                int LA65_1 = input.LA(2);

                if ( (synpred107_Pddl()) ) {
                    alt65=1;
                }
                else if ( (synpred108_Pddl()) ) {
                    alt65=2;
                }
                else if ( (synpred109_Pddl()) ) {
                    alt65=3;
                }
                else if ( (synpred110_Pddl()) ) {
                    alt65=4;
                }
                else if ( (true) ) {
                    alt65=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 65, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;

            }
            switch (alt65) {
                case 1 :
                    // Pddl.g:437:4: '(' 'and' ( daEffect )* ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal336=(Token)match(input,70,FOLLOW_70_in_daEffect2808); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal336_tree = 
                    (Object)adaptor.create(char_literal336)
                    ;
                    adaptor.addChild(root_0, char_literal336_tree);
                    }

                    string_literal337=(Token)match(input,110,FOLLOW_110_in_daEffect2810); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal337_tree = 
                    (Object)adaptor.create(string_literal337)
                    ;
                    adaptor.addChild(root_0, string_literal337_tree);
                    }

                    // Pddl.g:437:14: ( daEffect )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==70) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // Pddl.g:437:14: daEffect
                    	    {
                    	    pushFollow(FOLLOW_daEffect_in_daEffect2812);
                    	    daEffect338=daEffect();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect338.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);


                    char_literal339=(Token)match(input,71,FOLLOW_71_in_daEffect2815); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal339_tree = 
                    (Object)adaptor.create(char_literal339)
                    ;
                    adaptor.addChild(root_0, char_literal339_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:438:4: timedEffect
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_timedEffect_in_daEffect2820);
                    timedEffect340=timedEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timedEffect340.getTree());

                    }
                    break;
                case 3 :
                    // Pddl.g:439:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal341=(Token)match(input,70,FOLLOW_70_in_daEffect2825); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal341_tree = 
                    (Object)adaptor.create(char_literal341)
                    ;
                    adaptor.addChild(root_0, char_literal341_tree);
                    }

                    string_literal342=(Token)match(input,121,FOLLOW_121_in_daEffect2827); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal342_tree = 
                    (Object)adaptor.create(string_literal342)
                    ;
                    adaptor.addChild(root_0, string_literal342_tree);
                    }

                    char_literal343=(Token)match(input,70,FOLLOW_70_in_daEffect2829); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal343_tree = 
                    (Object)adaptor.create(char_literal343)
                    ;
                    adaptor.addChild(root_0, char_literal343_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_daEffect2831);
                    typedVariableList344=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList344.getTree());

                    char_literal345=(Token)match(input,71,FOLLOW_71_in_daEffect2833); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal345_tree = 
                    (Object)adaptor.create(char_literal345)
                    ;
                    adaptor.addChild(root_0, char_literal345_tree);
                    }

                    pushFollow(FOLLOW_daEffect_in_daEffect2835);
                    daEffect346=daEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect346.getTree());

                    char_literal347=(Token)match(input,71,FOLLOW_71_in_daEffect2837); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal347_tree = 
                    (Object)adaptor.create(char_literal347)
                    ;
                    adaptor.addChild(root_0, char_literal347_tree);
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:440:4: '(' 'when' daGD timedEffect ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal348=(Token)match(input,70,FOLLOW_70_in_daEffect2842); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal348_tree = 
                    (Object)adaptor.create(char_literal348)
                    ;
                    adaptor.addChild(root_0, char_literal348_tree);
                    }

                    string_literal349=(Token)match(input,144,FOLLOW_144_in_daEffect2844); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal349_tree = 
                    (Object)adaptor.create(string_literal349)
                    ;
                    adaptor.addChild(root_0, string_literal349_tree);
                    }

                    pushFollow(FOLLOW_daGD_in_daEffect2846);
                    daGD350=daGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, daGD350.getTree());

                    pushFollow(FOLLOW_timedEffect_in_daEffect2848);
                    timedEffect351=timedEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timedEffect351.getTree());

                    char_literal352=(Token)match(input,71,FOLLOW_71_in_daEffect2850); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal352_tree = 
                    (Object)adaptor.create(char_literal352)
                    ;
                    adaptor.addChild(root_0, char_literal352_tree);
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:441:4: '(' assignOp fHead fExpDA ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal353=(Token)match(input,70,FOLLOW_70_in_daEffect2855); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal353_tree = 
                    (Object)adaptor.create(char_literal353)
                    ;
                    adaptor.addChild(root_0, char_literal353_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_daEffect2857);
                    assignOp354=assignOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp354.getTree());

                    pushFollow(FOLLOW_fHead_in_daEffect2859);
                    fHead355=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead355.getTree());

                    pushFollow(FOLLOW_fExpDA_in_daEffect2861);
                    fExpDA356=fExpDA();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA356.getTree());

                    char_literal357=(Token)match(input,71,FOLLOW_71_in_daEffect2863); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal357_tree = 
                    (Object)adaptor.create(char_literal357)
                    ;
                    adaptor.addChild(root_0, char_literal357_tree);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "timedEffect"
    // Pddl.g:444:1: timedEffect : ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' );
    public final PddlParser.timedEffect_return timedEffect() throws RecognitionException {
        PddlParser.timedEffect_return retval = new PddlParser.timedEffect_return();
        retval.start = input.LT(1);

        int timedEffect_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal358=null;
        Token string_literal359=null;
        Token char_literal362=null;
        Token char_literal363=null;
        Token string_literal364=null;
        Token char_literal367=null;
        Token char_literal368=null;
        Token char_literal372=null;
        PddlParser.timeSpecifier_return timeSpecifier360 =null;

        PddlParser.daEffect_return daEffect361 =null;

        PddlParser.timeSpecifier_return timeSpecifier365 =null;

        PddlParser.fAssignDA_return fAssignDA366 =null;

        PddlParser.assignOp_return assignOp369 =null;

        PddlParser.fHead_return fHead370 =null;

        PddlParser.fExp_return fExp371 =null;


        Object char_literal358_tree=null;
        Object string_literal359_tree=null;
        Object char_literal362_tree=null;
        Object char_literal363_tree=null;
        Object string_literal364_tree=null;
        Object char_literal367_tree=null;
        Object char_literal368_tree=null;
        Object char_literal372_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }

            // Pddl.g:445:2: ( '(' 'at' timeSpecifier daEffect ')' | '(' 'at' timeSpecifier fAssignDA ')' | '(' assignOp fHead fExp ')' )
            int alt66=3;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==70) ) {
                int LA66_1 = input.LA(2);

                if ( (synpred111_Pddl()) ) {
                    alt66=1;
                }
                else if ( (synpred112_Pddl()) ) {
                    alt66=2;
                }
                else if ( (true) ) {
                    alt66=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 66, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // Pddl.g:445:4: '(' 'at' timeSpecifier daEffect ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal358=(Token)match(input,70,FOLLOW_70_in_timedEffect2874); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal358_tree = 
                    (Object)adaptor.create(char_literal358)
                    ;
                    adaptor.addChild(root_0, char_literal358_tree);
                    }

                    string_literal359=(Token)match(input,112,FOLLOW_112_in_timedEffect2876); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal359_tree = 
                    (Object)adaptor.create(string_literal359)
                    ;
                    adaptor.addChild(root_0, string_literal359_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2878);
                    timeSpecifier360=timeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier360.getTree());

                    pushFollow(FOLLOW_daEffect_in_timedEffect2880);
                    daEffect361=daEffect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, daEffect361.getTree());

                    char_literal362=(Token)match(input,71,FOLLOW_71_in_timedEffect2882); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal362_tree = 
                    (Object)adaptor.create(char_literal362)
                    ;
                    adaptor.addChild(root_0, char_literal362_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:446:4: '(' 'at' timeSpecifier fAssignDA ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal363=(Token)match(input,70,FOLLOW_70_in_timedEffect2892); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal363_tree = 
                    (Object)adaptor.create(char_literal363)
                    ;
                    adaptor.addChild(root_0, char_literal363_tree);
                    }

                    string_literal364=(Token)match(input,112,FOLLOW_112_in_timedEffect2894); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal364_tree = 
                    (Object)adaptor.create(string_literal364)
                    ;
                    adaptor.addChild(root_0, string_literal364_tree);
                    }

                    pushFollow(FOLLOW_timeSpecifier_in_timedEffect2896);
                    timeSpecifier365=timeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, timeSpecifier365.getTree());

                    pushFollow(FOLLOW_fAssignDA_in_timedEffect2898);
                    fAssignDA366=fAssignDA();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fAssignDA366.getTree());

                    char_literal367=(Token)match(input,71,FOLLOW_71_in_timedEffect2900); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal367_tree = 
                    (Object)adaptor.create(char_literal367)
                    ;
                    adaptor.addChild(root_0, char_literal367_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:447:4: '(' assignOp fHead fExp ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal368=(Token)match(input,70,FOLLOW_70_in_timedEffect2905); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal368_tree = 
                    (Object)adaptor.create(char_literal368)
                    ;
                    adaptor.addChild(root_0, char_literal368_tree);
                    }

                    pushFollow(FOLLOW_assignOp_in_timedEffect2907);
                    assignOp369=assignOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp369.getTree());

                    pushFollow(FOLLOW_fHead_in_timedEffect2909);
                    fHead370=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead370.getTree());

                    pushFollow(FOLLOW_fExp_in_timedEffect2911);
                    fExp371=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp371.getTree());

                    char_literal372=(Token)match(input,71,FOLLOW_71_in_timedEffect2913); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal372_tree = 
                    (Object)adaptor.create(char_literal372)
                    ;
                    adaptor.addChild(root_0, char_literal372_tree);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fAssignDA"
    // Pddl.g:450:1: fAssignDA : '(' assignOp fHead fExpDA ')' ;
    public final PddlParser.fAssignDA_return fAssignDA() throws RecognitionException {
        PddlParser.fAssignDA_return retval = new PddlParser.fAssignDA_return();
        retval.start = input.LT(1);

        int fAssignDA_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal373=null;
        Token char_literal377=null;
        PddlParser.assignOp_return assignOp374 =null;

        PddlParser.fHead_return fHead375 =null;

        PddlParser.fExpDA_return fExpDA376 =null;


        Object char_literal373_tree=null;
        Object char_literal377_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }

            // Pddl.g:451:2: ( '(' assignOp fHead fExpDA ')' )
            // Pddl.g:451:4: '(' assignOp fHead fExpDA ')'
            {
            root_0 = (Object)adaptor.nil();


            char_literal373=(Token)match(input,70,FOLLOW_70_in_fAssignDA2933); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal373_tree = 
            (Object)adaptor.create(char_literal373)
            ;
            adaptor.addChild(root_0, char_literal373_tree);
            }

            pushFollow(FOLLOW_assignOp_in_fAssignDA2935);
            assignOp374=assignOp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignOp374.getTree());

            pushFollow(FOLLOW_fHead_in_fAssignDA2937);
            fHead375=fHead();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead375.getTree());

            pushFollow(FOLLOW_fExpDA_in_fAssignDA2939);
            fExpDA376=fExpDA();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA376.getTree());

            char_literal377=(Token)match(input,71,FOLLOW_71_in_fAssignDA2941); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal377_tree = 
            (Object)adaptor.create(char_literal377)
            ;
            adaptor.addChild(root_0, char_literal377_tree);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fExpDA"
    // Pddl.g:454:1: fExpDA : ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp );
    public final PddlParser.fExpDA_return fExpDA() throws RecognitionException {
        PddlParser.fExpDA_return retval = new PddlParser.fExpDA_return();
        retval.start = input.LT(1);

        int fExpDA_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal378=null;
        Token char_literal382=null;
        Token char_literal384=null;
        Token string_literal385=null;
        PddlParser.binaryOp_return binaryOp379 =null;

        PddlParser.fExpDA_return fExpDA380 =null;

        PddlParser.fExpDA_return fExpDA381 =null;

        PddlParser.fExpDA_return fExpDA383 =null;

        PddlParser.fExp_return fExp386 =null;


        Object char_literal378_tree=null;
        Object char_literal382_tree=null;
        Object char_literal384_tree=null;
        Object string_literal385_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }

            // Pddl.g:455:2: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' | '?duration' | fExp )
            int alt68=3;
            switch ( input.LA(1) ) {
            case 70:
                {
                int LA68_1 = input.LA(2);

                if ( (synpred114_Pddl()) ) {
                    alt68=1;
                }
                else if ( (true) ) {
                    alt68=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 1, input);

                    throw nvae;

                }
                }
                break;
            case 104:
                {
                alt68=2;
                }
                break;
            case NAME:
            case NUMBER:
            case 69:
                {
                alt68=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;

            }

            switch (alt68) {
                case 1 :
                    // Pddl.g:455:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal378=(Token)match(input,70,FOLLOW_70_in_fExpDA2952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal378_tree = 
                    (Object)adaptor.create(char_literal378)
                    ;
                    adaptor.addChild(root_0, char_literal378_tree);
                    }

                    // Pddl.g:455:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==74) ) {
                        int LA67_1 = input.LA(2);

                        if ( (synpred113_Pddl()) ) {
                            alt67=1;
                        }
                        else if ( (true) ) {
                            alt67=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 67, 1, input);

                            throw nvae;

                        }
                    }
                    else if ( ((LA67_0 >= 72 && LA67_0 <= 73)||LA67_0==75||LA67_0==105) ) {
                        alt67=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 67, 0, input);

                        throw nvae;

                    }
                    switch (alt67) {
                        case 1 :
                            // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
                            {
                            // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
                            // Pddl.g:455:10: binaryOp fExpDA fExpDA
                            {
                            pushFollow(FOLLOW_binaryOp_in_fExpDA2956);
                            binaryOp379=binaryOp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, binaryOp379.getTree());

                            pushFollow(FOLLOW_fExpDA_in_fExpDA2958);
                            fExpDA380=fExpDA();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA380.getTree());

                            pushFollow(FOLLOW_fExpDA_in_fExpDA2960);
                            fExpDA381=fExpDA();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA381.getTree());

                            }


                            }
                            break;
                        case 2 :
                            // Pddl.g:455:36: ( '-' fExpDA )
                            {
                            // Pddl.g:455:36: ( '-' fExpDA )
                            // Pddl.g:455:37: '-' fExpDA
                            {
                            char_literal382=(Token)match(input,74,FOLLOW_74_in_fExpDA2966); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal382_tree = 
                            (Object)adaptor.create(char_literal382)
                            ;
                            adaptor.addChild(root_0, char_literal382_tree);
                            }

                            pushFollow(FOLLOW_fExpDA_in_fExpDA2968);
                            fExpDA383=fExpDA();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, fExpDA383.getTree());

                            }


                            }
                            break;

                    }


                    char_literal384=(Token)match(input,71,FOLLOW_71_in_fExpDA2972); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal384_tree = 
                    (Object)adaptor.create(char_literal384)
                    ;
                    adaptor.addChild(root_0, char_literal384_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:456:4: '?duration'
                    {
                    root_0 = (Object)adaptor.nil();


                    string_literal385=(Token)match(input,104,FOLLOW_104_in_fExpDA2977); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal385_tree = 
                    (Object)adaptor.create(string_literal385)
                    ;
                    adaptor.addChild(root_0, string_literal385_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:457:4: fExp
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_fExp_in_fExpDA2982);
                    fExp386=fExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fExp386.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problem"
    // Pddl.g:462:1: problem : '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) ;
    public final PddlParser.problem_return problem() throws RecognitionException {
        PddlParser.problem_return retval = new PddlParser.problem_return();
        retval.start = input.LT(1);

        int problem_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal387=null;
        Token string_literal388=null;
        Token char_literal397=null;
        PddlParser.problemDecl_return problemDecl389 =null;

        PddlParser.problemDomain_return problemDomain390 =null;

        PddlParser.requireDef_return requireDef391 =null;

        PddlParser.objectDecl_return objectDecl392 =null;

        PddlParser.init_return init393 =null;

        PddlParser.goal_return goal394 =null;

        PddlParser.probConstraints_return probConstraints395 =null;

        PddlParser.metricSpec_return metricSpec396 =null;


        Object char_literal387_tree=null;
        Object string_literal388_tree=null;
        Object char_literal397_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
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

            // Pddl.g:463:2: ( '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')' -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ) )
            // Pddl.g:463:4: '(' 'define' problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? ')'
            {
            char_literal387=(Token)match(input,70,FOLLOW_70_in_problem2996); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal387);


            string_literal388=(Token)match(input,116,FOLLOW_116_in_problem2998); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_116.add(string_literal388);


            pushFollow(FOLLOW_problemDecl_in_problem3000);
            problemDecl389=problemDecl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_problemDecl.add(problemDecl389.getTree());

            pushFollow(FOLLOW_problemDomain_in_problem3005);
            problemDomain390=problemDomain();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_problemDomain.add(problemDomain390.getTree());

            // Pddl.g:465:7: ( requireDef )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==70) ) {
                int LA69_1 = input.LA(2);

                if ( (LA69_1==97) ) {
                    alt69=1;
                }
            }
            switch (alt69) {
                case 1 :
                    // Pddl.g:465:7: requireDef
                    {
                    pushFollow(FOLLOW_requireDef_in_problem3013);
                    requireDef391=requireDef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_requireDef.add(requireDef391.getTree());

                    }
                    break;

            }


            // Pddl.g:466:7: ( objectDecl )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==70) ) {
                int LA70_1 = input.LA(2);

                if ( (LA70_1==92) ) {
                    alt70=1;
                }
            }
            switch (alt70) {
                case 1 :
                    // Pddl.g:466:7: objectDecl
                    {
                    pushFollow(FOLLOW_objectDecl_in_problem3022);
                    objectDecl392=objectDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_objectDecl.add(objectDecl392.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_init_in_problem3031);
            init393=init();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_init.add(init393.getTree());

            pushFollow(FOLLOW_goal_in_problem3039);
            goal394=goal();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_goal.add(goal394.getTree());

            // Pddl.g:469:7: ( probConstraints )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==70) ) {
                int LA71_1 = input.LA(2);

                if ( (LA71_1==80) ) {
                    alt71=1;
                }
            }
            switch (alt71) {
                case 1 :
                    // Pddl.g:469:7: probConstraints
                    {
                    pushFollow(FOLLOW_probConstraints_in_problem3047);
                    probConstraints395=probConstraints();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_probConstraints.add(probConstraints395.getTree());

                    }
                    break;

            }


            // Pddl.g:470:7: ( metricSpec )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==70) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // Pddl.g:470:7: metricSpec
                    {
                    pushFollow(FOLLOW_metricSpec_in_problem3056);
                    metricSpec396=metricSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricSpec.add(metricSpec396.getTree());

                    }
                    break;

            }


            char_literal397=(Token)match(input,71,FOLLOW_71_in_problem3072); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal397);


            // AST REWRITE
            // elements: metricSpec, requireDef, objectDecl, goal, init, probConstraints, problemDecl, problemDomain
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 473:7: -> ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
            {
                // Pddl.g:473:10: ^( PROBLEM problemDecl problemDomain ( requireDef )? ( objectDecl )? init goal ( probConstraints )? ( metricSpec )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM, "PROBLEM")
                , root_1);

                adaptor.addChild(root_1, stream_problemDecl.nextTree());

                adaptor.addChild(root_1, stream_problemDomain.nextTree());

                // Pddl.g:473:46: ( requireDef )?
                if ( stream_requireDef.hasNext() ) {
                    adaptor.addChild(root_1, stream_requireDef.nextTree());

                }
                stream_requireDef.reset();

                // Pddl.g:473:58: ( objectDecl )?
                if ( stream_objectDecl.hasNext() ) {
                    adaptor.addChild(root_1, stream_objectDecl.nextTree());

                }
                stream_objectDecl.reset();

                adaptor.addChild(root_1, stream_init.nextTree());

                adaptor.addChild(root_1, stream_goal.nextTree());

                // Pddl.g:474:19: ( probConstraints )?
                if ( stream_probConstraints.hasNext() ) {
                    adaptor.addChild(root_1, stream_probConstraints.nextTree());

                }
                stream_probConstraints.reset();

                // Pddl.g:474:36: ( metricSpec )?
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problemDecl"
    // Pddl.g:477:1: problemDecl : '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) ;
    public final PddlParser.problemDecl_return problemDecl() throws RecognitionException {
        PddlParser.problemDecl_return retval = new PddlParser.problemDecl_return();
        retval.start = input.LT(1);

        int problemDecl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal398=null;
        Token string_literal399=null;
        Token NAME400=null;
        Token char_literal401=null;

        Object char_literal398_tree=null;
        Object string_literal399_tree=null;
        Object NAME400_tree=null;
        Object char_literal401_tree=null;
        RewriteRuleTokenStream stream_135=new RewriteRuleTokenStream(adaptor,"token 135");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }

            // Pddl.g:478:5: ( '(' 'problem' NAME ')' -> ^( PROBLEM_NAME NAME ) )
            // Pddl.g:478:7: '(' 'problem' NAME ')'
            {
            char_literal398=(Token)match(input,70,FOLLOW_70_in_problemDecl3129); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal398);


            string_literal399=(Token)match(input,135,FOLLOW_135_in_problemDecl3131); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_135.add(string_literal399);


            NAME400=(Token)match(input,NAME,FOLLOW_NAME_in_problemDecl3133); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME400);


            char_literal401=(Token)match(input,71,FOLLOW_71_in_problemDecl3135); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal401);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 479:5: -> ^( PROBLEM_NAME NAME )
            {
                // Pddl.g:479:8: ^( PROBLEM_NAME NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_NAME, "PROBLEM_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "problemDomain"
    // Pddl.g:482:1: problemDomain : '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) ;
    public final PddlParser.problemDomain_return problemDomain() throws RecognitionException {
        PddlParser.problemDomain_return retval = new PddlParser.problemDomain_return();
        retval.start = input.LT(1);

        int problemDomain_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal402=null;
        Token string_literal403=null;
        Token NAME404=null;
        Token char_literal405=null;

        Object char_literal402_tree=null;
        Object string_literal403_tree=null;
        Object NAME404_tree=null;
        Object char_literal405_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }

            // Pddl.g:483:2: ( '(' ':domain' NAME ')' -> ^( PROBLEM_DOMAIN NAME ) )
            // Pddl.g:483:4: '(' ':domain' NAME ')'
            {
            char_literal402=(Token)match(input,70,FOLLOW_70_in_problemDomain3161); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal402);


            string_literal403=(Token)match(input,82,FOLLOW_82_in_problemDomain3163); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(string_literal403);


            NAME404=(Token)match(input,NAME,FOLLOW_NAME_in_problemDomain3165); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(NAME404);


            char_literal405=(Token)match(input,71,FOLLOW_71_in_problemDomain3167); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal405);


            // AST REWRITE
            // elements: NAME
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 484:2: -> ^( PROBLEM_DOMAIN NAME )
            {
                // Pddl.g:484:5: ^( PROBLEM_DOMAIN NAME )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_DOMAIN, "PROBLEM_DOMAIN")
                , root_1);

                adaptor.addChild(root_1, 
                stream_NAME.nextNode()
                );

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objectDecl"
    // Pddl.g:487:1: objectDecl : '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) ;
    public final PddlParser.objectDecl_return objectDecl() throws RecognitionException {
        PddlParser.objectDecl_return retval = new PddlParser.objectDecl_return();
        retval.start = input.LT(1);

        int objectDecl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal406=null;
        Token string_literal407=null;
        Token char_literal409=null;
        PddlParser.typedNameList_return typedNameList408 =null;


        Object char_literal406_tree=null;
        Object string_literal407_tree=null;
        Object char_literal409_tree=null;
        RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_typedNameList=new RewriteRuleSubtreeStream(adaptor,"rule typedNameList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }

            // Pddl.g:488:2: ( '(' ':objects' typedNameList ')' -> ^( OBJECTS typedNameList ) )
            // Pddl.g:488:4: '(' ':objects' typedNameList ')'
            {
            char_literal406=(Token)match(input,70,FOLLOW_70_in_objectDecl3187); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal406);


            string_literal407=(Token)match(input,92,FOLLOW_92_in_objectDecl3189); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_92.add(string_literal407);


            pushFollow(FOLLOW_typedNameList_in_objectDecl3191);
            typedNameList408=typedNameList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typedNameList.add(typedNameList408.getTree());

            char_literal409=(Token)match(input,71,FOLLOW_71_in_objectDecl3193); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal409);


            // AST REWRITE
            // elements: typedNameList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 489:2: -> ^( OBJECTS typedNameList )
            {
                // Pddl.g:489:5: ^( OBJECTS typedNameList )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(OBJECTS, "OBJECTS")
                , root_1);

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
            if ( state.backtracking>0 ) { memoize(input, 68, objectDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "objectDecl"


    public static class init_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "init"
    // Pddl.g:492:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) );
    public final PddlParser.init_return init() throws RecognitionException {
        PddlParser.init_return retval = new PddlParser.init_return();
        retval.start = input.LT(1);

        int init_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal410=null;
        Token string_literal411=null;
        Token char_literal413=null;
        Token char_literal414=null;
        Token string_literal415=null;
        Token char_literal417=null;
        PddlParser.initEl_return initEl412 =null;

        PddlParser.belief_return belief416 =null;


        Object char_literal410_tree=null;
        Object string_literal411_tree=null;
        Object char_literal413_tree=null;
        Object char_literal414_tree=null;
        Object string_literal415_tree=null;
        Object char_literal417_tree=null;
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_initEl=new RewriteRuleSubtreeStream(adaptor,"rule initEl");
        RewriteRuleSubtreeStream stream_belief=new RewriteRuleSubtreeStream(adaptor,"rule belief");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }

            // Pddl.g:493:2: ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) )
            int alt74=2;
            alt74 = dfa74.predict(input);
            switch (alt74) {
                case 1 :
                    // Pddl.g:493:4: '(' ':init' ( initEl )* ')'
                    {
                    char_literal410=(Token)match(input,70,FOLLOW_70_in_init3213); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal410);


                    string_literal411=(Token)match(input,90,FOLLOW_90_in_init3215); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(string_literal411);


                    // Pddl.g:493:16: ( initEl )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==70) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // Pddl.g:493:16: initEl
                    	    {
                    	    pushFollow(FOLLOW_initEl_in_init3217);
                    	    initEl412=initEl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_initEl.add(initEl412.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);


                    char_literal413=(Token)match(input,71,FOLLOW_71_in_init3220); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal413);


                    // AST REWRITE
                    // elements: initEl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 494:2: -> ^( INIT ( initEl )* )
                    {
                        // Pddl.g:494:5: ^( INIT ( initEl )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT, "INIT")
                        , root_1);

                        // Pddl.g:494:12: ( initEl )*
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
                    // Pddl.g:495:4: '(' ':init' belief ')'
                    {
                    char_literal414=(Token)match(input,70,FOLLOW_70_in_init3235); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal414);


                    string_literal415=(Token)match(input,90,FOLLOW_90_in_init3237); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(string_literal415);


                    pushFollow(FOLLOW_belief_in_init3239);
                    belief416=belief();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_belief.add(belief416.getTree());

                    char_literal417=(Token)match(input,71,FOLLOW_71_in_init3241); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal417);


                    // AST REWRITE
                    // elements: belief
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 495:27: -> ^( FORMULAINIT belief )
                    {
                        // Pddl.g:495:30: ^( FORMULAINIT belief )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(FORMULAINIT, "FORMULAINIT")
                        , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "initEl"
    // Pddl.g:498:1: initEl : ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) );
    public final PddlParser.initEl_return initEl() throws RecognitionException {
        PddlParser.initEl_return retval = new PddlParser.initEl_return();
        retval.start = input.LT(1);

        int initEl_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal419=null;
        Token char_literal420=null;
        Token NUMBER422=null;
        Token char_literal423=null;
        Token char_literal424=null;
        Token string_literal425=null;
        Token NUMBER426=null;
        Token char_literal428=null;
        Token char_literal429=null;
        Token string_literal430=null;
        Token char_literal432=null;
        Token char_literal433=null;
        Token string_literal434=null;
        Token char_literal436=null;
        Token char_literal437=null;
        Token string_literal438=null;
        Token char_literal440=null;
        PddlParser.nameLiteral_return nameLiteral418 =null;

        PddlParser.fHead_return fHead421 =null;

        PddlParser.nameLiteral_return nameLiteral427 =null;

        PddlParser.atomicNameFormula_return atomicNameFormula431 =null;

        PddlParser.atomicNameFormula_return atomicNameFormula435 =null;

        PddlParser.nameLiteral_return nameLiteral439 =null;


        Object char_literal419_tree=null;
        Object char_literal420_tree=null;
        Object NUMBER422_tree=null;
        Object char_literal423_tree=null;
        Object char_literal424_tree=null;
        Object string_literal425_tree=null;
        Object NUMBER426_tree=null;
        Object char_literal428_tree=null;
        Object char_literal429_tree=null;
        Object string_literal430_tree=null;
        Object char_literal432_tree=null;
        Object char_literal433_tree=null;
        Object string_literal434_tree=null;
        Object char_literal436_tree=null;
        Object char_literal437_tree=null;
        Object string_literal438_tree=null;
        Object char_literal440_tree=null;
        RewriteRuleTokenStream stream_143=new RewriteRuleTokenStream(adaptor,"token 143");
        RewriteRuleTokenStream stream_132=new RewriteRuleTokenStream(adaptor,"token 132");
        RewriteRuleTokenStream stream_101=new RewriteRuleTokenStream(adaptor,"token 101");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");
        RewriteRuleTokenStream stream_112=new RewriteRuleTokenStream(adaptor,"token 112");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_131=new RewriteRuleTokenStream(adaptor,"token 131");
        RewriteRuleSubtreeStream stream_fHead=new RewriteRuleSubtreeStream(adaptor,"rule fHead");
        RewriteRuleSubtreeStream stream_atomicNameFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicNameFormula");
        RewriteRuleSubtreeStream stream_nameLiteral=new RewriteRuleSubtreeStream(adaptor,"rule nameLiteral");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }

            // Pddl.g:499:2: ( nameLiteral | '(' '=' fHead NUMBER ')' -> ^( INIT_EQ fHead NUMBER ) | '(' 'at' NUMBER nameLiteral ')' -> ^( INIT_AT NUMBER nameLiteral ) | '(' 'unknown' atomicNameFormula ')' -> ^( UNKNOWN atomicNameFormula ) | '(' 'oneof' ( atomicNameFormula )* ')' -> ^( ONEOF ( atomicNameFormula )* ) | '(' 'or' ( nameLiteral )* ')' -> ^( OR_GD ( nameLiteral )* ) )
            int alt77=6;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==70) ) {
                switch ( input.LA(2) ) {
                case NAME:
                case 129:
                    {
                    alt77=1;
                    }
                    break;
                case 101:
                    {
                    alt77=2;
                    }
                    break;
                case 112:
                    {
                    alt77=3;
                    }
                    break;
                case 143:
                    {
                    alt77=4;
                    }
                    break;
                case 131:
                    {
                    alt77=5;
                    }
                    break;
                case 132:
                    {
                    alt77=6;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 77, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;

            }
            switch (alt77) {
                case 1 :
                    // Pddl.g:499:4: nameLiteral
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_nameLiteral_in_initEl3260);
                    nameLiteral418=nameLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nameLiteral418.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:500:4: '(' '=' fHead NUMBER ')'
                    {
                    char_literal419=(Token)match(input,70,FOLLOW_70_in_initEl3265); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal419);


                    char_literal420=(Token)match(input,101,FOLLOW_101_in_initEl3267); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_101.add(char_literal420);


                    pushFollow(FOLLOW_fHead_in_initEl3269);
                    fHead421=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fHead.add(fHead421.getTree());

                    NUMBER422=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_initEl3271); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NUMBER.add(NUMBER422);


                    char_literal423=(Token)match(input,71,FOLLOW_71_in_initEl3273); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal423);


                    // AST REWRITE
                    // elements: fHead, NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 500:37: -> ^( INIT_EQ fHead NUMBER )
                    {
                        // Pddl.g:500:40: ^( INIT_EQ fHead NUMBER )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT_EQ, "INIT_EQ")
                        , root_1);

                        adaptor.addChild(root_1, stream_fHead.nextTree());

                        adaptor.addChild(root_1, 
                        stream_NUMBER.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:501:4: '(' 'at' NUMBER nameLiteral ')'
                    {
                    char_literal424=(Token)match(input,70,FOLLOW_70_in_initEl3296); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal424);


                    string_literal425=(Token)match(input,112,FOLLOW_112_in_initEl3298); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_112.add(string_literal425);


                    NUMBER426=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_initEl3300); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NUMBER.add(NUMBER426);


                    pushFollow(FOLLOW_nameLiteral_in_initEl3302);
                    nameLiteral427=nameLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_nameLiteral.add(nameLiteral427.getTree());

                    char_literal428=(Token)match(input,71,FOLLOW_71_in_initEl3304); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal428);


                    // AST REWRITE
                    // elements: NUMBER, nameLiteral
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 501:37: -> ^( INIT_AT NUMBER nameLiteral )
                    {
                        // Pddl.g:501:40: ^( INIT_AT NUMBER nameLiteral )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(INIT_AT, "INIT_AT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_NUMBER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_nameLiteral.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:502:4: '(' 'unknown' atomicNameFormula ')'
                    {
                    char_literal429=(Token)match(input,70,FOLLOW_70_in_initEl3320); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal429);


                    string_literal430=(Token)match(input,143,FOLLOW_143_in_initEl3322); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_143.add(string_literal430);


                    pushFollow(FOLLOW_atomicNameFormula_in_initEl3325);
                    atomicNameFormula431=atomicNameFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula431.getTree());

                    char_literal432=(Token)match(input,71,FOLLOW_71_in_initEl3327); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal432);


                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 502:42: -> ^( UNKNOWN atomicNameFormula )
                    {
                        // Pddl.g:502:45: ^( UNKNOWN atomicNameFormula )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(UNKNOWN, "UNKNOWN")
                        , root_1);

                        adaptor.addChild(root_1, stream_atomicNameFormula.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:503:4: '(' 'oneof' ( atomicNameFormula )* ')'
                    {
                    char_literal433=(Token)match(input,70,FOLLOW_70_in_initEl3341); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal433);


                    string_literal434=(Token)match(input,131,FOLLOW_131_in_initEl3343); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_131.add(string_literal434);


                    // Pddl.g:503:17: ( atomicNameFormula )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==70) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // Pddl.g:503:17: atomicNameFormula
                    	    {
                    	    pushFollow(FOLLOW_atomicNameFormula_in_initEl3346);
                    	    atomicNameFormula435=atomicNameFormula();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula435.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);


                    char_literal436=(Token)match(input,71,FOLLOW_71_in_initEl3349); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal436);


                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 503:41: -> ^( ONEOF ( atomicNameFormula )* )
                    {
                        // Pddl.g:503:44: ^( ONEOF ( atomicNameFormula )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ONEOF, "ONEOF")
                        , root_1);

                        // Pddl.g:503:52: ( atomicNameFormula )*
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
                    // Pddl.g:504:4: '(' 'or' ( nameLiteral )* ')'
                    {
                    char_literal437=(Token)match(input,70,FOLLOW_70_in_initEl3364); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal437);


                    string_literal438=(Token)match(input,132,FOLLOW_132_in_initEl3366); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_132.add(string_literal438);


                    // Pddl.g:504:14: ( nameLiteral )*
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==70) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // Pddl.g:504:14: nameLiteral
                    	    {
                    	    pushFollow(FOLLOW_nameLiteral_in_initEl3369);
                    	    nameLiteral439=nameLiteral();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_nameLiteral.add(nameLiteral439.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop76;
                        }
                    } while (true);


                    char_literal440=(Token)match(input,71,FOLLOW_71_in_initEl3372); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal440);


                    // AST REWRITE
                    // elements: nameLiteral
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 504:32: -> ^( OR_GD ( nameLiteral )* )
                    {
                        // Pddl.g:504:35: ^( OR_GD ( nameLiteral )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(OR_GD, "OR_GD")
                        , root_1);

                        // Pddl.g:504:43: ( nameLiteral )*
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nameLiteral"
    // Pddl.g:507:1: nameLiteral : ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) );
    public final PddlParser.nameLiteral_return nameLiteral() throws RecognitionException {
        PddlParser.nameLiteral_return retval = new PddlParser.nameLiteral_return();
        retval.start = input.LT(1);

        int nameLiteral_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal442=null;
        Token string_literal443=null;
        Token char_literal445=null;
        PddlParser.atomicNameFormula_return atomicNameFormula441 =null;

        PddlParser.atomicNameFormula_return atomicNameFormula444 =null;


        Object char_literal442_tree=null;
        Object string_literal443_tree=null;
        Object char_literal445_tree=null;
        RewriteRuleTokenStream stream_129=new RewriteRuleTokenStream(adaptor,"token 129");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_atomicNameFormula=new RewriteRuleSubtreeStream(adaptor,"rule atomicNameFormula");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }

            // Pddl.g:508:2: ( atomicNameFormula | '(' 'not' atomicNameFormula ')' -> ^( NOT_PRED_INIT atomicNameFormula ) )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==70) ) {
                int LA78_1 = input.LA(2);

                if ( (LA78_1==129) ) {
                    alt78=2;
                }
                else if ( (LA78_1==NAME) ) {
                    alt78=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 78, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;

            }
            switch (alt78) {
                case 1 :
                    // Pddl.g:508:4: atomicNameFormula
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3393);
                    atomicNameFormula441=atomicNameFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atomicNameFormula441.getTree());

                    }
                    break;
                case 2 :
                    // Pddl.g:509:4: '(' 'not' atomicNameFormula ')'
                    {
                    char_literal442=(Token)match(input,70,FOLLOW_70_in_nameLiteral3398); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal442);


                    string_literal443=(Token)match(input,129,FOLLOW_129_in_nameLiteral3400); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_129.add(string_literal443);


                    pushFollow(FOLLOW_atomicNameFormula_in_nameLiteral3402);
                    atomicNameFormula444=atomicNameFormula();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atomicNameFormula.add(atomicNameFormula444.getTree());

                    char_literal445=(Token)match(input,71,FOLLOW_71_in_nameLiteral3404); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal445);


                    // AST REWRITE
                    // elements: atomicNameFormula
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 509:36: -> ^( NOT_PRED_INIT atomicNameFormula )
                    {
                        // Pddl.g:509:39: ^( NOT_PRED_INIT atomicNameFormula )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NOT_PRED_INIT, "NOT_PRED_INIT")
                        , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atomicNameFormula"
    // Pddl.g:512:1: atomicNameFormula : '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) ;
    public final PddlParser.atomicNameFormula_return atomicNameFormula() throws RecognitionException {
        PddlParser.atomicNameFormula_return retval = new PddlParser.atomicNameFormula_return();
        retval.start = input.LT(1);

        int atomicNameFormula_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal446=null;
        Token NAME448=null;
        Token char_literal449=null;
        PddlParser.predicate_return predicate447 =null;


        Object char_literal446_tree=null;
        Object NAME448_tree=null;
        Object char_literal449_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleSubtreeStream stream_predicate=new RewriteRuleSubtreeStream(adaptor,"rule predicate");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }

            // Pddl.g:513:2: ( '(' predicate ( NAME )* ')' -> ^( PRED_INST predicate ( NAME )* ) )
            // Pddl.g:513:4: '(' predicate ( NAME )* ')'
            {
            char_literal446=(Token)match(input,70,FOLLOW_70_in_atomicNameFormula3423); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal446);


            pushFollow(FOLLOW_predicate_in_atomicNameFormula3425);
            predicate447=predicate();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_predicate.add(predicate447.getTree());

            // Pddl.g:513:18: ( NAME )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==NAME) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // Pddl.g:513:18: NAME
            	    {
            	    NAME448=(Token)match(input,NAME,FOLLOW_NAME_in_atomicNameFormula3427); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NAME.add(NAME448);


            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);


            char_literal449=(Token)match(input,71,FOLLOW_71_in_atomicNameFormula3430); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal449);


            // AST REWRITE
            // elements: NAME, predicate
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 513:28: -> ^( PRED_INST predicate ( NAME )* )
            {
                // Pddl.g:513:31: ^( PRED_INST predicate ( NAME )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PRED_INST, "PRED_INST")
                , root_1);

                adaptor.addChild(root_1, stream_predicate.nextTree());

                // Pddl.g:513:53: ( NAME )*
                while ( stream_NAME.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_NAME.nextNode()
                    );

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "goal"
    // Pddl.g:520:1: goal : '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) ;
    public final PddlParser.goal_return goal() throws RecognitionException {
        PddlParser.goal_return retval = new PddlParser.goal_return();
        retval.start = input.LT(1);

        int goal_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal450=null;
        Token string_literal451=null;
        Token char_literal453=null;
        PddlParser.goalDesc_return goalDesc452 =null;


        Object char_literal450_tree=null;
        Object string_literal451_tree=null;
        Object char_literal453_tree=null;
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_goalDesc=new RewriteRuleSubtreeStream(adaptor,"rule goalDesc");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }

            // Pddl.g:520:6: ( '(' ':goal' goalDesc ')' -> ^( GOAL goalDesc ) )
            // Pddl.g:520:8: '(' ':goal' goalDesc ')'
            {
            char_literal450=(Token)match(input,70,FOLLOW_70_in_goal3455); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal450);


            string_literal451=(Token)match(input,89,FOLLOW_89_in_goal3457); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_89.add(string_literal451);


            pushFollow(FOLLOW_goalDesc_in_goal3459);
            goalDesc452=goalDesc();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_goalDesc.add(goalDesc452.getTree());

            char_literal453=(Token)match(input,71,FOLLOW_71_in_goal3462); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal453);


            // AST REWRITE
            // elements: goalDesc
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 520:34: -> ^( GOAL goalDesc )
            {
                // Pddl.g:520:37: ^( GOAL goalDesc )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(GOAL, "GOAL")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "probConstraints"
    // Pddl.g:522:1: probConstraints : '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) ;
    public final PddlParser.probConstraints_return probConstraints() throws RecognitionException {
        PddlParser.probConstraints_return retval = new PddlParser.probConstraints_return();
        retval.start = input.LT(1);

        int probConstraints_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal454=null;
        Token string_literal455=null;
        Token char_literal457=null;
        PddlParser.prefConGD_return prefConGD456 =null;


        Object char_literal454_tree=null;
        Object string_literal455_tree=null;
        Object char_literal457_tree=null;
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_prefConGD=new RewriteRuleSubtreeStream(adaptor,"rule prefConGD");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }

            // Pddl.g:523:2: ( '(' ':constraints' prefConGD ')' -> ^( PROBLEM_CONSTRAINT prefConGD ) )
            // Pddl.g:523:4: '(' ':constraints' prefConGD ')'
            {
            char_literal454=(Token)match(input,70,FOLLOW_70_in_probConstraints3480); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal454);


            string_literal455=(Token)match(input,80,FOLLOW_80_in_probConstraints3482); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_80.add(string_literal455);


            pushFollow(FOLLOW_prefConGD_in_probConstraints3485);
            prefConGD456=prefConGD();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_prefConGD.add(prefConGD456.getTree());

            char_literal457=(Token)match(input,71,FOLLOW_71_in_probConstraints3487); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal457);


            // AST REWRITE
            // elements: prefConGD
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 524:4: -> ^( PROBLEM_CONSTRAINT prefConGD )
            {
                // Pddl.g:524:7: ^( PROBLEM_CONSTRAINT prefConGD )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_CONSTRAINT, "PROBLEM_CONSTRAINT")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prefConGD"
    // Pddl.g:527:1: prefConGD : ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD );
    public final PddlParser.prefConGD_return prefConGD() throws RecognitionException {
        PddlParser.prefConGD_return retval = new PddlParser.prefConGD_return();
        retval.start = input.LT(1);

        int prefConGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal458=null;
        Token string_literal459=null;
        Token char_literal461=null;
        Token char_literal462=null;
        Token string_literal463=null;
        Token char_literal464=null;
        Token char_literal466=null;
        Token char_literal468=null;
        Token char_literal469=null;
        Token string_literal470=null;
        Token NAME471=null;
        Token char_literal473=null;
        PddlParser.prefConGD_return prefConGD460 =null;

        PddlParser.typedVariableList_return typedVariableList465 =null;

        PddlParser.prefConGD_return prefConGD467 =null;

        PddlParser.conGD_return conGD472 =null;

        PddlParser.conGD_return conGD474 =null;


        Object char_literal458_tree=null;
        Object string_literal459_tree=null;
        Object char_literal461_tree=null;
        Object char_literal462_tree=null;
        Object string_literal463_tree=null;
        Object char_literal464_tree=null;
        Object char_literal466_tree=null;
        Object char_literal468_tree=null;
        Object char_literal469_tree=null;
        Object string_literal470_tree=null;
        Object NAME471_tree=null;
        Object char_literal473_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }

            // Pddl.g:528:2: ( '(' 'and' ( prefConGD )* ')' | '(' 'forall' '(' typedVariableList ')' prefConGD ')' | '(' 'preference' ( NAME )? conGD ')' | conGD )
            int alt82=4;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==70) ) {
                int LA82_1 = input.LA(2);

                if ( (synpred132_Pddl()) ) {
                    alt82=1;
                }
                else if ( (synpred133_Pddl()) ) {
                    alt82=2;
                }
                else if ( (synpred135_Pddl()) ) {
                    alt82=3;
                }
                else if ( (true) ) {
                    alt82=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 82, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;

            }
            switch (alt82) {
                case 1 :
                    // Pddl.g:528:4: '(' 'and' ( prefConGD )* ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal458=(Token)match(input,70,FOLLOW_70_in_prefConGD3509); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal458_tree = 
                    (Object)adaptor.create(char_literal458)
                    ;
                    adaptor.addChild(root_0, char_literal458_tree);
                    }

                    string_literal459=(Token)match(input,110,FOLLOW_110_in_prefConGD3511); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal459_tree = 
                    (Object)adaptor.create(string_literal459)
                    ;
                    adaptor.addChild(root_0, string_literal459_tree);
                    }

                    // Pddl.g:528:14: ( prefConGD )*
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==70) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // Pddl.g:528:14: prefConGD
                    	    {
                    	    pushFollow(FOLLOW_prefConGD_in_prefConGD3513);
                    	    prefConGD460=prefConGD();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, prefConGD460.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop80;
                        }
                    } while (true);


                    char_literal461=(Token)match(input,71,FOLLOW_71_in_prefConGD3516); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal461_tree = 
                    (Object)adaptor.create(char_literal461)
                    ;
                    adaptor.addChild(root_0, char_literal461_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:529:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal462=(Token)match(input,70,FOLLOW_70_in_prefConGD3521); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal462_tree = 
                    (Object)adaptor.create(char_literal462)
                    ;
                    adaptor.addChild(root_0, char_literal462_tree);
                    }

                    string_literal463=(Token)match(input,121,FOLLOW_121_in_prefConGD3523); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal463_tree = 
                    (Object)adaptor.create(string_literal463)
                    ;
                    adaptor.addChild(root_0, string_literal463_tree);
                    }

                    char_literal464=(Token)match(input,70,FOLLOW_70_in_prefConGD3525); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal464_tree = 
                    (Object)adaptor.create(char_literal464)
                    ;
                    adaptor.addChild(root_0, char_literal464_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_prefConGD3527);
                    typedVariableList465=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList465.getTree());

                    char_literal466=(Token)match(input,71,FOLLOW_71_in_prefConGD3529); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal466_tree = 
                    (Object)adaptor.create(char_literal466)
                    ;
                    adaptor.addChild(root_0, char_literal466_tree);
                    }

                    pushFollow(FOLLOW_prefConGD_in_prefConGD3531);
                    prefConGD467=prefConGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, prefConGD467.getTree());

                    char_literal468=(Token)match(input,71,FOLLOW_71_in_prefConGD3533); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal468_tree = 
                    (Object)adaptor.create(char_literal468)
                    ;
                    adaptor.addChild(root_0, char_literal468_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:530:4: '(' 'preference' ( NAME )? conGD ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal469=(Token)match(input,70,FOLLOW_70_in_prefConGD3538); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal469_tree = 
                    (Object)adaptor.create(char_literal469)
                    ;
                    adaptor.addChild(root_0, char_literal469_tree);
                    }

                    string_literal470=(Token)match(input,134,FOLLOW_134_in_prefConGD3540); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal470_tree = 
                    (Object)adaptor.create(string_literal470)
                    ;
                    adaptor.addChild(root_0, string_literal470_tree);
                    }

                    // Pddl.g:530:21: ( NAME )?
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==NAME) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // Pddl.g:530:21: NAME
                            {
                            NAME471=(Token)match(input,NAME,FOLLOW_NAME_in_prefConGD3542); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NAME471_tree = 
                            (Object)adaptor.create(NAME471)
                            ;
                            adaptor.addChild(root_0, NAME471_tree);
                            }

                            }
                            break;

                    }


                    pushFollow(FOLLOW_conGD_in_prefConGD3545);
                    conGD472=conGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD472.getTree());

                    char_literal473=(Token)match(input,71,FOLLOW_71_in_prefConGD3547); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal473_tree = 
                    (Object)adaptor.create(char_literal473)
                    ;
                    adaptor.addChild(root_0, char_literal473_tree);
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:531:4: conGD
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_conGD_in_prefConGD3552);
                    conGD474=conGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD474.getTree());

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "metricSpec"
    // Pddl.g:534:1: metricSpec : '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) ;
    public final PddlParser.metricSpec_return metricSpec() throws RecognitionException {
        PddlParser.metricSpec_return retval = new PddlParser.metricSpec_return();
        retval.start = input.LT(1);

        int metricSpec_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal475=null;
        Token string_literal476=null;
        Token char_literal479=null;
        PddlParser.optimization_return optimization477 =null;

        PddlParser.metricFExp_return metricFExp478 =null;


        Object char_literal475_tree=null;
        Object string_literal476_tree=null;
        Object char_literal479_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_metricFExp=new RewriteRuleSubtreeStream(adaptor,"rule metricFExp");
        RewriteRuleSubtreeStream stream_optimization=new RewriteRuleSubtreeStream(adaptor,"rule optimization");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }

            // Pddl.g:535:2: ( '(' ':metric' optimization metricFExp ')' -> ^( PROBLEM_METRIC optimization metricFExp ) )
            // Pddl.g:535:4: '(' ':metric' optimization metricFExp ')'
            {
            char_literal475=(Token)match(input,70,FOLLOW_70_in_metricSpec3563); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal475);


            string_literal476=(Token)match(input,91,FOLLOW_91_in_metricSpec3565); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_91.add(string_literal476);


            pushFollow(FOLLOW_optimization_in_metricSpec3567);
            optimization477=optimization();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_optimization.add(optimization477.getTree());

            pushFollow(FOLLOW_metricFExp_in_metricSpec3569);
            metricFExp478=metricFExp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp478.getTree());

            char_literal479=(Token)match(input,71,FOLLOW_71_in_metricSpec3571); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal479);


            // AST REWRITE
            // elements: metricFExp, optimization
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 536:4: -> ^( PROBLEM_METRIC optimization metricFExp )
            {
                // Pddl.g:536:7: ^( PROBLEM_METRIC optimization metricFExp )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(PROBLEM_METRIC, "PROBLEM_METRIC")
                , root_1);

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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "optimization"
    // Pddl.g:539:1: optimization : ( 'minimize' | 'maximize' );
    public final PddlParser.optimization_return optimization() throws RecognitionException {
        PddlParser.optimization_return retval = new PddlParser.optimization_return();
        retval.start = input.LT(1);

        int optimization_StartIndex = input.index();

        Object root_0 = null;

        Token set480=null;

        Object set480_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }

            // Pddl.g:539:14: ( 'minimize' | 'maximize' )
            // Pddl.g:
            {
            root_0 = (Object)adaptor.nil();


            set480=(Token)input.LT(1);

            if ( (input.LA(1) >= 127 && input.LA(1) <= 128) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set480)
                );
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "metricFExp"
    // Pddl.g:541:1: metricFExp : ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' );
    public final PddlParser.metricFExp_return metricFExp() throws RecognitionException {
        PddlParser.metricFExp_return retval = new PddlParser.metricFExp_return();
        retval.start = input.LT(1);

        int metricFExp_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal481=null;
        Token char_literal485=null;
        Token char_literal486=null;
        Token char_literal490=null;
        Token char_literal491=null;
        Token char_literal492=null;
        Token char_literal494=null;
        Token NUMBER495=null;
        Token char_literal497=null;
        Token string_literal498=null;
        Token NAME499=null;
        Token char_literal500=null;
        PddlParser.binaryOp_return binaryOp482 =null;

        PddlParser.metricFExp_return metricFExp483 =null;

        PddlParser.metricFExp_return metricFExp484 =null;

        PddlParser.multiOp_return multiOp487 =null;

        PddlParser.metricFExp_return metricFExp488 =null;

        PddlParser.metricFExp_return metricFExp489 =null;

        PddlParser.metricFExp_return metricFExp493 =null;

        PddlParser.fHead_return fHead496 =null;


        Object char_literal481_tree=null;
        Object char_literal485_tree=null;
        Object char_literal486_tree=null;
        Object char_literal490_tree=null;
        Object char_literal491_tree=null;
        Object char_literal492_tree=null;
        Object char_literal494_tree=null;
        Object NUMBER495_tree=null;
        Object char_literal497_tree=null;
        Object string_literal498_tree=null;
        Object NAME499_tree=null;
        Object char_literal500_tree=null;
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_binaryOp=new RewriteRuleSubtreeStream(adaptor,"rule binaryOp");
        RewriteRuleSubtreeStream stream_metricFExp=new RewriteRuleSubtreeStream(adaptor,"rule metricFExp");
        RewriteRuleSubtreeStream stream_multiOp=new RewriteRuleSubtreeStream(adaptor,"rule multiOp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }

            // Pddl.g:542:2: ( '(' binaryOp metricFExp metricFExp ')' -> ^( BINARY_OP binaryOp metricFExp metricFExp ) | '(' multiOp metricFExp ( metricFExp )+ ')' -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ ) | '(' '-' metricFExp ')' -> ^( MINUS_OP metricFExp ) | NUMBER | fHead | '(' 'is-violated' NAME ')' )
            int alt84=6;
            switch ( input.LA(1) ) {
            case 70:
                {
                int LA84_1 = input.LA(2);

                if ( (synpred137_Pddl()) ) {
                    alt84=1;
                }
                else if ( (synpred139_Pddl()) ) {
                    alt84=2;
                }
                else if ( (synpred140_Pddl()) ) {
                    alt84=3;
                }
                else if ( (synpred142_Pddl()) ) {
                    alt84=5;
                }
                else if ( (true) ) {
                    alt84=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 1, input);

                    throw nvae;

                }
                }
                break;
            case NUMBER:
                {
                alt84=4;
                }
                break;
            case NAME:
            case 69:
                {
                alt84=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // Pddl.g:542:4: '(' binaryOp metricFExp metricFExp ')'
                    {
                    char_literal481=(Token)match(input,70,FOLLOW_70_in_metricFExp3608); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal481);


                    pushFollow(FOLLOW_binaryOp_in_metricFExp3610);
                    binaryOp482=binaryOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binaryOp.add(binaryOp482.getTree());

                    pushFollow(FOLLOW_metricFExp_in_metricFExp3612);
                    metricFExp483=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp483.getTree());

                    pushFollow(FOLLOW_metricFExp_in_metricFExp3614);
                    metricFExp484=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp484.getTree());

                    char_literal485=(Token)match(input,71,FOLLOW_71_in_metricFExp3616); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal485);


                    // AST REWRITE
                    // elements: metricFExp, metricFExp, binaryOp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 543:6: -> ^( BINARY_OP binaryOp metricFExp metricFExp )
                    {
                        // Pddl.g:543:9: ^( BINARY_OP binaryOp metricFExp metricFExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(BINARY_OP, "BINARY_OP")
                        , root_1);

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
                    // Pddl.g:544:4: '(' multiOp metricFExp ( metricFExp )+ ')'
                    {
                    char_literal486=(Token)match(input,70,FOLLOW_70_in_metricFExp3638); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal486);


                    pushFollow(FOLLOW_multiOp_in_metricFExp3640);
                    multiOp487=multiOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_multiOp.add(multiOp487.getTree());

                    pushFollow(FOLLOW_metricFExp_in_metricFExp3642);
                    metricFExp488=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp488.getTree());

                    // Pddl.g:544:27: ( metricFExp )+
                    int cnt83=0;
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==NAME||LA83_0==NUMBER||(LA83_0 >= 69 && LA83_0 <= 70)) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // Pddl.g:544:27: metricFExp
                    	    {
                    	    pushFollow(FOLLOW_metricFExp_in_metricFExp3644);
                    	    metricFExp489=metricFExp();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp489.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt83 >= 1 ) break loop83;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(83, input);
                                throw eee;
                        }
                        cnt83++;
                    } while (true);


                    char_literal490=(Token)match(input,71,FOLLOW_71_in_metricFExp3647); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal490);


                    // AST REWRITE
                    // elements: metricFExp, multiOp, metricFExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 545:7: -> ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
                    {
                        // Pddl.g:545:10: ^( MULTI_OP multiOp metricFExp ( metricFExp )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(MULTI_OP, "MULTI_OP")
                        , root_1);

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
                    // Pddl.g:546:4: '(' '-' metricFExp ')'
                    {
                    char_literal491=(Token)match(input,70,FOLLOW_70_in_metricFExp3671); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_70.add(char_literal491);


                    char_literal492=(Token)match(input,74,FOLLOW_74_in_metricFExp3673); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(char_literal492);


                    pushFollow(FOLLOW_metricFExp_in_metricFExp3675);
                    metricFExp493=metricFExp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_metricFExp.add(metricFExp493.getTree());

                    char_literal494=(Token)match(input,71,FOLLOW_71_in_metricFExp3677); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal494);


                    // AST REWRITE
                    // elements: metricFExp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 547:4: -> ^( MINUS_OP metricFExp )
                    {
                        // Pddl.g:547:7: ^( MINUS_OP metricFExp )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(MINUS_OP, "MINUS_OP")
                        , root_1);

                        adaptor.addChild(root_1, stream_metricFExp.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:548:4: NUMBER
                    {
                    root_0 = (Object)adaptor.nil();


                    NUMBER495=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_metricFExp3694); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER495_tree = 
                    (Object)adaptor.create(NUMBER495)
                    ;
                    adaptor.addChild(root_0, NUMBER495_tree);
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:549:4: fHead
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_fHead_in_metricFExp3699);
                    fHead496=fHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fHead496.getTree());

                    }
                    break;
                case 6 :
                    // Pddl.g:551:4: '(' 'is-violated' NAME ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal497=(Token)match(input,70,FOLLOW_70_in_metricFExp3714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal497_tree = 
                    (Object)adaptor.create(char_literal497)
                    ;
                    adaptor.addChild(root_0, char_literal497_tree);
                    }

                    string_literal498=(Token)match(input,126,FOLLOW_126_in_metricFExp3716); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal498_tree = 
                    (Object)adaptor.create(string_literal498)
                    ;
                    adaptor.addChild(root_0, string_literal498_tree);
                    }

                    NAME499=(Token)match(input,NAME,FOLLOW_NAME_in_metricFExp3718); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME499_tree = 
                    (Object)adaptor.create(NAME499)
                    ;
                    adaptor.addChild(root_0, NAME499_tree);
                    }

                    char_literal500=(Token)match(input,71,FOLLOW_71_in_metricFExp3720); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal500_tree = 
                    (Object)adaptor.create(char_literal500)
                    ;
                    adaptor.addChild(root_0, char_literal500_tree);
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
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conGD"
    // Pddl.g:556:1: conGD : ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' );
    public final PddlParser.conGD_return conGD() throws RecognitionException {
        PddlParser.conGD_return retval = new PddlParser.conGD_return();
        retval.start = input.LT(1);

        int conGD_StartIndex = input.index();

        Object root_0 = null;

        Token char_literal501=null;
        Token string_literal502=null;
        Token char_literal504=null;
        Token char_literal505=null;
        Token string_literal506=null;
        Token char_literal507=null;
        Token char_literal509=null;
        Token char_literal511=null;
        Token char_literal512=null;
        Token string_literal513=null;
        Token string_literal514=null;
        Token char_literal516=null;
        Token char_literal517=null;
        Token string_literal518=null;
        Token char_literal520=null;
        Token char_literal521=null;
        Token string_literal522=null;
        Token char_literal524=null;
        Token char_literal525=null;
        Token string_literal526=null;
        Token NUMBER527=null;
        Token char_literal529=null;
        Token char_literal530=null;
        Token string_literal531=null;
        Token char_literal533=null;
        Token char_literal534=null;
        Token string_literal535=null;
        Token char_literal538=null;
        Token char_literal539=null;
        Token string_literal540=null;
        Token char_literal543=null;
        Token char_literal544=null;
        Token string_literal545=null;
        Token NUMBER546=null;
        Token char_literal549=null;
        Token char_literal550=null;
        Token string_literal551=null;
        Token NUMBER552=null;
        Token NUMBER553=null;
        Token char_literal555=null;
        Token char_literal556=null;
        Token string_literal557=null;
        Token NUMBER558=null;
        Token char_literal560=null;
        PddlParser.conGD_return conGD503 =null;

        PddlParser.typedVariableList_return typedVariableList508 =null;

        PddlParser.conGD_return conGD510 =null;

        PddlParser.goalDesc_return goalDesc515 =null;

        PddlParser.goalDesc_return goalDesc519 =null;

        PddlParser.goalDesc_return goalDesc523 =null;

        PddlParser.goalDesc_return goalDesc528 =null;

        PddlParser.goalDesc_return goalDesc532 =null;

        PddlParser.goalDesc_return goalDesc536 =null;

        PddlParser.goalDesc_return goalDesc537 =null;

        PddlParser.goalDesc_return goalDesc541 =null;

        PddlParser.goalDesc_return goalDesc542 =null;

        PddlParser.goalDesc_return goalDesc547 =null;

        PddlParser.goalDesc_return goalDesc548 =null;

        PddlParser.goalDesc_return goalDesc554 =null;

        PddlParser.goalDesc_return goalDesc559 =null;


        Object char_literal501_tree=null;
        Object string_literal502_tree=null;
        Object char_literal504_tree=null;
        Object char_literal505_tree=null;
        Object string_literal506_tree=null;
        Object char_literal507_tree=null;
        Object char_literal509_tree=null;
        Object char_literal511_tree=null;
        Object char_literal512_tree=null;
        Object string_literal513_tree=null;
        Object string_literal514_tree=null;
        Object char_literal516_tree=null;
        Object char_literal517_tree=null;
        Object string_literal518_tree=null;
        Object char_literal520_tree=null;
        Object char_literal521_tree=null;
        Object string_literal522_tree=null;
        Object char_literal524_tree=null;
        Object char_literal525_tree=null;
        Object string_literal526_tree=null;
        Object NUMBER527_tree=null;
        Object char_literal529_tree=null;
        Object char_literal530_tree=null;
        Object string_literal531_tree=null;
        Object char_literal533_tree=null;
        Object char_literal534_tree=null;
        Object string_literal535_tree=null;
        Object char_literal538_tree=null;
        Object char_literal539_tree=null;
        Object string_literal540_tree=null;
        Object char_literal543_tree=null;
        Object char_literal544_tree=null;
        Object string_literal545_tree=null;
        Object NUMBER546_tree=null;
        Object char_literal549_tree=null;
        Object char_literal550_tree=null;
        Object string_literal551_tree=null;
        Object NUMBER552_tree=null;
        Object NUMBER553_tree=null;
        Object char_literal555_tree=null;
        Object char_literal556_tree=null;
        Object string_literal557_tree=null;
        Object NUMBER558_tree=null;
        Object char_literal560_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }

            // Pddl.g:557:2: ( '(' 'and' ( conGD )* ')' | '(' 'forall' '(' typedVariableList ')' conGD ')' | '(' 'at' 'end' goalDesc ')' | '(' 'always' goalDesc ')' | '(' 'sometime' goalDesc ')' | '(' 'within' NUMBER goalDesc ')' | '(' 'at-most-once' goalDesc ')' | '(' 'sometime-after' goalDesc goalDesc ')' | '(' 'sometime-before' goalDesc goalDesc ')' | '(' 'always-within' NUMBER goalDesc goalDesc ')' | '(' 'hold-during' NUMBER NUMBER goalDesc ')' | '(' 'hold-after' NUMBER goalDesc ')' )
            int alt86=12;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==70) ) {
                switch ( input.LA(2) ) {
                case 110:
                    {
                    alt86=1;
                    }
                    break;
                case 121:
                    {
                    alt86=2;
                    }
                    break;
                case 112:
                    {
                    alt86=3;
                    }
                    break;
                case 108:
                    {
                    alt86=4;
                    }
                    break;
                case 139:
                    {
                    alt86=5;
                    }
                    break;
                case 145:
                    {
                    alt86=6;
                    }
                    break;
                case 113:
                    {
                    alt86=7;
                    }
                    break;
                case 140:
                    {
                    alt86=8;
                    }
                    break;
                case 141:
                    {
                    alt86=9;
                    }
                    break;
                case 109:
                    {
                    alt86=10;
                    }
                    break;
                case 123:
                    {
                    alt86=11;
                    }
                    break;
                case 122:
                    {
                    alt86=12;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }
            switch (alt86) {
                case 1 :
                    // Pddl.g:557:4: '(' 'and' ( conGD )* ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal501=(Token)match(input,70,FOLLOW_70_in_conGD3734); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal501_tree = 
                    (Object)adaptor.create(char_literal501)
                    ;
                    adaptor.addChild(root_0, char_literal501_tree);
                    }

                    string_literal502=(Token)match(input,110,FOLLOW_110_in_conGD3736); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal502_tree = 
                    (Object)adaptor.create(string_literal502)
                    ;
                    adaptor.addChild(root_0, string_literal502_tree);
                    }

                    // Pddl.g:557:14: ( conGD )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==70) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // Pddl.g:557:14: conGD
                    	    {
                    	    pushFollow(FOLLOW_conGD_in_conGD3738);
                    	    conGD503=conGD();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD503.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop85;
                        }
                    } while (true);


                    char_literal504=(Token)match(input,71,FOLLOW_71_in_conGD3741); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal504_tree = 
                    (Object)adaptor.create(char_literal504)
                    ;
                    adaptor.addChild(root_0, char_literal504_tree);
                    }

                    }
                    break;
                case 2 :
                    // Pddl.g:558:4: '(' 'forall' '(' typedVariableList ')' conGD ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal505=(Token)match(input,70,FOLLOW_70_in_conGD3746); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal505_tree = 
                    (Object)adaptor.create(char_literal505)
                    ;
                    adaptor.addChild(root_0, char_literal505_tree);
                    }

                    string_literal506=(Token)match(input,121,FOLLOW_121_in_conGD3748); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal506_tree = 
                    (Object)adaptor.create(string_literal506)
                    ;
                    adaptor.addChild(root_0, string_literal506_tree);
                    }

                    char_literal507=(Token)match(input,70,FOLLOW_70_in_conGD3750); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal507_tree = 
                    (Object)adaptor.create(char_literal507)
                    ;
                    adaptor.addChild(root_0, char_literal507_tree);
                    }

                    pushFollow(FOLLOW_typedVariableList_in_conGD3752);
                    typedVariableList508=typedVariableList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedVariableList508.getTree());

                    char_literal509=(Token)match(input,71,FOLLOW_71_in_conGD3754); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal509_tree = 
                    (Object)adaptor.create(char_literal509)
                    ;
                    adaptor.addChild(root_0, char_literal509_tree);
                    }

                    pushFollow(FOLLOW_conGD_in_conGD3756);
                    conGD510=conGD();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conGD510.getTree());

                    char_literal511=(Token)match(input,71,FOLLOW_71_in_conGD3758); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal511_tree = 
                    (Object)adaptor.create(char_literal511)
                    ;
                    adaptor.addChild(root_0, char_literal511_tree);
                    }

                    }
                    break;
                case 3 :
                    // Pddl.g:559:4: '(' 'at' 'end' goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal512=(Token)match(input,70,FOLLOW_70_in_conGD3763); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal512_tree = 
                    (Object)adaptor.create(char_literal512)
                    ;
                    adaptor.addChild(root_0, char_literal512_tree);
                    }

                    string_literal513=(Token)match(input,112,FOLLOW_112_in_conGD3765); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal513_tree = 
                    (Object)adaptor.create(string_literal513)
                    ;
                    adaptor.addChild(root_0, string_literal513_tree);
                    }

                    string_literal514=(Token)match(input,119,FOLLOW_119_in_conGD3767); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal514_tree = 
                    (Object)adaptor.create(string_literal514)
                    ;
                    adaptor.addChild(root_0, string_literal514_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3769);
                    goalDesc515=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc515.getTree());

                    char_literal516=(Token)match(input,71,FOLLOW_71_in_conGD3771); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal516_tree = 
                    (Object)adaptor.create(char_literal516)
                    ;
                    adaptor.addChild(root_0, char_literal516_tree);
                    }

                    }
                    break;
                case 4 :
                    // Pddl.g:560:25: '(' 'always' goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal517=(Token)match(input,70,FOLLOW_70_in_conGD3797); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal517_tree = 
                    (Object)adaptor.create(char_literal517)
                    ;
                    adaptor.addChild(root_0, char_literal517_tree);
                    }

                    string_literal518=(Token)match(input,108,FOLLOW_108_in_conGD3799); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal518_tree = 
                    (Object)adaptor.create(string_literal518)
                    ;
                    adaptor.addChild(root_0, string_literal518_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3801);
                    goalDesc519=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc519.getTree());

                    char_literal520=(Token)match(input,71,FOLLOW_71_in_conGD3803); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal520_tree = 
                    (Object)adaptor.create(char_literal520)
                    ;
                    adaptor.addChild(root_0, char_literal520_tree);
                    }

                    }
                    break;
                case 5 :
                    // Pddl.g:561:4: '(' 'sometime' goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal521=(Token)match(input,70,FOLLOW_70_in_conGD3808); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal521_tree = 
                    (Object)adaptor.create(char_literal521)
                    ;
                    adaptor.addChild(root_0, char_literal521_tree);
                    }

                    string_literal522=(Token)match(input,139,FOLLOW_139_in_conGD3810); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal522_tree = 
                    (Object)adaptor.create(string_literal522)
                    ;
                    adaptor.addChild(root_0, string_literal522_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3812);
                    goalDesc523=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc523.getTree());

                    char_literal524=(Token)match(input,71,FOLLOW_71_in_conGD3814); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal524_tree = 
                    (Object)adaptor.create(char_literal524)
                    ;
                    adaptor.addChild(root_0, char_literal524_tree);
                    }

                    }
                    break;
                case 6 :
                    // Pddl.g:562:5: '(' 'within' NUMBER goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal525=(Token)match(input,70,FOLLOW_70_in_conGD3820); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal525_tree = 
                    (Object)adaptor.create(char_literal525)
                    ;
                    adaptor.addChild(root_0, char_literal525_tree);
                    }

                    string_literal526=(Token)match(input,145,FOLLOW_145_in_conGD3822); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal526_tree = 
                    (Object)adaptor.create(string_literal526)
                    ;
                    adaptor.addChild(root_0, string_literal526_tree);
                    }

                    NUMBER527=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3824); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER527_tree = 
                    (Object)adaptor.create(NUMBER527)
                    ;
                    adaptor.addChild(root_0, NUMBER527_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3826);
                    goalDesc528=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc528.getTree());

                    char_literal529=(Token)match(input,71,FOLLOW_71_in_conGD3828); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal529_tree = 
                    (Object)adaptor.create(char_literal529)
                    ;
                    adaptor.addChild(root_0, char_literal529_tree);
                    }

                    }
                    break;
                case 7 :
                    // Pddl.g:563:4: '(' 'at-most-once' goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal530=(Token)match(input,70,FOLLOW_70_in_conGD3833); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal530_tree = 
                    (Object)adaptor.create(char_literal530)
                    ;
                    adaptor.addChild(root_0, char_literal530_tree);
                    }

                    string_literal531=(Token)match(input,113,FOLLOW_113_in_conGD3835); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal531_tree = 
                    (Object)adaptor.create(string_literal531)
                    ;
                    adaptor.addChild(root_0, string_literal531_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3837);
                    goalDesc532=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc532.getTree());

                    char_literal533=(Token)match(input,71,FOLLOW_71_in_conGD3839); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal533_tree = 
                    (Object)adaptor.create(char_literal533)
                    ;
                    adaptor.addChild(root_0, char_literal533_tree);
                    }

                    }
                    break;
                case 8 :
                    // Pddl.g:564:4: '(' 'sometime-after' goalDesc goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal534=(Token)match(input,70,FOLLOW_70_in_conGD3844); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal534_tree = 
                    (Object)adaptor.create(char_literal534)
                    ;
                    adaptor.addChild(root_0, char_literal534_tree);
                    }

                    string_literal535=(Token)match(input,140,FOLLOW_140_in_conGD3846); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal535_tree = 
                    (Object)adaptor.create(string_literal535)
                    ;
                    adaptor.addChild(root_0, string_literal535_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3848);
                    goalDesc536=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc536.getTree());

                    pushFollow(FOLLOW_goalDesc_in_conGD3850);
                    goalDesc537=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc537.getTree());

                    char_literal538=(Token)match(input,71,FOLLOW_71_in_conGD3852); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal538_tree = 
                    (Object)adaptor.create(char_literal538)
                    ;
                    adaptor.addChild(root_0, char_literal538_tree);
                    }

                    }
                    break;
                case 9 :
                    // Pddl.g:565:4: '(' 'sometime-before' goalDesc goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal539=(Token)match(input,70,FOLLOW_70_in_conGD3857); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal539_tree = 
                    (Object)adaptor.create(char_literal539)
                    ;
                    adaptor.addChild(root_0, char_literal539_tree);
                    }

                    string_literal540=(Token)match(input,141,FOLLOW_141_in_conGD3859); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal540_tree = 
                    (Object)adaptor.create(string_literal540)
                    ;
                    adaptor.addChild(root_0, string_literal540_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3861);
                    goalDesc541=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc541.getTree());

                    pushFollow(FOLLOW_goalDesc_in_conGD3863);
                    goalDesc542=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc542.getTree());

                    char_literal543=(Token)match(input,71,FOLLOW_71_in_conGD3865); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal543_tree = 
                    (Object)adaptor.create(char_literal543)
                    ;
                    adaptor.addChild(root_0, char_literal543_tree);
                    }

                    }
                    break;
                case 10 :
                    // Pddl.g:566:4: '(' 'always-within' NUMBER goalDesc goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal544=(Token)match(input,70,FOLLOW_70_in_conGD3870); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal544_tree = 
                    (Object)adaptor.create(char_literal544)
                    ;
                    adaptor.addChild(root_0, char_literal544_tree);
                    }

                    string_literal545=(Token)match(input,109,FOLLOW_109_in_conGD3872); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal545_tree = 
                    (Object)adaptor.create(string_literal545)
                    ;
                    adaptor.addChild(root_0, string_literal545_tree);
                    }

                    NUMBER546=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3874); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER546_tree = 
                    (Object)adaptor.create(NUMBER546)
                    ;
                    adaptor.addChild(root_0, NUMBER546_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3876);
                    goalDesc547=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc547.getTree());

                    pushFollow(FOLLOW_goalDesc_in_conGD3878);
                    goalDesc548=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc548.getTree());

                    char_literal549=(Token)match(input,71,FOLLOW_71_in_conGD3880); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal549_tree = 
                    (Object)adaptor.create(char_literal549)
                    ;
                    adaptor.addChild(root_0, char_literal549_tree);
                    }

                    }
                    break;
                case 11 :
                    // Pddl.g:567:4: '(' 'hold-during' NUMBER NUMBER goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal550=(Token)match(input,70,FOLLOW_70_in_conGD3885); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal550_tree = 
                    (Object)adaptor.create(char_literal550)
                    ;
                    adaptor.addChild(root_0, char_literal550_tree);
                    }

                    string_literal551=(Token)match(input,123,FOLLOW_123_in_conGD3887); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal551_tree = 
                    (Object)adaptor.create(string_literal551)
                    ;
                    adaptor.addChild(root_0, string_literal551_tree);
                    }

                    NUMBER552=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3889); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER552_tree = 
                    (Object)adaptor.create(NUMBER552)
                    ;
                    adaptor.addChild(root_0, NUMBER552_tree);
                    }

                    NUMBER553=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3891); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER553_tree = 
                    (Object)adaptor.create(NUMBER553)
                    ;
                    adaptor.addChild(root_0, NUMBER553_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3893);
                    goalDesc554=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc554.getTree());

                    char_literal555=(Token)match(input,71,FOLLOW_71_in_conGD3895); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal555_tree = 
                    (Object)adaptor.create(char_literal555)
                    ;
                    adaptor.addChild(root_0, char_literal555_tree);
                    }

                    }
                    break;
                case 12 :
                    // Pddl.g:568:4: '(' 'hold-after' NUMBER goalDesc ')'
                    {
                    root_0 = (Object)adaptor.nil();


                    char_literal556=(Token)match(input,70,FOLLOW_70_in_conGD3900); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal556_tree = 
                    (Object)adaptor.create(char_literal556)
                    ;
                    adaptor.addChild(root_0, char_literal556_tree);
                    }

                    string_literal557=(Token)match(input,122,FOLLOW_122_in_conGD3902); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal557_tree = 
                    (Object)adaptor.create(string_literal557)
                    ;
                    adaptor.addChild(root_0, string_literal557_tree);
                    }

                    NUMBER558=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_conGD3904); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NUMBER558_tree = 
                    (Object)adaptor.create(NUMBER558)
                    ;
                    adaptor.addChild(root_0, NUMBER558_tree);
                    }

                    pushFollow(FOLLOW_goalDesc_in_conGD3906);
                    goalDesc559=goalDesc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, goalDesc559.getTree());

                    char_literal560=(Token)match(input,71,FOLLOW_71_in_conGD3908); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal560_tree = 
                    (Object)adaptor.create(char_literal560)
                    ;
                    adaptor.addChild(root_0, char_literal560_tree);
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
    public final void synpred18_Pddl_fragment() throws RecognitionException {
        // Pddl.g:168:5: ( atomicFunctionSkeleton )
        // Pddl.g:168:5: atomicFunctionSkeleton
        {
        pushFollow(FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl852);
        atomicFunctionSkeleton();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_Pddl

    // $ANTLR start synpred51_Pddl
    public final void synpred51_Pddl_fragment() throws RecognitionException {
        // Pddl.g:298:7: ( fComp )
        // Pddl.g:298:7: fComp
        {
        pushFollow(FOLLOW_fComp_in_synpred51_Pddl1806);
        fComp();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred51_Pddl

    // $ANTLR start synpred54_Pddl
    public final void synpred54_Pddl_fragment() throws RecognitionException {
        // Pddl.g:321:28: ( typedVariableList )
        // Pddl.g:321:28: typedVariableList
        {
        pushFollow(FOLLOW_typedVariableList_in_synpred54_Pddl1965);
        typedVariableList();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred54_Pddl

    // $ANTLR start synpred67_Pddl
    public final void synpred67_Pddl_fragment() throws RecognitionException {
        // Pddl.g:361:4: ( '(' binaryOp fExp fExp2 ')' )
        // Pddl.g:361:4: '(' binaryOp fExp fExp2 ')'
        {
        match(input,70,FOLLOW_70_in_synpred67_Pddl2221); if (state.failed) return ;

        pushFollow(FOLLOW_binaryOp_in_synpred67_Pddl2223);
        binaryOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_fExp_in_synpred67_Pddl2225);
        fExp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_fExp2_in_synpred67_Pddl2227);
        fExp2();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred67_Pddl2229); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred67_Pddl

    // $ANTLR start synpred68_Pddl
    public final void synpred68_Pddl_fragment() throws RecognitionException {
        // Pddl.g:362:4: ( '(' '-' fExp ')' )
        // Pddl.g:362:4: '(' '-' fExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred68_Pddl2246); if (state.failed) return ;

        match(input,74,FOLLOW_74_in_synpred68_Pddl2248); if (state.failed) return ;

        pushFollow(FOLLOW_fExp_in_synpred68_Pddl2250);
        fExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred68_Pddl2252); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred68_Pddl

    // $ANTLR start synpred69_Pddl
    public final void synpred69_Pddl_fragment() throws RecognitionException {
        // Pddl.g:363:4: ( '(' 'sin' fExp ')' )
        // Pddl.g:363:4: '(' 'sin' fExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred69_Pddl2265); if (state.failed) return ;

        match(input,138,FOLLOW_138_in_synpred69_Pddl2267); if (state.failed) return ;

        pushFollow(FOLLOW_fExp_in_synpred69_Pddl2269);
        fExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred69_Pddl2271); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred69_Pddl

    // $ANTLR start synpred70_Pddl
    public final void synpred70_Pddl_fragment() throws RecognitionException {
        // Pddl.g:364:4: ( '(' 'cos' fExp ')' )
        // Pddl.g:364:4: '(' 'cos' fExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred70_Pddl2284); if (state.failed) return ;

        match(input,114,FOLLOW_114_in_synpred70_Pddl2286); if (state.failed) return ;

        pushFollow(FOLLOW_fExp_in_synpred70_Pddl2288);
        fExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred70_Pddl2290); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred70_Pddl

    // $ANTLR start synpred71_Pddl
    public final void synpred71_Pddl_fragment() throws RecognitionException {
        // Pddl.g:365:4: ( '(' 'abs' fExp ')' )
        // Pddl.g:365:4: '(' 'abs' fExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred71_Pddl2303); if (state.failed) return ;

        match(input,106,FOLLOW_106_in_synpred71_Pddl2305); if (state.failed) return ;

        pushFollow(FOLLOW_fExp_in_synpred71_Pddl2307);
        fExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred71_Pddl2309); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred71_Pddl

    // $ANTLR start synpred105_Pddl
    public final void synpred105_Pddl_fragment() throws RecognitionException {
        // Pddl.g:434:12: ( NUMBER )
        // Pddl.g:434:12: NUMBER
        {
        match(input,NUMBER,FOLLOW_NUMBER_in_synpred105_Pddl2794); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred105_Pddl

    // $ANTLR start synpred107_Pddl
    public final void synpred107_Pddl_fragment() throws RecognitionException {
        // Pddl.g:437:4: ( '(' 'and' ( daEffect )* ')' )
        // Pddl.g:437:4: '(' 'and' ( daEffect )* ')'
        {
        match(input,70,FOLLOW_70_in_synpred107_Pddl2808); if (state.failed) return ;

        match(input,110,FOLLOW_110_in_synpred107_Pddl2810); if (state.failed) return ;

        // Pddl.g:437:14: ( daEffect )*
        loop105:
        do {
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==70) ) {
                alt105=1;
            }


            switch (alt105) {
        	case 1 :
        	    // Pddl.g:437:14: daEffect
        	    {
        	    pushFollow(FOLLOW_daEffect_in_synpred107_Pddl2812);
        	    daEffect();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop105;
            }
        } while (true);


        match(input,71,FOLLOW_71_in_synpred107_Pddl2815); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred107_Pddl

    // $ANTLR start synpred108_Pddl
    public final void synpred108_Pddl_fragment() throws RecognitionException {
        // Pddl.g:438:4: ( timedEffect )
        // Pddl.g:438:4: timedEffect
        {
        pushFollow(FOLLOW_timedEffect_in_synpred108_Pddl2820);
        timedEffect();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred108_Pddl

    // $ANTLR start synpred109_Pddl
    public final void synpred109_Pddl_fragment() throws RecognitionException {
        // Pddl.g:439:4: ( '(' 'forall' '(' typedVariableList ')' daEffect ')' )
        // Pddl.g:439:4: '(' 'forall' '(' typedVariableList ')' daEffect ')'
        {
        match(input,70,FOLLOW_70_in_synpred109_Pddl2825); if (state.failed) return ;

        match(input,121,FOLLOW_121_in_synpred109_Pddl2827); if (state.failed) return ;

        match(input,70,FOLLOW_70_in_synpred109_Pddl2829); if (state.failed) return ;

        pushFollow(FOLLOW_typedVariableList_in_synpred109_Pddl2831);
        typedVariableList();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred109_Pddl2833); if (state.failed) return ;

        pushFollow(FOLLOW_daEffect_in_synpred109_Pddl2835);
        daEffect();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred109_Pddl2837); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred109_Pddl

    // $ANTLR start synpred110_Pddl
    public final void synpred110_Pddl_fragment() throws RecognitionException {
        // Pddl.g:440:4: ( '(' 'when' daGD timedEffect ')' )
        // Pddl.g:440:4: '(' 'when' daGD timedEffect ')'
        {
        match(input,70,FOLLOW_70_in_synpred110_Pddl2842); if (state.failed) return ;

        match(input,144,FOLLOW_144_in_synpred110_Pddl2844); if (state.failed) return ;

        pushFollow(FOLLOW_daGD_in_synpred110_Pddl2846);
        daGD();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_timedEffect_in_synpred110_Pddl2848);
        timedEffect();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred110_Pddl2850); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred110_Pddl

    // $ANTLR start synpred111_Pddl
    public final void synpred111_Pddl_fragment() throws RecognitionException {
        // Pddl.g:445:4: ( '(' 'at' timeSpecifier daEffect ')' )
        // Pddl.g:445:4: '(' 'at' timeSpecifier daEffect ')'
        {
        match(input,70,FOLLOW_70_in_synpred111_Pddl2874); if (state.failed) return ;

        match(input,112,FOLLOW_112_in_synpred111_Pddl2876); if (state.failed) return ;

        pushFollow(FOLLOW_timeSpecifier_in_synpred111_Pddl2878);
        timeSpecifier();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_daEffect_in_synpred111_Pddl2880);
        daEffect();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred111_Pddl2882); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred111_Pddl

    // $ANTLR start synpred112_Pddl
    public final void synpred112_Pddl_fragment() throws RecognitionException {
        // Pddl.g:446:4: ( '(' 'at' timeSpecifier fAssignDA ')' )
        // Pddl.g:446:4: '(' 'at' timeSpecifier fAssignDA ')'
        {
        match(input,70,FOLLOW_70_in_synpred112_Pddl2892); if (state.failed) return ;

        match(input,112,FOLLOW_112_in_synpred112_Pddl2894); if (state.failed) return ;

        pushFollow(FOLLOW_timeSpecifier_in_synpred112_Pddl2896);
        timeSpecifier();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_fAssignDA_in_synpred112_Pddl2898);
        fAssignDA();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred112_Pddl2900); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred112_Pddl

    // $ANTLR start synpred113_Pddl
    public final void synpred113_Pddl_fragment() throws RecognitionException {
        // Pddl.g:455:9: ( ( binaryOp fExpDA fExpDA ) )
        // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
        {
        // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
        // Pddl.g:455:10: binaryOp fExpDA fExpDA
        {
        pushFollow(FOLLOW_binaryOp_in_synpred113_Pddl2956);
        binaryOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_fExpDA_in_synpred113_Pddl2958);
        fExpDA();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_fExpDA_in_synpred113_Pddl2960);
        fExpDA();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred113_Pddl

    // $ANTLR start synpred114_Pddl
    public final void synpred114_Pddl_fragment() throws RecognitionException {
        // Pddl.g:455:4: ( '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')' )
        // Pddl.g:455:4: '(' ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) ) ')'
        {
        match(input,70,FOLLOW_70_in_synpred114_Pddl2952); if (state.failed) return ;

        // Pddl.g:455:8: ( ( binaryOp fExpDA fExpDA ) | ( '-' fExpDA ) )
        int alt106=2;
        int LA106_0 = input.LA(1);

        if ( (LA106_0==74) ) {
            int LA106_1 = input.LA(2);

            if ( (synpred113_Pddl()) ) {
                alt106=1;
            }
            else if ( (true) ) {
                alt106=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 1, input);

                throw nvae;

            }
        }
        else if ( ((LA106_0 >= 72 && LA106_0 <= 73)||LA106_0==75||LA106_0==105) ) {
            alt106=1;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 106, 0, input);

            throw nvae;

        }
        switch (alt106) {
            case 1 :
                // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
                {
                // Pddl.g:455:9: ( binaryOp fExpDA fExpDA )
                // Pddl.g:455:10: binaryOp fExpDA fExpDA
                {
                pushFollow(FOLLOW_binaryOp_in_synpred114_Pddl2956);
                binaryOp();

                state._fsp--;
                if (state.failed) return ;

                pushFollow(FOLLOW_fExpDA_in_synpred114_Pddl2958);
                fExpDA();

                state._fsp--;
                if (state.failed) return ;

                pushFollow(FOLLOW_fExpDA_in_synpred114_Pddl2960);
                fExpDA();

                state._fsp--;
                if (state.failed) return ;

                }


                }
                break;
            case 2 :
                // Pddl.g:455:36: ( '-' fExpDA )
                {
                // Pddl.g:455:36: ( '-' fExpDA )
                // Pddl.g:455:37: '-' fExpDA
                {
                match(input,74,FOLLOW_74_in_synpred114_Pddl2966); if (state.failed) return ;

                pushFollow(FOLLOW_fExpDA_in_synpred114_Pddl2968);
                fExpDA();

                state._fsp--;
                if (state.failed) return ;

                }


                }
                break;

        }


        match(input,71,FOLLOW_71_in_synpred114_Pddl2972); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred114_Pddl

    // $ANTLR start synpred121_Pddl
    public final void synpred121_Pddl_fragment() throws RecognitionException {
        // Pddl.g:493:4: ( '(' ':init' ( initEl )* ')' )
        // Pddl.g:493:4: '(' ':init' ( initEl )* ')'
        {
        match(input,70,FOLLOW_70_in_synpred121_Pddl3213); if (state.failed) return ;

        match(input,90,FOLLOW_90_in_synpred121_Pddl3215); if (state.failed) return ;

        // Pddl.g:493:16: ( initEl )*
        loop107:
        do {
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==70) ) {
                alt107=1;
            }


            switch (alt107) {
        	case 1 :
        	    // Pddl.g:493:16: initEl
        	    {
        	    pushFollow(FOLLOW_initEl_in_synpred121_Pddl3217);
        	    initEl();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop107;
            }
        } while (true);


        match(input,71,FOLLOW_71_in_synpred121_Pddl3220); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred121_Pddl

    // $ANTLR start synpred132_Pddl
    public final void synpred132_Pddl_fragment() throws RecognitionException {
        // Pddl.g:528:4: ( '(' 'and' ( prefConGD )* ')' )
        // Pddl.g:528:4: '(' 'and' ( prefConGD )* ')'
        {
        match(input,70,FOLLOW_70_in_synpred132_Pddl3509); if (state.failed) return ;

        match(input,110,FOLLOW_110_in_synpred132_Pddl3511); if (state.failed) return ;

        // Pddl.g:528:14: ( prefConGD )*
        loop109:
        do {
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==70) ) {
                alt109=1;
            }


            switch (alt109) {
        	case 1 :
        	    // Pddl.g:528:14: prefConGD
        	    {
        	    pushFollow(FOLLOW_prefConGD_in_synpred132_Pddl3513);
        	    prefConGD();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    break loop109;
            }
        } while (true);


        match(input,71,FOLLOW_71_in_synpred132_Pddl3516); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred132_Pddl

    // $ANTLR start synpred133_Pddl
    public final void synpred133_Pddl_fragment() throws RecognitionException {
        // Pddl.g:529:4: ( '(' 'forall' '(' typedVariableList ')' prefConGD ')' )
        // Pddl.g:529:4: '(' 'forall' '(' typedVariableList ')' prefConGD ')'
        {
        match(input,70,FOLLOW_70_in_synpred133_Pddl3521); if (state.failed) return ;

        match(input,121,FOLLOW_121_in_synpred133_Pddl3523); if (state.failed) return ;

        match(input,70,FOLLOW_70_in_synpred133_Pddl3525); if (state.failed) return ;

        pushFollow(FOLLOW_typedVariableList_in_synpred133_Pddl3527);
        typedVariableList();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred133_Pddl3529); if (state.failed) return ;

        pushFollow(FOLLOW_prefConGD_in_synpred133_Pddl3531);
        prefConGD();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred133_Pddl3533); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred133_Pddl

    // $ANTLR start synpred135_Pddl
    public final void synpred135_Pddl_fragment() throws RecognitionException {
        // Pddl.g:530:4: ( '(' 'preference' ( NAME )? conGD ')' )
        // Pddl.g:530:4: '(' 'preference' ( NAME )? conGD ')'
        {
        match(input,70,FOLLOW_70_in_synpred135_Pddl3538); if (state.failed) return ;

        match(input,134,FOLLOW_134_in_synpred135_Pddl3540); if (state.failed) return ;

        // Pddl.g:530:21: ( NAME )?
        int alt110=2;
        int LA110_0 = input.LA(1);

        if ( (LA110_0==NAME) ) {
            alt110=1;
        }
        switch (alt110) {
            case 1 :
                // Pddl.g:530:21: NAME
                {
                match(input,NAME,FOLLOW_NAME_in_synpred135_Pddl3542); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_conGD_in_synpred135_Pddl3545);
        conGD();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred135_Pddl3547); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred135_Pddl

    // $ANTLR start synpred137_Pddl
    public final void synpred137_Pddl_fragment() throws RecognitionException {
        // Pddl.g:542:4: ( '(' binaryOp metricFExp metricFExp ')' )
        // Pddl.g:542:4: '(' binaryOp metricFExp metricFExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred137_Pddl3608); if (state.failed) return ;

        pushFollow(FOLLOW_binaryOp_in_synpred137_Pddl3610);
        binaryOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred137_Pddl3612);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred137_Pddl3614);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred137_Pddl3616); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred137_Pddl

    // $ANTLR start synpred139_Pddl
    public final void synpred139_Pddl_fragment() throws RecognitionException {
        // Pddl.g:544:4: ( '(' multiOp metricFExp ( metricFExp )+ ')' )
        // Pddl.g:544:4: '(' multiOp metricFExp ( metricFExp )+ ')'
        {
        match(input,70,FOLLOW_70_in_synpred139_Pddl3638); if (state.failed) return ;

        pushFollow(FOLLOW_multiOp_in_synpred139_Pddl3640);
        multiOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred139_Pddl3642);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        // Pddl.g:544:27: ( metricFExp )+
        int cnt111=0;
        loop111:
        do {
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==NAME||LA111_0==NUMBER||(LA111_0 >= 69 && LA111_0 <= 70)) ) {
                alt111=1;
            }


            switch (alt111) {
        	case 1 :
        	    // Pddl.g:544:27: metricFExp
        	    {
        	    pushFollow(FOLLOW_metricFExp_in_synpred139_Pddl3644);
        	    metricFExp();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt111 >= 1 ) break loop111;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(111, input);
                    throw eee;
            }
            cnt111++;
        } while (true);


        match(input,71,FOLLOW_71_in_synpred139_Pddl3647); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred139_Pddl

    // $ANTLR start synpred140_Pddl
    public final void synpred140_Pddl_fragment() throws RecognitionException {
        // Pddl.g:546:4: ( '(' '-' metricFExp ')' )
        // Pddl.g:546:4: '(' '-' metricFExp ')'
        {
        match(input,70,FOLLOW_70_in_synpred140_Pddl3671); if (state.failed) return ;

        match(input,74,FOLLOW_74_in_synpred140_Pddl3673); if (state.failed) return ;

        pushFollow(FOLLOW_metricFExp_in_synpred140_Pddl3675);
        metricFExp();

        state._fsp--;
        if (state.failed) return ;

        match(input,71,FOLLOW_71_in_synpred140_Pddl3677); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred140_Pddl

    // $ANTLR start synpred142_Pddl
    public final void synpred142_Pddl_fragment() throws RecognitionException {
        // Pddl.g:549:4: ( fHead )
        // Pddl.g:549:4: fHead
        {
        pushFollow(FOLLOW_fHead_in_synpred142_Pddl3699);
        fHead();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred142_Pddl

    // Delegated rules

    public final boolean synpred112_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred112_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred137_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred137_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred140_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred140_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred142_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred142_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred111_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred111_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred114_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred114_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred121_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred121_Pddl_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred132_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred132_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred113_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred113_Pddl_fragment(); // can never throw exception
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
    public final boolean synpred139_Pddl() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred139_Pddl_fragment(); // can never throw exception
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
    protected DFA74 dfa74 = new DFA74(this);
    static final String DFA14_eotS =
        "\4\uffff";
    static final String DFA14_eofS =
        "\4\uffff";
    static final String DFA14_minS =
        "\2\51\2\uffff";
    static final String DFA14_maxS =
        "\1\107\1\112\2\uffff";
    static final String DFA14_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA14_specialS =
        "\4\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1\35\uffff\1\2",
            "\1\1\35\uffff\1\2\2\uffff\1\3",
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

    class DFA14 extends DFA {

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
        public String getDescription() {
            return "146:7: ( ( NAME )* | ( singleTypeNameList )+ ( NAME )* )";
        }
    }
    static final String DFA12_eotS =
        "\4\uffff";
    static final String DFA12_eofS =
        "\4\uffff";
    static final String DFA12_minS =
        "\2\51\2\uffff";
    static final String DFA12_maxS =
        "\1\107\1\112\2\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA12_specialS =
        "\4\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\35\uffff\1\2",
            "\1\1\35\uffff\1\2\2\uffff\1\3",
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

    class DFA12 extends DFA {

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
        public String getDescription() {
            return "()+ loopback of 146:16: ( singleTypeNameList )+";
        }
    }
    static final String DFA25_eotS =
        "\4\uffff";
    static final String DFA25_eofS =
        "\2\2\2\uffff";
    static final String DFA25_minS =
        "\2\102\2\uffff";
    static final String DFA25_maxS =
        "\1\107\1\112\2\uffff";
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

    class DFA25 extends DFA {

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
        public String getDescription() {
            return "197:7: ( ( VARIABLE )* | ( singleTypeVarList )+ ( VARIABLE )* )";
        }
    }
    static final String DFA23_eotS =
        "\4\uffff";
    static final String DFA23_eofS =
        "\2\2\2\uffff";
    static final String DFA23_minS =
        "\2\102\2\uffff";
    static final String DFA23_maxS =
        "\1\107\1\112\2\uffff";
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

    class DFA23 extends DFA {

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
        public String getDescription() {
            return "()+ loopback of 197:20: ( singleTypeVarList )+";
        }
    }
    static final String DFA74_eotS =
        "\155\uffff";
    static final String DFA74_eofS =
        "\155\uffff";
    static final String DFA74_minS =
        "\1\106\1\132\1\106\1\51\1\uffff\1\106\1\51\2\106\1\uffff\6\51\1"+
        "\106\1\51\1\106\1\51\1\106\2\51\1\107\1\51\1\0\1\51\1\106\1\51\2"+
        "\0\1\51\1\107\2\51\1\106\1\51\2\106\1\51\1\55\3\106\3\51\2\106\1"+
        "\0\2\51\1\55\1\106\2\51\1\106\1\51\1\106\1\51\1\106\1\51\1\0\2\51"+
        "\1\107\3\51\1\0\1\106\1\51\2\0\1\51\1\107\1\51\1\107\1\51\1\55\2"+
        "\106\2\51\1\107\1\51\1\106\2\51\3\106\1\0\2\51\1\107\1\106\1\51"+
        "\1\0\1\51\1\106\1\0\1\51\1\107\1\51\1\107\1\0\1\106\1\107";
    static final String DFA74_maxS =
        "\1\106\1\132\1\107\1\u008f\1\uffff\2\106\2\107\1\uffff\1\107\1\u0084"+
        "\1\u008a\2\106\1\u0084\1\107\1\u0084\7\107\1\0\1\u008f\1\106\1\107"+
        "\2\0\3\107\1\106\3\107\2\106\1\55\1\106\3\107\1\u0084\3\107\1\0"+
        "\1\51\1\105\1\55\1\106\2\51\1\107\1\u0081\4\107\1\0\3\107\1\u0081"+
        "\2\107\1\0\1\106\1\107\2\0\5\107\1\55\1\107\1\106\5\107\1\51\4\107"+
        "\1\0\1\51\4\107\1\0\2\107\1\0\4\107\1\0\2\107";
    static final String DFA74_acceptS =
        "\4\uffff\1\1\4\uffff\1\2\143\uffff";
    static final String DFA74_specialS =
        "\31\uffff\1\1\3\uffff\1\2\1\4\22\uffff\1\10\14\uffff\1\11\6\uffff"+
        "\1\3\2\uffff\1\0\1\12\22\uffff\1\13\5\uffff\1\5\2\uffff\1\7\4\uffff"+
        "\1\6\2\uffff}>";
    static final String[] DFA74_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\1\4",
            "\1\12\71\uffff\2\11\1\6\2\11\6\uffff\1\11\1\uffff\1\4\7\uffff"+
            "\2\11\2\uffff\1\11\4\uffff\1\5\1\uffff\1\7\1\10\12\uffff\1\4",
            "",
            "\1\13",
            "\1\15\3\uffff\1\11\24\uffff\1\11\2\uffff\1\16\1\14",
            "\1\17\1\20",
            "\1\21\1\22",
            "",
            "\1\23\30\uffff\1\11\4\uffff\1\24",
            "\1\25\71\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11"+
            "\4\uffff\1\11\1\uffff\2\11",
            "\1\26\33\uffff\1\26\2\uffff\4\11\35\uffff\2\11\7\uffff\1\11"+
            "\27\uffff\1\11",
            "\1\11\3\uffff\1\27\24\uffff\1\11\2\uffff\2\11",
            "\1\11\3\uffff\1\27\27\uffff\2\11",
            "\1\30\71\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11"+
            "\4\uffff\1\11\1\uffff\2\11",
            "\1\32\1\31",
            "\1\34\71\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11"+
            "\4\uffff\1\33\1\uffff\2\11",
            "\1\32\1\35",
            "\1\23\30\uffff\1\11\4\uffff\1\24",
            "\1\32\1\36",
            "\1\37\30\uffff\1\11\4\uffff\1\40",
            "\1\41\30\uffff\1\41\4\uffff\1\42",
            "\1\43",
            "\1\44\30\uffff\1\11\4\uffff\1\45",
            "\1\uffff",
            "\1\54\73\uffff\1\47\12\uffff\1\50\20\uffff\1\46\1\uffff\1\52"+
            "\1\53\12\uffff\1\51",
            "\1\55",
            "\1\56\30\uffff\1\11\4\uffff\1\57",
            "\1\uffff",
            "\1\uffff",
            "\1\37\30\uffff\1\11\4\uffff\1\40",
            "\1\60",
            "\1\41\30\uffff\1\41\4\uffff\1\42",
            "\1\11\3\uffff\1\27\27\uffff\2\11",
            "\1\32\1\61",
            "\1\44\30\uffff\1\11\4\uffff\1\45",
            "\1\17\1\20",
            "\1\62",
            "\1\64\33\uffff\1\64\1\63",
            "\1\65",
            "\1\66",
            "\1\67\1\70",
            "\1\71\1\72",
            "\1\73\35\uffff\1\74",
            "\1\75\71\uffff\5\11\6\uffff\1\11\11\uffff\2\11\2\uffff\1\11"+
            "\4\uffff\1\11\1\uffff\2\11",
            "\1\56\30\uffff\1\11\4\uffff\1\57",
            "\1\21\1\22",
            "\1\32\1\76",
            "\1\uffff",
            "\1\77",
            "\1\100\33\uffff\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\32\1\105",
            "\1\107\127\uffff\1\106",
            "\1\32\1\110",
            "\1\73\35\uffff\1\74",
            "\1\32\1\111",
            "\1\112\30\uffff\1\11\4\uffff\1\113",
            "\1\uffff",
            "\1\114\35\uffff\1\115",
            "\1\116\30\uffff\1\116\4\uffff\1\117",
            "\1\120",
            "\1\122\127\uffff\1\121",
            "\1\123\35\uffff\1\124",
            "\1\125\35\uffff\1\126",
            "\1\uffff",
            "\1\127",
            "\1\130\35\uffff\1\131",
            "\1\uffff",
            "\1\uffff",
            "\1\112\30\uffff\1\11\4\uffff\1\113",
            "\1\132",
            "\1\114\35\uffff\1\115",
            "\1\133",
            "\1\116\30\uffff\1\116\4\uffff\1\117",
            "\1\101",
            "\1\32\1\134",
            "\1\135",
            "\1\136\35\uffff\1\137",
            "\1\123\35\uffff\1\124",
            "\1\140",
            "\1\125\35\uffff\1\126",
            "\1\67\1\70",
            "\1\141",
            "\1\130\35\uffff\1\131",
            "\1\71\1\72",
            "\1\21\1\22",
            "\1\32\1\142",
            "\1\uffff",
            "\1\143",
            "\1\136\35\uffff\1\137",
            "\1\144",
            "\1\32\1\145",
            "\1\146\35\uffff\1\147",
            "\1\uffff",
            "\1\150\35\uffff\1\151",
            "\1\32\1\152",
            "\1\uffff",
            "\1\146\35\uffff\1\147",
            "\1\153",
            "\1\150\35\uffff\1\151",
            "\1\154",
            "\1\uffff",
            "\1\71\1\72",
            "\1\144"
    };

    static final short[] DFA74_eot = DFA.unpackEncodedString(DFA74_eotS);
    static final short[] DFA74_eof = DFA.unpackEncodedString(DFA74_eofS);
    static final char[] DFA74_min = DFA.unpackEncodedStringToUnsignedChars(DFA74_minS);
    static final char[] DFA74_max = DFA.unpackEncodedStringToUnsignedChars(DFA74_maxS);
    static final short[] DFA74_accept = DFA.unpackEncodedString(DFA74_acceptS);
    static final short[] DFA74_special = DFA.unpackEncodedString(DFA74_specialS);
    static final short[][] DFA74_transition;

    static {
        int numStates = DFA74_transitionS.length;
        DFA74_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA74_transition[i] = DFA.unpackEncodedString(DFA74_transitionS[i]);
        }
    }

    class DFA74 extends DFA {

        public DFA74(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 74;
            this.eot = DFA74_eot;
            this.eof = DFA74_eof;
            this.min = DFA74_min;
            this.max = DFA74_max;
            this.accept = DFA74_accept;
            this.special = DFA74_special;
            this.transition = DFA74_transition;
        }
        public String getDescription() {
            return "492:1: init : ( '(' ':init' ( initEl )* ')' -> ^( INIT ( initEl )* ) | '(' ':init' belief ')' -> ^( FORMULAINIT belief ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA74_72 = input.LA(1);

                         
                        int index74_72 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_72);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA74_25 = input.LA(1);

                         
                        int index74_25 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_25);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA74_29 = input.LA(1);

                         
                        int index74_29 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_29);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA74_69 = input.LA(1);

                         
                        int index74_69 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_69);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA74_30 = input.LA(1);

                         
                        int index74_30 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_30);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA74_98 = input.LA(1);

                         
                        int index74_98 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_98);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA74_106 = input.LA(1);

                         
                        int index74_106 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_106);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA74_101 = input.LA(1);

                         
                        int index74_101 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_101);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA74_49 = input.LA(1);

                         
                        int index74_49 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_49);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA74_62 = input.LA(1);

                         
                        int index74_62 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_62);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA74_73 = input.LA(1);

                         
                        int index74_73 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_73);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA74_92 = input.LA(1);

                         
                        int index74_92 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred121_Pddl()) ) {s = 4;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index74_92);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 74, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

    public static final BitSet FOLLOW_domain_in_pddlDoc405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_problem_in_pddlDoc409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_domain424 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_domain426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_domainName_in_domain428 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_requireDef_in_domain436 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_typesDef_in_domain445 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_constantsDef_in_domain454 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_predicatesDef_in_domain463 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_functionsDef_in_domain472 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_free_functionsDef_in_domain481 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_constraints_in_domain490 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_structureDef_in_domain499 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_domain508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_free_functionsDef592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_free_functionsDef594 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_functionList_in_free_functionsDef596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_free_functionsDef598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_domainName621 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});
    public static final BitSet FOLLOW_117_in_domainName623 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_NAME_in_domainName625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_domainName627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_requireDef654 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_97_in_requireDef656 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_REQUIRE_KEY_in_requireDef658 = new BitSet(new long[]{0x2000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_requireDef661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_typesDef682 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_typesDef684 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typedNameList_in_typesDef686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_typesDef688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_typedNameList715 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_singleTypeNameList_in_typedNameList720 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_NAME_in_typedNameList723 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_NAME_in_singleTypeNameList743 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_singleTypeNameList746 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_type_in_singleTypeNameList750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_type777 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_118_in_type779 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_primType_in_type781 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_type784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primType_in_type805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_primType815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_functionsDef825 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_functionsDef827 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_functionList_in_functionsDef829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_functionsDef831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_functionList852 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000440L});
    public static final BitSet FOLLOW_74_in_functionList856 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_functionType_in_functionList858 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_atomicFunctionSkeleton874 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_functionSymbol_in_atomicFunctionSkeleton877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_atomicFunctionSkeleton880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_atomicFunctionSkeleton882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_functionType905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_constantsDef916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_constantsDef918 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typedNameList_in_constantsDef920 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_constantsDef922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_predicatesDef942 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_predicatesDef944 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_atomicFormulaSkeleton_in_predicatesDef946 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_predicatesDef949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_atomicFormulaSkeleton970 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_predicate_in_atomicFormulaSkeleton973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_atomicFormulaSkeleton976 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_atomicFormulaSkeleton978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_predicate989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList1004 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_singleTypeVarList_in_typedVariableList1009 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_typedVariableList1012 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_singleTypeVarList1032 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000404L});
    public static final BitSet FOLLOW_74_in_singleTypeVarList1035 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_type_in_singleTypeVarList1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_constraints1070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_constraints1073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_conGD_in_constraints1076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_constraints1078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_actionDef_in_structureDef1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_durativeActionDef_in_structureDef1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_derivedDef_in_structureDef1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraintDef_in_structureDef1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_processDef_in_structureDef1110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eventDef_in_structureDef1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_actionDef1130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_actionDef1132 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_actionSymbol_in_actionDef1134 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_actionDef1143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_actionDef1146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_actionDef1148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_actionDef1150 = new BitSet(new long[]{0x0000000000000000L,0x0000000040200080L});
    public static final BitSet FOLLOW_actionDefBody_in_actionDef1163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_actionDef1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_eventDef1198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_eventDef1200 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_actionSymbol_in_eventDef1202 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_eventDef1211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_eventDef1214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_eventDef1216 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_eventDef1218 = new BitSet(new long[]{0x0000000000000000L,0x0000000040200080L});
    public static final BitSet FOLLOW_actionDefBody_in_eventDef1231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_eventDef1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_processDef1266 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
    public static final BitSet FOLLOW_96_in_processDef1268 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_actionSymbol_in_processDef1270 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_processDef1279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_processDef1282 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_processDef1284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_processDef1286 = new BitSet(new long[]{0x0000000000000000L,0x0000000040200080L});
    public static final BitSet FOLLOW_actionDefBody_in_processDef1299 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_processDef1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_constraintDef1335 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_constraintDef1337 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_constraintSymbol_in_constraintDef1339 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_constraintDef1348 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_constraintDef1351 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_constraintDef1353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_constraintDef1355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002080L});
    public static final BitSet FOLLOW_constraintDefBody_in_constraintDef1368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_constraintDef1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_actionSymbol1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_constraintSymbol1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_actionDefBody1428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_actionDefBody1432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_actionDefBody1434 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_goalDesc_in_actionDefBody1439 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_actionDefBody1449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_actionDefBody1453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_actionDefBody1455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_effect_in_actionDefBody1460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_constraintDefBody1495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_constraintDefBody1499 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_constraintDefBody1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goalDesc_in_constraintDefBody1506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goalDesc_in_belief1544 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_initEl_in_belief1549 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_atomicTermFormula_in_goalDesc1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1576 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_goalDesc1578 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1580 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_goalDesc1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1608 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_132_in_goalDesc1610 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1612 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_goalDesc1615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_goalDesc1642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1644 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1670 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_goalDesc1672 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1675 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_goalDesc1678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1693 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_124_in_goalDesc1695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1697 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1727 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
    public static final BitSet FOLLOW_120_in_goalDesc1729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_goalDesc1731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1737 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goalDesc1765 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_goalDesc1767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_goalDesc1769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_goalDesc1771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1773 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goalDesc1775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goalDesc1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fComp_in_goalDesc1806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equality_in_goalDesc1833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_equality1861 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_equality1864 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_equality1866 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_term_in_equality1868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_equality1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fComp1881 = new BitSet(new long[]{0x0000000000000000L,0x000000F800000000L});
    public static final BitSet FOLLOW_binaryComp_in_fComp1884 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fComp1886 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fComp1888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fComp1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_atomicTermFormula1902 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_predicate_in_atomicTermFormula1904 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_term_in_atomicTermFormula1906 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_71_in_atomicTermFormula1909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_durativeActionDef1946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_durativeActionDef1948 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_actionSymbol_in_durativeActionDef1950 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_durativeActionDef1959 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_durativeActionDef1962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_durativeActionDef1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_durativeActionDef1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000282000L});
    public static final BitSet FOLLOW_daDefBody_in_durativeActionDef1982 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_durativeActionDef1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_daDefBody2017 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_durationConstraint_in_daDefBody2019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_daDefBody2024 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_daDefBody2028 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daDefBody2030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daGD_in_daDefBody2035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_daDefBody2044 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_daDefBody2048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daDefBody2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_daEffect_in_daDefBody2055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefTimedGD_in_daGD2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daGD2075 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_daGD2077 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_daGD_in_daGD2079 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_daGD2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daGD2087 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_daGD2089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_daGD2091 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_daGD2093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daGD2095 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daGD_in_daGD2097 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daGD2099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_prefTimedGD2115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_prefTimedGD2117 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_NAME_in_prefTimedGD2119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_timedGD_in_prefTimedGD2122 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_prefTimedGD2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_timedGD2135 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_timedGD2137 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedGD2139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_timedGD2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_timedGD2148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_133_in_timedGD2150 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_interval_in_timedGD2152 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_timedGD2154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_timedGD2156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_interval2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_derivedDef2191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_derivedDef2194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000044L});
    public static final BitSet FOLLOW_typedVariableList_in_derivedDef2197 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_derivedDef2199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_derivedDef2201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_fExp2216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExp2221 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_fExp2223 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fExp2225 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp2_in_fExp2227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExp2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_fExp2248 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fExp2250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExp2265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_138_in_fExp2267 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fExp2269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExp2284 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_fExp2286 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fExp2288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExp2303 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_fExp2305 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_fExp2307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExp2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_fExp2322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExp22334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fHead2344 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2346 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_term_in_fHead2348 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_71_in_fHead2351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionSymbol_in_fHead2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_effect2386 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_effect2388 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_cEffect_in_effect2390 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_effect2393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cEffect_in_effect2407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_cEffect2418 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_cEffect2420 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_cEffect2422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_cEffect2424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_cEffect2426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_effect_in_cEffect2428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_cEffect2430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_cEffect2448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_144_in_cEffect2450 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_cEffect2452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_condEffect_in_cEffect2454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_cEffect2456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_cEffect2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_cEffect2479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_cEffect2481 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_condEffect_in_cEffect2483 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_cEffect2486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_pEffect2506 = new BitSet(new long[]{0x0000000000000000L,0x2008800000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_assignOp_in_pEffect2508 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fHead_in_pEffect2510 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_pEffect2512 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_pEffect2514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_pEffect2534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_pEffect2536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_pEffect2540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicTermFormula_in_pEffect2556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_condEffect2569 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_condEffect2571 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2573 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_condEffect2576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_condEffect2590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_condEffect2592 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_condEffect_in_condEffect2594 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_condEffect2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pEffect_in_condEffect2611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_durationConstraint2716 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_durationConstraint2718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2720 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_durationConstraint2723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_durationConstraint2728 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_durationConstraint2730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_durationConstraint2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_simpleDurationConstraint2746 = new BitSet(new long[]{0x0000000000000000L,0x000000B000000000L});
    public static final BitSet FOLLOW_durOp_in_simpleDurationConstraint2748 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_104_in_simpleDurationConstraint2750 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_durValue_in_simpleDurationConstraint2752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_simpleDurationConstraint2754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_simpleDurationConstraint2759 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_simpleDurationConstraint2761 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_simpleDurationConstraint2763 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_simpleDurationConstraint_in_simpleDurationConstraint2765 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_simpleDurationConstraint2767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_durValue2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_durValue2798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daEffect2808 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_daEffect2810 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2812 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_daEffect2815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daEffect2825 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_daEffect2827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_daEffect2829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_daEffect2831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daEffect2833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daEffect_in_daEffect2835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daEffect2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daEffect2842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_144_in_daEffect2844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daGD_in_daEffect2846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_timedEffect_in_daEffect2848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daEffect2850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_daEffect2855 = new BitSet(new long[]{0x0000000000000000L,0x2008800000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_assignOp_in_daEffect2857 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fHead_in_daEffect2859 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_daEffect2861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_daEffect2863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_timedEffect2874 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_timedEffect2876 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daEffect_in_timedEffect2880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_timedEffect2882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_timedEffect2892 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_timedEffect2894 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_timedEffect2896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_fAssignDA_in_timedEffect2898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_timedEffect2900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_timedEffect2905 = new BitSet(new long[]{0x0000000000000000L,0x2008800000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_assignOp_in_timedEffect2907 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fHead_in_timedEffect2909 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_timedEffect2911 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_timedEffect2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fAssignDA2933 = new BitSet(new long[]{0x0000000000000000L,0x2008800000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_assignOp_in_fAssignDA2935 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fHead_in_fAssignDA2937 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_fAssignDA2939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fAssignDA2941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_fExpDA2952 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_fExpDA2956 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2958 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_74_in_fExpDA2966 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_fExpDA2968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_fExpDA2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_fExpDA2977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fExp_in_fExpDA2982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_problem2996 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_problem2998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_problemDecl_in_problem3000 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_problemDomain_in_problem3005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_requireDef_in_problem3013 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_objectDecl_in_problem3022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_init_in_problem3031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goal_in_problem3039 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_probConstraints_in_problem3047 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_metricSpec_in_problem3056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_problem3072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_problemDecl3129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_135_in_problemDecl3131 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDecl3133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_problemDecl3135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_problemDomain3161 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_problemDomain3163 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_NAME_in_problemDomain3165 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_problemDomain3167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_objectDecl3187 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_92_in_objectDecl3189 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_typedNameList_in_objectDecl3191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_objectDecl3193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_init3213 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_init3215 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_initEl_in_init3217 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_init3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_init3235 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_init3237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_belief_in_init3239 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_init3241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_initEl3265 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_initEl3267 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fHead_in_initEl3269 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3271 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_initEl3273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_initEl3296 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_initEl3298 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_initEl3300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3302 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_initEl3304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_initEl3320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_143_in_initEl3322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3325 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_initEl3327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_initEl3341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_131_in_initEl3343 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_atomicNameFormula_in_initEl3346 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_initEl3349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_initEl3364 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_132_in_initEl3366 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_nameLiteral_in_initEl3369 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_initEl3372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_nameLiteral3398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_nameLiteral3400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_atomicNameFormula_in_nameLiteral3402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_nameLiteral3404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_atomicNameFormula3423 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_predicate_in_atomicNameFormula3425 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_NAME_in_atomicNameFormula3427 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_atomicNameFormula3430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_goal3455 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_goal3457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_goal3459 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_goal3462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_probConstraints3480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_probConstraints3482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_prefConGD_in_probConstraints3485 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_probConstraints3487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_prefConGD3509 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_prefConGD3511 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3513 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_prefConGD3516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_prefConGD3521 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_prefConGD3523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_prefConGD3525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_prefConGD3527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_prefConGD3529 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_prefConGD_in_prefConGD3531 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_prefConGD3533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_prefConGD3538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_prefConGD3540 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_NAME_in_prefConGD3542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3545 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_prefConGD3547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conGD_in_prefConGD3552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_metricSpec3563 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_91_in_metricSpec3565 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_optimization_in_metricSpec3567 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricSpec3569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_metricSpec3571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_metricFExp3608 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_metricFExp3610 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3612 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_metricFExp3616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_metricFExp3638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_multiOp_in_metricFExp3640 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3642 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3644 = new BitSet(new long[]{0x0000220000000000L,0x00000000000000E0L});
    public static final BitSet FOLLOW_71_in_metricFExp3647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_metricFExp3671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_metricFExp3673 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_metricFExp3675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_metricFExp3677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_metricFExp3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_metricFExp3699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_metricFExp3714 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_126_in_metricFExp3716 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_NAME_in_metricFExp3718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_metricFExp3720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3734 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_conGD3736 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_conGD_in_conGD3738 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_conGD3741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3746 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_conGD3748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_conGD3750 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_conGD3752 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3754 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_conGD_in_conGD3756 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3763 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_conGD3765 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L});
    public static final BitSet FOLLOW_119_in_conGD3767 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3769 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3797 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
    public static final BitSet FOLLOW_108_in_conGD3799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_139_in_conGD3810 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3812 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_145_in_conGD3822 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3833 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_113_in_conGD3835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_140_in_conGD3846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3857 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_141_in_conGD3859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3863 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3870 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_109_in_conGD3872 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3885 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_123_in_conGD3887 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3889 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3891 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_conGD3900 = new BitSet(new long[]{0x0000000000000000L,0x0400000000000000L});
    public static final BitSet FOLLOW_122_in_conGD3902 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_NUMBER_in_conGD3904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_goalDesc_in_conGD3906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_conGD3908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atomicFunctionSkeleton_in_synpred18_Pddl852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fComp_in_synpred51_Pddl1806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred54_Pddl1965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred67_Pddl2221 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_synpred67_Pddl2223 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_synpred67_Pddl2225 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp2_in_synpred67_Pddl2227 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred67_Pddl2229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred68_Pddl2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_synpred68_Pddl2248 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_synpred68_Pddl2250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred68_Pddl2252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred69_Pddl2265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_138_in_synpred69_Pddl2267 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_synpred69_Pddl2269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred69_Pddl2271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred70_Pddl2284 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_114_in_synpred70_Pddl2286 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_synpred70_Pddl2288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred70_Pddl2290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred71_Pddl2303 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_synpred71_Pddl2305 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_fExp_in_synpred71_Pddl2307 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred71_Pddl2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_synpred105_Pddl2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred107_Pddl2808 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_synpred107_Pddl2810 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_daEffect_in_synpred107_Pddl2812 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_synpred107_Pddl2815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_timedEffect_in_synpred108_Pddl2820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred109_Pddl2825 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_synpred109_Pddl2827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_synpred109_Pddl2829 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred109_Pddl2831 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred109_Pddl2833 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daEffect_in_synpred109_Pddl2835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred109_Pddl2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred110_Pddl2842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_144_in_synpred110_Pddl2844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daGD_in_synpred110_Pddl2846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_timedEffect_in_synpred110_Pddl2848 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred110_Pddl2850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred111_Pddl2874 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_synpred111_Pddl2876 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred111_Pddl2878 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_daEffect_in_synpred111_Pddl2880 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred111_Pddl2882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred112_Pddl2892 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});
    public static final BitSet FOLLOW_112_in_synpred112_Pddl2894 = new BitSet(new long[]{0x0000000000000000L,0x0080000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_timeSpecifier_in_synpred112_Pddl2896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_fAssignDA_in_synpred112_Pddl2898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred112_Pddl2900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binaryOp_in_synpred113_Pddl2956 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_synpred113_Pddl2958 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_synpred113_Pddl2960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred114_Pddl2952 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_synpred114_Pddl2956 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_synpred114_Pddl2958 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_synpred114_Pddl2960 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_74_in_synpred114_Pddl2966 = new BitSet(new long[]{0x0000220000000000L,0x0000010000000060L});
    public static final BitSet FOLLOW_fExpDA_in_synpred114_Pddl2968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred114_Pddl2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred121_Pddl3213 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_synpred121_Pddl3215 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_initEl_in_synpred121_Pddl3217 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_synpred121_Pddl3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred132_Pddl3509 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_110_in_synpred132_Pddl3511 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_prefConGD_in_synpred132_Pddl3513 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000C0L});
    public static final BitSet FOLLOW_71_in_synpred132_Pddl3516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred133_Pddl3521 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_121_in_synpred133_Pddl3523 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_synpred133_Pddl3525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000084L});
    public static final BitSet FOLLOW_typedVariableList_in_synpred133_Pddl3527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred133_Pddl3529 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_prefConGD_in_synpred133_Pddl3531 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred133_Pddl3533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred135_Pddl3538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_synpred135_Pddl3540 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_NAME_in_synpred135_Pddl3542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_conGD_in_synpred135_Pddl3545 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred135_Pddl3547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred137_Pddl3608 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000F00L});
    public static final BitSet FOLLOW_binaryOp_in_synpred137_Pddl3610 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_synpred137_Pddl3612 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_synpred137_Pddl3614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred137_Pddl3616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred139_Pddl3638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_multiOp_in_synpred139_Pddl3640 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_synpred139_Pddl3642 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_synpred139_Pddl3644 = new BitSet(new long[]{0x0000220000000000L,0x00000000000000E0L});
    public static final BitSet FOLLOW_71_in_synpred139_Pddl3647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_synpred140_Pddl3671 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_synpred140_Pddl3673 = new BitSet(new long[]{0x0000220000000000L,0x0000000000000060L});
    public static final BitSet FOLLOW_metricFExp_in_synpred140_Pddl3675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_synpred140_Pddl3677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fHead_in_synpred142_Pddl3699 = new BitSet(new long[]{0x0000000000000002L});

}