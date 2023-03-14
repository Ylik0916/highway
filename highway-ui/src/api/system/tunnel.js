import request from '@/utils/request'

// 查询隧道信息列表
export function listTunnel(query) {
  return request({
    url: '/system/tunnel/list',
    method: 'get',
    params: query
  })
}

// 查询隧道信息详细
export function getTunnel(tunnelId) {
  return request({
    url: '/system/tunnel/' + tunnelId,
    method: 'get'
  })
}

// 新增隧道信息
export function addTunnel(data) {
  return request({
    url: '/system/tunnel',
    method: 'post',
    data: data
  })
}

// 修改隧道信息
export function updateTunnel(data) {
  return request({
    url: '/system/tunnel',
    method: 'put',
    data: data
  })
}

// 删除隧道信息
export function delTunnel(tunnelId) {
  return request({
    url: '/system/tunnel/' + tunnelId,
    method: 'delete'
  })
}
