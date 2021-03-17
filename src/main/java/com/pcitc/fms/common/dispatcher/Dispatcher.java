package com.pcitc.fms.common.dispatcher;

/**
 *
 * <p>Title: Dispatcher</p>
 * <p>Description: 根据 handler_code 调用不同的 Handler</p>
 * <p>Company: </p>
 * @author 赵振强
 * @version V 0.0.1
 */
public class Dispatcher {

    /**
     * 根据 handler_code 调用不同的 Handler，执行handler.execute()。
     * @param req
     * @param res
     */
//    public void executeAction(HttpServletRequest req, HttpServletResponse res) {
//
//        String handler_code = req.getParameter("handler_code");
//        Handler handler = null;
//        try {
//            Class home = Class.forName(SysGlobal.getDispatcherParam(handler_code));
//            handler = (Handler) home.newInstance();
//        }
//        catch (Exception ex) {
//            SysCommon.printException(ex);
//            Forward forward = new Forward(req, res);
//            forward.forwardError("sysadmin_controller_no_find_handler", null);
//            return;
//        }
//
//        handler.initParameters(req, res);
//        handler.execute();
//		handler = null;
//		handler_code = null;
//    }
}