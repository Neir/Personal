/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config E:/Scade/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-14T16:12:49
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "Deplacement.h"

#ifndef KCG_USER_DEFINED_INIT
void Deplacement_init(outC_Deplacement *outC)
{
  kcg_size i;
  kcg_size i1;
  kcg_size i2;
  kcg_size i3;
  kcg_size i4;
  kcg_size i5;
  kcg_size i6;
  kcg_size i7;
  kcg_size i8;
  kcg_size i9;
  kcg_size i10;
  kcg_size i11;
  kcg_size i12;
  kcg_size i13;

  outC->SM1_fired = SSM_TR_no_trans_SM1;
  outC->SM1_fired_strong = SSM_TR_no_trans_SM1;
  outC->SM1_state_act = SSM_st_left_SM1;
  outC->SM1_state_sel = SSM_st_left_SM1;
  for (i = 0; i < 3; i++) {
    outC->_L2_SM1_hovering[i] = kcg_lit_int32(0);
  }
  for (i1 = 0; i1 < 3; i1++) {
    outC->_L3_SM1_Landing[i1] = kcg_lit_int32(0);
  }
  for (i2 = 0; i2 < 3; i2++) {
    outC->_L2_SM1_Landing[i2] = kcg_lit_int32(0);
  }
  for (i3 = 0; i3 < 3; i3++) {
    outC->_L1_SM1_Landing[i3] = kcg_lit_int32(0);
  }
  for (i4 = 0; i4 < 3; i4++) {
    outC->_L5_SM1_takingOff[i4] = kcg_lit_int32(0);
  }
  for (i5 = 0; i5 < 3; i5++) {
    outC->_L4_SM1_takingOff[i5] = kcg_lit_int32(0);
  }
  for (i6 = 0; i6 < 3; i6++) {
    outC->_L3_SM1_takingOff[i6] = kcg_lit_int32(0);
  }
  for (i7 = 0; i7 < 3; i7++) {
    outC->_L12_SM1_right[i7] = kcg_lit_int32(0);
  }
  for (i8 = 0; i8 < 3; i8++) {
    outC->_L11_SM1_right[i8] = kcg_lit_int32(0);
  }
  for (i9 = 0; i9 < 3; i9++) {
    outC->_L10_SM1_right[i9] = kcg_lit_int32(0);
  }
  for (i10 = 0; i10 < 3; i10++) {
    outC->_L12_SM1_left[i10] = kcg_lit_int32(0);
  }
  for (i11 = 0; i11 < 3; i11++) {
    outC->_L11_SM1_left[i11] = kcg_lit_int32(0);
  }
  for (i12 = 0; i12 < 3; i12++) {
    outC->_L10_SM1_left[i12] = kcg_lit_int32(0);
  }
  for (i13 = 0; i13 < 3; i13++) {
    outC->OUTPUT[i13] = kcg_lit_int32(0);
  }
  outC->SM1_state_nxt = SSM_st_hovering_SM1;
  outC->SM1_reset_act = kcg_false;
  outC->SM1_reset_nxt = kcg_false;
}
#endif /* KCG_USER_DEFINED_INIT */


#ifndef KCG_NO_EXTERN_CALL_TO_RESET
void Deplacement_reset(outC_Deplacement *outC)
{
  outC->SM1_state_nxt = SSM_st_hovering_SM1;
  outC->SM1_reset_act = kcg_false;
  outC->SM1_reset_nxt = kcg_false;
}
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

