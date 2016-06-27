/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Command: kcg65.exe -config C:/Users/stl/Desktop/Reda-Juan-Jerome/Simulation/config.txt
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "Control.h"

#ifndef KCG_USER_DEFINED_INIT
void Control_init(outC_Control *outC)
{
  outC->_L12 = kcg_lit_float64(0.0);
  outC->_L11 = kcg_lit_float64(0.0);
  outC->_L10 = kcg_lit_float64(0.0);
  outC->_L9 = kcg_lit_float64(0.0);
  outC->_L7 = kcg_lit_float64(0.0);
  outC->_L6 = kcg_true;
  outC->_L5 = kcg_lit_float64(0.0);
  outC->_L4 = kcg_true;
  outC->_L3 = kcg_lit_float64(0.0);
  outC->_L2 = kcg_true;
  outC->_L1 = kcg_lit_float64(0.0);
  outC->newValue = kcg_lit_float64(0.0);
}
#endif /* KCG_USER_DEFINED_INIT */


#ifndef KCG_NO_EXTERN_CALL_TO_RESET
void Control_reset(outC_Control *outC)
{
}
#endif /* KCG_NO_EXTERN_CALL_TO_RESET */

/* Control */
void Control(
  /* Control::value */ kcg_float64 value,
  /* Control::max */ kcg_float64 max,
  /* Control::on */ kcg_bool on,
  outC_Control *outC)
{
  outC->_L11 = max;
  outC->_L10 = kcg_lit_float64(10.0);
  outC->_L12 = outC->_L11 / outC->_L10;
  outC->_L9 = outC->_L11 - outC->_L12;
  outC->_L7 = value;
  outC->_L1 = value;
  outC->_L3 = max;
  outC->_L4 = outC->_L1 > outC->_L3;
  outC->_L2 = on;
  outC->_L6 = outC->_L4 & outC->_L2;
  /* 1 */ if (outC->_L6) {
    outC->_L5 = outC->_L9;
  }
  else {
    outC->_L5 = outC->_L7;
  }
  outC->newValue = outC->_L5;
}

/* $********** SCADE Suite KCG 64-bit 6.5 (build i12) ***********
** Control.c
** Generation date: 2016-04-12T18:07:54
*************************************************************$ */

