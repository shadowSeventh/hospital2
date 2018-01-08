var env = __ENV__.toUpperCase();

var baseObj = {
    app: __APP__,
    version: __VERSION__,
    imgView1: "?imageView2/1/w/500/h/200",  // 对图片进行缩放(首页)
    imgView2: "?imageView2/2/w/100/h/100",  // 对图片进行缩放(用户中心)
    imgUpload: "/common/uploadImgS",
    title: "钱皇平台"
};

/**
 * 开发用的测试环境。
 */
function dev() {


    const devPort = "16300";              //开发端口
    const apiPort = "11400";              //api端口，支付默认api端口

    /////////////////////////////////这是分割线
    let domain = "//kingsilk.net";
    let rootUrl = `/shop/local/${devPort}/`;
    let apiPath = domain + `/shop/rs/local/${apiPort}/api`;



    return Object.assign(baseObj, {
        rootUrl: rootUrl,                   // FIXME 有用到？
        apiPath: apiPath,
        maxSize: 8,                         // 页数多少多少翻页数
        pageSize: 10,                       // 每页多少条数据
        imgView1: "?imageView2/2/w/100/h/100",// 对图片进行缩放
    });
}


function appConfigFactory() {
    return dev();
}

export default appConfigFactory();
