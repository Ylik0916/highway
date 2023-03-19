import request from '@/utils/request'

// 查询桥梁质量列表
export function listQuality(query) {
  return request({
    url: '/system/quality/list',
    method: 'get',
    params: query
  })
}

// 查询桥梁质量详细
export function getQuality(routeQualityId) {
  return request({
    url: '/system/quality/' + routeQualityId,
    method: 'get'
  })
}

// 新增桥梁质量
export function addQuality(data) {
  return request({
    url: '/system/quality',
    method: 'post',
    data: data
  })
}

// 修改桥梁质量
export function updateQuality(data) {
  return request({
    url: '/system/quality',
    method: 'put',
    data: data
  })
}

// 删除桥梁质量
export function delQuality(routeQualityId) {
  return request({
    url: '/system/quality/' + routeQualityId,
    method: 'delete'
  })
}
export function you() {
  return request({
    url: '/system/quality/you',
    method: 'get'
  })
}

