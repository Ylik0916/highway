import request from '@/utils/request'

// 查询项目信息管理列表
export function listInformation(query) {
  return request({
    url: '/system/information/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息管理详细
export function getInformation(projectId) {
  return request({
    url: '/system/information/' + projectId,
    method: 'get'
  })
}

// 新增项目信息管理
export function addInformation(data) {
  return request({
    url: '/system/information',
    method: 'post',
    data: data
  })
}

// 修改项目信息管理
export function updateInformation(data) {
  return request({
    url: '/system/information',
    method: 'put',
    data: data
  })
}

// 删除项目信息管理
export function delInformation(projectId) {
  return request({
    url: '/system/information/' + projectId,
    method: 'delete'
  })
}
