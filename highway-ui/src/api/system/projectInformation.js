import request from '@/utils/request'

// 查询项目信息管理列表
export function listProjectInformation(query) {
  return request({
    url: '/system/projectInformation/allInformationList',
    method: 'get',
    params: query
  })
}

// 查询项目信息管理详细
export function getProjectInformation(projectId) {
  return request({
    url: '/system/projectInformation/' + projectId,
    method: 'get'
  })
}

// 新增项目信息管理
export function addProjectInformation(data) {
  return request({
    url: '/system/projectInformation',
    method: 'post',
    data: data
  })
}

// 修改项目信息管理
export function updateProjectInformation(data) {
  return request({
    url: '/system/projectInformation',
    method: 'put',
    data: data
  })
}

// 删除项目信息管理
export function delProjectInformation(projectId) {
  return request({
    url: '/system/projectInformation/' + projectId,
    method: 'delete'
  })
}
