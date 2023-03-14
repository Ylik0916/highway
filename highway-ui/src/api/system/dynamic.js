import request from '@/utils/request'

// 查询动态养护管理列表
export function listDynamic(query) {
  return request({
    url: '/system/dynamic/list',
    method: 'get',
    params: query
  })
}

// 查询动态养护管理详细
export function getDynamic(mdid) {
  return request({
    url: '/system/dynamic/' + mdid,
    method: 'get'
  })
}

// 新增动态养护管理
export function addDynamic(data) {
  return request({
    url: '/system/dynamic',
    method: 'post',
    data: data
  })
}

// 修改动态养护管理
export function updateDynamic(data) {
  return request({
    url: '/system/dynamic',
    method: 'put',
    data: data
  })
}

// 删除动态养护管理
export function delDynamic(mdid) {
  return request({
    url: '/system/dynamic/' + mdid,
    method: 'delete'
  })
}
