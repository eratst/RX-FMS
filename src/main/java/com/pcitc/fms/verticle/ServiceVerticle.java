package com.pcitc.fms.verticle;

import amq.synchronize.service.SyncService;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.common.ClassUtil;
import com.pcitc.fms.common.DISCMAp;
import com.pcitc.fms.common.FMSTableAndPrimaryKey;
import com.pcitc.fms.common.JDBCUtil;
import com.pcitc.fms.common.MySpringConfiguration;
import com.pcitc.fms.common.RedisUtil;
import com.pcitc.fms.common.YLCloseableHttpClient;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.LoadMap;
import com.pcitc.fms.mqtool.StartSyncService;
import com.pcitc.fms.service.ResourceRegister;
import com.pcitc.fms.service.handler.AAAInfoHandler;
import com.pcitc.fms.service.handler.AAAUserHandler;
import com.pcitc.fms.service.handler.AdministrationHandler;
import com.pcitc.fms.service.handler.AirDenModCoefHandler;
import com.pcitc.fms.service.handler.AreaDictGatherHandler;
import com.pcitc.fms.service.handler.AreaDictionaryHandler;
import com.pcitc.fms.service.handler.AssociativesHandler;
import com.pcitc.fms.service.handler.BizOrgDTLHandler;
import com.pcitc.fms.service.handler.CacheRentHandler;
import com.pcitc.fms.service.handler.CapacityUnitHandler;
import com.pcitc.fms.service.handler.CnfgTankHandler;
import com.pcitc.fms.service.handler.CommunityHandler;
import com.pcitc.fms.service.handler.ConnectionsHandler;
import com.pcitc.fms.service.handler.DeltcnfgHandler;
import com.pcitc.fms.service.handler.DepartmentsHandler;
import com.pcitc.fms.service.handler.DictionaryTableHandler;
import com.pcitc.fms.service.handler.DimensionHandler;
import com.pcitc.fms.service.handler.DivisionsHandler;
import com.pcitc.fms.service.handler.EdgePointHandler;
import com.pcitc.fms.service.handler.EnMeasurementHandler;
import com.pcitc.fms.service.handler.EnNodeHandler;
import com.pcitc.fms.service.handler.EnNodeTypeHandler;
import com.pcitc.fms.service.handler.EnPipeNetHandler;
import com.pcitc.fms.service.handler.EnterprisesHandler;
import com.pcitc.fms.service.handler.EntityHandler;
import com.pcitc.fms.service.handler.EquipmentHandler;
import com.pcitc.fms.service.handler.FMSObjectTreeHandler;
import com.pcitc.fms.service.handler.FactoryHandler;
import com.pcitc.fms.service.handler.FltperCuabHandler;
import com.pcitc.fms.service.handler.GlbCubasHandler;
import com.pcitc.fms.service.handler.GlbPreCoefHandler;
import com.pcitc.fms.service.handler.HeadquartersHandler;
import com.pcitc.fms.service.handler.IcMtrlFormCnfgHandler;
import com.pcitc.fms.service.handler.IcPipenettankCoefHandler;
import com.pcitc.fms.service.handler.IcStangasdenHandler;
import com.pcitc.fms.service.handler.IcVcfHandler;
import com.pcitc.fms.service.handler.LieCubasHandler;
import com.pcitc.fms.service.handler.LiqprodCubaTempCoefHandler;
import com.pcitc.fms.service.handler.LiquidProdCoefHandler;
import com.pcitc.fms.service.handler.LoadPointHandler;
import com.pcitc.fms.service.handler.LoadPointTypeHandler;
import com.pcitc.fms.service.handler.LoadingDockHandler;
import com.pcitc.fms.service.handler.ManagementMtrlHandler;
import com.pcitc.fms.service.handler.ManagementTankAreaHandler;
import com.pcitc.fms.service.handler.ManagementTankHandler;
import com.pcitc.fms.service.handler.MapSampleNodeHandler;
import com.pcitc.fms.service.handler.MaterialHandler;
import com.pcitc.fms.service.handler.MeasurementHandler;
import com.pcitc.fms.service.handler.MtrlMolarHandler;
import com.pcitc.fms.service.handler.MtrlVcfHandler;
import com.pcitc.fms.service.handler.NewTankHandler;
import com.pcitc.fms.service.handler.NodeAndAreaHandler;
import com.pcitc.fms.service.handler.NodeDictGatherHandler;
import com.pcitc.fms.service.handler.NodeDictionaryHandler;
import com.pcitc.fms.service.handler.NodeIdxTypeHandler;
import com.pcitc.fms.service.handler.NodeTopDTLHandler;
import com.pcitc.fms.service.handler.OfficesHandler;
import com.pcitc.fms.service.handler.Openindexhandler;
import com.pcitc.fms.service.handler.OrgFindAreaHandler;
import com.pcitc.fms.service.handler.OrgRelationHandler;
import com.pcitc.fms.service.handler.OutletHandler;
import com.pcitc.fms.service.handler.PipeNetworkHandler;
import com.pcitc.fms.service.handler.PlantHandler;
import com.pcitc.fms.service.handler.PlateHandler;
import com.pcitc.fms.service.handler.PositionHandler;
import com.pcitc.fms.service.handler.PositionOrgHandler;
import com.pcitc.fms.service.handler.PostionHandler;
import com.pcitc.fms.service.handler.PrdtcellHandler;
import com.pcitc.fms.service.handler.PrdtcellMeasindexHandler;
import com.pcitc.fms.service.handler.ProduceFactoriesHandler;
import com.pcitc.fms.service.handler.PropertyHandler;
import com.pcitc.fms.service.handler.RelationsHandler;
import com.pcitc.fms.service.handler.RentHandler;
import com.pcitc.fms.service.handler.SamplePointHandler;
import com.pcitc.fms.service.handler.SideLineHandler;
import com.pcitc.fms.service.handler.SiloHandler;
import com.pcitc.fms.service.handler.SpclCuabHandler;
import com.pcitc.fms.service.handler.StaalgrConfHandler;
import com.pcitc.fms.service.handler.StaalgrConfitemHandler;
import com.pcitc.fms.service.handler.StanDenHandler;
import com.pcitc.fms.service.handler.StationHandler;
import com.pcitc.fms.service.handler.StdSecHandler;
import com.pcitc.fms.service.handler.StdcmmmCubasHandler;
import com.pcitc.fms.service.handler.StddmCuabsHandler;
import com.pcitc.fms.service.handler.StdpresCoefHandler;
import com.pcitc.fms.service.handler.StockHandler;
import com.pcitc.fms.service.handler.TPmOrgHandler;
import com.pcitc.fms.service.handler.TankAreaHandler;
import com.pcitc.fms.service.handler.TankBaseInfoHandler;
import com.pcitc.fms.service.handler.TankCnfgHandler;
import com.pcitc.fms.service.handler.TankHandler;
import com.pcitc.fms.service.handler.TeamAndUserHandler;
import com.pcitc.fms.service.handler.TeamsHandler;
import com.pcitc.fms.service.handler.TeeHandler;
import com.pcitc.fms.service.handler.TempcondenHandler;
import com.pcitc.fms.service.handler.TempdenHandler;
import com.pcitc.fms.service.handler.TemppredenHandler;
import com.pcitc.fms.service.handler.TubulationHandler;
import com.pcitc.fms.service.handler.UnitAlarmHandler;
import com.pcitc.fms.service.handler.UnitAreaHandler;
import com.pcitc.fms.service.handler.UnitAreaRelHandler;
import com.pcitc.fms.service.handler.UserHandler;
import com.pcitc.fms.service.handler.UserPositionHandler;
import com.pcitc.fms.service.handler.ValveHandler;
import com.pcitc.fms.service.handler.WarehouseHandler;
import com.pcitc.fms.service.handler.WorkshopsHandler;
import com.pcitc.fms.service.handler.YwUnitHandler;
import com.pcitc.fms.service.stormHandler.StormTankHandler;
import com.pcitc.fms.service.stormHandler.StormUnitHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceVerticle extends AbstractVerticle {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ServiceVerticle.class);
    private ApplicationContext context;

    public ServiceVerticle() {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.Log4jLogDelegateFactory");
        if (this.context == null) {
            this.context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);
            JDBCUtil.setContext(context);
            FMSTableAndPrimaryKey.setFMSTableAndPrimaryKey();
            if (System.getenv("activemq_broker_url") != null && System.getenv("activemq_user") != null && System.getenv("activemq_password") != null && System.getenv("mq_consumer_destination") != null) {
                oumRun();
            }
        }

        //??????????????????
        CacheRentHandler cacheRentHandler = (CacheRentHandler) context.getBean("cacheRentHandler");
        cacheRentHandler.execute();
