/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config C:/Users/stl/Desktop/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */
#ifndef _Control_H_
#define _Control_H_

#include "kcg_types.h"

/* =====================  no input structure  ====================== */

/* =====================  no output structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_float64 /* Control::newValue */ newValue;
  /* -----------------------  no local probes  ----------------------- */
  /* -----------------  no initialization variables  ----------------- */
  /* -----------------------  no local memory  ----------------------- */
  /* -------------------- no sub nodes' contexts  -------------------- */
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_float64 /* Control::_L1 */ _L1;
  kcg_bool /* Control::_L2 */ _L2;
  kcg_float64 /* Control::_L3 */ _L3;
  kcg_bool /* Control::_L4 */ _L4;
  kcg_float64 /* Control::_L5 */ _L5;
  kcg_bool /* Control::_L6 */ _L6;
  kcg_float64 /* Control::_L7 */ _L7;
  kcg_float64 /* Control::_L9 */ _L9;
  kcg_float64 /* Control::_L10 */ _L10;
  kcg_float64 /* Control::_L11 */ _L11;
  kcg_float64 /* Control::_L12 */ _L12;
} outC_Control;

/* ===========  node initialization and cycle functions  =========== */
/* Control */
extern void Control(
  /* Control::value */ kcg_float64 value,
  /* Control::max */ kcg_float64 max,
  /* Control::on */ kcg_bool on,
  outC_Control *outC);

#ifndef KCG_NO_EXTERN_CALL_TO_RESET
extern void Control_reset(outC_Control *outC);
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

#ifndef KCG_USER_DEFINED_INIT
extern void Control_init(outC_Control *outC);
#endif /* KCG_USER_DEFINED_INIT */

#endif /* _Control_H_ */
/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Control.h
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

