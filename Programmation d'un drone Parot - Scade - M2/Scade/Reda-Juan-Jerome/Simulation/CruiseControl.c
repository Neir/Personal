/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config C:/Users/stl/Desktop/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "CruiseControl.h"

#ifndef KCG_USER_DEFINED_INIT
void CruiseControl_init(outC_CruiseControl *outC)
{
  outC->SM1_fired = SSM_TR_no_trans_SM1;
  outC->SM1_fired_strong = SSM_TR_no_trans_SM1;
  outC->SM1_state_sel = SSM_st_State1_SM1;
  outC->_L1_SM1_State2 = kcg_lit_float64(0.0);
  outC->_L2_SM1_State2 = kcg_lit_float64(0.0);
  outC->_L3_SM1_State2 = kcg_true;
  outC->_L4_SM1_State2 = kcg_lit_float64(0.0);
  outC->_L1_SM1_State1 = kcg_lit_float64(0.0);
  outC->SM1_state_act = SSM_st_State1_SM1;
  outC->init = kcg_true;
  outC->init1 = kcg_true;
  outC->pre_times_1 = 0;
  outC->pre_times_2 = 0;
  outC->cruiseControled = kcg_lit_float64(0.0);
  /* 1 */ Control_init(&outC->Context_1);
  outC->SM1_state_nxt = SSM_st_State1_SM1;
  outC->SM1_reset_act = kcg_false;
  outC->SM1_reset_nxt = kcg_false;
}
#endif /* KCG_USER_DEFINED_INIT */


#ifndef KCG_NO_EXTERN_CALL_TO_RESET
void CruiseControl_reset(outC_CruiseControl *outC)
{
  outC->init = kcg_true;
  outC->init1 = kcg_true;
  /* 1 */ Control_reset(&outC->Context_1);
  outC->SM1_state_nxt = SSM_st_State1_SM1;
  outC->SM1_reset_act = kcg_false;
  outC->SM1_reset_nxt = kcg_false;
}
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

