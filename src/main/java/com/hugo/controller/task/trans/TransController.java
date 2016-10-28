package com.hugo.controller.task.trans;

import com.hugo.controller.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ohj on 2016/10/13.
 */
@Controller
@RequiresRoles("ROLE_TRANS")
@RequestMapping("/task/trans")
public class TransController extends BaseController {


}
