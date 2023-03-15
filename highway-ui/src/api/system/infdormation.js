import request from '@/utils/request'

// 查询桥梁信息列表
export function listInfdormation(query) {
  return request({
    url: '/system/infdormation/list',
    method: 'get',
    params: query
  })
}

// 查询桥梁信息详细
export function getInfdormation(routeId) {
  return request({
    url: '/system/infdormation/' + routeId,
    method: 'get'
  })
}

// 新增桥梁信息
export function addInfdormation(data) {
  return request({
    url: '/system/infdormation',
    method: 'post',
    data: data
  })
}

// 修改桥梁信息
export function updateInfdormation(data) {
  return request({
    url: '/system/infdormation',
    method: 'put',
    data: data
  })
}

// 删除桥梁信息
export function delInfdormation(routeId) {
  return request({
    url: '/system/infdormation/' + routeId,
    method: 'delete'
  })
}