/* CruiseControl */
void CruiseControl(inC_CruiseControl *inC, outC_CruiseControl *outC)
{
  kcg_bool _4_times_2;
  kcg_bool _5_times_2;
  kcg_size _6_times_2;
  kcg_size times_2;
  kcg_bool _1_times_1;
  kcg_bool _2_times_1;
  kcg_size _3_times_1;
  kcg_size times_1;
  /* CruiseControl::SM1 */ SSM_TR_SM1 _10_SM1_fired;
  /* CruiseControl::SM1 */ kcg_bool _9_SM1_reset_nxt;
  /* CruiseControl::SM1 */ SSM_ST_SM1 _8_SM1_state_nxt;
  /* CruiseControl::cruiseControled */ kcg_float64 _7_cruiseControled;
  /* CruiseControl::SM1::State1 */ kcg_bool br_1_guard_SM1_State1;
  /* CruiseControl::SM1 */ SSM_TR_SM1 SM1_fired;
  /* CruiseControl::SM1 */ kcg_bool SM1_reset_nxt;
  /* CruiseControl::SM1 */ SSM_ST_SM1 SM1_state_nxt;
  /* CruiseControl::cruiseControled */ kcg_float64 cruiseControled;
  /* CruiseControl::SM1::State2 */ kcg_bool br_1_guard_SM1_State2;
  /* CruiseControl::SM1 */ kcg_bool SM1_reset_sel;
  /* CruiseControl::SM1 */ kcg_bool SM1_reset_prv;

  outC->SM1_state_sel = outC->SM1_state_nxt;
  outC->SM1_state_act = outC->SM1_state_sel;
  SM1_reset_sel = outC->SM1_reset_nxt;
  /* sel_SM1 */ switch (outC->SM1_state_sel) {
    case SSM_st_State1_SM1 :
      if (SM1_reset_sel) {
        outC->init = kcg_true;
      }
      break;
    case SSM_st_State2_SM1 :
      if (SM1_reset_sel) {
        outC->init1 = kcg_true;
      }
      break;

    default :
      /* this default branch is unreachable */
      break;
  }
  /* act_SM1 */ switch (outC->SM1_state_act) {
    case SSM_st_State1_SM1 :
      _2_times_1 = inC->cruise > inC->maxCruise;
      _3_times_1 = 10;
      /* 1 */ if (outC->init) {
        times_1 = _3_times_1;
      }
      else {
        times_1 = outC->pre_times_1;
      }
      if (times_1 < 0) {
        outC->pre_times_1 = times_1;
      }
      else if (_2_times_1) {
        outC->pre_times_1 = times_1 - 1;
      }
      else {
        outC->pre_times_1 = times_1;
      }
      _1_times_1 = _2_times_1 & (outC->pre_times_1 == 0);
      br_1_guard_SM1_State1 = _1_times_1;
      if (br_1_guard_SM1_State1) {
        _10_SM1_fired = SSM_TR_State1_1_SM1;
      }
      else {
        _10_SM1_fired = SSM_TR_no_trans_SM1;
      }
      _9_SM1_reset_nxt = br_1_guard_SM1_State1;
      if (br_1_guard_SM1_State1) {
        _8_SM1_state_nxt = SSM_st_State2_SM1;
      }
      else {
        _8_SM1_state_nxt = SSM_st_State1_SM1;
      }
      outC->_L1_SM1_State1 = inC->cruise;
      _7_cruiseControled = outC->_L1_SM1_State1;
      outC->SM1_fired = _10_SM1_fired;
      outC->SM1_reset_nxt = _9_SM1_reset_nxt;
      outC->SM1_state_nxt = _8_SM1_state_nxt;
      outC->cruiseControled = _7_cruiseControled;
      break;
    case SSM_st_State2_SM1 :
      _5_times_2 = inC->cruise <= inC->maxCruise;
      _6_times_2 = 10;
      /* 2 */ if (outC->init1) {
        times_2 = _6_times_2;
      }
      else {
        times_2 = outC->pre_times_2;
      }
      if (times_2 < 0) {
        outC->pre_times_2 = times_2;
      }
      else if (_5_times_2) {
        outC->pre_times_2 = times_2 - 1;
      }
      else {
        outC->pre_times_2 = times_2;
      }
      _4_times_2 = _5_times_2 & (outC->pre_times_2 == 0);
      br_1_guard_SM1_State2 = _4_times_2;
      if (br_1_guard_SM1_State2) {
        SM1_fired = SSM_TR_State2_1_SM1;
      }
      else {
        SM1_fired = SSM_TR_no_trans_SM1;
      }
      SM1_reset_nxt = br_1_guard_SM1_State2;
      if (br_1_guard_SM1_State2) {
        SM1_state_nxt = SSM_st_State1_SM1;
      }
      else {
        SM1_state_nxt = SSM_st_State2_SM1;
      }
      outC->_L1_SM1_State2 = inC->cruise;
      outC->_L2_SM1_State2 = inC->maxCruise;
      outC->_L3_SM1_State2 = inC->cruiseControlOn;
      /* 1 */
      Control(
        outC->_L1_SM1_State2,
        outC->_L2_SM1_State2,
        outC->_L3_SM1_State2,
        &outC->Context_1);
      outC->_L4_SM1_State2 = outC->Context_1.newValue;
      cruiseControled = outC->_L4_SM1_State2;
      outC->SM1_fired = SM1_fired;
      outC->SM1_reset_nxt = SM1_reset_nxt;
      outC->SM1_state_nxt = SM1_state_nxt;
      outC->cruiseControled = cruiseControled;
      break;

    default :
      /* this default branch is unreachable */
      break;
  }
  outC->SM1_fired_strong = SSM_TR_no_trans_SM1;
  SM1_reset_prv = outC->SM1_reset_act;
  outC->SM1_reset_act = kcg_false;
  /* act_SM1 */ switch (outC->SM1_state_act) {
    case SSM_st_State1_SM1 :
      outC->init = kcg_false;
      break;
    case SSM_st_State2_SM1 :
      outC->init1 = kcg_false;
      break;

    default :
      /* this default branch is unreachable */
      break;
  }
}

/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** CruiseControl.c
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

