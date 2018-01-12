var env = __ENV__.toUpperCase();

var baseObj = {
    app: __APP__,
    version: __VERSION__,
    imgView1: "?imageView2/1/w/500/h/200",  // 对图片进行缩放(首页)
    imgView2: "?imageView2/2/w/100/h/100",  // 对图片进行缩放(用户中心)
    imgUpload: "/common/uploadImgS",
    title: "仁心病房管理系统"
};

/**
 * 开发用的测试环境。
 */
function dev() {

    const devPort = "14300";            //开发端口
    const apiPort = "14300";            //api端口

    /////////////////////////////////////////////分割线
    let domain = "//127.0.0.1:60129";
    // let rootUrl = `/local/${devPort}/admin`;
    // let rootPath = domain + rootUrl;
    let apiPath = domain + `/hospital/rs/api`;

    let yunApiPath = `https://kingsilk.net/yun/rs/api`;

    return Object.assign(baseObj, {
        // rootUrl: rootUrl,
        // share: rootPath + "#/share",
        // apiPath: apiPath,
        maxSize: 8,                         // 页数多少多少翻页数
        pageSize: 15,                       // 每页多少条数据
        imgUrl: "//img.kingsilk.net/",      // 图片地址
        yunApiPath: yunApiPath,
        apiPath: apiPath,
    });
}


function appConfigFactory() {

    return dev();;
}


export default appConfigFactory();