/* Deplacement */
void Deplacement(inC_Deplacement *inC, outC_Deplacement *outC)
{
  kcg_size i3;
  kcg_size i2;
  kcg_size i1;
  kcg_size i;
  /* Deplacement::SM1 */ SSM_TR_SM1 _19_SM1_fired;
  /* Deplacement::SM1 */ kcg_bool _18_SM1_reset_nxt;
  /* Deplacement::SM1 */ SSM_ST_SM1 _17_SM1_state_nxt;
  /* Deplacement::OUTPUT */ array_int32_3 _16_OUTPUT;
  /* Deplacement::SM1::left */ kcg_bool br_1_guard_SM1_left;
  /* Deplacement::SM1 */ SSM_TR_SM1 _15_SM1_fired;
  /* Deplacement::SM1 */ kcg_bool _14_SM1_reset_nxt;
  /* Deplacement::SM1 */ SSM_ST_SM1 _13_SM1_state_nxt;
  /* Deplacement::OUTPUT */ array_int32_3 _12_OUTPUT;
  /* Deplacement::SM1 */ SSM_TR_SM1 _11_SM1_fired;
  /* Deplacement::SM1 */ kcg_bool _10_SM1_reset_nxt;
  /* Deplacement::SM1 */ SSM_ST_SM1 _9_SM1_state_nxt;
  /* Deplacement::OUTPUT */ array_int32_3 _8_OUTPUT;
  /* Deplacement::SM1 */ SSM_TR_SM1 _7_SM1_fired;
  /* Deplacement::SM1 */ kcg_bool _6_SM1_reset_nxt;
  /* Deplacement::SM1 */ SSM_ST_SM1 _5_SM1_state_nxt;
  /* Deplacement::OUTPUT */ array_int32_3 _4_OUTPUT;
  /* Deplacement::SM1 */ SSM_TR_SM1 SM1_fired;
  /* Deplacement::SM1 */ kcg_bool SM1_reset_nxt;
  /* Deplacement::SM1 */ SSM_ST_SM1 SM1_state_nxt;
  /* Deplacement::OUTPUT */ array_int32_3 OUTPUT;
  /* Deplacement::SM1::hovering */ kcg_bool br_2_guard_SM1_hovering;
  /* Deplacement::SM1::hovering */ kcg_bool br_1_guard_SM1_hovering;
  /* Deplacement::SM1 */ kcg_bool SM1_reset_sel;
  /* Deplacement::SM1 */ kcg_bool SM1_reset_prv;

  outC->SM1_state_sel = outC->SM1_state_nxt;
  outC->SM1_state_act = outC->SM1_state_sel;
  /* act_SM1 */ switch (outC->SM1_state_act) {
    case SSM_st_hovering_SM1 :
      br_2_guard_SM1_hovering = inC->inGOTO == TAKEOFF;
      br_1_guard_SM1_hovering = inC->inGOTO == LEFT;
      if (br_1_guard_SM1_hovering) {
        SM1_fired = SSM_TR_hovering_1_SM1;
      }
      else if (br_2_guard_SM1_hovering) {
        SM1_fired = SSM_TR_hovering_2_SM1;
      }
      else {
        SM1_fired = SSM_TR_no_trans_SM1;
      }
      if (br_1_guard_SM1_hovering) {
        SM1_reset_nxt = kcg_true;
      }
      else {
        SM1_reset_nxt = br_2_guard_SM1_hovering;
      }
      if (br_1_guard_SM1_hovering) {
        SM1_state_nxt = SSM_st_left_SM1;
      }
      else if (br_2_guard_SM1_hovering) {
        SM1_state_nxt = SSM_st_takingOff_SM1;
      }
      else {
        SM1_state_nxt = SSM_st_hovering_SM1;
      }
      kcg_copy_array_int32_3(&outC->_L2_SM1_hovering, &inC->Triplet);
      kcg_copy_array_int32_3(&OUTPUT, &outC->_L2_SM1_hovering);
      break;

    default :
      /* this branch is empty */
      break;
  }
  outC->SM1_fired_strong = SSM_TR_no_trans_SM1;
  /* act_SM1 */ switch (outC->SM1_state_act) {
    case SSM_st_left_SM1 :
      br_1_guard_SM1_left = kcg_true;
      if (br_1_guard_SM1_left) {
        _19_SM1_fired = SSM_TR_left_1_SM1;
      }
      else {
        _19_SM1_fired = SSM_TR_no_trans_SM1;
      }
      _18_SM1_reset_nxt = br_1_guard_SM1_left;
      if (br_1_guard_SM1_left) {
        _17_SM1_state_nxt = SSM_st_hovering_SM1;
      }
      else {
        _17_SM1_state_nxt = SSM_st_left_SM1;
      }
      kcg_copy_array_int32_3(&outC->_L12_SM1_left, &inC->Triplet);
      outC->_L11_SM1_left[0] = kcg_lit_int32(0);
      outC->_L11_SM1_left[1] = kcg_lit_int32(0);
      outC->_L11_SM1_left[2] = kcg_lit_int32(5);
      /* 27 */ for (i = 0; i < 3; i++) {
        outC->_L10_SM1_left[i] = outC->_L12_SM1_left[i] + outC->_L11_SM1_left[i];
      }
      kcg_copy_array_int32_3(&_16_OUTPUT, &outC->_L10_SM1_left);
      outC->SM1_fired = _19_SM1_fired;
      break;
    case SSM_st_right_SM1 :
      _15_SM1_fired = outC->SM1_fired_strong;
      _14_SM1_reset_nxt = kcg_false;
      _13_SM1_state_nxt = SSM_st_right_SM1;
      kcg_copy_array_int32_3(&outC->_L12_SM1_right, &inC->Triplet);
      outC->_L10_SM1_right[0] = kcg_lit_int32(0);
      outC->_L10_SM1_right[1] = kcg_lit_int32(0);
      outC->_L10_SM1_right[2] = kcg_lit_int32(-5);
      /* 29 */ for (i1 = 0; i1 < 3; i1++) {
        outC->_L11_SM1_right[i1] = outC->_L12_SM1_right[i1] + outC->_L10_SM1_right[i1];
      }
      kcg_copy_array_int32_3(&_12_OUTPUT, &outC->_L11_SM1_right);
      outC->SM1_fired = _15_SM1_fired;
      break;
    case SSM_st_takingOff_SM1 :
      _11_SM1_fired = outC->SM1_fired_strong;
      _10_SM1_reset_nxt = kcg_false;
      _9_SM1_state_nxt = SSM_st_takingOff_SM1;
      kcg_copy_array_int32_3(&outC->_L4_SM1_takingOff, &inC->Triplet);
      outC->_L3_SM1_takingOff[0] = kcg_lit_int32(5);
      outC->_L3_SM1_takingOff[1] = kcg_lit_int32(0);
      outC->_L3_SM1_takingOff[2] = kcg_lit_int32(0);
      /* 11 */ for (i2 = 0; i2 < 3; i2++) {
        outC->_L5_SM1_takingOff[i2] = outC->_L4_SM1_takingOff[i2] +
          outC->_L3_SM1_takingOff[i2];
      }
      kcg_copy_array_int32_3(&_8_OUTPUT, &outC->_L5_SM1_takingOff);
      outC->SM1_fired = _11_SM1_fired;
      break;
    case SSM_st_Landing_SM1 :
      _7_SM1_fired = outC->SM1_fired_strong;
      _6_SM1_reset_nxt = kcg_false;
      _5_SM1_state_nxt = SSM_st_Landing_SM1;
      kcg_copy_array_int32_3(&outC->_L1_SM1_Landing, &inC->Triplet);
      outC->_L3_SM1_Landing[0] = kcg_lit_int32(-5);
      outC->_L3_SM1_Landing[1] = kcg_lit_int32(0);
      outC->_L3_SM1_Landing[2] = kcg_lit_int32(5);
      /* 15 */ for (i3 = 0; i3 < 3; i3++) {
        outC->_L2_SM1_Landing[i3] = outC->_L1_SM1_Landing[i3] +
          outC->_L3_SM1_Landing[i3];
      }
      kcg_copy_array_int32_3(&_4_OUTPUT, &outC->_L2_SM1_Landing);
      outC->SM1_fired = _7_SM1_fired;
      break;
    case SSM_st_hovering_SM1 :
      outC->SM1_fired = SM1_fired;
      break;

    default :
      /* this default branch is unreachable */
      break;
  }
  SM1_reset_sel = outC->SM1_reset_nxt;
  /* act_SM1 */ switch (outC->SM1_state_act) {
    case SSM_st_left_SM1 :
      outC->SM1_reset_nxt = _18_SM1_reset_nxt;
      outC->SM1_state_nxt = _17_SM1_state_nxt;
      kcg_copy_array_int32_3(&outC->OUTPUT, &_16_OUTPUT);
      break;
    case SSM_st_right_SM1 :
      outC->SM1_reset_nxt = _14_SM1_reset_nxt;
      outC->SM1_state_nxt = _13_SM1_state_nxt;
      kcg_copy_array_int32_3(&outC->OUTPUT, &_12_OUTPUT);
      break;
    case SSM_st_takingOff_SM1 :
      outC->SM1_reset_nxt = _10_SM1_reset_nxt;
      outC->SM1_state_nxt = _9_SM1_state_nxt;
      kcg_copy_array_int32_3(&outC->OUTPUT, &_8_OUTPUT);
      break;
    case SSM_st_Landing_SM1 :
      outC->SM1_reset_nxt = _6_SM1_reset_nxt;
      outC->SM1_state_nxt = _5_SM1_state_nxt;
      kcg_copy_array_int32_3(&outC->OUTPUT, &_4_OUTPUT);
      break;
    case SSM_st_hovering_SM1 :
      outC->SM1_reset_nxt = SM1_reset_nxt;
      outC->SM1_state_nxt = SM1_state_nxt;
      kcg_copy_array_int32_3(&outC->OUTPUT, &OUTPUT);
      break;

    default :
      /* this default branch is unreachable */
      break;
  }
  SM1_reset_prv = outC->SM1_reset_act;
  outC->SM1_reset_act = kcg_false;
}

/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Deplacement.c
** Generation date: 2016-04-14T16:12:49
*************************************************************$ */

