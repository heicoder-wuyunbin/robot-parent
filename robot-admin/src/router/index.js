import { createRouter, createWebHashHistory } from 'vue-router';
import AdminIndex from '@/pages/admin/index.vue';
import About from '@/pages/about.vue';
import NotFound from '@/pages/404.vue';
import AdminLogin from '@/pages/admin/login.vue';
import Admin from '@/layouts/admin.vue'
import AdminArticleList from '@/pages/admin/article-list.vue'
import AdminCategoryList from '@/pages/admin/category-list.vue'
import AdminTagList from '@/pages/admin/tag-list.vue'
import AdminBlogSetting from '@/pages/admin/blog-setting.vue'

const routes = [
    {
        // 指定访问路径
        path: '/admin',
        component: Admin,
        // 使用到 admin.vue 布局的，都需要放置在其子路由下面
        children: [{
            path: '/admin',
            component: AdminIndex,
            meta: {
                title: '仪表盘'
            }
        }, {
            path: '/admin/article/list',
            component: AdminArticleList,
            meta: {
                title: '文章管理'
            }
        }, {
            path: '/admin/category/list',
            component: AdminCategoryList,
            meta: {
                title: '分类管理'
            }
        }, {
            path: '/admin/tag/list',
            component: AdminTagList,
            meta: {
                title: '标签管理'
            }
        }, {
            path: '/admin/blog/setting',
            component: AdminBlogSetting,
            meta: {
                title: '博客设置'
            }
        }]

    },
    {
        path: '/about',
        component: About
    },
    // 将匹配所有内容并将其放在 `$route.params.pathMatch` 下
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: NotFound
    },
    {
        path: '/',
        component: AdminLogin,
        meta: {
            title: '登录页'
        }
    }
]

const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})

// ES6 模块导出语句，它用于将 router 对象导出，以便其他文件可以导入和使用这个对象
export default router