//		cacheRentHandler.getCacheInfoForBiz();
        System.out.println(".........");
    }

    //??????oum
    private void oumRun() {
        System.out.println(".........??????......................");
        StartSyncService startSyncService = new StartSyncService();
        Map<String, SyncService> services = new HashMap<>();
        LoadMap loadMap = (LoadMap) context.getBean("loadMap");
        startSyncService.init(loadMap.loadMap());
        System.out.println(".........??????......................");
    }


    @Override
    public void start(Future<Void> future) throws Exception {
        init();
        Router router = Router.router(vertx);
        bindHelloMessage(router);
        bindApiResources(router);
        createServer(future, router);
    }

    /**
     * ????????????????????????
     *
     * @param router
     */
    private void bindUri(Router router) {
        /**
         * ?????????????????????????????????
         */
        // ResourceRegister interceptor = (ResourceRegister)
        // context.getBean("authInterceptor");
        // interceptor.registeResource(router);

        /**
         * ??????handler????????????????????????
         */
        List<String> resourceRegs = ClassUtil.getResourceRegister("com.pcitc.fms.service.handler");
        for (String name : resourceRegs) {
            ResourceRegister resourceRegister = (ResourceRegister) context.getBean(name);
            resourceRegister.registeResource(router);
        }

    }

    private void init() throws BusinessException {
    }

    /**
     * ????????????
     *
     * @param router
     */
    private void bindHelloMessage(Router router) {
        StringBuffer list = new StringBuffer();
        list.append("<h1>Hello from AlertService Link</h1>");
        list.append("<h2><a href='/FactoryModelService/Factories' target='MyList'>????????????</a></h2>");
        list.append("<h2><a href='/FactoryModelService/Factories/1/Warehouses/12'>????????????</a></h2>");

        router.route("/").handler(routingContext -> routingContext.response()
                .putHeader("content-type", "text/html; charset=utf-8").end(list.toString()));
    }

    /**
     * URL????????????
     *
     * @param router
     * @throws SQLException
     */
    private void bindApiResources(Router router) throws SQLException {
        // ??????
        router.route()
                .handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST)
                        .allowedMethod(HttpMethod.DELETE).allowedMethod(HttpMethod.PUT)
                        .allowedMethod(HttpMethod.OPTIONS).allowedHeader("Content-Type"));
        router.route("/*").handler(LoggerHandler.create());
        // router.route("/*").handler(TimeoutHandler.create(10000));
        router.route("/*").handler(ResponseTimeHandler.create());
        // router.route("/*").handler(ResponseContentTypeHandler.create());
        router.route("/*").produces("application/json;charset=UTF-8").handler(BodyHandler.create());
        bindUri(router);

        router.get("/FactoryModelService/*").blockingHandler(routingContext -> {
            String uri = routingContext.request().uri();
            if (routingContext.request().method() == HttpMethod.GET && !uri.contains("DMBService") && !uri.contains("aaaUser") && !uri.contains("dmbweb")) {
                if (System.getenv("USE_REDIS") == null || !System.getenv("USE_REDIS").equals("1")) {
                    useMap(routingContext, uri);
                } else {
                    useRedis(routingContext, uri);
                }
            } else {
                routingContext.next();
            }
        }, false);


        CacheRentHandler cacheRentHandler = (CacheRentHandler) context.getBean("cacheRentHandler");
        router.get("/FactoryModelService/caches").handler(cacheRentHandler::refreshCache);
        router.get("/FactoryModelService/bizcaches").handler(cacheRentHandler::refreshCacheBiz);
        router.get("/FactoryModelService/rents/:rentCode/caches").handler(cacheRentHandler::refreshCache);

        BizOrgDTLHandler bizOrgDTLHandler = context.getBean(BizOrgDTLHandler.class);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/bizOrgDTLs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/bizOrgDTLs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgDTLs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);

        router.get("/FactoryModelService/orgs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);
        router.get("/FactoryModelService/rents/:rentCode/orgs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/orgs").produces("application/json;charset=UTF-8").handler(bizOrgDTLHandler::getBizOrgDTLs);


        // ??????
        HeadquartersHandler headquartersHandler = (HeadquartersHandler) context.getBean("headquartersHandler");
        router.route("/FactoryModelService/headquarters*").handler(BodyHandler.create());
        router.get("/FactoryModelService/headquarters").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/headquarters").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/headquarters/:orgCode").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/headquarters").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/headquarters").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/headquarters/").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/headquarters/").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/headquarters/").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/headquarters/:orgCode").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/headquarters/:orgCode").handler(headquartersHandler::getHeadquarters);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/headquarters/:orgCode").handler(headquartersHandler::getHeadquarters);
        // ?????????
        DivisionsHandler divisionsHandler = (DivisionsHandler) context.getBean("divisionsHandler");
        router.route("/FactoryModelService/divisions*").handler(BodyHandler.create());
        router.get("/FactoryModelService/divisions").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/divisions").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/divisions").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/divisions/").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/divisions/").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/divisions/").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/divisions/:orgCode").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/divisions/:orgCode").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/divisions/:orgCode").handler(divisionsHandler::getDivisions);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/divisions/:orgCode").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/divisions").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/divisions").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/divisions/:orgCode").handler(divisionsHandler::getDivisions);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/divisions").handler(divisionsHandler::getDivisions);

        // ??????
        EnterprisesHandler enterprisesHandler = (EnterprisesHandler) context.getBean("enterprisesHandler");
        router.route("/FactoryModelService/enterprises*").handler(BodyHandler.create());
        router.get("/FactoryModelService/enterprises").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/enterprises").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/enterprises").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/enterprises/").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/enterprises/").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/enterprises/").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/enterprises/:orgCode").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/enterprises/:orgCode").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/enterprises/:orgCode").handler(enterprisesHandler::getEnterprises);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/enterprises/:orgCode").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/enterprises").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/enterprises").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/enterprises/:orgCode").handler(enterprisesHandler::getEnterprises);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/enterprises").handler(enterprisesHandler::getEnterprises);


        // ????????????
        OfficesHandler officesHandler = (OfficesHandler) context.getBean("officesHandler");
        router.route("/FactoryModelService/offices*").handler(BodyHandler.create());
        router.get("/FactoryModelService/offices").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/offices").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/offices").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/offices/").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/offices/").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/offices/").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/offices/:orgCode").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/offices/:orgCode").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/offices/:orgCode").handler(officesHandler::getOffices);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/offices/:orgCode").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/offices").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/offices").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/offices/:orgCode").handler(officesHandler::getOffices);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/offices").handler(officesHandler::getOffices);

        // ????????????
        ProduceFactoriesHandler produceFactoriesHandler = (ProduceFactoriesHandler) context
                .getBean("produceFactoriesHandler");
        router.route("/FactoryModelService/produceFactories*").handler(BodyHandler.create());
        router.get("/FactoryModelService/produceFactories").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/produceFactories").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/produceFactories").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/produceFactories/").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/produceFactories/").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/produceFactories/").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/produceFactories/:orgCode").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/produceFactories/:orgCode").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/produceFactories/:orgCode").handler(produceFactoriesHandler::getProduceFactories);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/produceFactories/:orgCode").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/produceFactories").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/produceFactories").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/produceFactories/:orgCode").handler(produceFactoriesHandler::getProduceFactories);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/produceFactories").handler(produceFactoriesHandler::getProduceFactories);


        // ??????
        DepartmentsHandler departmentsHandler = (DepartmentsHandler) context.getBean("departmentsHandler");
        router.route("/FactoryModelService/departments*").handler(BodyHandler.create());
        router.get("/FactoryModelService/departments").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/departments").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/departments").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/departments/").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/departments/").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/departments/").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/departments/:orgCode").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/departments/:orgCode").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/departments/:orgCode").handler(departmentsHandler::getDepartments);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/departments/:orgCode").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/departments").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/departments").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/departments/:orgCode").handler(departmentsHandler::getDepartments);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/departments").handler(departmentsHandler::getDepartments);


        // ??????
        WorkshopsHandler workshopsHandler = (WorkshopsHandler) context.getBean("workshopsHandler");
        router.route("/FactoryModelService/workshops*").handler(BodyHandler.create());
        router.get("/FactoryModelService/workshops").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/workshops").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/workshops").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/workshops/").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/workshops/").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/workshops/").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/workshops/:orgCode").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/workshops/:orgCode").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/workshops/:orgCode").handler(workshopsHandler::getWorkshops);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/workshops/:orgCode").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/workshops").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/rents/:rentCode/:parentOrgTypeCode/:parentOrgCode/workshops").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/workshops/:orgCode").handler(workshopsHandler::getWorkshops);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/:parentOrgTypeCode/:parentOrgCode/workshops").handler(workshopsHandler::getWorkshops);

        // ??????
        TeamsHandler teamsHandler = (TeamsHandler) context.getBean("teamsHandler");
        router.route("/FactoryModelService/teams*").handler(BodyHandler.create());
        router.get("/FactoryModelService/teams").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/teams").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/teams").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/teams/").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/teams/").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/teams/").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/teams/:orgCode").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/teams/:orgCode").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/teams/:orgCode").handler(teamsHandler::getTeams);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/teams").handler(teamsHandler::getTeams);

        // ??????
        FactoryHandler factoryHandler = (FactoryHandler) context.getBean("factoryHandler");
        router.get("/FactoryModelService/factories").handler(factoryHandler::getFactories);
        router.get("/FactoryModelService/factories/").handler(factoryHandler::getFactories);
        router.get("/FactoryModelService/rents/:rentCode/factories/").handler(factoryHandler::getFactories);
        router.get("/FactoryModelService/factories/:factoryCode").handler(factoryHandler::getFactories);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode").handler(factoryHandler::getFactories);
        router.route("/FactoryModelService/factories*").handler(BodyHandler.create());
        router.post("/FactoryModelService/factories").handler(factoryHandler::addFactories);
        router.post("/FactoryModelService/rents/:rentCode/factories").handler(factoryHandler::addFactories);
        router.delete("/FactoryModelService/factories/:factoryCode").handler(factoryHandler::deleteFactory);
        router.delete("/FactoryModelService/rents/:rentCode/factories/:factoryCode")
                .handler(factoryHandler::deleteFactory);
        router.put("/FactoryModelService/factories/:factoryCode").handler(factoryHandler::updateFactory);
        router.put("/FactoryModelService/rents/:rentCode/factories/:factoryCode").handler(factoryHandler::updateFactory);

        // ????????????
        ConnectionsHandler connectionsHandler = (ConnectionsHandler) context.getBean("connectionsHandler");
        router.get("/FactoryModelService/factories/:factoryCode/connections")
                .handler(connectionsHandler::getConnectionsByFactoryCode);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/connections")
                .handler(connectionsHandler::getConnectionsByFactoryCode);
        router.get(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections")
                .handler(connectionsHandler::getConnections);
        router.get(
                "/FactoryModelService/rents/:rentCode/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections")
                .handler(connectionsHandler::getConnections);
        router.get(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections/:connectionsCode")
                .handler(connectionsHandler::getConnectionsByCode);
        router.get(
                "/FactoryModelService/rents/:rentCode/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections/:connectionsCode")
                .handler(connectionsHandler::getConnectionsByCode);
        router.post(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections")
                .handler(connectionsHandler::addConnections);
        router.put(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections/:connectionsCode")
                .handler(connectionsHandler::updateConnections);
        router.delete(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:conParentType/:sourceCode/connections/:connectionsCode")
                .handler(connectionsHandler::deleteConnectionsByCode);
        // router.delete("/FactoryModelService/factories/:factoryCode/:warehouses/:warehouseId/:parentType/:equipmentId/connections").handler(connectionsHandler::deleteConnectionsByIds);

        // ????????????
        RelationsHandler relationsHandler = (RelationsHandler) context.getBean("relationsHandler");
        // router.route("/FactoryModelService/factories/:factoryCode/:warehouses/:warehouseId/equipments/:equipmentId/relations*").handler(BodyHandler.create());
        router.get("/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations")
                .handler(relationsHandler::getRelations);
        router.get(
                "/FactoryModelService/rents/:rentCode/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations")
                .handler(relationsHandler::getRelations);
        router.get(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations/:relationsCode")
                .handler(relationsHandler::getRelationsByCode);
        router.get(
                "/FactoryModelService/rents/:rentCode/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations/:relationsCode")
                .handler(relationsHandler::getRelationsByCode);
        // ??????????????????????????????????????????:????????????????????????id????????????id,sourceType=measurements
        // $idList=????????????id??????
        // ??????????????????????????????????????????)
        router.get("/FactoryModelService/relations/:sourceType").handler(relationsHandler::getRelationsForPM);
        router.get("/FactoryModelService/rents/:rentCode/relations/:sourceType")
                .handler(relationsHandler::getRelationsForPM);
        // ??????????????????
        // router.post("/FactoryModelService/relations").handler(relationsHandler::addRelations);
        router.post("/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations")
                .handler(relationsHandler::addRelations);
        router.put(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations/:relationsCode")
                .handler(relationsHandler::updateRelations);
        router.delete(
                "/FactoryModelService/factories/:factoryCode/:areas/:areasCode/:sourceType/:sourceCode/relations/:relationsCode")
                .handler(relationsHandler::deleteRelationsByCode);
        // router.delete("/FactoryModelService/factories/:factoryCode/warehouses/:warehouseId/:parentType1/:parentCode/relations").handler(relationsHandler::deleteRelationsByIds);

        // ??????
        TankAreaHandler tankAreaHandler = (TankAreaHandler) context.getBean("tankAreaHandler");
        router.get("/FactoryModelService/orgs/:orgCode/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/orgs/:orgCode/tankAreas/").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/tankAreas/").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/tankAreas/").handler(tankAreaHandler::getTankAreas);

        router.get("/FactoryModelService/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tankAreas").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/tankAreas/").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/tankAreas/").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tankAreas/").handler(tankAreaHandler::getTankAreas);

        router.get("/FactoryModelService/orgs/:orgCode/tankAreas/:tankAreaCode").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/tankAreas/:tankAreaCode").handler(tankAreaHandler::getTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/tankAreas/:tankAreaCode").handler(tankAreaHandler::getTankAreas);
        router.route("/FactoryModelService/orgs/:factoryCode/tankAreas*").handler(BodyHandler.create());

        NewTankHandler newTankHandler = (NewTankHandler) context.getBean("newTankHandler");
        router.get("/FactoryModelService/newTanks").handler(newTankHandler::getTanks);
        router.get("/FactoryModelService/newTanks").handler(newTankHandler::getTanks);

        // ???
        TankHandler tankHandler = (TankHandler) context.getBean("tankHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/tanks").handler(tankHandler::getTanks);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/tanks").handler(tankHandler::getTanks);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/tanks/").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/tanks/").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/tanks/").handler(tankHandler::getTanks);

        router.get("/FactoryModelService/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/tanks").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tanks").handler(tankHandler::getTanks);


        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/tanks").handler(tankHandler::getTanks);


        router.get("/FactoryModelService/tanks/").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/tanks/").handler(tankHandler::getTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tanks/").handler(tankHandler::getTanks);

        // ?????????
        // TankTypeDictHandler tankTypeDictHandler = (TankTypeDictHandler)
        // context.getBean("tankTypeDictHandler");
        // router.get("/FactoryModelService/tankTypeDicts").handler(tankTypeDictHandler::getTankTypeDices);

        // ??????
        SiloHandler siloHandler = (SiloHandler) context.getBean("siloHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/silos").handler(siloHandler::getSilos);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/silos").handler(siloHandler::getSilos);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/silos/").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/silos/").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/silos/").handler(siloHandler::getSilos);

        router.get("/FactoryModelService/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/silos").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/silos").handler(siloHandler::getSilos);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/silos").handler(siloHandler::getSilos);


        router.get("/FactoryModelService/silos/").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/silos/").handler(siloHandler::getSilos);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/silos/").handler(siloHandler::getSilos);

        router.route("/FactoryModelService/orgs/:factoryCode/:parentType/:areaCode/silos*")
                .handler(BodyHandler.create());
        router.route("/FactoryModelService/rents/:rentCode/orgs/:factoryCode/:parentType/:areaCode/silos*")
                .handler(BodyHandler.create());
        // ??????
        StockHandler stockHandler = (StockHandler) context.getBean("stockHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/stocks").handler(stockHandler::getStocks);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/stocks").handler(stockHandler::getStocks);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/stocks/").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/stocks/").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/stocks/").handler(stockHandler::getStocks);

        router.get("/FactoryModelService/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/stocks").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stocks").handler(stockHandler::getStocks);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/stocks").handler(stockHandler::getStocks);


        router.get("/FactoryModelService/stocks/").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/stocks/").handler(stockHandler::getStocks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stocks/").handler(stockHandler::getStocks);

        // ??????
        PlantHandler plantHandler = (PlantHandler) context.getBean("plantHandler");
        router.get("/FactoryModelService/orgs/:orgCode/plants").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/plants").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/plants").handler(plantHandler::getPlants);

        router.get("/FactoryModelService/orgs/:orgCode/plants/").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/plants/").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/plants/").handler(plantHandler::getPlants);

        router.get("/FactoryModelService/plants").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/plants").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/plants").handler(plantHandler::getPlants);

        router.get("/FactoryModelService/plants/").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/plants/").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/plants/").handler(plantHandler::getPlants);

        router.get("/FactoryModelService/orgs/:orgCode/plants/:plantCode").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/plants/:plantCode").handler(plantHandler::getPlants);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/plants/:plantCode").handler(plantHandler::getPlants);
        router.route("/FactoryModelService/orgs/:factoryCode/plants*").handler(BodyHandler.create());
        router.route("/FactoryModelService/plants").handler(BodyHandler.create());

        // ?????????
        AdministrationHandler administrationHandler = (AdministrationHandler) context.getBean("administrationHandler");
        router.get("/FactoryModelService/orgs/:orgCode/administrations").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/administrations").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/administrations").handler(administrationHandler::getAdministrations);

        router.get("/FactoryModelService/orgs/:orgCode/administrations/").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/administrations/").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/administrations/").handler(administrationHandler::getAdministrations);

        router.get("/FactoryModelService/orgs/:orgCode/administrations/:administrationCode").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/administrations/:administrationCode").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/administrations/:administrationCode").handler(administrationHandler::getAdministrations);

        router.get("/FactoryModelService/administrations").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/administrations").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/administrations").handler(administrationHandler::getAdministrations);

        router.get("/FactoryModelService/administrations/").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/administrations/").handler(administrationHandler::getAdministrations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/administrations/").handler(administrationHandler::getAdministrations);

        router.route("/FactoryModelService/orgs/:orgCode/administrations*").handler(BodyHandler.create());
        router.route("/FactoryModelService/administrations*").handler(BodyHandler.create());
        // ??????
        EquipmentHandler equipmentHandler = (EquipmentHandler) context.getBean("equipmentHandler");
        // router.route("/FactoryModelService/factories/:factoryCode/:parentType/:warehouseId/equipments*").handler(BodyHandler.create());
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/equipments").handler(equipmentHandler::getEquipments);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/equipments").handler(equipmentHandler::getEquipments);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/equipments/").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/equipments/").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/equipments/").handler(equipmentHandler::getEquipments);

        router.get("/FactoryModelService/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/equipments").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/equipments").handler(equipmentHandler::getEquipments);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/equipments").handler(equipmentHandler::getEquipments);


        router.get("/FactoryModelService/equipments/").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/equipments/").handler(equipmentHandler::getEquipments);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/equipments/").handler(equipmentHandler::getEquipments);

        // ??????????????????links
        router.route("/FactoryModelService/orgs/:factoryCode/:parentType/:areaCode/equipments*")
                .handler(BodyHandler.create());

        // ??????
        PipeNetworkHandler pipeNetworkHandler = (PipeNetworkHandler) context.getBean("pipeNetworkHandler");
        router.get("/FactoryModelService/orgs/:orgCode/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);

        router.get("/FactoryModelService/orgs/:orgCode/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);

        router.get("/FactoryModelService/orgs/:orgCode/pipeNetworks/:pipeNetworkCode").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/pipeNetworks/:pipeNetworkCode").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/pipeNetworks/:pipeNetworkCode").handler(pipeNetworkHandler::getPipeNetworkes);

        router.get("/FactoryModelService/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/pipeNetworks").handler(pipeNetworkHandler::getPipeNetworkes);

        router.get("/FactoryModelService/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/pipeNetworks/").handler(pipeNetworkHandler::getPipeNetworkes);

        router.route("/FactoryModelService/orgs/:orgCode/pipeNetworks*").handler(BodyHandler.create());
        router.route("/FactoryModelService/pipeNetworks*").handler(BodyHandler.create());

        // ?????????
        LoadingDockHandler loadingDockHandler = (LoadingDockHandler) context.getBean("loadingDockHandler");
        router.get("/FactoryModelService/orgs/:orgCode/loadingDocks").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/loadingDocks").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/loadingDocks").handler(loadingDockHandler::getLoadingDockes);

        router.get("/FactoryModelService/orgs/:orgCode/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);

        router.get("/FactoryModelService/orgs/:orgCode/loadingDocks/:loadingDockCode").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/loadingDocks/:loadingDockCode").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/loadingDocks/:loadingDockCode").handler(loadingDockHandler::getLoadingDockes);

        router.get("/FactoryModelService/loadingDocks").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/loadingDocks").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/loadingDocks").handler(loadingDockHandler::getLoadingDockes);

        router.get("/FactoryModelService/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/loadingDocks/").handler(loadingDockHandler::getLoadingDockes);

        router.route("/FactoryModelService/orgs/:orgCode/loadingDocks*").handler(BodyHandler.create());

        // ??????
        EntityHandler entityHandler = (EntityHandler) context.getBean("entityHandler");
        router.get("/FactoryModelService/entities").handler(entityHandler::getEntities);
        router.get("/FactoryModelService/rents/:rentCode/entities").handler(entityHandler::getEntities);
        router.get("/FactoryModelService/entities/").handler(entityHandler::getEntities);
        router.get("/FactoryModelService/rents/:rentCode/entities/").handler(entityHandler::getEntities);

        router.route("/FactoryModelService/entities*").handler(BodyHandler.create());
        router.post("/FactoryModelService/entities").handler(entityHandler::addEntity);
        router.get("/FactoryModelService/entities/:entityCode").handler(entityHandler::getEntity);
        router.get("/FactoryModelService/rents/:rentCode/entities/:entityCode").handler(entityHandler::getEntity);
        router.put("/FactoryModelService/entities/:entityCode").handler(entityHandler::updateEntity);
        router.delete("/FactoryModelService/entities/:entityCode").handler(entityHandler::delEntity);
        // ??????
        PropertyHandler propertyHandler = (PropertyHandler) context.getBean("propertyHandler");
        router.get("/FactoryModelService/entities/:entityCode/properties").handler(propertyHandler::getProperties);
        router.get("/FactoryModelService/rents/:rentCode/entities/:entityCode/properties")
                .handler(propertyHandler::getProperties);
        router.get("/FactoryModelService/entities/:entityCode/properties/").handler(propertyHandler::getProperties);
        router.get("/FactoryModelService/rents/:rentCode/entities/:entityCode/properties/")
                .handler(propertyHandler::getProperties);

        router.route("/FactoryModelService/entities/:entityCode/properties*").handler(BodyHandler.create());
        router.post("/FactoryModelService/entities/:entityCode/properties").handler(propertyHandler::addProperty);
        router.get("/FactoryModelService/entities/:entityCode/properties/:propertyCode")
                .handler(propertyHandler::getProperty);
        router.get("/FactoryModelService/rents/:rentCode/entities/:entityCode/properties/:propertyCode")
                .handler(propertyHandler::getProperty);
        router.put("/FactoryModelService/entities/:entityCode/properties/:propertyCode")
                .handler(propertyHandler::updateProperty);
        router.delete("/FactoryModelService/entities/:entityCode/properties/:propertyCode")
                .handler(propertyHandler::deleteProperty);
        //?????????
        AreaDictionaryHandler areaDictionaryHandler = (AreaDictionaryHandler) context.getBean("areaDictionaryHandler");
        router.get("/FactoryModelService/areaDictionaries").handler(areaDictionaryHandler::getAreaDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/areaDictionaries").handler(areaDictionaryHandler::getAreaDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/areaDictionaries").handler(areaDictionaryHandler::getAreaDictionaryTables);
        router.get("/FactoryModelService/areaDictionaries/").handler(areaDictionaryHandler::getAreaDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/areaDictionaries/").handler(areaDictionaryHandler::getAreaDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/areaDictionaries/").handler(areaDictionaryHandler::getAreaDictionaryTables);

        router.get("/FactoryModelService/workshops/:orgCode/areaDictionaries").handler(areaDictionaryHandler::getAreaDictionaryTables);

        // [?????????]3.8 ????????????
        StationHandler stationHandler = (StationHandler) context.getBean("stationHandler");
        router.get("/FactoryModelService/stations/:areaCode").handler(stationHandler::findAllStation);
        router.get("/FactoryModelService/stations").handler(stationHandler::findAllStation);

        router.get("/FactoryModelService/rents/:rentCode/stations/:areaCode").handler(stationHandler::findAllStation);
        router.get("/FactoryModelService/rents/:rentCode/stations").handler(stationHandler::findAllStation);

        //?????????
        NodeDictionaryHandler nodeDictionaryHandler = (NodeDictionaryHandler) context.getBean("nodeDictionaryHandler");
        router.get("/FactoryModelService/factories/:factoryCode/areaDictionaryTables/:areaDictionaryTableCode/nodeDictionaryTables").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/areaDictionaryTables/:areaDictionaryTableCode/nodeDictionaryTables").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/factories/:factoryCode/areaDictionaryTables/:areaDictionaryTableCode/nodeDictionaryTables/").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/areaDictionaryTables/:areaDictionaryTableCode/nodeDictionaryTables/").handler(nodeDictionaryHandler::getNodeDictionaryTables);

        router.get("/FactoryModelService/nodeDictionaries").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/:nodeCode").handler(nodeDictionaryHandler::getNodeDictionaryTables);

        router.get("/FactoryModelService/nodeDictionaries/").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/").handler(nodeDictionaryHandler::getNodeDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/").handler(nodeDictionaryHandler::getNodeDictionaryTables);

        router.route("/FactoryModelService/factories/:factoryCode/areaDictionaryTables*").handler(BodyHandler.create());
        // ?????????
        DictionaryTableHandler dictionaryTableHandler = (DictionaryTableHandler) context
                .getBean("dictionaryTableHandler");
        router.get("/FactoryModelService/dictionaryTables").handler(dictionaryTableHandler::getDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/dictionaryTables")
                .handler(dictionaryTableHandler::getDictionaryTables);
        router.get("/FactoryModelService/dictionaryTables/").handler(dictionaryTableHandler::getDictionaryTables);
        router.get("/FactoryModelService/rents/:rentCode/dictionaryTables/")
                .handler(dictionaryTableHandler::getDictionaryTables);

        router.route("/FactoryModelService/dictionaryTables*").handler(BodyHandler.create());
        router.post("/FactoryModelService/dictionaryTables").handler(dictionaryTableHandler::addDictionaryTable);
        router.get("/FactoryModelService/dictionaryTables/:dictionaryTableId")
                .handler(dictionaryTableHandler::getDictionaryTable);
        router.get("/FactoryModelService/rents/:rentCode/dictionaryTables/:dictionaryTableId")
                .handler(dictionaryTableHandler::getDictionaryTable);
        router.put("/FactoryModelService/dictionaryTables/:dictionaryTableId")
                .handler(dictionaryTableHandler::updateDictionaryTable);
        router.delete("/FactoryModelService/dictionaryTables/:dictionaryTableId")
                .handler(dictionaryTableHandler::deleteDictionaryTable);

        // ??????
        ValveHandler valveHandler = (ValveHandler) context.getBean("valveHandler");
        router.route("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/valves*").handler(BodyHandler.create());
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/valves").handler(valveHandler::getValves);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/valves/").handler(valveHandler::getValves);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/valves").handler(valveHandler::getValves);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/valves/").handler(valveHandler::getValves);

        router.get("/FactoryModelService/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/valves").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/valves").handler(valveHandler::getValves);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/valves").handler(valveHandler::getValves);


        router.get("/FactoryModelService/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/valves/").handler(valveHandler::getValves);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/valves/").handler(valveHandler::getValves);

        // ??????????????????links

        // ??????
        PlateHandler plateHandler = (PlateHandler) context.getBean("plateHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/plates").handler(plateHandler::getPlates);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/plates/").handler(plateHandler::getPlates);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/plates").handler(plateHandler::getPlates);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/plates/").handler(plateHandler::getPlates);

        router.get("/FactoryModelService/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/plates").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/plates").handler(plateHandler::getPlates);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/plates").handler(plateHandler::getPlates);


        router.get("/FactoryModelService/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/plates/").handler(plateHandler::getPlates);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/plates/").handler(plateHandler::getPlates);

        // ?????????
        OutletHandler outletHandler = (OutletHandler) context.getBean("outletHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/outlets").handler(outletHandler::getOutlets);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/outlets/").handler(outletHandler::getOutlets);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/outlets").handler(outletHandler::getOutlets);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/outlets/").handler(outletHandler::getOutlets);

        router.get("/FactoryModelService/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/outlets").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/outlets").handler(outletHandler::getOutlets);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/outlets").handler(outletHandler::getOutlets);


        router.get("/FactoryModelService/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/outlets/").handler(outletHandler::getOutlets);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/outlets/").handler(outletHandler::getOutlets);

        // ?????????
        SamplePointHandler samplePointHandler = (SamplePointHandler) context.getBean("samplePointHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/samplePoints/").handler(samplePointHandler::getSamplePoints);

        router.get("/FactoryModelService/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/mapSampleNodes/:nodeCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/mapSampleNodes/:nodeCode/samplePoints").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/samplePoints").handler(samplePointHandler::getSamplePoints);


        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/samplePoints").handler(samplePointHandler::getSamplePoints);


        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mapSampleNodes/:nodeCode/samplePoints").handler(samplePointHandler::getSamplePoints);

        router.get("/FactoryModelService/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/samplePoints/").handler(samplePointHandler::getSamplePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/samplePoints/").handler(samplePointHandler::getSamplePoints);

        // ??????
        TubulationHandler tubulationHandler = (TubulationHandler) context.getBean("tubulationHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations").handler(tubulationHandler::getTubulations);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/tubulations").handler(tubulationHandler::getTubulations);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/tubulations/").handler(tubulationHandler::getTubulations);

        router.get("/FactoryModelService/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/tubulations").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tubulations").handler(tubulationHandler::getTubulations);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/tubulations").handler(tubulationHandler::getTubulations);


        router.get("/FactoryModelService/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/tubulations/").handler(tubulationHandler::getTubulations);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tubulations/").handler(tubulationHandler::getTubulations);

        // ??????
        TeeHandler teeHandler = (TeeHandler) context.getBean("teeHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/tees").handler(teeHandler::getTees);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/tees").handler(teeHandler::getTees);

        router.get("/FactoryModelService/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/tees").handler(teeHandler::getTees);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tees").handler(teeHandler::getTees);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/tees").handler(teeHandler::getTees);


        router.route("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/tees*").handler(BodyHandler.create());

        // ????????????
        EdgePointHandler edgePointHandler = (EdgePointHandler) context.getBean("edgePointHandler");
        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);

        router.get("/FactoryModelService/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/edgePoints").handler(edgePointHandler::getEdgePoints);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);
        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/edgePoints").handler(edgePointHandler::getEdgePoints);

        router.getWithRegex("^\\/.*\\/edgePoints\\/\\w+$").handler(edgePointHandler::getEdgePointsByCodeLinks);
        router.route("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/edgePoints*")
                .handler(BodyHandler.create());

        // ??????
        MaterialHandler materialHandler = (MaterialHandler) context.getBean("materialHandler");
        router.get("/FactoryModelService/materials").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/associatives/:mtrlCode/materials").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/materials").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/associatives/:mtrlCode/materials").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/materials").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/associatives/:mtrlCode/materials").handler(materialHandler::getMaterials);

        router.get("/FactoryModelService/materials/:mtrlCode").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/materials/:mtrlCode").handler(materialHandler::getMaterials);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/materials/:mtrlCode").handler(materialHandler::getMaterials);
        // ??????????????????????????????
        // router.get("/FactoryModelService/relmaterials").handler(materialHandler::getRelMaterials);
        // router.getWithRegex("/FactoryModelService/.*/materials").handler(materialHandler::getLinkMaterials);
        // router.getWithRegex("/FactoryModelService/.*/materials/.*").handler(materialHandler::getLinkMaterials);
        router.route("/FactoryModelService/materials*").handler(BodyHandler.create());
        // router.post("/FactoryModelService/materials").handler(materialHandler::addMaterial);
        // router.put("/FactoryModelService/materials/:mtrlCode").handler(materialHandler::updateMaterial);
        // router.delete("/FactoryModelService/materials/:mtrlCode").handler(materialHandler::deleteMaterial);

        // ??????
        WarehouseHandler warehouseHandler = (WarehouseHandler) context.getBean("warehouseHandler");
        router.route("/FactoryModelService/orgs/:factoryCode/warehouses*").handler(BodyHandler.create());
        router.get("/FactoryModelService/orgs/:orgCode/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/orgs/:orgCode/warehouses/").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/warehouses/").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/warehouses/").handler(warehouseHandler::getWarehouses);

        router.get("/FactoryModelService/orgs/:orgCode/warehouses/:warehouseCode").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/warehouses/:warehouseCode").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/warehouses/:warehouseCode").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/warehouses").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/warehouses/").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/warehouses/").handler(warehouseHandler::getWarehouses);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/warehouses/").handler(warehouseHandler::getWarehouses);

        router.route("/FactoryModelService/warehouses*").handler(BodyHandler.create());
        // ?????????
        CommunityHandler communityHandler = (CommunityHandler) context.getBean("communityHandler");
        router.get("/FactoryModelService/orgs/:orgCode/communities").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/communities").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/communities").handler(communityHandler::getCommunities);

        router.get("/FactoryModelService/orgs/:orgCode/communities/").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/communities/").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/communities/").handler(communityHandler::getCommunities);

        router.get("/FactoryModelService/communities").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/communities").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/communities").handler(communityHandler::getCommunities);

        router.get("/FactoryModelService/orgs/:orgCode/communities/:communityCode").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/communities/:communityCode").handler(communityHandler::getCommunities);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/communities/:communityCode").handler(communityHandler::getCommunities);
        router.route("/FactoryModelService/orgs/:orgCode/communities/").handler(BodyHandler.create());
        router.route("/FactoryModelService/communities*").handler(BodyHandler.create());
        // ????????????
