import request from '@/utils/request'

// 查询道路病害管理列表
export function listDisease(query) {
  return request({
    url: '/system/disease/list',
    method: 'get',
    params: query
  })
}

// 查询道路病害管理详细
export function getDisease(wdid) {
  return request({
    url: '/system/disease/' + wdid,
    method: 'get'
  })
}

// 新增道路病害管理
export function addDisease(data) {
  return request({
    url: '/system/disease',
    method: 'post',
    data: data
  })
}

// 修改道路病害管理
export function updateDisease(data) {
  return request({
    url: '/system/disease',
    method: 'put',
    data: data
  })
}

// 删除道路病害管理
export function delDisease(wdid) {
  return request({
    url: '/system/disease/' + wdid,
    method: 'delete'
  })
}

export function diseaseCount() {
  return request({
    url: '/system/disease/diseaseCount',
    method: 'get'
  })
}
export function routeDisease() {
  return request({
    url: '/system/disease/routeDisease',
    method: 'get'
  })
}
