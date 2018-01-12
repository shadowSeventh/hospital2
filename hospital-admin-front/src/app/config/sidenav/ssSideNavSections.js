export default  [

    {
        name: '医生-工作台',
        type: 'toggle',
        authorities: ['APPLICATION_A', 'SA'],
        icon: {
            mdFontSet: "ks-admin-font",
            mdFontIcon: "ks-category",
        },
        pages: [
            {
                name: '医生-工作台',
                state: 'main.hospital.home',
                authorities: ['APPLICATION_A', 'SA','APPLICATION_R'],       show:true,
            },
        ]
    },
    {
        name: '护士-工作台',
        type: 'toggle',
        authorities: ['APPLICATION_A', 'SA'],
        icon: {
            mdFontSet: "ks-admin-font",
            mdFontIcon: "ks-category",
        },
        pages: [
            {
                name: '护士-工作台',
                state: 'main.hospital.home',
                authorities: ['APPLICATION_A', 'SA','APPLICATION_R'],       show:true,
            },
        ]
    },
    {
        name: '人员管理',
        type: 'toggle',
        authorities: ['APPLICATION_A', 'SA'],
        icon: {
            mdFontSet: "ks-admin-font",
            mdFontIcon: "ks-category",
        },
        pages: [
            {
                name: '医生管理',
                state: 'main.hospital.home',
                authorities: ['APPLICATION_A', 'SA','APPLICATION_R'],       show:true,
            },
            {
                name: '护士管理',
                state: 'main.hospital.home',
                authorities: ['APPLICATION_A', 'SA','APPLICATION_R'],       show:true,
            },
            {
                name: '患者管理',
                state: 'main.hospital.home',
                authorities: ['APPLICATION_A', 'SA','APPLICATION_R'],       show:true,
            },
        ]
    },
    {
        name: '分诊',
        type: 'toggle',
        authorities: ['BRANDAPP_A', 'SA'],
        icon: {
            mdFontSet: "ks-admin-font",
            mdFontIcon: "ks-manage-s"
        },
        pages: [
            {
                name: '医生门诊',
                state: 'main.hospital.home',
                authorities: ['hospital_A', 'SA','BRANDCOM_R'],
                show:true,
            },
            {
                name: '门诊记录',
                state: 'main.hospital.home',
                authorities: ['hospital_A', 'SA','BRANDCOM_R'],       show:true,
            },
        ]
    },
    {
        name: '设置',
        type: 'toggle',
        authorities: ['BRANDAPP_A', 'SA'],
        icon: {
            mdFontSet: "ks-admin-font",
            mdFontIcon: "ks-manage-s"
        },
        pages: [
            {
                name: '活动图文',
                state: 'main.hospital.articleList',
                authorities: ['hospital_A', 'SA','BRANDCOM_R'],
                show:true,
            }
        ]
    }

];