//		FactorySiteHandler factorySiteHandler = (FactorySiteHandler) context.getBean("factorySiteHandler");
//		router.get("/FactoryModelService/factories/:factoryCode/factorySites")
//				.handler(factorySiteHandler::getFactorySites);
//		router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/factorySites")
//				.handler(factorySiteHandler::getFactorySites);
//		router.get("/FactoryModelService/factories/:factoryCode/factorySites/:factorySiteCode")
//				.handler(factorySiteHandler::getFactorySiteByCode);
//		router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/factorySites/:factorySiteCode")
//				.handler(factorySiteHandler::getFactorySiteByCode);
//		router.route("/FactoryModelService/factories/:factoryCode/factorySites*").handler(BodyHandler.create());
//		router.route("/FactoryModelService/factories/:factoryCode/factorySites*").handler(BodyHandler.create());
//		router.post("/FactoryModelService/factories/:factoryCode/factorySites")
//				.handler(factorySiteHandler::addFactorySite);
//		router.route("/FactoryModelService/factorySites*").handler(BodyHandler.create());
//		router.put("/FactoryModelService/factories/:factoryCode/factorySites/:factorySiteCode")
//				.handler(factorySiteHandler::updateFactorySite);
//		router.delete("/FactoryModelService/factories/:factoryCode/factorySites/:factorySiteCode")
//				.handler(factorySiteHandler::deleteFactorySite);
        // ??????
        SideLineHandler sideLineHandler = (SideLineHandler) context.getBean("sideLineHandler");
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines/:nodeCode").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines/:nodeCode").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines/:nodeCode").handler(sideLineHandler::getSideLines);
        // ????????????????????????id???nodeId???????????????
        router.get("/FactoryModelService/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/sideLines").handler(sideLineHandler::getSideLines);

        //link
        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);

        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/areas/:areaCode/sideLines").handler(sideLineHandler::getSideLines);

        router.get("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines").handler(sideLineHandler::getSideLines);

        router.getWithRegex("/FactoryModelService/.*/SideLines").handler(sideLineHandler::getLinkSideLines);
        router.route("/FactoryModelService/orgs/:orgCode/:areaTypeCode/:areaCode/sideLines*").handler(BodyHandler.create());
        /*// ????????????
        MeasurementHandler measurementHandler = (MeasurementHandler) context.getBean("measurementHandler");
        router.get("/FactoryModelService/measurements").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode/measurements").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/measurements").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/measurements").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/measurements").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/:nodeCode/measurements").handler(measurementHandler::getMeasurements);

        router.get("/FactoryModelService/measurements/:idxCode").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode/measurements/:idxCode").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/measurements/:idxCode").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/measurements/:idxCode").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/measurements/:idxCode").handler(measurementHandler::getMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/:nodeCode/measurements/:idxCode").handler(measurementHandler::getMeasurements);*/

        // [?????????]5 ????????????*
        MeasurementHandler measurementHandler = (MeasurementHandler) context.getBean("measurementHandler");
        router.get("/FactoryModelService/measurements/:idxCode").handler(measurementHandler::findMeasurements);
        router.get("/FactoryModelService/measurements").handler(measurementHandler::findMeasurements);

        router.get("/FactoryModelService/rents/:rentCode/measurements/:idxCode").handler(measurementHandler::findMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/measurements").handler(measurementHandler::findMeasurements);

        // ?????????????????????????????????
        // router.get("/FactoryModelService/relMeasurements").handler(measurementHandler::getRelMeasurements);

        router.route("/FactoryModelService/measurements*").handler(BodyHandler.create());
        // router.post("/FactoryModelService/measurements").handler(measurementHandler::addMeasurement);
        router.route("/FactoryModelService/.*/measurements*").handler(BodyHandler.create());
        // router.put("/FactoryModelService/measurements/:idxCode").handler(measurementHandler::updateMeasurement);
        // router.delete("/FactoryModelService/measurements/:idxCode").handler(measurementHandler::deleteMeasurement);

        // ????????????

        // OrganizationHandler organizationHandler = (OrganizationHandler)
        // context.getBean("organizationHandler");
        // router.get("/FactoryModelService/orgs").handler(organizationHandler::getOrgs);
        // router.get("/FactoryModelService/orgs/:orgId").handler(organizationHandler::getOrgId);
        // router.route("/FactoryModelService/orgs*").handler(BodyHandler.create());
        // router.post("/FactoryModelService/orgs").handler(organizationHandler::addOrg);
        // router.put("/FactoryModelService/orgs/:orgId").handler(organizationHandler::updateOrg);
        // router.delete("/FactoryModelService/orgs/:orgId").handler(organizationHandler::deleteOrg);
        // // APP
        // AppHandler appHandler = (AppHandler) context.getBean("appHandler");
        // router.get("/FactoryModelService/apps").handler(appHandler::findPageApp);
        // router.get("/FactoryModelService/apps/:appId").handler(appHandler::findAppById);
        // router.route("/FactoryModelService/apps*").handler(BodyHandler.create());
        // router.post("/FactoryModelService/apps").handler(appHandler::saveApp);
        // router.put("/FactoryModelService/apps/:appId").handler(appHandler::updateApp);
        // router.delete("/FactoryModelService/apps/:appId").handler(appHandler::deleteApp);
        // ????????????
        // OrganizationUnitHandler organizationUnitHandler =
        // (OrganizationUnitHandler) context
        // .getBean("organizationUnitHandler");
        // router.route("/FactoryModelService/orgs/:orgId/orgUnits*").handler(BodyHandler.create());
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits").handler(organizationUnitHandler::getOrgUnits);
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId").handler(organizationUnitHandler::getOrgUnitId);
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/nodes").handler(organizationUnitHandler::getNodes);//
        // ???????????????
        // // zhaozhenqiang
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/nodes/:nodeId").handler(organizationUnitHandler::getNodesByNodeId);//
        // ???????????????
        // router.post("/FactoryModelService/orgs/:orgId/orgUnits").handler(organizationUnitHandler::addOrgUnit);
        // router.put("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId").handler(organizationUnitHandler::updateOrgUnit);
        // router.delete("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId").handler(organizationUnitHandler::deleteOrgUnit);

        // ?????????????????? ????????????????????????
        // OrgUnitRelationHandler orgUnitRelationHandler =
        // (OrgUnitRelationHandler) context.getBean("orgUnitRelationHandler");
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations").handler(orgUnitRelationHandler::getOrgUnitRelations);
        // router.get("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations/:orgUnitRelationId").handler(orgUnitRelationHandler::getOrgUnitRelationById);
        // router.post("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations").handler(orgUnitRelationHandler::addOrgUnitRelations);
        // router.put("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations/:orgUnitRelationId").handler(orgUnitRelationHandler::updateOrgUnitRelation);
        // router.delete("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations").handler(orgUnitRelationHandler::deleteOrgUnitRelationsByIds);
        // router.delete("/FactoryModelService/orgs/:orgId/orgUnits/:orgUnitId/orgUnitRelations/:orgUnitRelationId").handler(orgUnitRelationHandler::deleteOrgUnitRelationById);

        // ??????????????????
        OrgRelationHandler orgRelationHandler = (OrgRelationHandler) context.getBean("orgRelationHandler");
        router.route("/FactoryModelService/factories/:factoryCode/orgRelations*").handler(BodyHandler.create());
        router.get("/FactoryModelService/factories/:factoryCode/orgRelations")
                .handler(orgRelationHandler::getOrgRelations);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/orgRelations")
                .handler(orgRelationHandler::getOrgRelations);
        router.get("/FactoryModelService/factories/:factoryCode/orgRelations/:orgRelationCode")
                .handler(orgRelationHandler::getOrgRelationByCode);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/orgRelations/:orgRelationCode")
                .handler(orgRelationHandler::getOrgRelationByCode);
        router.post("/FactoryModelService/factories/:factoryCode/orgRelations")
                .handler(orgRelationHandler::addOrgRelations);
        router.put("/FactoryModelService/factories/:factoryCode/orgRelations/:orgRelationCode")
                .handler(orgRelationHandler::updateOrgRelation);
        router.delete("/FactoryModelService/factories/:factoryCode/orgRelations")
                .handler(orgRelationHandler::deleteOrgRelationsByCodes);
        router.delete("/FactoryModelService/factories/:factoryCode/orgRelations/:orgRelationCode")
                .handler(orgRelationHandler::deleteOrgRelationByCode);

        // ?????????????????????id?????????????????????
        NodeAndAreaHandler nodeAndAreaHandler = (NodeAndAreaHandler) context.getBean("nodeAndAreaHandler");
        router.route("/FactoryModelService/factories/:factoryCode/nodeAndAreas*").handler(BodyHandler.create());
        router.get("/FactoryModelService/factories/:factoryCode/nodeAndAreas")
                .handler(nodeAndAreaHandler::getNodeAndAreas);
        router.get("/FactoryModelService/rents/:rentCode/factories/:factoryCode/nodeAndAreas")
                .handler(nodeAndAreaHandler::getNodeAndAreas);

        // ?????????????????????????????? ????????????????????????
        // OrgUnitViewRelationHandler orgUnitViewRelationHandler =
        // (OrgUnitViewRelationHandler)
        // context.getBean("orgUnitViewRelationHandler");
        // router.get(
        // "/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations").handler(orgUnitViewRelationHandler::getOrgUnitViewRelations);
        // router.get("/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations/:orgUnitViewRelationId").handler(orgUnitViewRelationHandler::getOrgUnitViewRelationById);
        // router.post("/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations").handler(orgUnitViewRelationHandler::addOrgUnitViewRelations);
        // router.put("/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations/:orgUnitViewRelationId").handler(orgUnitViewRelationHandler::updateOrgUnitViewRelation);
        // router.delete("/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations").handler(orgUnitViewRelationHandler::deleteOrgUnitViewRelationsByIds);
        // router.delete("/FactoryModelService/apps/:appId/orgViews/:orgViewId/:orgUnitsType/:orgUnitViewId/orgUnitViewRelations/:orgUnitViewRelationId").handler(orgUnitViewRelationHandler::deleteOrgUnitViewRelationById);

        // ??????userHandler
        // UserHandler userHandler = (UserHandler)
        // context.getBean("userHandler");
        // router.get("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents")
        // .handler(userHandler::getrents);
        // router.get("/FactoryModelService/rents/:rentCode/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents")
        // .handler(userHandler::getrents);
        // router.get("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents/:rentCode")
        // .handler(userHandler::getUser);
        // router.get("/FactoryModelService/rents/:rentCode/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents/:rentCode")
        // .handler(userHandler::getUser);
        // router.route("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents*")
        // .handler(BodyHandler.create());
        // router.post("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents")
        // .handler(userHandler::addUser);
        // router.put("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents/:rentCode")
        // .handler(userHandler::updateUser);
        // router.delete("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/rents/:rentCode")
        // .handler(userHandler::deleteUser);

        // ????????????-??????
        PostionHandler postionHandler = (PostionHandler) context.getBean("postionHandler");
        router.get("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/postions")
                .handler(postionHandler::getPostions);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgId/:orgUnitsParentType/:orgUnitId/postions")
                .handler(postionHandler::getPostions);
        router.get("/FactoryModelService/orgs/:orgId/:orgUnitsParentType/:orgUnitId/postions/:postionId")
                .handler(postionHandler::getPostion);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgId/:orgUnitsParentType/:orgUnitId/postions/:postionId")
                .handler(postionHandler::getPostion);
        // // ?????????????????? ????????????????????????
        // OrgViewHandler orgViewHandler = (OrgViewHandler)
        // context.getBean("orgViewHandler");
        // router.route("/FactoryModelService/apps/:appId/orgViews*").handler(BodyHandler.create());
        // router.get("/FactoryModelService/apps/:appId/orgViews").handler(orgViewHandler::getOrgViews);
        // router.get("/FactoryModelService/apps/:appId/orgViews/:orgViewId").handler(orgViewHandler::getOrgViewById);
        // router.post("/FactoryModelService/apps/:appId/orgViews").handler(orgViewHandler::addOrgViews);
        // router.put("/FactoryModelService/apps/:appId/orgViews/:orgViewId").handler(orgViewHandler::updateOrgView);
        // router.delete("/FactoryModelService/apps/:appId/orgViews").handler(orgViewHandler::deleteOrgViewByIds);
        // router.delete("/FactoryModelService/apps/:appId/orgViews/:orgViewId").handler(orgViewHandler::deleteOrgViewById);
        // //
        // // //???????????????????????? ????????????????????????
        // OrgUnitViewHandler orgUnitViewHandler = (OrgUnitViewHandler)
        // context.getBean("orgUnitViewHandler");
        // router.get("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews").handler(orgUnitViewHandler::getOrgUnitViews);
        // router.get("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews/:orgUnitViewId").handler(orgUnitViewHandler::getOrgUnitViewById);
        // router.post("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews").handler(orgUnitViewHandler::addOrgUnitViews);
        // router.put("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews/:orgUnitViewId").handler(orgUnitViewHandler::updateOrgUnitView);
        // router.delete("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews").handler(orgUnitViewHandler::deleteOrgUnitViewsByIds);
        // router.delete("/FactoryModelService/apps/:appId/orgViews/:orgViewId/orgUnitViews/:orgUnitViewId").handler(orgUnitViewHandler::deleteOrgUnitViewById);
        // ??????????????????

        // ??????
        DimensionHandler dimensionHandler = (DimensionHandler) context.getBean("dimensionHandler");
        router.get("/FactoryModelService/dimensions").handler(dimensionHandler::getDimensions);
        router.get("/FactoryModelService/rents/:rentCode/dimensions").handler(dimensionHandler::getDimensions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/dimensions").handler(dimensionHandler::getDimensions);


        router.get("/FactoryModelService/dimensions/:dimensionCode").handler(dimensionHandler::getDimensions);
        router.get("/FactoryModelService/rents/:rentCode/dimensions/:dimensionCode").handler(dimensionHandler::getDimensions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/dimensions/:dimensionCode").handler(dimensionHandler::getDimensions);
        router.route("/FactoryModelService/dimensions*").handler(BodyHandler.create());
        // router.post("/FactoryModelService/dimensions").handler(dimensionHandler::addDimension);
        // router.put("/FactoryModelService/dimensions/:dimensionCode").handler(dimensionHandler::updateDimension);
        // router.delete("/FactoryModelService/dimensions/:dimensionCode").handler(dimensionHandler::deleteDimension);
        // FactoryDictHandler factoryDictHandler = (FactoryDictHandler)
        // context.getBean("factoryDictHandler");
        // router.route("/FactoryModelService*").handler(BodyHandler.create());
        // ?????????????????????
        AreaDictGatherHandler areaDictGatherHandler = (AreaDictGatherHandler) context.getBean("areaDictGatherHandler");
        router.route("/FactoryModelService*").handler(BodyHandler.create());
        // ??????????????????
        router.get("/FactoryModelService/orgTypes/:code").handler(areaDictGatherHandler::getOrgTypes);
        router.get("/FactoryModelService/rents/:rentCode/orgTypes/:code").handler(areaDictGatherHandler::getOrgTypes);
        router.get("/FactoryModelService/orgTypes").handler(areaDictGatherHandler::getOrgTypes);
        router.get("/FactoryModelService/rents/:rentCode/orgTypes").handler(areaDictGatherHandler::getOrgTypes);
        // ????????????
        router.get("/FactoryModelService/areaTypes/:code").handler(areaDictGatherHandler::getAreaTypes);
        router.get("/FactoryModelService/rents/:rentCode/areaTypes/:code").handler(areaDictGatherHandler::getAreaTypes);
        router.get("/FactoryModelService/areaTypes").handler(areaDictGatherHandler::getAreaTypes);
        router.get("/FactoryModelService/rents/:rentCode/areaTypes").handler(areaDictGatherHandler::getAreaTypes);
        // ??????????????????
        router.get("/FactoryModelService/unitTypes/:code").handler(areaDictGatherHandler::getUnitTypes);
        router.get("/FactoryModelService/rents/:rentCode/unitTypes/:code").handler(areaDictGatherHandler::getUnitTypes);
        router.get("/FactoryModelService/unitTypes").handler(areaDictGatherHandler::getUnitTypes);
        router.get("/FactoryModelService/rents/:rentCode/unitTypes").handler(areaDictGatherHandler::getUnitTypes);
        // ??????????????????
        router.get("/FactoryModelService/technics/:code").handler(areaDictGatherHandler::getTechnics);
        router.get("/FactoryModelService/rents/:rentCode/technics/:code").handler(areaDictGatherHandler::getTechnics);
        router.get("/FactoryModelService/technics").handler(areaDictGatherHandler::getTechnics);
        router.get("/FactoryModelService/rents/:rentCode/technics").handler(areaDictGatherHandler::getTechnics);
        // ??????????????????
        router.get("/FactoryModelService/tankAreaTypes/:code").handler(areaDictGatherHandler::getTankAreaTypes);
        router.get("/FactoryModelService/rents/:rentCode/tankAreaTypes/:code")
                .handler(areaDictGatherHandler::getTankAreaTypes);
        router.get("/FactoryModelService/tankAreaTypes").handler(areaDictGatherHandler::getTankAreaTypes);
        router.get("/FactoryModelService/rents/:rentCode/tankAreaTypes").handler(areaDictGatherHandler::getTankAreaTypes);
        // ??????????????????
        router.get("/FactoryModelService/tankAreaTechnics/:code").handler(areaDictGatherHandler::getTankAreaTechnics);
        router.get("/FactoryModelService/rents/:rentCode/tankAreaTechnics/:code")
                .handler(areaDictGatherHandler::getTankAreaTechnics);
        router.get("/FactoryModelService/tankAreaTechnics").handler(areaDictGatherHandler::getTankAreaTechnics);
        router.get("/FactoryModelService/rents/:rentCode/tankAreaTechnics")
                .handler(areaDictGatherHandler::getTankAreaTechnics);
        // ??????????????????
        router.get("/FactoryModelService/warehouseTypes/:code").handler(areaDictGatherHandler::getWarehouseTypes);
        router.get("/FactoryModelService/rents/:rentCode/warehouseTypes/:code")
                .handler(areaDictGatherHandler::getWarehouseTypes);
        router.get("/FactoryModelService/warehouseTypes").handler(areaDictGatherHandler::getWarehouseTypes);
        router.get("/FactoryModelService/rents/:rentCode/warehouseTypes")
                .handler(areaDictGatherHandler::getWarehouseTypes);
        // ??????????????????
        router.get("/FactoryModelService/warehousTechnics/:code").handler(areaDictGatherHandler::getWarehousTechnics);
        router.get("/FactoryModelService/rents/:rentCode/warehousTechnics/:code")
                .handler(areaDictGatherHandler::getWarehousTechnics);
        router.get("/FactoryModelService/warehousTechnics").handler(areaDictGatherHandler::getWarehousTechnics);
        router.get("/FactoryModelService/rents/:rentCode/warehousTechnics")
                .handler(areaDictGatherHandler::getWarehousTechnics);
        // ?????????????????????
        router.get("/FactoryModelService/loadrackTechnics/:code").handler(areaDictGatherHandler::getLoadrackTechnics);
        router.get("/FactoryModelService/rents/:rentCode/loadrackTechnics/:code")
                .handler(areaDictGatherHandler::getLoadrackTechnics);
        router.get("/FactoryModelService/loadrackTechnics").handler(areaDictGatherHandler::getLoadrackTechnics);
        router.get("/FactoryModelService/rents/:rentCode/loadrackTechnics")
                .handler(areaDictGatherHandler::getLoadrackTechnics);
        // ??????????????????
        router.get("/FactoryModelService/pipenetTechnics/:code").handler(areaDictGatherHandler::getPipenetTechnics);
        router.get("/FactoryModelService/rents/:rentCode/pipenetTechnics/:code")
                .handler(areaDictGatherHandler::getPipenetTechnics);
        router.get("/FactoryModelService/pipenetTechnics").handler(areaDictGatherHandler::getPipenetTechnics);
        router.get("/FactoryModelService/rents/:rentCode/pipenetTechnics")
                .handler(areaDictGatherHandler::getPipenetTechnics);
        // ??????????????????
        router.get("/FactoryModelService/opeindexclasses/:code").handler(areaDictGatherHandler::getOpeindexclass);
        router.get("/FactoryModelService/rents/:rentCode/opeindexclasses/:code")
                .handler(areaDictGatherHandler::getOpeindexclass);
        router.get("/FactoryModelService/opeindexclasses").handler(areaDictGatherHandler::getOpeindexclass);
        router.get("/FactoryModelService/rents/:rentCode/opeindexclasses")
                .handler(areaDictGatherHandler::getOpeindexclass);
        // ??????????????????
        router.get("/FactoryModelService/controldeps/:code").handler(areaDictGatherHandler::getControldeps);
        router.get("/FactoryModelService/rents/:rentCode/controldeps/:code")
                .handler(areaDictGatherHandler::getControldeps);
        router.get("/FactoryModelService/controldeps").handler(areaDictGatherHandler::getControldeps);
        router.get("/FactoryModelService/rents/:rentCode/controldeps").handler(areaDictGatherHandler::getControldeps);
        // ???????????????????????????
        router.get("/FactoryModelService/staalgrs/:code").handler(areaDictGatherHandler::getStaalgrs);
        router.get("/FactoryModelService/rents/:rentCode/staalgrs/:code").handler(areaDictGatherHandler::getStaalgrs);
        router.get("/FactoryModelService/staalgrs").handler(areaDictGatherHandler::getStaalgrs);
        router.get("/FactoryModelService/rents/:rentCode/staalgrs").handler(areaDictGatherHandler::getStaalgrs);

        // ?????????????????????
        NodeDictGatherHandler nodeDictGatherHandler = (NodeDictGatherHandler) context.getBean("nodeDictGatherHandler");
        // router.route("/FactoryModelService*").handler(BodyHandler.create());
        // ????????????
        router.get("/FactoryModelService/nodeTypes/:code").handler(nodeDictGatherHandler::getNodeTypes);
        router.get("/FactoryModelService/rents/:rentCode/nodeTypes/:code").handler(nodeDictGatherHandler::getNodeTypes);
        router.get("/FactoryModelService/nodeTypes").handler(nodeDictGatherHandler::getNodeTypes);
        router.get("/FactoryModelService/rents/:rentCode/nodeTypes").handler(nodeDictGatherHandler::getNodeTypes);
        // ??????????????????
        router.get("/FactoryModelService/sidelineTypes/:code").handler(nodeDictGatherHandler::getSidelineTypes);
        router.get("/FactoryModelService/rents/:rentCode/sidelineTypes/:code")
                .handler(nodeDictGatherHandler::getSidelineTypes);
        router.get("/FactoryModelService/sidelineTypes").handler(nodeDictGatherHandler::getSidelineTypes);
        router.get("/FactoryModelService/rents/:rentCode/sidelineTypes").handler(nodeDictGatherHandler::getSidelineTypes);
        // ??????????????????
        router.get("/FactoryModelService/sideMtrlTypes/:code").handler(nodeDictGatherHandler::getSideMtrlTypes);
        router.get("/FactoryModelService/rents/:rentCode/sideMtrlTypes/:code")
                .handler(nodeDictGatherHandler::getSideMtrlTypes);
        router.get("/FactoryModelService/sideMtrlTypes").handler(nodeDictGatherHandler::getSideMtrlTypes);
        router.get("/FactoryModelService/rents/:rentCode/sideMtrlTypes").handler(nodeDictGatherHandler::getSideMtrlTypes);
        // ?????????
        router.get("/FactoryModelService/tankTypes/:code").handler(nodeDictGatherHandler::getTankTypes);
        router.get("/FactoryModelService/rents/:rentCode/tankTypes/:code").handler(nodeDictGatherHandler::getTankTypes);
        router.get("/FactoryModelService/tankTypes").handler(nodeDictGatherHandler::getTankTypes);
        router.get("/FactoryModelService/rents/:rentCode/tankTypes").handler(nodeDictGatherHandler::getTankTypes);
        // VCF?????????
        router.get("/FactoryModelService/vcfTypes/:code").handler(nodeDictGatherHandler::getVcfTypes);
        router.get("/FactoryModelService/rents/:rentCode/vcfTypes/:code").handler(nodeDictGatherHandler::getVcfTypes);
        router.get("/FactoryModelService/vcfTypes").handler(nodeDictGatherHandler::getVcfTypes);
        router.get("/FactoryModelService/rents/:rentCode/vcfTypes").handler(nodeDictGatherHandler::getVcfTypes);
        // ??????????????????
        router.get("/FactoryModelService/equTechnics/:code").handler(nodeDictGatherHandler::getEquTechnics);
        router.get("/FactoryModelService/rents/:rentCode/equTechnics/:code")
                .handler(nodeDictGatherHandler::getEquTechnics);
        router.get("/FactoryModelService/equTechnics").handler(nodeDictGatherHandler::getEquTechnics);
        router.get("/FactoryModelService/rents/:rentCode/equTechnics").handler(nodeDictGatherHandler::getEquTechnics);
        // ?????????????????????
        router.get("/FactoryModelService/transTypes/:code").handler(nodeDictGatherHandler::getTransTypes);
        router.get("/FactoryModelService/rents/:rentCode/transTypes/:code").handler(nodeDictGatherHandler::getTransTypes);
        router.get("/FactoryModelService/transTypes").handler(nodeDictGatherHandler::getTransTypes);
        router.get("/FactoryModelService/rents/:rentCode/transTypes").handler(nodeDictGatherHandler::getTransTypes);
        // ??????????????????
        router.get("/FactoryModelService/siloTypes/:code").handler(nodeDictGatherHandler::getSiloTypes);
        router.get("/FactoryModelService/rents/:rentCode/siloTypes/:code").handler(nodeDictGatherHandler::getSiloTypes);
        router.get("/FactoryModelService/siloTypes").handler(nodeDictGatherHandler::getSiloTypes);
        router.get("/FactoryModelService/rents/:rentCode/siloTypes").handler(nodeDictGatherHandler::getSiloTypes);
        // ?????????????????????
        router.get("/FactoryModelService/samplepointTypes/:code").handler(nodeDictGatherHandler::getSamplepointTypes);
        router.get("/FactoryModelService/rents/:rentCode/samplepointTypes/:code")
                .handler(nodeDictGatherHandler::getSamplepointTypes);
        router.get("/FactoryModelService/samplepointTypes").handler(nodeDictGatherHandler::getSamplepointTypes);
        router.get("/FactoryModelService/rents/:rentCode/samplepointTypes")
                .handler(nodeDictGatherHandler::getSamplepointTypes);
        // ???????????????
        router.get("/FactoryModelService/signboardTypes/:code").handler(nodeDictGatherHandler::getSignboardTypes);
        router.get("/FactoryModelService/rents/:rentCode/signboardTypes/:code")
                .handler(nodeDictGatherHandler::getSignboardTypes);
        router.get("/FactoryModelService/signboardTypes").handler(nodeDictGatherHandler::getSignboardTypes);
        router.get("/FactoryModelService/rents/:rentCode/signboardTypes")
                .handler(nodeDictGatherHandler::getSignboardTypes);
        // ????????????
        router.get("/FactoryModelService/idxTypes/:code").handler(nodeDictGatherHandler::getIdxTypes);
        router.get("/FactoryModelService/rents/:rentCode/idxTypes/:code").handler(nodeDictGatherHandler::getIdxTypes);
        router.get("/FactoryModelService/idxTypes").handler(nodeDictGatherHandler::getIdxTypes);
        router.get("/FactoryModelService/rents/:rentCode/idxTypes").handler(nodeDictGatherHandler::getIdxTypes);
        // ????????????
        router.get("/FactoryModelService/mtrlTypes/:code").handler(nodeDictGatherHandler::getMtrlTypes);
        router.get("/FactoryModelService/rents/:rentCode/mtrlTypes/:code").handler(nodeDictGatherHandler::getMtrlTypes);
        router.get("/FactoryModelService/mtrlTypes").handler(nodeDictGatherHandler::getMtrlTypes);
        router.get("/FactoryModelService/rents/:rentCode/mtrlTypes").handler(nodeDictGatherHandler::getMtrlTypes);

        // ?????????????????????
        StaalgrConfHandler staalgrConfHandler = (StaalgrConfHandler) context.getBean("staalgrConfHandler");
        router.get("/FactoryModelService/staalgrConf").handler(staalgrConfHandler::getStaalgrConfs);
        router.get("/FactoryModelService/rents/:rentCode/staalgrConf").handler(staalgrConfHandler::getStaalgrConfs);
        router.get("/FactoryModelService/staalgrConf/:code").handler(staalgrConfHandler::getStaalgrConf);
        router.get("/FactoryModelService/rents/:rentCode/staalgrConf/:code").handler(staalgrConfHandler::getStaalgrConf);
        // ????????????????????????
        StaalgrConfitemHandler staalgrConfitemHandler = (StaalgrConfitemHandler) context
                .getBean("staalgrConfitemHandler");
        router.get("/FactoryModelService/staalgrConfitem").handler(staalgrConfitemHandler::getStaalgrConfitems);
        router.get("/FactoryModelService/rents/:rentCode/staalgrConfitem")
                .handler(staalgrConfitemHandler::getStaalgrConfitems);
        router.get("/FactoryModelService/staalgrConfitem/:code").handler(staalgrConfitemHandler::getStaalgrConfitem);
        router.get("/FactoryModelService/rents/:rentCode/staalgrConfitem/:code")
                .handler(staalgrConfitemHandler::getStaalgrConfitem);
        // ????????????????????????
        UnitAlarmHandler unitAlarmHandler = (UnitAlarmHandler) context.getBean("unitAlarmHandler");
        router.get("/FactoryModelService/unitAlarm").handler(unitAlarmHandler::getUnitAlarms);
        router.get("/FactoryModelService/rents/:rentCode/unitAlarm").handler(unitAlarmHandler::getUnitAlarms);
        router.get("/FactoryModelService/unitAlarm/:code").handler(unitAlarmHandler::getUnitAlarm);
        router.get("/FactoryModelService/rents/:rentCode/unitAlarm/:code").handler(unitAlarmHandler::getUnitAlarm);
        // ????????????
        StormTankHandler stormTankHandler = (StormTankHandler) context.getBean("stormTankHandler");
        router.get("/FactoryModelService/tankCal").handler(stormTankHandler::getObject);
        router.get("/FactoryModelService/rents/:rentCode/tankCal").handler(stormTankHandler::getObject);
        // ????????????
        StormUnitHandler stormUnitHandler = (StormUnitHandler) context.getBean("stormUnitHandler");
        router.get("/FactoryModelService/unitCal").handler(stormUnitHandler::getTuple);
        router.get("/FactoryModelService/rents/:rentCode/unitCal").handler(stormUnitHandler::getTuple);
        // ????????????
        Openindexhandler openindexhandler = (Openindexhandler) context.getBean("openindexhandler");
        router.get("/FactoryModelService/openindex").handler(openindexhandler::getOpenindexs);
        router.get("/FactoryModelService/rents/:rentCode/openindex").handler(openindexhandler::getOpenindexs);
        router.get("/FactoryModelService/openindex/:openindexCode/deupdownlimit")
                .handler(openindexhandler::getDeupdownlimits);
        router.get("/FactoryModelService/rents/:rentCode/openindex/:openindexCode/deupdownlimit")
                .handler(openindexhandler::getDeupdownlimits);
        ApplicationContextTool.setContext(context);
        // ??????????????????????????????
        OrgFindAreaHandler orgFindAreaHandler = (OrgFindAreaHandler) context.getBean("orgFindAreaHandler");
        router.get("/FactoryModelService/orgs/:orgCode/areas").handler(orgFindAreaHandler::getOrgFindArea);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas")
                .handler(orgFindAreaHandler::getOrgFindArea);
        router.get("/FactoryModelService/orgs/:orgCode/areas/:areaCode")
                .handler(orgFindAreaHandler::getOrgFindAreaNodes);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/areas/:areaCode")
                .handler(orgFindAreaHandler::getOrgFindAreaNodes);
        // ???????????????????????????????????????????????????
        NodeIdxTypeHandler nodeIdxTypeHandler = (NodeIdxTypeHandler) context.getBean("nodeIdxTypeHandler");
        router.get("/FactoryModelService/IdxType").handler(nodeIdxTypeHandler::getIdxType);
        router.get("/FactoryModelService/rents/:rentCode/IdxType").handler(nodeIdxTypeHandler::getIdxType);

        TankBaseInfoHandler tankBaseInfoHandler = (TankBaseInfoHandler) context.getBean("tankBaseInfoHandler");
        router.get("/FactoryModelService/tankBaseInfo").handler(tankBaseInfoHandler::getTankBaseInfo);
        router.get("/FactoryModelService/rents/:rentCode/tankBaseInfo").handler(tankBaseInfoHandler::getTankBaseInfo);

        ManagementMtrlHandler managementMtrlHandler = (ManagementMtrlHandler) context.getBean("managementMtrlHandler");
        router.get("/FactoryModelService/management/managementMtrls")
                .handler(managementMtrlHandler::getAllManagementMtrls);
        router.get("/FactoryModelService/rents/:rentCode/management/managementMtrls")
                .handler(managementMtrlHandler::getAllManagementMtrls);

        ManagementTankAreaHandler managementTankAreaHandler = (ManagementTankAreaHandler) context
                .getBean("managementTankAreaHandler");
        router.get("/FactoryModelService/management/tankAreas")
                .handler(managementTankAreaHandler::getManagementTankAreas);
        router.get("/FactoryModelService/rents/:rentCode/management/tankAreas")
                .handler(managementTankAreaHandler::getManagementTankAreas);

        ManagementTankHandler managementTankHandler = (ManagementTankHandler) context.getBean("managementTankHandler");
        router.get("/FactoryModelService/management/managementTanks")
                .handler(managementTankHandler::getManagementTanks);
        router.get("/FactoryModelService/rents/:rentCode/management/managementTanks")
                .handler(managementTankHandler::getManagementTanks);

        //[????????????-????????????]7.1 ?????????????????????
        LiquidProdCoefHandler liquidProdCoefHandler = (LiquidProdCoefHandler) context.getBean("liquidProdCoefHandler");
        router.get("/FactoryModelService/liqprodCoefs").handler(liquidProdCoefHandler::findLiquidProdCoefs);
        router.get("/FactoryModelService/rents/:rentCode/liqprodCoefs").handler(liquidProdCoefHandler::findLiquidProdCoefs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/liqprodCoefs").handler(liquidProdCoefHandler::findLiquidProdCoefs);
        //[????????????-????????????]7.2 ??????????????????????????????
        LiqprodCubaTempCoefHandler liqprodCubaTempCoefHandler = (LiqprodCubaTempCoefHandler) context.getBean("liqprodCubaTempCoefHandler");
        router.get("/FactoryModelService/liqprodCubaTempCoefs").handler(liqprodCubaTempCoefHandler::findLiqprodCubaTempCoefs);
        router.get("/FactoryModelService/rents/:rentCode/liqprodCubaTempCoefs").handler(liqprodCubaTempCoefHandler::findLiqprodCubaTempCoefs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/liqprodCubaTempCoefs").handler(liqprodCubaTempCoefHandler::findLiqprodCubaTempCoefs);
        //[????????????-????????????]9 VCF???
        IcVcfHandler icVcfHandler = (IcVcfHandler) context.getBean("icVcfHandler");
        router.get("/FactoryModelService/vcfs").handler(icVcfHandler::findIcVcf);
        router.get("/FactoryModelService/rents/:rentCode/vcfs").handler(icVcfHandler::findIcVcf);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/vcfs").handler(icVcfHandler::findIcVcf);

        router.get("/FactoryModelService/VcfDegree").handler(icVcfHandler::getVcfDegree);
        router.get("/FactoryModelService/rents/:rentCode/VcfDegree").handler(icVcfHandler::getVcfDegree);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/VcfDegree").handler(icVcfHandler::getVcfDegree);


        //???????????????????????????
        TemppredenHandler temppredenHandler = (TemppredenHandler) context.getBean("temppredenHandler");
        router.get("/FactoryModelService/tempPerDens").handler(temppredenHandler::findTemppredens);
        router.get("/FactoryModelService/rents/:rentCode/tempPerDens").handler(temppredenHandler::findTemppredens);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tempPerDens").handler(temppredenHandler::findTemppredens);

        // ??????????????????
        FltperCuabHandler fltperCuabHandler = (FltperCuabHandler) context.getBean("fltperCuabHandler");
        router.get("/FactoryModelService/fltperCuabs").handler(fltperCuabHandler::getfltperCuabs);
        router.get("/FactoryModelService/rents/:rentCode/fltperCuabs").handler(fltperCuabHandler::getfltperCuabs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/fltperCuabs").handler(fltperCuabHandler::getfltperCuabs);

        // ??????????????????
        StdpresCoefHandler stdpresCoefHandler = (StdpresCoefHandler) context.getBean("stdpresCoefHandler");
        router.get("/FactoryModelService/stdpresCoefs").handler(stdpresCoefHandler::getStdpresCoefs);
        router.get("/FactoryModelService/rents/:rentCode/stdpresCoefs").handler(stdpresCoefHandler::getStdpresCoefs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stdpresCoefs").handler(stdpresCoefHandler::getStdpresCoefs);

        // ????????????
        SpclCuabHandler spclCuabHandler = (SpclCuabHandler) context.getBean("spclCuabHandler");
        router.get("/FactoryModelService/spclCuabs").handler(spclCuabHandler::getSpclCuabs);
        router.get("/FactoryModelService/rents/:rentCode/spclCuabs").handler(spclCuabHandler::getSpclCuabs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/spclCuabs").handler(spclCuabHandler::getSpclCuabs);

        // ??????????????????
        TempdenHandler tempdenHandler = (TempdenHandler) context.getBean("tempdenHandler");
        router.get("/FactoryModelService/tempdens").handler(tempdenHandler::getTempdens);
        router.get("/FactoryModelService/rents/:rentCode/tempdens").handler(tempdenHandler::getTempdens);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tempdens").handler(tempdenHandler::getTempdens);

        // ????????????????????????
        TempcondenHandler tempcondenHandler = (TempcondenHandler) context.getBean("tempcondenHandler");
        router.get("/FactoryModelService/tempcondens").handler(tempcondenHandler::getTempcondens);
        router.get("/FactoryModelService/rents/:rentCode/tempcondens").handler(tempcondenHandler::getTempcondens);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tempcondens").handler(tempcondenHandler::getTempcondens);

        //[????????????-????????????] 1.1 ????????????????????????
        CnfgTankHandler cnfgTankHandler = (CnfgTankHandler) context.getBean("cnfgTankHandler");
        router.get("/FactoryModelService/icCnfgTanks").handler(cnfgTankHandler::findCnfgTanks);
        router.get("/FactoryModelService/rents/:rentCode/icCnfgTanks").handler(cnfgTankHandler::findCnfgTanks);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/icCnfgTanks").handler(cnfgTankHandler::findCnfgTanks);

        // ???????????????????????????
        LieCubasHandler lieCubasHandler = (LieCubasHandler) context.getBean("lieCubasHandler");
        router.get("/FactoryModelService/lieCubas").handler(lieCubasHandler::getLieCubas);
        router.get("/FactoryModelService/rents/:rentCode/lieCubas").handler(lieCubasHandler::getLieCubas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/lieCubas").handler(lieCubasHandler::getLieCubas);

        //??????????????????????????????
        router.get("/FactoryModelService/lieCubaDegree").handler(lieCubasHandler::getLieCubaDegree);
        router.get("/FactoryModelService/rents/:rentCode/lieCubaDegree").handler(lieCubasHandler::getLieCubaDegree);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/lieCubaDegree").handler(lieCubasHandler::getLieCubaDegree);

        // ???????????????????????????
        GlbCubasHandler glbCubasHandler = (GlbCubasHandler) context.getBean("glbCubasHandler");
        router.get("/FactoryModelService/glbCubas").handler(glbCubasHandler::getGlbCubas);
        router.get("/FactoryModelService/rents/:rentCode/glbCubas").handler(glbCubasHandler::getGlbCubas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/glbCubas").handler(glbCubasHandler::getGlbCubas);
        //??????????????????????????????
        router.get("/FactoryModelService/glbCubaDegree").handler(glbCubasHandler::getGlbCubaDegree);
        router.get("/FactoryModelService/rents/:rentCode/glbCubaDegree").handler(glbCubasHandler::getGlbCubaDegree);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/glbCubaDegree").handler(glbCubasHandler::getGlbCubaDegree);


        // ???????????????????????????????????????
        StddmCuabsHandler stddmCuabsHandler = (StddmCuabsHandler) context.getBean("stddmCuabsHandler");
        router.get("/FactoryModelService/stddmCuabs").handler(stddmCuabsHandler::getStddmCuabs);
        router.get("/FactoryModelService/rents/:rentCode/stddmCuabs").handler(stddmCuabsHandler::getStddmCuabs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stddmCuabs").handler(stddmCuabsHandler::getStddmCuabs);
        // ???????????????????????????????????????
        StdcmmmCubasHandler stdcmmmCubasHandler = (StdcmmmCubasHandler) context.getBean("stdcmmmCubasHandler");
        router.get("/FactoryModelService/stdcmmmCubas").handler(stdcmmmCubasHandler::getStdcmmmCubas);
        router.get("/FactoryModelService/rents/:rentCode/stdcmmmCubas").handler(stdcmmmCubasHandler::getStdcmmmCubas);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stdcmmmCubas").handler(stdcmmmCubasHandler::getStdcmmmCubas);
        // ???????????????????????????
        StdSecHandler stdSecHandler = (StdSecHandler) context.getBean("stdSecHandler");
        router.get("/FactoryModelService/stdSecs").handler(stdSecHandler::getStdSec);
        router.get("/FactoryModelService/rents/:rentCode/stdSecs").handler(stdSecHandler::getStdSec);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stdSecs").handler(stdSecHandler::getStdSec);

        //[?????????]8.2 ????????????????????????
        PrdtcellMeasindexHandler prdtcellMeasindexHandler = (PrdtcellMeasindexHandler) context.getBean("prdtcellMeasindexHandler");
        router.get("/FactoryModelService/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/plants/:plantCode/prdtcells/:cellCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/prdtcells/:cellCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/orgs/:orgCode/plants/:plantCode/prdtcells/:cellCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);
        router.get("/FactoryModelService/orgs/:orgCode/plants/:plantCode/prdtcells/:cellCode/prdtCellMeasurements").handler(prdtcellMeasindexHandler::findAll);


        //[?????????]8.1 ????????????
        PrdtcellHandler prdtcellHandler = (PrdtcellHandler) context.getBean("prdtcellHandler");
        router.get("/FactoryModelService/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/orgs/:orgCode/plants/:plantCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/orgs/:orgCode/plants/:plantCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/plants/:plantCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/rents/:rentCode/plants/:plantCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/rents/:rentCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/prdtcells").handler(prdtcellHandler::getAllPrdtcell);

        //[???????????????]3?????????????????????????????????
        MapSampleNodeHandler mapSampleNodeHandler = (MapSampleNodeHandler) context.getBean("mapSampleNodeHandler");
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);
        router.get("/FactoryModelService/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);
        router.get("/FactoryModelService/rents/:rentCode/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/:nodeCode/mapSampleNodes").handler(mapSampleNodeHandler::getMapSampleNodes);

        //[???????????????]5?????????????????????
        AssociativesHandler associativesHandler = (AssociativesHandler) context.getBean("associativesHandler");
        router.get("/FactoryModelService/associatives").handler(associativesHandler::findAssociatives);
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode/associatives").handler(associativesHandler::findAssociatives);
        router.get("/FactoryModelService/rents/:rentCode/associatives").handler(associativesHandler::findAssociatives);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/associatives").handler(associativesHandler::findAssociatives);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/associatives").handler(associativesHandler::findAssociatives);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/nodeDictionaries/:nodeCode/associatives").handler(associativesHandler::findAssociatives);

//		//[???????????????]4.1 ??????????????????
//		NodeTopDTLHandler nodeTopDTLHandler = (NodeTopDTLHandler) context.getBean("nodeTopDTLHandler");
//		router.get("/FactoryModelService/nodetopDTL").handler(nodeTopDTLHandler::getNodeTopDTLs);
//		router.get("/FactoryModelService/nodeDictionaries/:nodeCode/nodetopDTL").handler(nodeTopDTLHandler::getNodeTopDTLs);
//		
//		router.get("/FactoryModelService/rents/:rentCode/nodetopDTL").handler(nodeTopDTLHandler::getNodeTopDTLs);
//		router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/nodetopDTL").handler(nodeTopDTLHandler::getNodeTopDTLs);

        //[???????????????]4.2  ?????????????????????
        NodeTopDTLHandler nodeTopDTLHandler = (NodeTopDTLHandler) context.getBean("nodeTopDTLHandler");
        router.get("/FactoryModelService/nodetopDTL").handler(nodeTopDTLHandler::findAll);
        router.get("/FactoryModelService/nodeDictionaries/:nodeCode/nodetopDTL").handler(nodeTopDTLHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/nodetopDTL").handler(nodeTopDTLHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/nodeDictionaries/:nodeCode/nodetopDTL").handler(nodeTopDTLHandler::findAll);

        //[?????????]10 ??????
        UserHandler userHandler = (UserHandler) context.getBean("userHandler");
        router.get("/FactoryModelService/user").handler(userHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/users").handler(userHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/users").handler(userHandler::findAll);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/teams/:orgCode/users").handler(userHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/teams/:orgCode/users").handler(userHandler::findAll);

        router.get("/FactoryModelService/orgs/:orgCode/users").handler(userHandler::findAll);

        //[?????????]????????????????????????
        TeamAndUserHandler teamAndUserHandler = (TeamAndUserHandler) context.getBean("teamAndUserHandler");
        router.get("/FactoryModelService/teamAndUsers").handler(teamAndUserHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/teamAndUsers").handler(teamAndUserHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/teamAndUsers").handler(teamAndUserHandler::findAll);
        router.get("/FactoryModelService/bizOrgMains/:bizCode/teams/:orgCode/teamAndUsers").handler(teamAndUserHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/teams/:orgCode/teamAndUsers").handler(teamAndUserHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/teams/:orgCode/teamAndUsers").handler(teamAndUserHandler::findAll);

        // [????????????-????????????]11?????????????????????
        MtrlMolarHandler mtrlMolarHandler = (MtrlMolarHandler) context.getBean("mtrlMolarHandler");
        router.get("/FactoryModelService/mtrlMolars").handler(mtrlMolarHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/mtrlMolars").handler(mtrlMolarHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mtrlMolars").handler(mtrlMolarHandler::getAll);

        //????????????????????????
        router.get("/FactoryModelService/mtrlMolarDegree").handler(mtrlMolarHandler::getMtrlMolarDegree);
        router.get("/FactoryModelService/rents/:rentCode/mtrlMolarDegree").handler(mtrlMolarHandler::getMtrlMolarDegree);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mtrlMolarDegree").handler(mtrlMolarHandler::getMtrlMolarDegree);

        // [????????????-????????????]12??????????????????
        GlbPreCoefHandler glbPreCoefHandler = (GlbPreCoefHandler) context.getBean("glbPreCoefHandler");
        router.get("/FactoryModelService/glbPreCoefs").handler(glbPreCoefHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/glbPreCoefs").handler(glbPreCoefHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/glbPreCoefs").handler(glbPreCoefHandler::getAll);

        // [????????????-????????????]14???????????????
        StanDenHandler stanDenHandler = (StanDenHandler) context.getBean("stanDenHandler");
        router.get("/FactoryModelService/stanDens").handler(stanDenHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/stanDens").handler(stanDenHandler::getAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stanDens").handler(mtrlMolarHandler::getAll);


        //?????????????????????
        IcStangasdenHandler icStangasdenHandler = context.getBean(IcStangasdenHandler.class);
        router.get("/FactoryModelService/stangasdens").handler(icStangasdenHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/stangasdens").handler(icStangasdenHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stangasdens").handler(icStangasdenHandler::findAll);

        router.get("/FactoryModelService/stangasdens/:stangasdenId").handler(icStangasdenHandler::findById);
        router.get("/FactoryModelService/rents/:rentCode/stangasdens/:stangasdenId").handler(icStangasdenHandler::findById);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/stangasdens/:stangasdenId").handler(icStangasdenHandler::findById);

        //16?????????????????????????????????
        IcMtrlFormCnfgHandler icMtrlFormCnfgHandler = context.getBean(IcMtrlFormCnfgHandler.class);
        router.get("/FactoryModelService/mtrlFormCnfgs").handler(icMtrlFormCnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/mtrlFormCnfgs").handler(icMtrlFormCnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mtrlFormCnfgs").handler(icMtrlFormCnfgHandler::findAll);


        router.get("/FactoryModelService/icMtrlFormCnfgs/:mtrlFormCnfgId").handler(icMtrlFormCnfgHandler::findById);
        router.get("/FactoryModelService/rents/:rentCode/mtrlFormCnfgs/:mtrlFormCnfgId").handler(icMtrlFormCnfgHandler::findById);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mtrlFormCnfgs/:mtrlFormCnfgId").handler(icMtrlFormCnfgHandler::findById);


        //[?????????]9?????????
        RentHandler rentHandler = (RentHandler) context.getBean("rentHandler");
        router.get("/FactoryModelService/rents").handler(rentHandler::getRents);
//		router.get("/FactoryModelService/rents/:rentName").handler(rentHandler::getRents);
        router.get("/FactoryModelService/rents/:rentCode").handler(rentHandler::getRents);
        router.get("/FactoryModelService/rents/:rentCode/rents").handler(rentHandler::getRents);


        // // [????????????-????????????]1 ????????????
        TankCnfgHandler tankCnfgHandler = (TankCnfgHandler)
                context.getBean("tankCnfgHandler");
        router.route("/FactoryModelService/tankCnfgs*").handler(BodyHandler.create());
        router.get("/FactoryModelService/tankCnfgs").handler(tankCnfgHandler::findAll);
        router.get("/FactoryModelService/tankCnfgs/:tankCnfgId").handler(tankCnfgHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/tankCnfgs").handler(tankCnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/tankCnfgs/:tankCnfgId").handler(tankCnfgHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tankCnfgs").handler(tankCnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/tankCnfgs/:tankCnfgId").handler(tankCnfgHandler::findAll);


        //17???????????????????????????
        DeltcnfgHandler deltcnfgHandler = (DeltcnfgHandler) context.getBean("deltcnfgHandler");
        router.route("/FactoryModelService/deltcnfgs*").handler(BodyHandler.create());
        router.get("/FactoryModelService/deltcnfgs").handler(deltcnfgHandler::findAll);
        router.get("/FactoryModelService/deltcnfgs/:deltcnfgId").handler(deltcnfgHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/deltcnfgs").handler(deltcnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/deltcnfgs/:deltcnfgId").handler(deltcnfgHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/deltcnfgs").handler(deltcnfgHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/deltcnfgs/:deltcnfgId").handler(deltcnfgHandler::findAll);


        //???????????????
        IcPipenettankCoefHandler icPipenettankCoefHandler = context.getBean(IcPipenettankCoefHandler.class);
        router.get("/FactoryModelService/pipenettankCoefs").handler(icPipenettankCoefHandler::findAll);
        router.get("/FactoryModelService/pipenettankCoefs/:pipenettankCoefId").handler(icPipenettankCoefHandler::findById);

        router.get("/FactoryModelService/rents/:rentCode/pipenettankCoefs").handler(icPipenettankCoefHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/pipenettankCoefs/:pipenettankCoefId").handler(icPipenettankCoefHandler::findById);

        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/pipenettankCoefs").handler(icPipenettankCoefHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/pipenettankCoefs/:pipenettankCoefId").handler(icPipenettankCoefHandler::findById);


        //???????????????????????????

        AirDenModCoefHandler airDenModCoefHandler = (AirDenModCoefHandler) context.getBean("airDenModCoefHandler");
        router.route("/FactoryModelService/airDenModCoefs*").handler(BodyHandler.create());
        router.get("/FactoryModelService/airDenModCoefs").handler(airDenModCoefHandler::getAirDenModCoef);
        router.get("/FactoryModelService/rents/:rentCode/airDenModCoefs").handler(airDenModCoefHandler::getAirDenModCoef);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/airDenModCoefs").handler(airDenModCoefHandler::getAirDenModCoef);

        //??????????????????VCF???
        MtrlVcfHandler mtrlVcfHandler = (MtrlVcfHandler) context.getBean("mtrlVcfHandler");
        router.route("/FactoryModelService/mtrlVcfs*").handler(BodyHandler.create());
        router.get("/FactoryModelService/mtrlVcfs").handler(mtrlVcfHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/mtrlVcfs").handler(mtrlVcfHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/mtrlVcfs").handler(mtrlVcfHandler::findAll);

        //??????
        PositionHandler positionHandler = context.getBean(PositionHandler.class);
        router.get("/FactoryModelService/positions").handler(positionHandler::getPositions);
        router.get("/FactoryModelService/rents/:rentCode/positions").handler(positionHandler::getPositions);

        //?????????????????????
        PositionOrgHandler positionOrgHandler = context.getBean(PositionOrgHandler.class);
        router.get("/FactoryModelService/positionOrgs").handler(positionOrgHandler::getPositionOrgs);
        router.get("/FactoryModelService/rents/:rentCode/positionOrgs").handler(positionOrgHandler::getPositionOrgs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/positionOrgs").handler(positionOrgHandler::getPositionOrgs);

        router.get("/FactoryModelService/orgs/:orgCode/positionOrgs").handler(positionOrgHandler::getPositionOrgs);
        router.get("/FactoryModelService/rents/:rentCode/orgs/:orgCode/positionOrgs").handler(positionOrgHandler::getPositionOrgs);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/orgs/:orgCode/positionOrgs").handler(positionOrgHandler::getPositionOrgs);

        //???????????????????????????
        UserPositionHandler userPositionHandler = context.getBean(UserPositionHandler.class);
        router.get("/FactoryModelService/userPositions").handler(userPositionHandler::getUserPositions);
        router.get("/FactoryModelService/rents/:rentCode/userPositions").handler(userPositionHandler::getUserPositions);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/userPositions").handler(userPositionHandler::getUserPositions);

        //?????????????????????
        TPmOrgHandler tPmOrgHandler = context.getBean(TPmOrgHandler.class);
        router.get("/FactoryModelService/rents/:rentCode/orgTrees").handler(tPmOrgHandler::getTrees);

        //AAA??????
        AAAInfoHandler aaaInfoHandler = context.getBean(AAAInfoHandler.class);
        router.get("/FactoryModelService/rents/:rentCode/aaaProperties").handler(aaaInfoHandler::getAAAProperties);

        //AAA??????
        AAAUserHandler aaaUser = context.getBean(AAAUserHandler.class);
        router.get("/FactoryModelService/aaaUser").handler(aaaUser::getUser);
        router.get("/FactoryModelService/rents/:rentCode/aaaUser").handler(aaaUser::getUser);

        //?????????
        FMSObjectTreeHandler fMSObjectTreeHandler = context.getBean(FMSObjectTreeHandler.class);
        router.get("/FactoryModelService/rents/:rentCode/fmsObjTrees").handler(fMSObjectTreeHandler::getFMSObjectTree);

        //???????????????
        LoadPointTypeHandler loadPointTypeHandler = context.getBean(LoadPointTypeHandler.class);
        router.get("/FactoryModelService/rents/:rentCode/loadPointTypes").handler(loadPointTypeHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/loadPointTypes/:loadPointTypeCode").handler(loadPointTypeHandler::findByLoadPointTypeCode);

        // ????????????????????????
        CapacityUnitHandler capacityUnitHandler = context.getBean(CapacityUnitHandler.class);
        router.get("/FactoryModelService/rents/:rentCode/capacityUnits").handler(capacityUnitHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/capacityUnits/:capacityUnitCode").handler(capacityUnitHandler::findByCapacityUnitCode);

        //?????????
        LoadPointHandler loadPointHandler = (LoadPointHandler) context.getBean("loadPointHandler");
        router.get("/FactoryModelService/loadPoints").handler(loadPointHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/loadPoints").handler(loadPointHandler::findAll);
        router.get("/FactoryModelService/rents/:rentCode/bizOrgMains/:bizCode/loadPoints").handler(loadPointHandler::findAll);

        router.get("/FactoryModelService/rents/:rentCode/:areaTypeCode/:areaCode/loadPoints").handler(loadPointHandler::findAll);

        // [?????????] ???????????????
        UnitAreaHandler unitAreaHandler = (UnitAreaHandler) context.getBean("unitAreaHandler");
        router.get("/FactoryModelService/unitAreas").handler(unitAreaHandler::findUnitAreas);
        router.get("/FactoryModelService/bizs/:bizCode/unitAreas").handler(unitAreaHandler::findUnitAreas);
        router.get("/FactoryModelService/bizs/:bizCode/unitAreas/:unitAreaCode").handler(unitAreaHandler::findUnitAreas);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/unitAreas").handler(unitAreaHandler::findUnitAreas);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/unitAreas/:unitAreaCode").handler(unitAreaHandler::findUnitAreas);

        // ???????????????
        EnPipeNetHandler enPipeNetHandler = (EnPipeNetHandler) context.getBean("enPipeNetHandler");
        router.get("/FactoryModelService/enPipeNets").handler(enPipeNetHandler::findEnPipeNets);
        router.get("/FactoryModelService/bizs/:bizCode/enPipeNets").handler(enPipeNetHandler::findEnPipeNets);
        router.get("/FactoryModelService/bizs/:bizCode/enPipeNets/:netCode").handler(enPipeNetHandler::findEnPipeNets);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enPipeNets").handler(enPipeNetHandler::findEnPipeNets);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enPipeNets/:netCode").handler(enPipeNetHandler::findEnPipeNets);

        // ???????????????
        YwUnitHandler ywUnitHandler = (YwUnitHandler) context.getBean("ywUnitHandler");
        router.get("/FactoryModelService/ywUnits").handler(ywUnitHandler::findYwUnits);
        router.get("/FactoryModelService/bizs/:bizCode/ywUnits").handler(ywUnitHandler::findYwUnits);
        router.get("/FactoryModelService/bizs/:bizCode/ywUnits/:areaCode").handler(ywUnitHandler::findYwUnits);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/ywUnits").handler(ywUnitHandler::findYwUnits);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/ywUnits/:areaCode").handler(ywUnitHandler::findYwUnits);

        // ??????????????????????????????
        UnitAreaRelHandler unitAreaRelHandler = (UnitAreaRelHandler) context.getBean("unitAreaRelHandler");
        router.get("/FactoryModelService/bizs/:bizCode/unitAreaRels").handler(unitAreaRelHandler::findUnitAreaRels);
        router.get("/FactoryModelService/bizs/:bizCode/unitAreaRels/:areaCode/:unitAreaCode").handler(unitAreaRelHandler::findUnitAreaRels);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/unitAreaRels").handler(unitAreaRelHandler::findUnitAreaRels);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/unitAreaRels/:areaCode/:unitAreaCode").handler(unitAreaRelHandler::findUnitAreaRels);

        // ?????????????????????
        EnNodeTypeHandler enNodeTypeHandler = (EnNodeTypeHandler) context.getBean("enNodeTypeHandler");
        router.get("/FactoryModelService/bizs/:bizCode/enNodeTypes").handler(enNodeTypeHandler::findEnNodeTypes);
        router.get("/FactoryModelService/bizs/:bizCode/enNodeTypes/:enNodeTypeCode").handler(enNodeTypeHandler::findEnNodeTypes);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enNodeTypes").handler(enNodeTypeHandler::findEnNodeTypes);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enNodeTypes/:enNodeTypeCode").handler(enNodeTypeHandler::findEnNodeTypes);


        // [?????????]5 ??????????????????*
        EnMeasurementHandler enMeasurementHandler = (EnMeasurementHandler) context.getBean("enMeasurementHandler");
        router.get("/FactoryModelService/bizs/:bizCode/enMeasurements/:idxCode").handler(enMeasurementHandler::findEnMeasurements);
        router.get("/FactoryModelService/bizs/:bizCode/enMeasurements").handler(enMeasurementHandler::findEnMeasurements);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enMeasurements/:idxCode").handler(enMeasurementHandler::findEnMeasurements);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enMeasurements").handler(enMeasurementHandler::findEnMeasurements);


        // ???????????????
        EnNodeHandler enNodeHandler = (EnNodeHandler) context.getBean("enNodeHandler");
        router.get("/FactoryModelService/bizs/:bizCode/enNodes").handler(enNodeHandler::findEnNodes);
        router.get("/FactoryModelService/bizs/:bizCode/enNodes/:enNodeCode").handler(enNodeHandler::findEnNodes);

        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enNodes").handler(enNodeHandler::findEnNodes);
        router.get("/FactoryModelService/rents/:rentCode/bizs/:bizCode/enNodes/:enNodeCode").handler(enNodeHandler::findEnNodes);


        //		router.post("/FactoryModelService/rents").handler(routingContext -> {
//			String collectionParam = routingContext.getBodyAsString();
//			List<Rent> modelList = new ArrayList<>();
//			try {
//				modelList = RestfulTool.toResourceRep(collectionParam, Rent.class);
//				if (modelList != null && !modelList.isEmpty()) {
//					router.route("/FactoryModelService/rents/" + modelList.get(0).getRentCode() + "/*")
//							.handler(StaticHandler.create());
//					System.out.println("/FactoryModelService/rents/" + modelList.get(0).getRentCode() + "/*");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			routingContext.next();
//		});
        router.post("/FactoryModelService/rents").handler(rentHandler::addRents);
        router.post("/FactoryModelService/rents").handler(rentHandler::getRents);
        router.post("/FactoryModelService/rents/:rentCode/rents").handler(rentHandler::addRents);
        router.delete("/FactoryModelService/rents/:rentCode").handler(rentHandler::deleteByCode);

//		List<String> rentCodes = getRentCodes();
//		for (String rentCode : rentCodes) {
//			System.out.println(rentCode);
//			router.route("/FactoryModelService/rents/" + rentCode + "/*").handler(StaticHandler.create());
//		}
//		router.route("/FactoryModelService/*").handler(StaticHandler.create());

        Route webRoute_pro = router.routeWithRegex("/.*/DMBService/.*");
        webRoute_pro.blockingHandler(routingContext -> {
            String baseAddr = System.getenv("DMB_ADDR");
            String url = routingContext.request().uri();
            if (StringUtils.isNotEmpty(url)) {
                url = url.substring(url.indexOf("/DMBService"), url.length());
            }
            String str = "http://" + baseAddr + "/" + url;
            HttpMethod method = routingContext.request().method();
            try {
                if (method == HttpMethod.DELETE) {
                    String delRes = YLCloseableHttpClient.delete(str, routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(delRes);
                }

                if (method == HttpMethod.POST) {
                    String addRes = YLCloseableHttpClient.post(str, routingContext.getBodyAsString(), routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(addRes);
                }

                if (method == HttpMethod.PUT) {
                    String updateRes = YLCloseableHttpClient.put(str, routingContext.getBodyAsString(), routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(updateRes);
                }

                if (method == HttpMethod.GET) {
                    String getRes = YLCloseableHttpClient.getObject(str, routingContext).toString();
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(getRes);

                }
            } catch (Exception e) {
                e.printStackTrace();
                routingContext.response().end(e.getMessage());
            }
        }, false);

        Route webRoute_dev = router.routeWithRegex("/DMBService/.*");
        webRoute_dev.blockingHandler(routingContext -> {
            String baseAddr = System.getenv("DMB_ADDR");
            String url = routingContext.request().uri();
            if (StringUtils.isNotEmpty(url)) {
                url = url.substring(url.indexOf("/DMBService"), url.length());
            }
            String str = "http://" + baseAddr + "/" + url;
            HttpMethod method = routingContext.request().method();
            try {
                if (method == HttpMethod.DELETE) {
                    String delRes = YLCloseableHttpClient.delete(str, routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(delRes);
                }

                if (method == HttpMethod.POST) {
                    String addRes = YLCloseableHttpClient.post(str, routingContext.getBodyAsString(), routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(addRes);
                }

                if (method == HttpMethod.PUT) {
                    String updateRes = YLCloseableHttpClient.put(str, routingContext.getBodyAsString(), routingContext);
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(updateRes);
                }

                if (method == HttpMethod.GET) {
                    String getRes = YLCloseableHttpClient.getObject(str, routingContext).toString();
                    HttpServerResponse res = routingContext.response();
                    res.putHeader("content-type", "application/json; charset=utf-8").end(getRes);

                }
            } catch (Exception e) {
                e.printStackTrace();
                routingContext.response().end(e.getMessage());
            }
        }, false);

        Route webRoute = router.routeWithRegex("/FactoryModelService/rents/.*");
        webRoute.pathRegex("/FactoryModelService/rents/([^\\/]+)/(.*)").handler(routingContext -> {
            routingContext.reroute("/dmb/" + routingContext.request().getParam("param1"));//????????????????????????/some/path/B??? handler
        });

        router.route("/dmb/*").handler(StaticHandler.create());


        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(8082);

    }

    private void useMap(RoutingContext routingContext, String uri) {
        String content = DISCMAp.getContentStr(uri);
        if (StringUtils.isEmpty(content)) {
            routingContext.next();
        } else {
            System.err.println("?????????=================================================================================");
            routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(content);
        }
    }

    private void useRedis(RoutingContext routingContext, String uri) {
        Jedis jedis = RedisUtil.getJedis();
        String timStr = jedis.get("collectionCache:time:" + uri);
        Long tim = null;
        if (StringUtils.isNotEmpty(timStr)) {
            tim = Long.valueOf(timStr);
        }
        if (tim == null) {
            jedis.set("collectionCache:time:" + uri, String.valueOf(System.currentTimeMillis()));
            routingContext.next();
        } else {
            Long cha = System.currentTimeMillis() - tim;
            String cache_time = context.getBean(Environment.class).getProperty("cache_time");
            Integer cache_time_integer = null;
            if (StringUtils.isNotEmpty(cache_time)) {
                cache_time_integer = Integer.parseInt(cache_time);
            } else {
                cache_time_integer = 1000 * 60 * 5;
            }
            if (cha > 1000 * 60 * cache_time_integer) {
                jedis.set("collectionCache:time:" + uri, String.valueOf(System.currentTimeMillis()));
                routingContext.next();
            } else {
                String content = jedis.get("collectionCache:data:" + uri);
                if (StringUtils.isNotEmpty(content)) {
                    System.err.println("???Redis?????????=================================================================================");
                    routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(content);
                } else {
                    jedis.set("collectionCache:time:" + uri, String.valueOf(System.currentTimeMillis()));
                    routingContext.next();
                }
            }
        }
    }

    private void createServer(Future<Void> future, Router router) {
        vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", 8082),
                result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }

}
